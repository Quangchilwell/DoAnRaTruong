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

import com.example.TrungTamTA.Model.DayOfWeekDTO;
import com.example.TrungTamTA.Service.DayOfWeekService;

import lombok.val;

@Controller
@RequestMapping("/admin")
@Transactional
public class DayOfWeekController {
	@Autowired
	DayOfWeekService service;

	@GetMapping("/day-of-week-list")
	public String dayOfWeekList(Model model) {
		model.addAttribute("dayOfWeekDTOs", service.getAll());
		return "dayOfWeek/dayOfWeekList";
	}

	// ADD
	@GetMapping("/add-day-of-week")
	public String addDayOfWeek(Model model) {
		model.addAttribute("dayOfWeekDTO", new DayOfWeekDTO());
		return "dayOfWeek/addDayOfWeek";
	}

	@PostMapping("/add-day-of-week")
	public String addDayOfWeek(Model model, @Valid DayOfWeekDTO dayOfWeekDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("dayOfWeekDTO", dayOfWeekDTO);
			return "dayOfWeek/addDayOfWeek";
		}
		service.add(dayOfWeekDTO);
		return "redirect:/admin/day-of-week-list";
	}

	// UPDATE
	@GetMapping("/update-day-of-week/{id}")
	public String updateDayOfWeek(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("dayOfWeekDTO", service.getById(id));
		return "dayOfWeek/updateDayOfWeek";
	}

	@PostMapping("/update-day-of-week")
	public String updateDayOfWeek(Model model, @Valid DayOfWeekDTO dayOfWeekDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("dayOfWeekDTO", dayOfWeekDTO);
			return "dayOfWeek/updateDayOfWeek";
		}
		service.update(dayOfWeekDTO);
		return "redirect:/admin/day-of-week-list";
	}

	@PostMapping("/delete-day-of-week")
	public String deleteDayOfWeek(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/admin/day-of-week-list";
	}
}
