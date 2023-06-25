package com.example.TrungTamTA.Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.example.TrungTamTA.Dao.ClassOpeningDao;
import com.example.TrungTamTA.Dao.InvoiceDao;
import com.example.TrungTamTA.Dao.RegisterCourseDao;
import com.example.TrungTamTA.Entity.ClassOpening;
import com.example.TrungTamTA.Entity.Invoice;
import com.example.TrungTamTA.Entity.RegisterCourse;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Model.ComboDTO;
import com.example.TrungTamTA.Model.ComboDetailDTO;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Model.InvoiceDetailDTO;
import com.example.TrungTamTA.Model.RegisterCourseDTO;
import com.example.TrungTamTA.Model.StudentDTO;
import com.example.TrungTamTA.Model.StudentDetailDTO;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.ComboDetailService;
import com.example.TrungTamTA.Service.ComboService;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.CourseTypeService;
import com.example.TrungTamTA.Service.InvoiceDetailService;
import com.example.TrungTamTA.Service.InvoiceService;
import com.example.TrungTamTA.Service.RegisterCourseService;
import com.example.TrungTamTA.Service.StudentDetailService;
import com.example.TrungTamTA.Service.StudentService;

@Controller
@RequestMapping("/tvv")
public class RegisterCourseController {

	@Autowired
	RegisterCourseService registerCourseService;
	
	@Autowired RegisterCourseDao registerCourseDao;

	@Autowired
	StudentService studentService;
	
	@Autowired 
	StudentDetailService studentDetailService;

	@Autowired
	CourseService courseService;

	@Autowired
	CourseTypeService courseTypeService;

	@Autowired
	ComboService comboService;

	@Autowired
	ComboDetailService comboDetailService;

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	InvoiceDao invoiceDao;

	@Autowired
	InvoiceDetailService invoiceDetailService;
	
	@Autowired
	ClassOpeningService classOpeningService;
	
	@Autowired 
	ClassOpeningDao classOpeningDao;

	@GetMapping("/register-course-list")
	public String registerCourseList(Model model) {
		model.addAttribute("courses", courseService.getOfflineCourses());
		model.addAttribute("registers", registerCourseService.getRegistersHaveNotClass());
		return "registerCourse/registerCourseList";
	}

	// INFO
	@GetMapping("/info-register-course/{id}")
	public String infoRegisterCourse(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("register", registerCourseService.getByID(id));
		return "registerCourse/infoRegisterCourse";
	}

	// ADD Khoa le
	@GetMapping("/add-register-course")
	public String addRegisterCourse(Model model, HttpSession session,
			@RequestParam(value = "idStudent") int idStudent) {
		session.setAttribute("courseTypes", courseTypeService.getAll());
		session.setAttribute("courses", courseService.getAll());
		model.addAttribute("idStudent", idStudent);
		model.addAttribute("registerCourseDTO", new RegisterCourseDTO());
		return "registerCourse/addRegisterCourse";
	}

	@PostMapping("/add-register-course")
	public String addRegisterCourse(Model model, @Valid RegisterCourseDTO registerCourseDTO,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("registerCourseDTO", registerCourseDTO);
			return "registerCourse/addRegisterCourse";
		}

		// Tao moi dang ki
		int idStudent = Integer.valueOf(request.getParameter("idStudent"));
		StudentDTO studentDTO = studentService.getByID(idStudent);
		registerCourseDTO.setStudentDTO(studentDTO);
		registerCourseDTO.setCourseDTO(courseService.getByID(registerCourseDTO.getIdCourse()));
		registerCourseService.add(registerCourseDTO);
		
		// Tao moi hoa don
		Invoice invoice = new Invoice();
		invoice.setIdStudent(studentDTO.getId());
		invoice.setMoneyPaid(0);
		invoice.setNote(null);
		invoice.setTotalPrice(registerCourseDTO.getCourseDTO().getTuition());
		invoice.setStatus(1);
		invoice.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		invoice = invoiceDao.add(invoice);
		
		// Chi tiet hoa don
		InvoiceDetailDTO invoiceDetailDTO = new InvoiceDetailDTO();
		invoiceDetailDTO.setComboDTO(null);
		invoiceDetailDTO.setCourseDTO(registerCourseDTO.getCourseDTO());
		invoiceDetailDTO.setInvoiceDTO(invoiceService.getByID(invoice.getId()));
		invoiceDetailDTO.setUnitPrice(registerCourseDTO.getCourseDTO().getTuition());
		invoiceDetailDTO.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		invoiceDetailService.add(invoiceDetailDTO);
		return "redirect:/tvv/register-course-list";
	}

	// GET REGISTER FOR COURSE
	@GetMapping("/registers-for-course")
	public String registersForCourse(Model model, @RequestParam(name = "idCourse") int idCourse) {
		CourseDTO courseDTO = courseService.getByID(idCourse);
		List<RegisterCourseDTO> registerCourseDTOs = registerCourseService.getByIdCourse(idCourse);

		model.addAttribute("courses", courseService.getAll());
		model.addAttribute("registers", registerCourseDTOs);
		model.addAttribute("size", registerCourseDTOs.size());
		model.addAttribute("courseDTO", courseDTO);
		return "registerCourse/registersForCourse";
	}

	// OPEN SCHEDULED CLASS
	@GetMapping("/open-scheduled-class/idCourse/{idCourse}")
	public String openScheduledClass(@RequestParam(name = "idRegisters") List<Integer> idRegisters,
			@PathVariable(name = "idCourse") int idCourse) {
		CourseDTO courseDTO = courseService.getByID(idCourse);
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

	// ADD REGISTER COMBO
	@GetMapping("/add-register-course-combo")
	public String addRegisterCourseCombo(Model model, HttpSession session,
			@RequestParam(value = "idStudent") int idStudent) {
		model.addAttribute("combos", comboService.getAll());
		model.addAttribute("student", studentService.getByID(idStudent));
		model.addAttribute("registerCourseDTO", new RegisterCourseDTO());
		return "registerCourse/addRegisterCourseCombo";
	}

	@GetMapping("/comfirm-register-combo")
	public String comfirmRegisterCombo(Model model, @RequestParam(value = "idCombo") int idCombo,
			@RequestParam(value = "idStudent") int idStudent) {
		model.addAttribute("combo", comboService.getByID(idCombo));
		model.addAttribute("student", studentService.getByID(idStudent));
		return "registerCourse/comfirmRegisterCombo";
	}

	@GetMapping("/register-combo-success")
	public String registerComboSuccess(Model model, @RequestParam(value = "idCombo") int idCombo,
			@RequestParam(value = "idStudent") int idStudent) {
		ComboDTO comboDTO = comboService.getByID(idCombo);
		StudentDTO studentDTO = studentService.getByID(idStudent);
		List<ComboDetailDTO> comboDetailDTOs = comboDetailService.getByidCombo(idCombo);
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();
		float totalPrice = 0;

		// Tạo lập đăng kí
		for (ComboDetailDTO comboDetailDTO : comboDetailDTOs) {
			totalPrice += comboDetailDTO.getNewPrice();
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			registerCourseDTO.setComboDTO(comboDTO);
			registerCourseDTO.setCourseDTO(comboDetailDTO.getCourseDTO());
			registerCourseDTO.setStudentDTO(studentDTO);
			registerCourseDTO.setRegisterDate(Timestamp.valueOf(LocalDateTime.now()));
			registerCourseDTO.setNote(null);
			registerCourseDTOs.add(registerCourseDTO);
		}

		for (var i = 0; i < registerCourseDTOs.size(); i++ ) {
			if(i == 0) {
				registerCourseDTOs.get(i).setEnable("Yes");
			}else {
				registerCourseDTOs.get(i).setEnable("No");
			}
		}
		
		registerCourseService.addMany(registerCourseDTOs);

		// Tạo lập hóa đơn
		Invoice invoice = new Invoice();
		invoice.setIdStudent(studentDTO.getId());
		invoice.setMoneyPaid(0);
		invoice.setNote(null);
		invoice.setTotalPrice(totalPrice);
		invoice.setStatus(1);
		invoice.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		invoice = invoiceDao.add(invoice);

		// Tao chi tiet hoa don
		for (ComboDetailDTO comboDetailDTO : comboDetailDTOs) {
			InvoiceDetailDTO invoiceDetailDTO = new InvoiceDetailDTO();
			invoiceDetailDTO.setComboDTO(comboDTO);
			invoiceDetailDTO.setCourseDTO(comboDetailDTO.getCourseDTO());
			invoiceDetailDTO.setInvoiceDTO(invoiceService.getByID(invoice.getId()));
			invoiceDetailDTO.setUnitPrice(comboDetailDTO.getNewPrice());
			invoiceDetailDTO.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
			invoiceDetailService.add(invoiceDetailDTO);
		}

		return "redirect:/tvv/register-course-list";
	}

	// Danh sach dang ki theo ma hoc vien
	@GetMapping("student-register-list")
	public String studentRegisterList(Model model, @RequestParam(name = "idStudent") int idStudent) {
		model.addAttribute("registerDTOs", registerCourseService.getByIdStudent(idStudent));
		return "registerCourse/studentRegisterList";
	}

	// UPDATE
	@GetMapping("/update-register-course/{id}")
	public String updateRegisterCourse(Model model, HttpSession session,
			@RequestParam(value = "idStudent") int idStudent, @PathVariable(name = "id") int id) {
		RegisterCourseDTO registerCourseDTO = registerCourseService.getByID(id);

		model.addAttribute("course", courseService.getByID(registerCourseDTO.getCourseDTO().getId()));
		model.addAttribute("registerCourseDTO", registerCourseDTO);
		return "registerCourse/updateRegisterCourse";
	}

	@PostMapping("/update-register-course/{id}")
	public String updateRegisterCourse(Model model, HttpServletRequest request,
			@PathVariable(name = "id") int id) {
		
		if(request.getParameter("idStudent") != null) {
			int idStudent = Integer.valueOf(request.getParameter("idStudent"));
			RegisterCourse registerCourse = registerCourseDao.getByID(id);
			registerCourse.setIdStudent(idStudent);
			registerCourseDao.update(registerCourse);
		}

		return "redirect:/tvv/register-course-list";
	}

	// DELETE
	@PostMapping("/delete-register-course/{id}")
	public String deleteRegisterCourse(Model model, @PathVariable(name = "id") int id) {
		registerCourseService.delete(id);
		return "redirect:/tvv/register-course-list";
	}

}
