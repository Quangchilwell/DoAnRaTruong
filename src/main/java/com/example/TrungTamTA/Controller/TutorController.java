package com.example.TrungTamTA.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.TutorDTO;
import com.example.TrungTamTA.Service.TutorService;

@Controller
@RequestMapping("/admin")
public class TutorController {
	
	@Autowired TutorService tutorService;
	
	@GetMapping("/tutor-list")
	public String tutorList(Model model)
	{
		model.addAttribute("tutorDTOs", tutorService.getAll());
		return "tutor/tutorList";
	}
	
	//INFO
	@GetMapping("/info-tutor/{id}")
	public String infoTutor(Model model, @PathVariable(name = "id") int id)
	{
		TutorDTO tutorDTO = tutorService.getByID(id);
		SimpleDateFormat spd = new SimpleDateFormat("dd-MM-yyyy");
		model.addAttribute("birthday", spd.format(Date.valueOf(tutorDTO.getBirthDay())));
		model.addAttribute("joiningDate", spd.format(Date.valueOf(tutorDTO.getJoiningDate())));
		model.addAttribute("tutorDTO", tutorDTO);
		return "tutor/infoTutor";
	}
	
	// ADD
	@GetMapping("/add-tutor")
	public String addTutor(Model model)
	{
		model.addAttribute("tutorDTO", new TutorDTO());
		return "tutor/addTutor";
	}
	
	@PostMapping("/add-tutor")
	public String addTutor(Model model, @Valid TutorDTO tutorDTO, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("tutorDTO", tutorDTO);			
			return "tutor/addTutor";
		}
		tutorService.add(tutorDTO);
		return "redirect:/admin/tutor-list";
	}
	
	@GetMapping("/update-tutor/{id}")
	public String updateTutor(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("tutorDTO", tutorService.getByID(id));
		return "tutor/updateTutor";
	}
	
	@PostMapping("/update-tutor")
	public String updateTutor(Model model, @Valid TutorDTO tutorDTO, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("tutorDTO", tutorDTO);			
			return "tutor/updateTutor";
		}
		tutorService.update(tutorDTO);
		return "redirect:/admin/tutor-list";
	}
	
	//DELETE
	@PostMapping("/delete-tutor/{id}")
	public String deleteTutor(Model model, @PathVariable(name = "id") int id)
	{
		tutorService.delete(id);
		return "redirect:/admin/tutor-list";
	}
}
