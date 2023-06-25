package com.example.TrungTamTA.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.TrungTamTA.Dao.CourseDao;
import com.example.TrungTamTA.Entity.Course;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Model.RegisterCourseDTO;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.CourseTypeService;
import com.example.TrungTamTA.Service.RegisterCourseService;
import com.example.TrungTamTA.Service.TeachingFormService;
import com.example.TrungTamTA.Service.Online.OnlineCourseService;

@Controller
@RequestMapping("/admin")
public class CourseController {

	@Autowired
	CourseService courseService;

	@Autowired
	TeachingFormService formService;

	@Autowired
	CourseTypeService typeService;
	
	@Autowired CourseDao courseDao;
	
	@Autowired RegisterCourseService registerCourseService;
	@Autowired OnlineCourseService onlineCourseService;

	@GetMapping("/course-list")
	public String courseList(Model model) {
		model.addAttribute("courses", courseService.getAll());
		model.addAttribute("forms", formService.getAll());
		return "course/courseList";
	}
	
	// INFO
	@GetMapping("/info-course/{id}")
	public String infoCourse(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("course", courseService.getByID(id));
		return "course/infoCourse";
	}

	// LOC THEO HINH THUC DAY
	@GetMapping("/courses-in-teaching-form")
	public String coursesInTeachingForm(Model model, @RequestParam(name = "idForm") int idForm)
	{
		List<CourseDTO> courseDTOs = courseService.getByIdForm(idForm);
		model.addAttribute("courseDTOs", courseDTOs);
		model.addAttribute("forms", formService.getAll());
		model.addAttribute("form", formService.getByID(idForm));
		return "course/coursesInTeachingForm";
	}
	
	
	// ADD
	@GetMapping("/add-course")
	public String addCourse(Model model, HttpSession session) {
		session.setAttribute("teachingForms", formService.getAll());
		session.setAttribute("courseTypes", typeService.getAll());
		model.addAttribute("courseDTO", new CourseDTO());
		return "course/addCourse";
	}

	@PostMapping("/add-course")
	public String addCourse(Model model, @Valid CourseDTO courseDTO, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("courseDTO", courseDTO);
//			return "course/addCourse";
//		}
		
		// Day anh len
		MultipartFile file = courseDTO.getFile();
		try {
			String nameImage = LocalTime.now().toString().replaceAll("[-+.^:,]","") + "-" + file.getOriginalFilename();
			File newFile = new File("E:/SpringBoot/workspace/TrungTamTA/src/main/resources/static/image/imgCourses/"
					+ nameImage);
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();

			courseDTO.setImage(nameImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		courseDTO.setCourseTypeDTO(typeService.getByID(courseDTO.getIdCourseType()));
		courseDTO.setTeachingFormDTO(formService.getByID(courseDTO.getIdTeachingForm()));
		courseService.add(courseDTO);
		return "redirect:/admin/course-list";
	}

	// UPDATE
	@GetMapping("/update-course/{id}")
	public String updateCourse(Model model, HttpSession session, @PathVariable(name = "id") int id) {
		CourseDTO courseDTO = courseService.getByID(id);
		
		session.setAttribute("teachingForms", formService.getAll());
		session.setAttribute("courseTypes", typeService.getAll());
		model.addAttribute("courseDTO", courseDTO);
		model.addAttribute("form", formService.getByID(courseDTO.getTeachingFormDTO().getId()));
		model.addAttribute("type",typeService.getByID(courseDTO.getCourseTypeDTO().getId()));
		return "course/updateCourse";
	}

	@PostMapping("/update-course")
	public String updateCourse(Model model, @ModelAttribute CourseDTO courseDTO) {
		MultipartFile file = courseDTO.getFile();
		if(!file.getOriginalFilename().equals("")) {
			try {
				String nameImage = LocalTime.now().toString().replaceAll("[-+.^:,]","") + "-" + file.getOriginalFilename();
				File newFile = new File("E:/SpringBoot/workspace/TrungTamTA/src/main/resources/static/image/imgCourses/"
						+ nameImage);
				FileOutputStream fileOutputStream = new FileOutputStream(newFile);
				fileOutputStream.write(file.getBytes());
				fileOutputStream.close();
				
				courseDTO.setImage(nameImage);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		else{
			Course course = courseDao.getByID(courseDTO.getId());
			courseDTO.setImage(course.getImage());
		}
		
		courseDTO.setCourseTypeDTO(typeService.getByID(courseDTO.getIdCourseType()));
		courseDTO.setTeachingFormDTO(formService.getByID(courseDTO.getIdTeachingForm()));
		courseService.update(courseDTO);
		return "redirect:/admin/course-list";
	}
	
	// DELETE
	@PostMapping("/delete-course/{id}")
	public String deleteCourse(Model model, @PathVariable(name = "id") int id)
	{
		// Kiem tra co ai dang ki khoa offline 
		int size = registerCourseService.getByIdCourse(id).size();
		if(size > 0) {
			model.addAttribute("size", size);
			model.addAttribute("course", courseService.getByID(id));
			return "course/cannotDeleteCourse";
		}
		
		// Kiem tra co ai dang ki khoa online
		int numberOnlCourses = onlineCourseService.getByidCourse(id).size();
		if(numberOnlCourses > 0) {
			model.addAttribute("numberOnlCourses", numberOnlCourses);
			model.addAttribute("course", courseService.getByID(id));
			return "course/cannotDeleteCourse";
		}
		courseService.delete(id);
		return "redirect:/admin/course-list";
	}
}
