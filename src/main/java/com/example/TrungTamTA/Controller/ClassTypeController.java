package com.example.TrungTamTA.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.ClassTypeDTO;
import com.example.TrungTamTA.Service.ClassTypeService;

@Controller
@RequestMapping("/admin")
public class ClassTypeController {
	@Autowired
	ClassTypeService classTypeService;

	@GetMapping("/class-type-list")
	public String classTypeList(Model model) {
		model.addAttribute("typeDTOs", classTypeService.getAll());
		return "classType/classTypeList";
	}

	// ADD
	@GetMapping("add-class-type")
	public String addClassType(Model model) {
		model.addAttribute("type", new ClassTypeDTO());
		return "classType/addClassType";
	}

	@PostMapping("add-class-type")
	public String addClassType(Model model, @Valid ClassTypeDTO type,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("type", type);
			return "classtype/addClassType";
		}
		classTypeService.add(type);
		return "redirect:/admin/class-type-list";
	}

	// UPDATE
	@GetMapping("update-class-type/{id}")
	public String updateClassType(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("type", classTypeService.getByID(id));
		return "classType/updateClassType";
	}

	@PostMapping("update-class-type")
	public String updateClassType(Model model, 
			@Valid ClassTypeDTO classTypeDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("type", classTypeDTO);
			return "classType/updateClassType";
		}
		classTypeService.update(classTypeDTO);
		return "redirect:/admin/class-type-list";
	}
	
	// DELETE
	@PostMapping("delete-class-type/{id}")
	public String deleteClassType(@PathVariable(name = "id") int id) {
		classTypeService.delete(id);
		return "redirect:/admin/class-type-list";
	}
	
	// INFO
	@GetMapping("info-class-type/{id}")
	public String infoClassType(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("type", classTypeService.getByID(id));
		return "classType/infoClassType";
	}

}
