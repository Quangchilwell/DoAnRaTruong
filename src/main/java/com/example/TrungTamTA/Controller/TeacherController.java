package com.example.TrungTamTA.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.TrungTamTA.Dao.TeacherDao;
import com.example.TrungTamTA.Entity.Teacher;
import com.example.TrungTamTA.Model.TeacherDTO;
import com.example.TrungTamTA.Service.TeacherService;

@Controller
@RequestMapping("/admin")
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	
	@Autowired TeacherDao teacherDao;
	
	@GetMapping("/teacher-list")
	public String teacherList(Model model)
	{
		model.addAttribute("teacherDTOs", teacherService.getAll());
		return "teacher/teacherList";
	}
	
//	GET INFO
	@GetMapping("/info-teacher/{id}")
	public String infoTeacher(Model model, @PathVariable(name = "id") int id)
	{
		SimpleDateFormat spd = new SimpleDateFormat("dd-MM-yyyy");
		model.addAttribute("birthday", spd.format(Date.valueOf(teacherService.getByID(id).getBirthday())));
		model.addAttribute("dateOfJoining", spd.format(Date.valueOf(teacherService.getByID(id).getDateOfJoining())));
		model.addAttribute("teacher", teacherService.getByID(id));
		return "teacher/infoTeacher";
	}
	
//	ADD
	@GetMapping("/add-teacher")
	public String addTeacher(Model model)
	{
		model.addAttribute("teacherDTO", new TeacherDTO());
		return "teacher/addTeacher";
	}
	
	@PostMapping("/add-teacher")
	public String addTeacher(Model model, @Valid TeacherDTO teacherDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("teacherDTO", teacherDTO);
			return "teacher/addTeacher";
		}
		
		MultipartFile file = teacherDTO.getFile();
		try {
			File newFile = new File("E:/SpringBoot/workspace/TrungTamTA/src/main/resources/static/image/avatarOfTeacher/"
					+ file.getOriginalFilename());
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();

			teacherDTO.setAvatar(file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		teacherService.add(teacherDTO);
		return "redirect:/admin/teacher-list";
	}
	
//	UPDATE
	@GetMapping("/update-teacher/{id}")
	public String updateTeacher(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("teacherDTO", teacherService.getByID(id));
		return "teacher/updateTeacher";
	}
	
	@PostMapping("/update-teacher")
	public String updateTeacher(Model model, @Valid TeacherDTO teacherDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("teacherDTO", teacherDTO);
			return "teacher/updateTeacher";
		}
		
		Teacher teacher = teacherDao.getByID(teacherDTO.getId());
		
		MultipartFile file = teacherDTO.getFile();
		try {
			File newFile = new File("E:/SpringBoot/workspace/TrungTamTA/src/main/resources/static/image/avatarOfTeacher/"
					+ file.getOriginalFilename());
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();

			teacherDTO.setAvatar(file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(file.getOriginalFilename().equals("")) {
			teacherDTO.setAvatar(teacher.getAvatar());
		}else {
			teacherDTO.setAvatar(file.getOriginalFilename());
		}
		
		teacherService.update(teacherDTO);
		return "redirect:/admin/teacher-list";
	}
	
//	DELETE
	@PostMapping("/delete-teacher/{id}")
	public String deleteTeacher(Model model, @PathVariable(name = "id") int id)
	{
		teacherService.delete(id);
		return "redirect:/admin/teacher-list";
	}
}
