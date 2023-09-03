package com.example.TrungTamTA.Controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TrungTamTA.Constant.ReservationStatus;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Model.RegisterCourseDTO;
import com.example.TrungTamTA.Model.ReservationDTO;
import com.example.TrungTamTA.Model.StudentDTO;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.RegisterCourseService;
import com.example.TrungTamTA.Service.ReservationService;
import com.example.TrungTamTA.Service.StudentService;

@Controller
@RequestMapping("/admin/reservation")
public class ReservationController {
	
	@Autowired 
	private ReservationService reservationService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ClassOpeningService classOpeningService;
	
	@Autowired
	private RegisterCourseService registerCourseService;
	
	@GetMapping("/add-reservation")
	public String addReservation(Model model,
			@RequestParam("idClass") int idClass,
			@RequestParam("idStudent") int idStudent, 
			@RequestParam("idRegister") int idRegister) {
		
		StudentDTO studentDTO = studentService.getByID(idStudent);
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(idClass);
		
		model.addAttribute("dto", new ReservationDTO());
		model.addAttribute("class", classOpeningDTO);
		model.addAttribute("student", studentDTO);
		model.addAttribute("idRegister", idRegister);
		
		return "reservation/add-reservation";
	}
	
	@PostMapping("/add-reservation")
	public String addReservation(Model model,
			@ModelAttribute ReservationDTO dto, HttpServletRequest request) {
		int idStudent = Integer.valueOf(request.getParameter("idStudent"));
		int idClass = Integer.valueOf(request.getParameter("idClass"));
		int idRegister = Integer.valueOf(request.getParameter("idRegister"));
		System.out.println(idRegister);
		model.addAttribute("student", studentService.getByID(idStudent));
		model.addAttribute("idClass", idClass);
		
		dto.setIdClass(idClass);
		dto.setIdStudent(idStudent);
		dto.setStatus(ReservationStatus.START);
		
		// Xóa khỏi danh sách lớp học
		RegisterCourseDTO reCourseDTO = registerCourseService.getByID(idRegister);
		reCourseDTO.setStatus(0);
		reCourseDTO.setClassOpeningDTO(null);
		
		registerCourseService.update(reCourseDTO);
		reservationService.add(dto);
		return "reservation/success-reservation";
	}
	
	@GetMapping(value = "/get-by-id-student/{idStudent}")
	public String getAllyStudentId(Model model, @PathVariable(name = "idStudent") int idStudent) {
		List<ReservationDTO> reservationDTOs = reservationService.getAllByIdStudent(idStudent);
		model.addAttribute("reserDtos", reservationDTOs);
		return "reservation/get-all-by-id-student";
	}
	
	// Ket thuc bao luu
	@GetMapping(value = "/finish-reservation/{id}")
	public String finnishReservation(@PathVariable(name = "id") int id) {
		ReservationDTO dto = reservationService.getById(id);
		dto.setStatus(ReservationStatus.END);
		dto.setEndDate(String.valueOf(LocalDate.now()));
		reservationService.update(dto);
		return "redirect:/admin/reservation/get-by-id-student/" + dto.getIdStudent();
	}
	
}
