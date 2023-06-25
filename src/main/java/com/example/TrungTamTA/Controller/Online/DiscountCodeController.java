package com.example.TrungTamTA.Controller.Online;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.Online.DiscountCodeDTO;
import com.example.TrungTamTA.Service.Online.DiscountCodeService;

@Controller
@Transactional
@RequestMapping("/admin")
public class DiscountCodeController {

	@Autowired
	DiscountCodeService service;

	@GetMapping("/discount-code-list")
	public String discountCodeList(Model model) {
		model.addAttribute("codeDTOs", service.getAll());
		return "online/discount/discountCodeList";
	}

	// Info
	@GetMapping("/info-discount-code/{id}")
	public String infoDiscountCode(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("code", service.getByID(id));
		return "online/discount/infoDiscountCode";
	}
	
	// ADD
	@GetMapping("/add-discount-code")
	public String addDiscountCode(Model model) {
		model.addAttribute("codeDTO", new DiscountCodeDTO());
		return "online/discount/addDiscountCode";
	}

	@PostMapping("/add-discount-code")
	public String addDiscountCode(@ModelAttribute(name = "codeDTO") DiscountCodeDTO codeDTO) {
		service.add(codeDTO);
		return "redirect:/admin/discount-code-list";
	}

	// Update
	@GetMapping("/update-discount-code/{id}")
	public String updateDiscountCode(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("codeDTO", service.getByID(id));
		return "online/discount/updateDiscountCode";
	}

	@PostMapping("/update-discount-code")
	public String updateDiscountCode(@ModelAttribute(name = "codeDTO") DiscountCodeDTO codeDTO) {
		service.update(codeDTO);
		return "redirect:/admin/discount-code-list";
	}
	
	@PostMapping("/delete-discount-code/{id}")
	public String deleteDiscountCode(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/admin/discount-code-list";
	}
}
