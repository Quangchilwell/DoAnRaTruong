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

import com.example.TrungTamTA.Model.CourseTypeDTO;
import com.example.TrungTamTA.Service.CourseTypeService;

@Controller
@RequestMapping("/admin")
public class CourseTypeController {
	
	@Autowired
	CourseTypeService courseTypeService;
	
	@GetMapping("/course-type-list")
	public String courseTypeList(Model model)
	{
		model.addAttribute("courseTypes", courseTypeService.getAll());
		return "courseType/courseTypeList";
	}
	
	// INFO
	@GetMapping("/info-course-type/{id}")
	public String infoCourseType(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("course", courseTypeService.getByID(id));
		return "courseType/infoCourseType";
	}
	
	// ADD
	@GetMapping("/add-course-type")
	public String addCourseType(Model model)
	{
		model.addAttribute("courseTypeDTO", new CourseTypeDTO());
		return "courseType/addCourseType";
	}
	
	@PostMapping("/add-course-type")
	public String addCourseType(Model model, @Valid CourseTypeDTO courseTypeDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("courseTypeDTO", courseTypeDTO);
			return "courseType/addCourseType";			
		}
		courseTypeService.add(courseTypeDTO);
		return "redirect:/admin/course-type-list";
	}
	
	// UPDATE
	@GetMapping("/update-course-type/{id}")
	public String updateCourseType(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("courseTypeDTO", courseTypeService.getByID(id));
		return "courseType/updateCourseType";
	}
	
	@PostMapping("/update-course-type")
	public String updateCourseType(Model model, @Valid CourseTypeDTO courseTypeDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("courseTypeDTO", courseTypeDTO);
			return "courseType/updateCourseType";			
		}
		courseTypeService.update(courseTypeDTO);
		return "redirect:/admin/course-type-list";
	}
	
	// DELETE
	@PostMapping("/delete-course-type/{id}")
	public String deleteCourseType(@PathVariable(name = "id") int id)
	{
		courseTypeService.delete(id);
		return "redirect:/admin/course-type-list";
	}
	
}
