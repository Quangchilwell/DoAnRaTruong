package com.example.TrungTamTA.Controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.StudentDetailService;

@Controller
@Transactional
@RequestMapping("/admin")
public class StudentDetailController {
	
//	@Autowired StudentDetailService service;
//	
//	@Autowired ClassOpeningService classOpeningService;
//	
//	// DELETE
//	@PostMapping("/delete-student-detail")
//	public String deleteStudentDetail(Model model, @PathVariable(name = "id") int id) {
////		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(idClass);
////		classOpeningDTO.setQuantityStudents(classOpeningDTO.getQuantityStudents() - 1);
////		classOpeningService.update(classOpeningDTO);
//		service.delete(id);
////		return "redirect:/admin/students-in-class?idClass=" + classOpeningDTO.getId();
//		return "";
//	}
}
