package com.example.TrungTamTA.Controller.Online;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.TrungTamTA.Model.Online.OnlineCourseDTO;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.TeacherService;
import com.example.TrungTamTA.Service.Online.DiscountCodeService;
import com.example.TrungTamTA.Service.Online.OnlineCourseService;

@Controller
@Transactional
@RequestMapping("/admin")
public class OnlineCourseController {

	@Autowired OnlineCourseService service;
	@Autowired DiscountCodeService discountCodeService;
	@Autowired TeacherService teacherService;
	@Autowired CourseService courseService;

	@GetMapping("online-course-list")
	public String onlineCourseList(Model model) {
		model.addAttribute("courseDTOs", service.getAll());
		return "online/onlineCourse/onlineCourseList";
	}

	// INFO
	@GetMapping("info-online-course/{id}")
	public String infoOnlineCourse(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("onCourse", service.getByID(id));
		return "online/onlineCourse/infoOnlineCourse";
	}

	// ADD
	@GetMapping("add-online-course")
	public String addOnlineCourse(Model model, @RequestParam(name = "idCourse") int idCourse) {
		model.addAttribute("onCourseDTO", new OnlineCourseDTO());
		model.addAttribute("course", courseService.getByID(idCourse));
		model.addAttribute("teachers", teacherService.getTeacherForOnlineCourse(idCourse));
		model.addAttribute("discountDTOs", discountCodeService.getAll());
		
		return "online/onlineCourse/addOnlineCourse";
	}

	@PostMapping("add-online-course")
	public String addOnlineCourse(Model model, @ModelAttribute(name = "onCourseDTO") OnlineCourseDTO onCourseDTO) {
		onCourseDTO.setCourseDTO(courseService.getByID(onCourseDTO.getIdCourse()));
		onCourseDTO.setTeacherDTO(teacherService.getByID(onCourseDTO.getIdTeacher()));
		
		if(onCourseDTO.getIdDiscount() != 0) {
			onCourseDTO.setDiscountCodeDTO(discountCodeService.getByID(onCourseDTO.getIdDiscount()));
		}
		
		MultipartFile file = onCourseDTO.getFile();
		if (!file.getOriginalFilename().equals(" ")) {
			try {
				String nameImage = LocalTime.now().toString().replaceAll("[-+.^:,]", "") + "-"
						+ file.getOriginalFilename();
				File newFile = new File(
						"E:/SpringBoot/workspace/TrungTamTA/src/main/resources/static/image/imgCourses/" + nameImage);
				FileOutputStream fileOutputStream = new FileOutputStream(newFile);
				fileOutputStream.write(file.getBytes());
				fileOutputStream.close();

				onCourseDTO.setImage(nameImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		service.add(onCourseDTO);
		return "redirect:/admin/online-course-list";
	}

	
	// UPDATE
//	@GetMapping("update-online-course/{id}")
//	public String updateOnlineCourse(Model model, @PathVariable(name = "id") int id) {
//		OnlineCourseDTO dto = service.getByID(id);
//
//		if (dto.getDiscountCodeDTO() != null) {
//			DiscountCodeDTO discountCodeDTO = discountCodeService.getByID(dto.getDiscountCodeDTO().getId());
//			model.addAttribute("discount", discountCodeDTO);
//		}
//
//		model.addAttribute("discountDTOs", discountCodeService.getAll());
//		model.addAttribute("courseDTO", dto);
//		return "online/onlineCourse/updateOnlineCourse";
//	}
//
//	@PostMapping("update-online-course")
//	public String updateOnlineCourse(Model model, @ModelAttribute(name = "courseDTO") OnlineCourseDTO courseDTO) {
//		MultipartFile file = courseDTO.getFile();
//
//		if (!file.getOriginalFilename().equals("")) {
//			try {
//				String nameImage = LocalTime.now().toString().replaceAll("[-+.^:,]", "") + "-"
//						+ file.getOriginalFilename();
//				File newFile = new File(
//						"E:/SpringBoot/workspace/TrungTamTA/src/main/resources/static/image/imgCourses/" + nameImage);
//				FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//				fileOutputStream.write(file.getBytes());
//				fileOutputStream.close();
//
//				courseDTO.setImage(nameImage);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		else {
//			OnlineCourse onlineCourse = dao.getByID(courseDTO.getId());
//			courseDTO.setImage(onlineCourse.getImage());
//		}
//		courseDTO.setCourseTypeDTO(courseTypeService.getByID(courseDTO.getIdCourseType()));
//		courseDTO.setDiscountCodeDTO(discountCodeService.getByID(courseDTO.getIdDiscount()));
//		service.update(courseDTO);
//		return "redirect:/admin/online-course-list";
//	}
//
//	// DELETE
//	@GetMapping("delete-online-course/{id}")
//	public String deleteOnlineCourse(Model model, @PathVariable(name = "id") int id) {
//		service.delete(id);
//		return "redirect:/admin/online-course-list";
//	}
}
