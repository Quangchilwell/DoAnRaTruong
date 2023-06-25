package com.example.TrungTamTA.Controller.Online;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Service.Online.AccountStudentService;
import com.example.TrungTamTA.Service.Online.DetailAddMoneyService;

@Controller
@Transactional
@RequestMapping("/admin")
public class DetailAddMoneyController {
	
	@Autowired DetailAddMoneyService service;
	@Autowired AccountStudentService acService;
	
	@GetMapping("/add-money-list")
	public String addMoneyList(Model model)
	{
		model.addAttribute("addMoneys", service.getAll());
		return "online/addMoney/list";
	}
	
	@GetMapping("/info-detail-add-money/{id}")
	public String infoDetailAddMoney(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("money", service.getByID(id));
		return "online/addMoney/info";
	}
}
