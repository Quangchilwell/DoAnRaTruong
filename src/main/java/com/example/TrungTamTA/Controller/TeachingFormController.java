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

import com.example.TrungTamTA.Model.TeachingFormDTO;
import com.example.TrungTamTA.Service.TeachingFormService;

@Controller
@RequestMapping("/admin")
public class TeachingFormController {

	@Autowired
	TeachingFormService teachingFormService;

	@GetMapping("/teaching-form-list")
	public String teachingFormList(Model model) {
		model.addAttribute("forms", teachingFormService.getAll());
		return "teachingForm/teachingFormList";
	}
	
	// INFO
	@GetMapping("/info-teaching-form/{id}")
	public String teachingFormList(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("form", teachingFormService.getByID(id));
		return "teachingForm/infoTeachingForm";
	}

	// ADD
	@GetMapping("/add-teaching-form")
	public String addTeachingForm(Model model) {
		model.addAttribute("teachingFormDTO", new TeachingFormDTO());
		return "teachingForm/addTeachingForm";
	}

	@PostMapping("/add-teaching-form")
	public String addTeachingForm(Model model, @Valid TeachingFormDTO teachingFormDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("teachingFormDTO", teachingFormDTO);
			return "teachingForm/addTeachingForm";
		}
		teachingFormService.add(teachingFormDTO);
		return "redirect:/admin/teaching-form-list";
	}

	// UPDATE
	@GetMapping("/update-teaching-form/{id}")
	public String updateTeachingForm(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("teachingFormDTO", teachingFormService.getByID(id));
		return "teachingForm/updateTeachingForm";
	}

	@PostMapping("/update-teaching-form")
	public String updateTeachingForm(Model model, @Valid TeachingFormDTO teachingFormDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("teachingFormDTO", teachingFormDTO);
			return "teachingForm/updateTeachingForm";
		}
		teachingFormService.update(teachingFormDTO);
		return "redirect:/admin/teaching-form-list";
	}
	
	// DELETE
	@PostMapping("/delete-teaching-form/{id}")
	public String deleteTeachingForm(@PathVariable(name = "id") int id) {
		teachingFormService.delete(id);
		return "redirect:/admin/teaching-form-list";
	}

}
