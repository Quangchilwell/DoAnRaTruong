package com.example.TrungTamTA.Controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TrungTamTA.Dao.ClassDayDao;
import com.example.TrungTamTA.Entity.ClassDay;
import com.example.TrungTamTA.Model.ClassDayDTO;
import com.example.TrungTamTA.Model.ClassDetailDTO;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Service.ClassDayService;
import com.example.TrungTamTA.Service.ClassDetailService;
import com.example.TrungTamTA.Service.ClassOpeningService;

@Controller
@Transactional
@RequestMapping("/admin")
public class ClassDayController {

	@Autowired
	ClassDayService classDayService;
	
	@Autowired
	ClassDayDao dao;

	@Autowired
	ClassOpeningService classOpeningService;

	@Autowired
	ClassDetailService classDetailService;

	@GetMapping("/classes-today")
	public String classesToday(Model model) {
		LocalDate localDate = LocalDate.now();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<ClassOpeningDTO> dtos = new ArrayList<ClassOpeningDTO>();
		List<ClassDetailDTO> classDetailDTOs = classDetailService.getAll();

		for (ClassDetailDTO classDetailDTO : classDetailDTOs) {
			if (classDetailDTO.getDayOfWeekDTO().getDay() == localDate.getDayOfWeek().getValue() + 1
					&& classDetailDTO.getClassOpeningDTO().getStatus() == 0) {
				ClassDay classDay = dao.checkClassCompleted(classDetailDTO.getClassOpeningDTO().getId(), 
						Date.valueOf(localDate));
				if(classDay == null) {
					dtos.add(classDetailDTO.getClassOpeningDTO());					
				}
				
			}
		}
		model.addAttribute("today", sdf.format(Date.valueOf(localDate)));
		model.addAttribute("date", localDate);
		model.addAttribute("classes", dtos);

		return "class/classesToday";
	}
	
	// HOAN THANH BUOI HOC
	@GetMapping("/lesson-completed")
	public String lessonCompleted(Model model, 
			@RequestParam(name = "idClass") int idClass,
			@RequestParam(name = "date") String date) {
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(idClass);
		ClassDayDTO classDayDTO = new ClassDayDTO();
	
		classDayDTO.setClassOpeningDTO(classOpeningDTO);
		classDayDTO.setDay(date);
		classDayDTO.setStatus(1);
		classDayDTO.setLesson(classOpeningDTO.getNumberOfLessonsLearned() + 1);
		classDayDTO.setCompletedAt(Timestamp.valueOf(LocalDateTime.now()));
		classDayService.add(classDayDTO);
		
		classOpeningDTO.setNumberOfLessonsLearned(classOpeningDTO.getNumberOfLessonsLearned() + 1);		
		if(classOpeningDTO.getCourseDTO().getStudyTime() * 8 == classOpeningDTO.getNumberOfLessonsLearned()) {
			classOpeningDTO.setStatus(1);
		}
		classOpeningService.update(classOpeningDTO);
		return "redirect:/admin/classes-today";
	}
	
	//HOÃN LẠI BUỔI HỌC
	@GetMapping("/postpone-class")
	public String postponeClass(Model model, 
			@RequestParam(name = "idClass") int idClass,
			@RequestParam(name = "date") String date) {
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(idClass);
		ClassDayDTO classDayDTO = new ClassDayDTO();
		
		classDayDTO.setClassOpeningDTO(classOpeningDTO);
		classDayDTO.setDay(String.valueOf(date));
		classDayDTO.setStatus(-1);
		classDayDTO.setLesson(classOpeningDTO.getNumberOfLessonsLearned() + 1);
		classDayDTO.setCompletedAt(Timestamp.valueOf(LocalDateTime.now()));
		classDayService.add(classDayDTO);
		return "redirect:/admin/classes-today";
	}
	
	//XEM LICH HOC CAC NGAY KHAC
	@GetMapping("/check-classes-in-date")
	public String previousClassSchedule(Model model, @RequestParam(name = "date", required = false) String date)
	{
		if(date != null) {
			LocalDate localDate = LocalDate.parse(date);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			List<ClassOpeningDTO> dtos = new ArrayList<ClassOpeningDTO>();
			List<ClassDetailDTO> classDetailDTOs = classDetailService.getAll();

			for (ClassDetailDTO classDetailDTO : classDetailDTOs) {
				if (classDetailDTO.getDayOfWeekDTO().getDay() == localDate.getDayOfWeek().getValue() + 1
						&& classDetailDTO.getClassOpeningDTO().getStatus() == 0) {
					ClassDay classDay = dao.checkClassCompleted(classDetailDTO.getClassOpeningDTO().getId(), 
							Date.valueOf(localDate));
					if(classDay == null) {
						dtos.add(classDetailDTO.getClassOpeningDTO());					
					}
					
				}
			}
			model.addAttribute("today", sdf.format(Date.valueOf(localDate)));
			model.addAttribute("date", localDate);
			model.addAttribute("classes", dtos);
		}
		return "class/checkClassesInDate";
	}
	
	@GetMapping("/result-check-classes")
	public String resultCheckClasses(Model model, HttpServletRequest request)
	{
		String dateAreSearching = String.valueOf(request.getParameter("dateAreSearching"));

		return "redirect:/admin/check-classes-in-date?date=" + dateAreSearching;
	}
	
	
}
