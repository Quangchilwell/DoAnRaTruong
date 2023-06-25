package com.example.TrungTamTA.Controller.Online;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.Online.InvoiceOnlineCourseDTO;
import com.example.TrungTamTA.Service.Online.InvoiceOnlineCourseService;

@Controller
@Transactional
@RequestMapping("/admin")
public class InvoiceOnlineCourseController {
	
	@Autowired InvoiceOnlineCourseService service;
	
	@GetMapping("/invoices-online-course-list")
	public String invoicesOnlineCourseList(Model model)
	{
		model.addAttribute("invoiceDTOs", service.getAll());
		return "online/invoiceOnlineCourse/list";
	}
	
	// ADD
	@PostMapping("/add-invoice-online-course")
	public String addInvoiceOnlineCourse(Model model)
	{
		model.addAttribute("invoiceDTO", new InvoiceOnlineCourseDTO());
		return "online/invoiceOnlineCourse/list";
	}
	
	// INFO
	@GetMapping("/info-invoice-online-course/{id}")
	public String infoOnvoicesOnlineCourse(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("invoice", service.getByID(id));
		return "online/invoiceOnlineCourse/info";
	}
}
