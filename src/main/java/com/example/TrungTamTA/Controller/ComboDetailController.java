package com.example.TrungTamTA.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.ComboDTO;
import com.example.TrungTamTA.Model.ComboDetailDTO;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Service.ComboDetailService;
import com.example.TrungTamTA.Service.ComboService;
import com.example.TrungTamTA.Service.CourseService;

@Controller
@RequestMapping("/admin")
public class ComboDetailController {
	
	@Autowired ComboDetailService comboDetailService;
	
	@Autowired ComboService comboService;
	
	@Autowired CourseService courseService;
	
	@PostMapping("/delete-combo-detail/{id}")
	public String deleteDetailCombo(Model model, @PathVariable(name = "id") int id)
	{
		ComboDetailDTO comboDetailDTO = comboDetailService.getByID(id);
		if(comboDetailDTO != null) {
			ComboDTO comboDTO = comboService.getByID(comboDetailDTO.getComboDTO().getId());
			CourseDTO courseDTO = courseService.getByID(comboDetailDTO.getCourseDTO().getId());
			float totalPrice = comboDTO.getTotalPrice() - courseDTO.getTuition();
			float newPrice = comboDTO.getNewTotalPrice() - comboDetailDTO.getNewPrice();
			
			comboDTO.setTotalPrice(totalPrice);
			comboDTO.setNewTotalPrice(newPrice);
			comboDTO.setQuantiityCourses(comboDTO.getQuantiityCourses() - 1);
			comboService.update(comboDTO);
			comboDetailService.delete(id);
			return "redirect:/admin/info-combo/" + comboDTO.getId();
		}
		return "redirect:/not-have-date";
	}
}
