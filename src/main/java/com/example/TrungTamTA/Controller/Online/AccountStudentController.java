package com.example.TrungTamTA.Controller.Online;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Service.Online.AccountStudentService;

@Controller
@Transactional
@RequestMapping("/admin")
public class AccountStudentController {
	@Autowired AccountStudentService service;
	
	@GetMapping("/account-student-list")
	public String getAll(Model model)
	{
		model.addAttribute("dtos", service.getAll());
		return "accountStudent/accountStudentList";
	}
}
