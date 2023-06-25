package com.example.TrungTamTA.Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TrungTamTA.Dao.ComboDao;
import com.example.TrungTamTA.Entity.Combo;
import com.example.TrungTamTA.Model.ComboDTO;
import com.example.TrungTamTA.Model.ComboDetailDTO;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Service.ComboDetailService;
import com.example.TrungTamTA.Service.ComboService;
import com.example.TrungTamTA.Service.CourseService;

@Controller
@RequestMapping("/admin")
public class ComboController {
	@Autowired ComboService comboService;
	@Autowired ComboDao comboDao;
	
	@Autowired CourseService courseService;
	
	@Autowired ComboDetailService comboDetailService;
	
	@GetMapping("/combo-list")
	public String comboList(Model model)
	{
		model.addAttribute("combos", comboService.getAll());
		return "combo/comboList";
	}
	
	// THÊM KHÓA HỌC VÀO CHO COMBO
		@GetMapping("add-course-for-combo/{id}")
		public String addCourseForCombo(Model model, @PathVariable(name = "id") int id)
		{
			List<CourseDTO> courseDTOs = courseService.getCoursesNotInCombo(id);
			ComboDTO comboDTO = comboService.getByID(id);
			
			model.addAttribute("combo", comboDTO);
			model.addAttribute("courseDTOs", courseDTOs);
			return "combo/addCourseForCombo";
		}
		
		@GetMapping("/accept-add-course-for-combo")
		public String acceptAddCourseForCombo(Model model, HttpServletRequest request,
				@RequestParam(name = "idCourse") int idCourse,
				@RequestParam(name = "idCombo") int idCombo)
		{
			ComboDTO comboDTO = comboService.getByID(idCombo);
			CourseDTO courseDTO = courseService.getByID(idCourse);
			float newPrice = courseDTO.getTuition() - (courseDTO.getTuition() * comboDTO.getDiscount() / 100);
			
			ComboDetailDTO comboDetailDTO = new ComboDetailDTO();
			comboDetailDTO.setComboDTO(comboDTO);
			comboDetailDTO.setCourseDTO(courseDTO);
			comboDetailDTO.setNewPrice(newPrice);
			comboDetailDTO.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
			comboDetailService.add(comboDetailDTO);
			
			// Cap nhat lai tong tien combo
			float totalPrice = comboDTO.getTotalPrice() + courseDTO.getTuition();
			float newtotalPrice = comboDTO.getNewTotalPrice() + newPrice;
			
			comboDTO.setTotalPrice(totalPrice);
			comboDTO.setNewTotalPrice(newtotalPrice);
			comboDTO.setQuantiityCourses(comboDTO.getQuantiityCourses() + 1);
			comboService.update(comboDTO);
			return "redirect:/admin/add-course-for-combo/" + idCombo;
		}
		
		
		// Thong tin chi tiet cua combo
		@GetMapping("/info-combo/{id}")
		public String coursesInCombo(Model model, @PathVariable(name = "id") int id)
		{
			ComboDTO comboDTO = comboService.getByID(id);
			List<ComboDetailDTO> comboDetailDTOs = comboDetailService.getByidCombo(id);
			
			model.addAttribute("comboDTO", comboDTO);
			model.addAttribute("comboDetailDTOs" ,comboDetailDTOs);
			
			return "combo/infoCombo";
		}
		
		// ADD
		@GetMapping("/add-combo")
		public String addCombo(Model model)
		{
			model.addAttribute("comboDTO", new ComboDTO());
			return "combo/addCombo";
		}
		
		
		@PostMapping("/add-combo")
		public String addCombo(Model model, @Valid ComboDTO comboDTO, BindingResult bindingResult)
		{
			if(bindingResult.hasErrors()) {
				model.addAttribute("comboDTO", comboDTO);
				return "combo/addCombo";			
			}
			
			comboService.add(comboDTO);
			return "redirect:/admin/combo-list";
		}
		
		// UPDATE
		@GetMapping("/update-combo/{id}")
		public String updateCombo(Model model, @PathVariable(name = "id") int id)
		{
			model.addAttribute("comboDTO", comboService.getByID(id));
			return "combo/updateCombo";
		}
		
		@PostMapping("/update-combo")
		public String updateCombo(Model model, @Valid ComboDTO comboDTO, BindingResult bindingResult,
				HttpServletRequest request)
		{
			if(bindingResult.hasErrors()) {
				model.addAttribute("comboDTO", comboDTO);
				return "combo/updateCombo";			
			}
			Combo combo = comboDao.getByID(comboDTO.getId());

			if(combo.getDiscount() != comboDTO.getDiscount()) {
				float newTotalPrice = 0;
				List<ComboDetailDTO> comboDetailDTOs = comboDetailService.getByidCombo(comboDTO.getId());
				
				for(ComboDetailDTO cbDTO: comboDetailDTOs) {
					CourseDTO courseDTO = courseService.getByID(cbDTO.getCourseDTO().getId());
					float newPrice = courseDTO.getTuition() - 
							(courseDTO.getTuition() * comboDTO.getDiscount() / 100);
					newTotalPrice += newPrice;
					
					cbDTO.setNewPrice(newPrice);
					comboDetailService.update(cbDTO);
				}
				comboDTO.setNewTotalPrice(newTotalPrice);
			}
			comboService.update(comboDTO);
			return "redirect:/admin/combo-list";
		}
		
		// DELETE
		@PostMapping("/delete-combo/{id}")
		public String deleteCombo(@PathVariable(name = "id") int id)
		{	
			comboService.delete(id);
			return "redirect:/admin/combo-list";
		}

}
