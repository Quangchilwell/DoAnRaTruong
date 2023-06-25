package com.example.TrungTamTA.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.ClassRoomDTO;
import com.example.TrungTamTA.Model.ClassTypeDTO;
import com.example.TrungTamTA.Model.RegisterCourseDTO;
import com.example.TrungTamTA.Service.ClassRoomService;
import com.example.TrungTamTA.Service.ClassTypeService;

@Controller
@RequestMapping("/admin")
public class ClassRoomController {
	
	@Autowired ClassRoomService classRoomService;
	
	@Autowired ClassTypeService classTypeService;
	
	@GetMapping("/class-room-list")
	public String classRoomList(Model model)
	{
		model.addAttribute("roomDTOs", classRoomService.getAll());
		return "classRoom/classRoomList";
	}
	
	// INFO
	@GetMapping("/info-class-room/{id}")
	public String infoClassRoom(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("room", classRoomService.getByID(id));
		return "classRoom/infoClassRoom";
	}
	
	// ADD
	@GetMapping("/add-class-room")
	public String addClassRoom(Model model, HttpSession session)
	{
		List<ClassTypeDTO> classTypeDTOs = classTypeService.getAll();
		session.setAttribute("classTypes", classTypeDTOs);
		model.addAttribute("classRoomDTO", new ClassRoomDTO());
		return "classRoom/addClassRoom";
	}
	
	@PostMapping("/add-class-room")
	public String addClassRoom(Model model, @Valid ClassRoomDTO classRoomDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("classRoomDTO", classRoomDTO);
			return "classRoom/addClassRoom";
		}
		classRoomDTO.setClassTypeDTO(classTypeService.getByID(classRoomDTO.getIdType()));
		classRoomService.add(classRoomDTO);
		return "redirect:/admin/class-room-list";
	}
	
	// UPDATE
	@GetMapping("/update-class-room/{id}")
	public String updateClassRoom(Model model, HttpSession session,
			@PathVariable(name = "id") int id)
	{
		ClassRoomDTO classRoomDTO = classRoomService.getByID(id);
		List<ClassTypeDTO> classTypeDTOs = classTypeService.getAll();
		ClassTypeDTO classTypeDTO = classTypeService.getByID(classRoomDTO.getClassTypeDTO().getId());

		session.setAttribute("classTypes", classTypeDTOs);
		session.setAttribute("typeOfRoom", classTypeDTO);
		model.addAttribute("classRoomDTO", classRoomDTO);
		return "classRoom/updateClassRoom";
	}
	
	@PostMapping("/update-class-room")
	public String updateClassRoom(Model model, @Valid ClassRoomDTO classRoomDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("classRoomDTO", classRoomDTO);
			return "classRoom/updateClassRoom";
		}
		classRoomDTO.setClassTypeDTO(classTypeService.getByID(classRoomDTO.getIdType()));
		classRoomService.update(classRoomDTO);
		return "redirect:/admin/class-room-list";
	}
	
	
}
