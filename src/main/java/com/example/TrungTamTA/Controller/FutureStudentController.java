package com.example.TrungTamTA.Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.FutureStudentDTO;
import com.example.TrungTamTA.Service.FutureStudentService;

@Controller
@Transactional
@RequestMapping("/tvv")
public class FutureStudentController {
	
	@Autowired FutureStudentService service;
	// Danh sach cac khach hang moi dang ki
	@GetMapping("future-student-list")
	public String futureStudentList(Model model)
	{
		List<FutureStudentDTO> dtos = service.getBystatus(0);
		model.addAttribute("students", dtos);
		return "futureStudent/futureStudentList";
	}
	
	// Danh sach DK da len lich hen
	@GetMapping("appointment-schedule-list")
	public String appointmentScheduleList(Model model)
	{
		List<FutureStudentDTO> dtos = service.getBystatus(1);
		model.addAttribute("students", dtos);
		return "futureStudent/appointmentScheduleList";
	}
	
	// INFO
	@GetMapping("info-future-student/{id}")
	public String info(Model model, @PathVariable(name = "id") int id)
	{
		FutureStudentDTO dto = service.getByID(id);
		model.addAttribute("student", dto);
		return "futureStudent/info";
	}
	
	// ADD
	@GetMapping("add-future-student")
	public String addFutureStudent(Model model)
	{
		model.addAttribute("futureStudentDTO", new FutureStudentDTO());
		return "futureStudent/addFutureStudent";
	}
	
	@PostMapping("add-future-student")
	public String addFutureStudent(Model model, @ModelAttribute(name = "futureStudentDTO") FutureStudentDTO studentDTO)
	{
		studentDTO.setStatus(0);
		service.add(studentDTO);
		return "redirect:/tvv/future-student-list";
	}
	
	// UPDATE
	@GetMapping("update-future-student/{id}")
	public String updateFutureStudent(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("futureStudentDTO", service.getByID(id));
		return "futureStudent/updateFutureStudent";
	}
	
	@PostMapping("update-future-student")
	public String updateFutureStudent(Model model, 
			@ModelAttribute(name = "futureStudentDTO") FutureStudentDTO studentDTO)
	{
	
		service.update(studentDTO);
		return "redirect:/tvv/info-future-student/" + studentDTO.getId();
	}
	
	// DELETE
	@PostMapping("delete-future-student/{id}")
	public String deleteFutureStudent(@PathVariable(name = "id") int id)
	{
		service.delete(id);
		return "redirect:/tvv/future-student-list";
	}
	
	// LÊN LỊCH HẸN
	@PostMapping("open-appointment-schedule")
	public String openSchedule(HttpServletRequest request)
	{
		int id = Integer.valueOf(request.getParameter("id"));
		Timestamp calendar = Timestamp.valueOf(LocalDateTime.parse(request.getParameter("calendar")));
		FutureStudentDTO dto = service.getByID(id);
		dto.setSchedule(calendar);
		dto.setStatus(1);
		service.update(dto);
		return "redirect:/tvv/appointment-schedule-list";
	}
}
