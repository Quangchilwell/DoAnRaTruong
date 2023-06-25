package com.example.TrungTamTA.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.example.TrungTamTA.Dao.Online.AccountStudentDao;
import com.example.TrungTamTA.Entity.Online.AccountStudent;
import com.example.TrungTamTA.Model.FutureStudentDTO;
import com.example.TrungTamTA.Model.Online.AccountStudentDTO;
import com.example.TrungTamTA.Model.Online.DetailAddMoneyDTO;
import com.example.TrungTamTA.Model.Online.InvoiceOnlineCourseDTO;
import com.example.TrungTamTA.Model.Online.OnlineCourseActivatedDTO;
import com.example.TrungTamTA.Model.Online.OnlineCourseDTO;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.CourseTypeService;
import com.example.TrungTamTA.Service.FutureStudentService;
import com.example.TrungTamTA.Service.Online.AccountStudentService;
import com.example.TrungTamTA.Service.Online.DetailAddMoneyService;
import com.example.TrungTamTA.Service.Online.InvoiceOnlineCourseService;
import com.example.TrungTamTA.Service.Online.OnlineCourseActivatedService;
import com.example.TrungTamTA.Service.Online.OnlineCourseService;

@Controller
@Transactional
@RequestMapping("/mqe")
public class WebSiteController {

	@Autowired
	AccountStudentDao acStudentDao;
	@Autowired
	AccountStudentService acService;
	@Autowired
	CourseService courseService;
	@Autowired
	CourseTypeService typeService;
	@Autowired
	OnlineCourseService onCourseService;
	@Autowired
	InvoiceOnlineCourseService inOnlineCourseService;
	@Autowired
	OnlineCourseActivatedService activatedService;
	@Autowired
	FutureStudentService futureStudentService;
	@Autowired
	DetailAddMoneyService addMoneyService;

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("futureStudentDTO", new FutureStudentDTO());
		return "website/home";
	}

	// DIEN THONG TIN CAN TU VAN TREN WESITE
	@PostMapping("/send-your-info")
	public String sendYourInfo(@ModelAttribute(name = "futureStudentDTO") FutureStudentDTO futureStudentDTO) {
		futureStudentDTO.setStatus(0);
		futureStudentService.add(futureStudentDTO);
		return "redirect:/mqe/send-your-info-success";
	}

	@GetMapping("/send-your-info-success")
	public String sendYourInfoSuccess() {
		return "website/sendYourInfoSuccess";
	}

	// CAC KHOA HOC ONLINE
	@GetMapping("/online-courses-list")
	public String onlineCourses(Model model) {
		model.addAttribute("onCourses", onCourseService.getAll());
		model.addAttribute("types", typeService.getAll());
		return "website/onlineCourses";
	}

	// CHI TIET CAC KHOA ONLINE
	@GetMapping("/info-online-course/{id}")
	public String infoOnlineCourse(Model model, @PathVariable(name = "id") int id, HttpSession session) {
		AccountStudentDTO accountDTO = (AccountStudentDTO) session.getAttribute("studentLogin");
		if (accountDTO != null) {
			List<OnlineCourseActivatedDTO> activatedDTOs = activatedService.getByidAccount(accountDTO.getId());
			for (OnlineCourseActivatedDTO activatedDTO : activatedDTOs) {
				if (activatedDTO.getOnlineCourseDTO().getId() == id) {
					model.addAttribute("actived", true);
					break;
				}
			}

		}

		model.addAttribute("onCourse", onCourseService.getByID(id));
		return "website/onlineCourseDetail";
	}

	// MUA KHOA HOC ONLINE
	@GetMapping("/buy-online-course/{idCourse}")
	public String onlineCourseDetail(HttpSession session, @PathVariable(name = "idCourse") int idCourse) {
		AccountStudentDTO accountDTO = (AccountStudentDTO) session.getAttribute("studentLogin");
		if (accountDTO == null) {
			return "redirect:/mqe/login";
		} else {
			OnlineCourseDTO onlineCourseDTO = onCourseService.getByID(idCourse);
			if (accountDTO.getMoneyAvailable() >= onlineCourseDTO.getCourseDTO().getTuition()) {

				// Kich hoat khoa hoc
				OnlineCourseActivatedDTO activatedDTO = new OnlineCourseActivatedDTO();
				activatedDTO.setAccountDTO(accountDTO);
				activatedDTO.setOnlineCourseDTO(onlineCourseDTO);
				activatedService.add(activatedDTO);

				// Tao hoa don cho KH onl
				InvoiceOnlineCourseDTO inCourseDTO = new InvoiceOnlineCourseDTO();
				inCourseDTO.setAccountStudentDTO(accountDTO);
				inCourseDTO.setOnlineCourseDTO(onlineCourseDTO);
				inCourseDTO.setTotalPrice(onlineCourseDTO.getCourseDTO().getTuition());
				inOnlineCourseService.add(inCourseDTO);

				// Tru tien tai khoan
				accountDTO.setMoneyAvailable(
						accountDTO.getMoneyAvailable() - onlineCourseDTO.getCourseDTO().getTuition());
				acService.update(accountDTO);
			} 
			else {
				return "website/cannotBuyCourse";
			}
		}
		return "redirect:/mqe/my-courses/account/" + accountDTO.getId();
	}

	// NAP TIEN VAO TAI KHOAN
	@GetMapping("/add-money-for-your-account")
	public String addMoney(Model model)
	{
		model.addAttribute("addMoneyDTO", new DetailAddMoneyDTO());
		return "website/addMoney";
	}
	
	@PostMapping("/add-money-for-your-account")
	public String addMoney(HttpSession session,
				@ModelAttribute(name = "addMoneyDTO") DetailAddMoneyDTO detailAddMoneyDTO)
	{
		AccountStudentDTO accountDTO = (AccountStudentDTO) session.getAttribute("studentLogin");
		if(detailAddMoneyDTO.getMoney() > 0 && accountDTO != null) {
			detailAddMoneyDTO.setAccountStudentDTO(accountDTO);
			addMoneyService.add(detailAddMoneyDTO);
			
			// Cap nhat tien trong tai khoan
			AccountStudent accountStudent = acStudentDao.getById(accountDTO.getId());
			accountStudent.setMoneyAvailable(accountStudent.getMoneyAvailable() + detailAddMoneyDTO.getMoney());
			acStudentDao.update(accountStudent);
			return "website/addMoneySuccess";
		}
		return "website/addMoneyFail";
	}
	
	// KHOA HOC CUA TOI
	@GetMapping("/my-courses/account/{id}")
	public String myCourses(Model model, @PathVariable(name = "id") int id, HttpSession session) {
		AccountStudentDTO accountDTO = (AccountStudentDTO) session.getAttribute("studentLogin");
		if (accountDTO != null && accountDTO.getId() == id) {
			List<OnlineCourseActivatedDTO> dtos = activatedService.getByidAccount(id);
			model.addAttribute("dtos", dtos);
			return "website/myCourses";
		}
		return "redirect:/mqe/login";
	}
	
	// DANG NHAP
	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error) {
		if (error != null) {
			model.addAttribute("error", error);
			return "website/login";
		}
		return "website/login";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		List<AccountStudentDTO> accountStudentDTOs = acService.getAll();
		boolean check = false;
		for (AccountStudentDTO dto : accountStudentDTOs) {
			if (dto.getEmail().equals(email) && dto.getPassword().equals(password)) {
				check = true;
				session.setAttribute("studentLogin", dto);
				break;
			}
		}

		if (check == false) {
			String error = "error";
			return "redirect:/mqe/login?error=" + error;
		}

		return "redirect:/mqe/home";
	}

	// DANG XUAT
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("studentLogin");
		return "redirect:/mqe/home";
	}

	// DANG KI TAI KHOAN MOI
	@GetMapping("/register")
	public String register(Model model, @RequestParam(name = "error", required = false) String error) {
		if (error != null) {
			model.addAttribute("error", error);
			model.addAttribute("account", new AccountStudentDTO());
			return "website/register";
		}
		model.addAttribute("account", new AccountStudentDTO());
		return "website/register";
	}

	@PostMapping("/register")
	public String register(Model model, @ModelAttribute(name = "account") AccountStudentDTO dto) {
		List<AccountStudentDTO> dtos = acService.getAll();
		for (AccountStudentDTO accountStudentDTO : dtos) {
			if (dto.getEmail().equals(accountStudentDTO.getEmail())) {
				String error = "existed";
				return "redirect:/mqe/register?error=" + error;
			}
		}
		dto.setMoneyAvailable(0);
		acService.add(dto);
		return "redirect:/mqe/login";
	}
}
