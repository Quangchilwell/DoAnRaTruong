package com.example.TrungTamTA.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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

import com.example.TrungTamTA.Dao.ClassOpeningDao;
import com.example.TrungTamTA.Dao.RegisterCourseDao;
import com.example.TrungTamTA.Entity.ClassOpening;
import com.example.TrungTamTA.Entity.RegisterCourse;
import com.example.TrungTamTA.Model.ClassDetailDTO;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Model.ClassRoomDTO;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Model.LessonDTO;
import com.example.TrungTamTA.Model.RegisterCourseDTO;
import com.example.TrungTamTA.Model.StudentDetailInCompletedClassDTO;
import com.example.TrungTamTA.Model.TeacherDTO;
import com.example.TrungTamTA.Model.TutorDTO;
import com.example.TrungTamTA.Service.ClassDetailService;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.ClassRoomService;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.DayOfWeekService;
import com.example.TrungTamTA.Service.LessonService;
import com.example.TrungTamTA.Service.RegisterCourseService;
import com.example.TrungTamTA.Service.ShiftService;
import com.example.TrungTamTA.Service.StudentDetailInCompletedClassService;
import com.example.TrungTamTA.Service.StudentDetailService;
import com.example.TrungTamTA.Service.TeacherService;
import com.example.TrungTamTA.Service.TutorService;

@Controller
@Transactional
@RequestMapping("/admin")
public class ClassOpeningController {
	@Autowired LessonService lessonService;
	
	@Autowired ClassOpeningService service;
	@Autowired ClassOpeningDao dao;
	
	@Autowired ClassDetailService classDetailService;
	@Autowired RegisterCourseService registerCourseService;
	
	@Autowired TeacherService teacherService;
	
	@Autowired ClassRoomService classRoomService;
	
	@Autowired TutorService tutorService;
	
	@Autowired CourseService courseService;
	
	@Autowired ShiftService shiftService;
	@Autowired DayOfWeekService dayOfWeekService;
	
	@Autowired ClassOpeningDao classOpeningDao;
	
	@Autowired RegisterCourseDao registerCourseDao;
	@Autowired StudentDetailService stuDetailService;
	
	@Autowired StudentDetailInCompletedClassService stuCompletedClassService;
	
	public void getResponsiveList(Model model, int idClass)
	{
		List<TeacherDTO> teacherDTOs = teacherService.getAll();
		List<ClassRoomDTO> classRoomDTOs = classRoomService.getAll();
		List<TutorDTO> tutorDTOs = tutorService.getAll();
		ClassOpeningDTO classOpeningDTO = service.getByID(idClass);
		
		int idShift = classOpeningDTO.getShiftDTO().getId();
		List<ClassOpeningDTO> classOpeningDTOs = service.getDuplicateClassList(idShift, idClass);
		
		for(ClassOpeningDTO dto: classOpeningDTOs) {
			if(dto.getTeacherDTO() != null) {
				teacherDTOs.remove(dto.getTeacherDTO());
			}
			if(dto.getTutorDTO() != null) {
				tutorDTOs.remove(dto.getTutorDTO());
			}
			if(dto.getClassRoomDTO() != null) {
				classRoomDTOs.remove(dto.getClassRoomDTO());
			}
		}
		model.addAttribute("teacherDTOs", teacherDTOs);
		model.addAttribute("tutorDTOs", tutorDTOs);
		model.addAttribute("classRoomDTOs", classRoomDTOs);
	}
	
	
	@GetMapping("/class-list")
	public String classList(Model model)
	{
		model.addAttribute("classes", service.getAll());
		return "class/classList";
	}
	
	// Danh sach lop hoc phan theo trang thai
	@GetMapping("/get-classes-by-status")
	public String classesByStatus(Model model, @RequestParam(name = "status") int status)
	{
		List<ClassOpeningDTO> classOpeningDTOs = service.getByStatus(status);
		model.addAttribute("classes", classOpeningDTOs);
		return "class/classesByStatus";
	}
	
	// INFO
	@GetMapping("/info-class/{id}")
	public String infoClass(Model model, @PathVariable(name = "id") int id)
	{
		ClassOpeningDTO dto = service.getByID(id);
		CourseDTO courseDTO = courseService.getByID(dto.getCourseDTO().getId());
		float days = courseDTO.getStudyTime() * 8;
		model.addAttribute("class", dto);
		model.addAttribute("details", classDetailService.getByidClassOpening(id));
		model.addAttribute("days", days);
		return "class/infoClass";
	}
	
	// Mo LH du kien
	@GetMapping("/open-schedule-class")
	public String openScheduleClass(Model model, HttpServletRequest request, 
			@RequestParam(name = "idCourse", required = false) int idCourse)
	{
		List<CourseDTO> courseDTOs = courseService.getOfflineCourses();
		model.addAttribute("size", 0);
		if(request.getParameter("idCourse") != null) {
			idCourse = Integer.valueOf(request.getParameter("idCourse"));
			List<RegisterCourseDTO> reCourseDTOs = registerCourseService.getRegistersCanOpenClass(idCourse);
			model.addAttribute("registers", reCourseDTOs);
			model.addAttribute("courseDTO", courseService.getByID(idCourse));
			model.addAttribute("size", reCourseDTOs.size());
		}
		
		model.addAttribute("courses", courseDTOs);
		return "class/openScheduleClass";
	}
	
	//Chap nhan mo lh du kien
	@PostMapping("/create-schedule-class/idCourse/{idCourse}")
	public String createScheduleClass(Model model, @PathVariable(name = "idCourse") int idCourse,
			@RequestParam(name = "idRegisters[]", required = false) List<Integer> idRegisters)
	{
		if(idRegisters.size() <= 0) {
			return "redirect:/admin/ll";
		}
		ClassOpening classOpening = new ClassOpening();
		int quantityStudent = 0;
		
		classOpening.setStatus(-1);
		classOpening.setIdCourse(idCourse);
		classOpening = classOpeningDao.add(classOpening);

		for (Integer idRegister : idRegisters) {
			RegisterCourse registerCourse = registerCourseDao.getByID(idRegister);
			registerCourse.setStatus(1);
			registerCourse.setIdClassOpening(classOpening.getId());
			registerCourseDao.update(registerCourse);
			quantityStudent += 1;
		}
		classOpening.setQuantityStudents(quantityStudent);
		classOpeningDao.update(classOpening);
		
		return "redirect:/admin/info-class/" + classOpening.getId();
	}
	
	// DANH SACH HOC SINH TRONG LOP
	@GetMapping("/students-in-class")
	public String studentsInClass(Model model, @RequestParam(name = "idClass") int idClass)
	{
		List<RegisterCourseDTO> registerCourseDTOs = registerCourseService.getStudentsInClass(idClass);
		model.addAttribute("students", registerCourseDTOs);
		model.addAttribute("class", service.getByID(idClass));
		return "class/studentsInClass";
	}
	
	// CHI TIET CAC BUOI HOC CUA LOP
	@GetMapping("/lessons-in-class/{idClass}")
	public String lessonsInClass(Model model, @PathVariable(name = "idClass") int idClass)
	{
		List<LessonDTO> dtos = lessonService.getLessonsWereCompleted(idClass);
		model.addAttribute("lessons", dtos);
		model.addAttribute("class", service.getByID(idClass));
		return "class/lessonsInClass";
	}
	
	// CAC BUOI HOC BI HOAN CUA LH
	@GetMapping("/postpone-class-list")
	public String postponeClassList(Model model, @RequestParam(name = "idClass") int idClass)
	{
		List<LessonDTO> dtos = lessonService.getLessonsWerePostPone(idClass);
		model.addAttribute("lessons", dtos);
		model.addAttribute("class", service.getByID(idClass));
		return "class/postponeClassList";
	}
	
	// SAP XEP CA CHO LOP HOC
	@GetMapping("/add-shift-for-class")
	public String addShiftForClass(Model model, @RequestParam(name = "idClass") int idClass)
	{
		model.addAttribute("shiftDTOs", shiftService.getAll());
		model.addAttribute("days", dayOfWeekService.getAll());
		model.addAttribute("classDTO", service.getByID(idClass));
		return "class/addShiftForClass";
	}
	
	// Chap nhan them ca
	@PostMapping("/add-shift-for-class")
	public String addShiftForClass(Model model, 
			@RequestParam(name = "dayOfWeeks", required = false) List<Integer> dayOfWeeks,
			@ModelAttribute(name = "classDTO") ClassOpeningDTO classOpeningDTO)
	{
		ClassOpeningDTO classOpeningDTO2 = service.getByID(classOpeningDTO.getId());
		for(Integer day: dayOfWeeks) {
			ClassDetailDTO classDetailDTO = new ClassDetailDTO();
			classDetailDTO.setClassOpeningDTO(service.getByID(classOpeningDTO.getId()));
			classDetailDTO.setDayOfWeekDTO(dayOfWeekService.getById(day));
			classDetailService.add(classDetailDTO);
		}
		classOpeningDTO2.setName(classOpeningDTO.getName());
		classOpeningDTO2.setShiftDTO(shiftService.getByID(classOpeningDTO.getIdShift()));
		classOpeningDTO2.setNote(classOpeningDTO.getNote());
		service.update(classOpeningDTO2);
		return "redirect:/admin/info-class/" + classOpeningDTO.getId();
	}
	
	// CAP NHAT LAI CA HOC
	@GetMapping("/update-shift-for-class")
	public String updateShiftForClass(Model model, @RequestParam(name = "idClass") int idClass)
	{
		model.addAttribute("shiftDTOs", shiftService.getAll());
		model.addAttribute("days", dayOfWeekService.getAll());
		model.addAttribute("classDTO", service.getByID(idClass));
		return "class/updateShiftForClass";
	}
	
	@PostMapping("/update-shift-for-class")
	public String updateShiftForClass(Model model, 
			@RequestParam(name = "dayOfWeeks", required = false) List<Integer> dayOfWeeks,
			@ModelAttribute(name = "classDTO") ClassOpeningDTO classOpeningDTO)
	{
		ClassOpeningDTO classOpeningDTO2 = service.getByID(classOpeningDTO.getId());
		List<ClassDetailDTO> classDetailDTOs = classDetailService.getByidClassOpening(classOpeningDTO.getId());
		
		for(Integer day: dayOfWeeks) {
			ClassDetailDTO classDetailDTO = new ClassDetailDTO();
			classDetailDTO.setClassOpeningDTO(service.getByID(classOpeningDTO.getId()));
			classDetailDTO.setDayOfWeekDTO(dayOfWeekService.getById(day));
			classDetailService.add(classDetailDTO);
		}
		
		for(ClassDetailDTO classDetailDTO: classDetailDTOs) {
			classDetailService.delete(classDetailDTO.getId());
		}

		classOpeningDTO2.setShiftDTO(shiftService.getByID(classOpeningDTO.getIdShift()));
		service.update(classOpeningDTO2);
		return "redirect:/admin/info-class/" + classOpeningDTO.getId();
	}
	
	
	// SAP XEP TAI NGUYEN CHO LOP HOC
	@GetMapping("/add-resources-for-class")
	public String addResourcesForClass(Model model, @RequestParam(name = "idClass") int idClass)
	{
		getResponsiveList(model, idClass);
		model.addAttribute("classDTO", service.getByID(idClass));
		return "class/addResourcesForClass";
	}
	
	@PostMapping("/add-resources-for-class")
	public String addResourcesForClass(Model model, @ModelAttribute(name = "classDTO") ClassOpeningDTO classOpeningDTO)
	{
		ClassOpening classOpening = dao.getByID(classOpeningDTO.getId());
		classOpening.setIdTeacher(classOpeningDTO.getIdTeacher());
		classOpening.setIdTutor(classOpeningDTO.getIdTutor());
		classOpening.setIdClassRoom(classOpeningDTO.getIdClassRoom());
		dao.update(classOpening);
		return "redirect:/admin/info-class/" + classOpeningDTO.getId();
	}
	
	// DUA LOP VAO HOAT DONG
	@GetMapping("/accept-class/{id}")
	public String acceptClass(Model model, @PathVariable(name = "id") int id)
	{
		ClassOpeningDTO dto = service.getByID(id);
		dto.setStatus(0);
		service.update(dto);
		return "redirect:/admin/class-list";
	}
	
	// ADD
	@GetMapping("/add-class")
	public String addClass(Model model)
	{
		model.addAttribute("classes", new ClassOpeningDTO());
		return "class/addClass";
	}
	
	@PostMapping("/add-class")
	public String addClass(Model model, @Valid ClassOpeningDTO classOpeningDTO, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("classes", classOpeningDTO);
			return "class/addClass";			
		}
		service.add(classOpeningDTO);
		return "redirect:/admin/class-list";
	}
	
	// UPDATE
	@GetMapping("/update-class/{id}")
	public String updateClass(Model model, @PathVariable(name = "id") int id)
	{
		ClassOpeningDTO dto = service.getByID(id);
		getResponsiveList(model, id);
		model.addAttribute("class", dto);
		return "class/updateClass";
	}
	
	@PostMapping("/update-class")
	public String updateClass(@ModelAttribute(name = "class") ClassOpeningDTO classOpeningDTO)
	{
		ClassOpeningDTO dto = service.getByID(classOpeningDTO.getId());
		dto.setTeacherDTO(teacherService.getByID(classOpeningDTO.getIdTeacher()));
		dto.setClassRoomDTO(classRoomService.getByID(classOpeningDTO.getIdClassRoom()));
		dto.setTutorDTO(tutorService.getByID(classOpeningDTO.getIdTutor()));
		dto.setName(classOpeningDTO.getName());
		dto.setNote(classOpeningDTO.getNote());
		service.update(dto);
		return "redirect:/admin/class-list";
	}
	
	// BO HOC VIEN TRONG LOP
	@PostMapping("/remove-student-to-class/{idStudent}")
	public String removeStudentToClass(Model model, @PathVariable(name = "idStudent") int id)
	{
		RegisterCourseDTO registerCourseDTO = registerCourseService.getByID(id);
		int idClass = registerCourseService.getByID(id).getClassOpeningDTO().getId();
		ClassOpeningDTO classOpeningDTO = service.getByID(idClass);
		
		registerCourseDTO.setStatus(0);
		registerCourseDTO.setClassOpeningDTO(null);
		registerCourseService.update(registerCourseDTO);
		classOpeningDTO.setQuantityStudents(classOpeningDTO.getQuantityStudents() - 1);
		service.update(classOpeningDTO);
		return "redirect:/admin/students-in-class?idClass=" + idClass;
	}
	
	// HUY LOP HOC
	@PostMapping("delete-class/{id}")
	public String deleteClass(Model model, @PathVariable(name = "id") int id)
	{
		List<RegisterCourseDTO> registerCourseDTOs = registerCourseService.getByIdClassOpening(id);
		List<ClassDetailDTO> classDetailDTOs = classDetailService.getByidClassOpening(id);
		
		for(RegisterCourseDTO dto: registerCourseDTOs) {
			dto.setStatus(0);
			dto.setClassOpeningDTO(null);
			registerCourseService.update(dto);
		}
		
		for(ClassDetailDTO dto: classDetailDTOs) {
			classDetailService.delete(dto.getId());
		}
		
		service.delete(id);
		return "redirect:/admin/class-list";
	}
	
	// Danh sach lop hoc da hoan thanh (status = 1)
	@GetMapping("complete-class-list")
	public String completeClassList(Model model) {
		List<ClassOpeningDTO> dtos = service.getByStatus(1);
		model.addAttribute("classDTOs", dtos);
		return "class/completion/complete-class-list";
	}
	
	// Hoan thanh lop hoc (Chờ xác nhận hoàn thành)
	@GetMapping("/confirm-complete-class/{idClassOpening}")
	public String classCompletion(Model model, @PathVariable(name = "idClassOpening") int idClassOpening) {
		ClassOpeningDTO dto = service.getByID(idClassOpening);
		model.addAttribute("class", dto);
		return "class/confirm-complete-class";
	}
	
	@GetMapping("completed-class") // Nen la Post
	public String completedClass(@RequestParam(name = "idClass") int idClass) {
		ClassOpeningDTO dto = service.getByID(idClass);
		dto.setStatus(1);
		service.updateStatusOfClass(dto);
		return "redirect:/admin/complete-class-list";
	}
	
	// Lay danh sach hoc vien trong lop de cap nhat trang thai
	@GetMapping("/update-status-students")
	public String updateStatusStudents(Model model, @RequestParam("idClass") int idClass) {
		List<RegisterCourseDTO> reDtos = registerCourseService.getByIdClassOpening(idClass);
		model.addAttribute("idClass", idClass);
		model.addAttribute("reDtos", reDtos);
		return "class/completion/update-status-students";
	}
	
	// Hoc vien qua lop hoc
	@GetMapping("/completed-class/student-passed-class/{idRegister}")
	public String studentCompleteClass(Model model, 
			@PathVariable(name = "idRegister") int idRegister) {
		
		// 1. Lay thong tin dang ki
		RegisterCourseDTO reDto = registerCourseService.getByID(idRegister);
		
		// 2. Mo khoa hoc (dang ki tiep theo)
		// 2.1. Lay thong tin cac dang ki cua hoc vien
		List<RegisterCourseDTO> registersOfStudent = 
				registerCourseService.getByIdStudent(reDto.getStudentDTO().getId());
		
		// Cho phep dang ki KH tiep theo
		registersOfStudent.get(1).setEnable("Yes");
		registerCourseService.update(registersOfStudent.get(1));
		
		// Luu thong tin hoc vien da qua lop
		StudentDetailInCompletedClassDTO stuInCompletedClassDTO = new StudentDetailInCompletedClassDTO();
		stuInCompletedClassDTO.setClassOpeningDTO(reDto.getClassOpeningDTO());
		stuInCompletedClassDTO.setStudentDTO(reDto.getStudentDTO());
		stuInCompletedClassDTO.setIsPassed(0);
		stuCompletedClassService.add(stuInCompletedClassDTO);
		
		// Xoa dang ki
		registerCourseService.delete(idRegister);
		
		return "redirect:/admin/update-status-students?idClass=" + reDto.getClassOpeningDTO().getId();
	}
	
	// Hoc vien khong qua lop
	@GetMapping("/completed-class/student-do-not-complete-class/{idRegister}")
	public String studentDoNotCompleteClass(Model model, 
			@PathVariable(name = "idRegister") int idRegister) {
		// Lay thong tin dang ki
		RegisterCourseDTO dto = registerCourseService.getByID(idRegister);
		model.addAttribute("idRegister", idRegister);
		model.addAttribute("student", dto.getStudentDTO());
		return "class/completion/student-do-not-complete-class";
	}
	
	// Hoc vien ko qua va duoc hoc lai mien phi
	@GetMapping("/student-do-not-complete-class/free/{idRegister}")
	public String free(Model model, 
			@PathVariable(name = "idRegister") int idRegister) {
		// Lay thong tin dang ki
		RegisterCourseDTO reDto = registerCourseService.getByID(idRegister);
		int idClass = reDto.getClassOpeningDTO().getId();
		
		// Luu thong tin hoc vien chua qua lop
		StudentDetailInCompletedClassDTO stuInCompletedClassDTO = new StudentDetailInCompletedClassDTO();
		stuInCompletedClassDTO.setClassOpeningDTO(reDto.getClassOpeningDTO());
		stuInCompletedClassDTO.setStudentDTO(reDto.getStudentDTO());
		stuInCompletedClassDTO.setIsPassed(1);
		stuCompletedClassService.add(stuInCompletedClassDTO);		
		
		// Cap nhat lai dang ki ve chua co lop
		reDto.setClassOpeningDTO(null);
		registerCourseService.update(reDto);
		
		return "redirect:/admin/update-status-students?idClass=" + idClass;
	}
	
//	@PostMapping("/student-do-not-complete-class/not-free/{idRegister}")
//	public String notFree(Model model, 
//			@PathVariable(name = "idRegister") int idRegister) {
//		// Lay thong tin dang ki
//		RegisterCourseDTO reDto = registerCourseService.getByID(idRegister);
//		
//		// Luu thong tin hoc vien chua qua lop
//		StudentDetailInCompletedClassDTO stuInCompletedClassDTO = new StudentDetailInCompletedClassDTO();
//		stuInCompletedClassDTO.setClassOpeningDTO(reDto.getClassOpeningDTO());
//		stuInCompletedClassDTO.setStudentDTO(reDto.getStudentDTO());
//		stuInCompletedClassDTO.setIsPassed(1);
//		stuCompletedClassService.add(stuInCompletedClassDTO);		
//		
//		// Cap nhat lai dang ki ve chua co lop
//		reDto.setClassOpeningDTO(null);
//		registerCourseService.update(reDto);
//		
//		// Tao mot hoa don moi 
//		
//		
//		return "redỉrect:/admin/class-completion";
//	}
	
	@GetMapping("class-completion/class-detail/{idClass}")
	public String classCompletionDetail(Model model, @PathVariable(name = "idClass") int idClass) {
		model.addAttribute("students", stuCompletedClassService.getByIdClass(idClass));
		model.addAttribute("class", service.getByID(idClass));
		return "class/completion/class-detail";
	}
	
}	
