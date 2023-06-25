package com.example.TrungTamTA.Controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.ShiftDTO;
import com.example.TrungTamTA.Service.ShiftService;

@Controller
@RequestMapping("/admin")
@Transactional
public class ShiftController {

	@Autowired
	ShiftService service;

	@GetMapping("/shift-list")
	public String shiftList(Model model) {
		model.addAttribute("shiftDTOs", service.getAll());
		return "shift/shiftList";
	}

	// ADD
	@GetMapping("/add-shift")
	public String addShift(Model model) {
		model.addAttribute("shiftDTO", new ShiftDTO());
		return "shift/addShift";
	}

	@PostMapping("/add-shift")
	public String addShift(Model model, @Valid ShiftDTO shiftDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("shiftDTO", new ShiftDTO());
			return "shift/addShift";
		}
		service.update(shiftDTO);
		return "redirect:/admin/shift-list";
	}

	// UPDATE
	@GetMapping("/update-shift/{id}")
	public String updateShift(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("shiftDTO", service.getByID(id));
		return "shift/updateShift";
	}

	@PostMapping("/update-shift")
	public String updateShift(Model model, @Valid ShiftDTO shiftDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("shiftDTO", shiftDTO);
			return "shift/updateShift/" + shiftDTO.getId();
		}
		service.update(shiftDTO);
		return "redirect:/admin/shift-list";
	}
	
	// DELETE
	@PostMapping("/delete-shift/{id}")
	public String deleteShift(Model model, @PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/admin/shift-list";
	}
}
