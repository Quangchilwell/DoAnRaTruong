package com.example.TrungTamTA.Controller.Online;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Service.Online.AccountStudentService;
import com.example.TrungTamTA.Service.Online.OnlineCourseActivatedService;

@Controller
@Transactional
@RequestMapping("/admin")
public class OnlineCourseActivatedController {
	
	@Autowired OnlineCourseActivatedService service;
	@Autowired AccountStudentService accountStudentService;
	
	@GetMapping("/online-courses-actived-list")
	public String onlineCoursesActivedList(Model model)
	{
		model.addAttribute("courses", service.getAll());
		return "online/activated/list";
	}
}
