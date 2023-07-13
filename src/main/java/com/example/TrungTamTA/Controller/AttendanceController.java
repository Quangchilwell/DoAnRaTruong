package com.example.TrungTamTA.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TrungTamTA.Model.AttendanceDTO;
import com.example.TrungTamTA.Model.LessonDTO;
import com.example.TrungTamTA.Service.AttendanceService;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.LessonService;

@Controller
@RequestMapping("/qlhv/attendance/")
public class AttendanceController {

	@Autowired AttendanceService attendanceService;
	@Autowired LessonService lessonService;
	
	@GetMapping("get-att-by-idLesson/{idLesson}")
	public String getByIdClass(Model model, @PathVariable("idLesson") int idLesson) {
		List<AttendanceDTO> attendanceDTOs = attendanceService.getByIdLesson(idLesson);
		
		model.addAttribute("attendanceDTOs", attendanceDTOs);
		model.addAttribute("lesson", lessonService.getByID(idLesson));
		return "attendance/get-att-by-id-lesson";
	}
	
	@GetMapping("update-att")
	public String updateAttendance(Model model, 
			@RequestParam("id") int id,
			@RequestParam("status") int status) {
		
		AttendanceDTO dto = attendanceService.getById(id);
		if(dto != null) {
			dto.setStatus(status);
			attendanceService.update(dto);
		}
		
		return "redirect:/qlhv/attendance/get-att-by-idLesson/" + dto.getLessonDTO().getId();
	}
}
