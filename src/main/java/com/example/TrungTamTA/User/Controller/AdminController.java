package com.example.TrungTamTA.User.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Model.ClassDetailDTO;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Model.CourseTypeDTO;
import com.example.TrungTamTA.Service.ClassDetailService;
import com.example.TrungTamTA.Service.CourseTypeService;
import com.example.TrungTamTA.Service.DayOfWeekService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired DayOfWeekService dayOfWeekService;
	
	@Autowired ClassDetailService classDetailService;
	
	@GetMapping("/time-table")
	public String timeTable(Model model)
	{
		try {
			List<ClassDetailDTO> classDetailDTOs = classDetailService.getAll();
			List<ClassDetailDTO> classes = new ArrayList<ClassDetailDTO>();
			
			if(classDetailDTOs.size() > 0) {
				for(ClassDetailDTO classDetailDTO: classDetailDTOs) {
					if(classDetailDTO.getClassOpeningDTO().getStatus() == 0) {
						classes.add(classDetailDTO);
					}
				}				
				
				model.addAttribute("classes", classes);
				model.addAttribute("dayOfWeeks", dayOfWeekService.getAll());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "webAdmin/timeTable";
	}
}
