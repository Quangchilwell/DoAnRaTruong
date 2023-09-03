package com.example.TrungTamTA.Controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.example.TrungTamTA.Constant.AttendanceStatus;
import com.example.TrungTamTA.Constant.ClassCompletionStatus;
import com.example.TrungTamTA.Constant.ClassStatus;
import com.example.TrungTamTA.Constant.LessonStatus;
import com.example.TrungTamTA.Entity.DraftAttendance;
import com.example.TrungTamTA.Entity.Lesson;
import com.example.TrungTamTA.Model.AttendanceDTO;
import com.example.TrungTamTA.Model.ClassDetailDTO;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Model.LessonDTO;
import com.example.TrungTamTA.Model.RegisterCourseDTO;
import com.example.TrungTamTA.Repository.DraftAttendanceRepository;
import com.example.TrungTamTA.Repository.LessonRepository;
import com.example.TrungTamTA.Service.AttendanceService;
import com.example.TrungTamTA.Service.ClassDetailService;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.LessonService;
import com.example.TrungTamTA.Service.RegisterCourseService;
import com.example.TrungTamTA.Service.StudentService;
import com.example.TrungTamTA.Utils.DateUtils;

@Controller
@Transactional
@RequestMapping("/admin")
public class LessonController {

	@Autowired
	LessonService lessonService;
	
	@Autowired LessonRepository lessonRepository;
	
	@Autowired
	ClassOpeningService classOpeningService;

	@Autowired
	ClassDetailService classDetailService;
	
	@Autowired
	RegisterCourseService registerCourseService;
	
	@Autowired 
	StudentService studentService;
	
	@Autowired AttendanceService attendanceService;
	@Autowired DraftAttendanceRepository draftAttendanceRepository;

	@GetMapping("/classes-today")
	public String classesToday(Model model) {
		LocalDate localDate = LocalDate.now();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<ClassOpeningDTO> dtos = new ArrayList<ClassOpeningDTO>();
		List<ClassDetailDTO> classDetailDTOs = classDetailService.getAll();
		
		for (ClassDetailDTO classDetailDTO : classDetailDTOs) {
			if (classDetailDTO.getDayOfWeekDTO().getDay() == localDate.getDayOfWeek().getValue() + 1
					&& classDetailDTO.getClassOpeningDTO().getStatus() == 0) {
				Lesson lesson = lessonRepository.checkClassCompleted(classDetailDTO.getClassOpeningDTO().getId(), 
						Date.valueOf(localDate), LessonStatus.CHUA_HOAN_THANH);
				if(lesson == null) {
					dtos.add(classDetailDTO.getClassOpeningDTO());					
				}
				
			}
		}
		model.addAttribute("today", sdf.format(Date.valueOf(localDate)));
		model.addAttribute("date", localDate);
		model.addAttribute("classes", dtos);

		return "class/classesToday";
	}
	
	// HOAN THANH BUOI HOC
	@GetMapping("/lesson-completed")
	public String lessonCompleted(Model model, 
			@RequestParam(name = "idClass") int idClass,
			@RequestParam(name = "date", required = false) String dateToCheck) {
		
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(idClass);
		Date currentDate = Date.valueOf(dateToCheck);
		
		// Danh sach hoc vien chua diem danh trong lop
		List<RegisterCourseDTO> studentDTOs = registerCourseService.getStudentsToCheckIn(idClass);
		
		// Luu ban ghi lesson voi trang thai chua hoan thanh
		if(currentDate != null) {
			Lesson oldLesson = lessonRepository.findByidClassOpeningAndDay(idClass, currentDate);
			
			if(oldLesson == null) {
				Lesson lesson = new Lesson();
				lesson.setIdClassOpening(idClass);
				lesson.setDay(currentDate);
				lesson.setStatus(LessonStatus.CHUA_HOAN_THANH);
				lesson.setLessonNumber(classOpeningDTO.getNumberOfLessonsLearned() + 1);
				lesson.setCompletedAt(Timestamp.valueOf(LocalDateTime.now()));
				lesson = lessonRepository.saveAndFlush(lesson);
				
				model.addAttribute("idLesson", lesson.getId());
			} else {
				model.addAttribute("idLesson", oldLesson.getId());
			}
		}
						
		
		model.addAttribute("studentDTOs", studentDTOs);
		model.addAttribute("class", classOpeningDTO);
		
		if(currentDate != null) {
			model.addAttribute("date", DateUtils.convertDateToDateOfVN(currentDate));			
		} else {
			model.addAttribute("date", DateUtils.convertDateToDateOfVN(LocalDate.now()));
		}
		if(dateToCheck != null) {
			model.addAttribute("dateToCheck", dateToCheck);
		}
		return "class/student-attendance";
	}

	// Hoãn buổi học
	@GetMapping(value = "lesson/postpone-lesson")
	public String postPoneClass(Model model, @RequestParam(name = "idClass") int idClass, 
			@RequestParam(name = "date") String date) {
		model.addAttribute("lessonDTO", new LessonDTO());
		model.addAttribute("class", classOpeningService.getByID(idClass));
		model.addAttribute("date", date);
		return "class/postponeLesson";
	}
	
	@PostMapping(value = "lesson/postpone-lesson")
	public String postPoneClass(@ModelAttribute("lessonDTO") LessonDTO lessonDTO,
			HttpServletRequest request) {
		int idClass = Integer.valueOf(request.getParameter("idClass"));
		String date = String.valueOf(request.getParameter("date"));
		
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(idClass);
		
		lessonDTO.setClassOpeningDTO(classOpeningDTO);
		lessonDTO.setDay(date);
		lessonDTO.setStatus(-1);
		lessonDTO.setLessonNumber(classOpeningDTO.getNumberOfLessonsLearned() + 1);
		lessonDTO.setCompletedAt(Timestamp.valueOf(LocalDateTime.now()));
		lessonService.add(lessonDTO);
		
		// Update number lesson
		classOpeningDTO.setNumberOfLessonsLearned(classOpeningDTO.getNumberOfLessonsLearned() + 1);
		classOpeningService.update(classOpeningDTO);
		return "redirect:/admin/classes-todays";
	}
	
	// Điểm danh có đi học
	@GetMapping("/classDay/student-attendance/present")
	public String present(Model model, @RequestParam("idStudent") int idStudent,
			@RequestParam("idClass") int idClass, 
			@RequestParam("idLesson") int idLesson,
			@RequestParam("date") String date) {
		
		// Thêm mới điểm danh
		AttendanceDTO attendanceDTO = new AttendanceDTO();
		attendanceDTO.setClassDTO(classOpeningService.getByID(idClass));
		attendanceDTO.setLessonDTO(lessonService.getByID(idLesson));
		attendanceDTO.setStudentDTO(studentService.getByID(idStudent));
		attendanceDTO.setStatus(AttendanceStatus.PRESENT);
		attendanceService.add(attendanceDTO);
		
		// Thêm mới bảng nháp điểm danh
		DraftAttendance draftAttendance = new DraftAttendance();
		draftAttendance.setIdStudent(idStudent);
		draftAttendanceRepository.save(draftAttendance);
		
		return "redirect:/admin/lesson-completed?idClass=" + idClass + "&date=" + date;
	}
	
	// Điểm danh không đi học
	@GetMapping("/classDay/student-attendance/absent")
	public String absesent(Model model, @RequestParam("idStudent") int idStudent,
			@RequestParam("idClass") int idClass,
			@RequestParam("idLesson") int idLesson,
			@RequestParam("date") String date) {
		
		// Thêm mới điểm danh
		AttendanceDTO attendanceDTO = new AttendanceDTO();
		attendanceDTO.setClassDTO(classOpeningService.getByID(idClass));
		attendanceDTO.setLessonDTO(lessonService.getByID(idLesson));
		attendanceDTO.setStudentDTO(studentService.getByID(idStudent));
		attendanceDTO.setStatus(AttendanceStatus.ABSENT);
		attendanceService.add(attendanceDTO);
		
		// Thêm mới bảng nháp điểm danh
		DraftAttendance draftAttendance = new DraftAttendance();
		draftAttendance.setIdStudent(idStudent);
		draftAttendanceRepository.save(draftAttendance);
		
		return "redirect:/admin/lesson-completed?idClass=" + idClass + "&date=" + date;
	}
	
	// Xác nhận hoàn thành buổi học
	@GetMapping("/classDay/confirm-complete-lesson")
	public String confirmCompleteLesson(Model model, @RequestParam("idClass") int idClass,
			@RequestParam("idLesson") int idLesson) {
		
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(idClass);
		
		List<RegisterCourseDTO> studentsCheckIn = registerCourseService.getStudentsToCheckIn(idClass);
		if(studentsCheckIn.size() > 0) {
			model.addAttribute("quantityStudents", studentsCheckIn.size());
			model.addAttribute("class", classOpeningDTO);
			model.addAttribute("today", DateUtils.convertDateToDateOfVN(LocalDate.now()));
			return "class/completion/class-can-not-be-completed";
		}
		
		List<RegisterCourseDTO> studentDTOs = registerCourseService.getByIdClassOpening(idClass);
		
		// Xoa cac nhap diem danh
		for(RegisterCourseDTO dto: studentDTOs) {
			DraftAttendance draftAttendance = draftAttendanceRepository.findByidStudent(dto.getStudentDTO().getId());
			draftAttendanceRepository.delete(draftAttendance);
		}
		
		// Cap nhat thong tin buoi hoc ve trang thai da hoan thanh
		LessonDTO lessonDTO = lessonService.getByID(idLesson);
		lessonDTO.setStatus(LessonStatus.DA_HOAN_THANH);
		if(lessonDTO != null) {
			lessonService.update(lessonDTO);
		}
		
		// Cap nhat so buoi hoc hoan thanh
		classOpeningDTO.setNumberOfLessonsLearned(classOpeningDTO.getNumberOfLessonsLearned() + 1);		
		classOpeningService.update(classOpeningDTO);
		
		// Ve danh sach lop hoc hom nay
		return "redirect:/admin/classes-today";
	}
	
	//HOÃN LẠI BUỔI HỌC
	@GetMapping("/postpone-class")
	public String postponeClass(Model model, 
			@RequestParam(name = "idClass") int idClass,
			@RequestParam(name = "date") String date) {
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(idClass);
		LessonDTO classDayDTO = new LessonDTO();
		
		classDayDTO.setClassOpeningDTO(classOpeningDTO);
		classDayDTO.setDay(String.valueOf(date));
		classDayDTO.setStatus(-1);
		classDayDTO.setLessonNumber(classOpeningDTO.getNumberOfLessonsLearned() + 1);
		classDayDTO.setCompletedAt(Timestamp.valueOf(LocalDateTime.now()));
		lessonService.add(classDayDTO);
		return "redirect:/admin/classes-today";
	}
	
	//XEM LICH HOC CAC NGAY KHAC
	@GetMapping("/check-classes-in-date")
	public String previousClassSchedule(Model model, @RequestParam(name = "date", required = false) String date)
	{
		if(date != null) {
			LocalDate localDate = LocalDate.parse(date);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			List<ClassOpeningDTO> dtos = new ArrayList<ClassOpeningDTO>();
			List<ClassDetailDTO> classDetailDTOs = classDetailService.getAll();

			for (ClassDetailDTO classDetailDTO : classDetailDTOs) {
				if (classDetailDTO.getDayOfWeekDTO().getDay() == localDate.getDayOfWeek().getValue() + 1
						&& classDetailDTO.getClassOpeningDTO().getStatus() == 0) {
					Lesson lesson = lessonRepository.checkClassCompleted(classDetailDTO.getClassOpeningDTO().getId(), 
							Date.valueOf(localDate), LessonStatus.DA_HOAN_THANH);
					if(lesson == null) {
						dtos.add(classDetailDTO.getClassOpeningDTO());					
					}
					
				}
			}
			model.addAttribute("today", sdf.format(Date.valueOf(localDate)));
			model.addAttribute("date", localDate);
			model.addAttribute("classes", dtos);
		}
		return "class/checkClassesInDate";
	}
	
	@GetMapping("/result-check-classes")
	public String resultCheckClasses(Model model, HttpServletRequest request)
	{
		String dateAreSearching = String.valueOf(request.getParameter("dateAreSearching"));

		return "redirect:/admin/check-classes-in-date?date=" + dateAreSearching;
	}
	
	
}
