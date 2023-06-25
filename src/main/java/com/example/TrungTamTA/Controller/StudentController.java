package com.example.TrungTamTA.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.StudentDTO;
import com.example.TrungTamTA.Service.StudentService;

@Controller
@RequestMapping("/admin")
public class StudentController {
	
	@Autowired 
	StudentService studentService;
	
	@GetMapping("/student-list")
	public String studentList(Model model)
	{
		model.addAttribute("students", studentService.getAll());
		return "student/studentList";
	}
	
	//INFO
	@GetMapping("/info-student/{id}")
	public String infoStudent(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("student", studentService.getByID(id));
		return "student/infoStudent";
	}
	
	// ADD
	@GetMapping("/add-student")
	public String addStudent(Model model)
	{
		model.addAttribute("studentDTO", new StudentDTO());
		return "student/addStudent";
	}
	
	@PostMapping("/add-student")
	public String addStudent(Model model, @Valid StudentDTO studentDTO, 
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("studentDTO", studentDTO);
			return "student/addStudent";	
		}
		studentService.add(studentDTO);
		return "redirect:/admin/student-list";
	}
	
	//UPDATE
	@GetMapping("/update-student/{id}")
	public String updateStudent(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("studentDTO", studentService.getByID(id));
		return "student/updateStudent";
	}
	
	@PostMapping("/update-student")
	public String updateStudent(Model model, @Valid StudentDTO studentDTO, 
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("studentDTO", studentDTO);
			return "student/updateStudent/" + studentDTO.getId();	
		}
		studentService.update(studentDTO);
		return "redirect:/admin/student-list";
	}
	
	@PostMapping("/delete-student/{id}")
	public String deleteStudent(Model model, @PathVariable(name = "id") int id)
	{
		studentService.delete(id);
		return "redirect:/admin/student-list";
	}
}
