package com.example.TrungTamTA.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TrungTamTA.Dao.InvoiceDao;
import com.example.TrungTamTA.Entity.Invoice;
import com.example.TrungTamTA.Entity.InvoiceDetail;
import com.example.TrungTamTA.Model.InvoiceDTO;
import com.example.TrungTamTA.Model.InvoiceDetailDTO;
import com.example.TrungTamTA.Model.RegisterCourseDTO;
import com.example.TrungTamTA.Model.StudentDTO;
import com.example.TrungTamTA.Service.InvoiceDetailService;
import com.example.TrungTamTA.Service.InvoiceService;
import com.example.TrungTamTA.Service.RegisterCourseService;
import com.example.TrungTamTA.Service.StudentService;

@Controller
@RequestMapping("/admin")
public class InvoiceController {
	
	@Autowired InvoiceService invoiceService;
	
	@Autowired InvoiceDao invoiceDao;
	
	@Autowired InvoiceDetailService invoiceDetailService;
	
	@Autowired StudentService studentService;
	
	@Autowired RegisterCourseService registerCourseService;
	
	@GetMapping("invoice-list")
	public String invoiceList(Model model)
	{
		List<InvoiceDTO> dtos = invoiceService.getAll();
		List<InvoiceDTO> invoiceDTOs = new ArrayList<InvoiceDTO>();
		for(InvoiceDTO invoiceDTO: dtos) {
			if(invoiceDTO.getStatus() != 0) {
				invoiceDTOs.add(invoiceDTO);
			}
		}
		model.addAttribute("invoiceDTOs", invoiceDTOs);
		return "invoice/invoiceList";
	}
	
	// CAC HOA DON DA THANH TOAN
	@GetMapping("invoices-completed")
	public String invoicesCompleted(Model model)
	{
		List<InvoiceDTO> dtos = invoiceService.getAll();
		List<InvoiceDTO> invoiceDTOs = new ArrayList<InvoiceDTO>();
		for(InvoiceDTO invoiceDTO: dtos) {
			if(invoiceDTO.getStatus() == 0) {
				invoiceDTOs.add(invoiceDTO);
			}
		}
		model.addAttribute("invoiceDTOs", invoiceDTOs);
		return "invoice/invoicesCompleted";
	}
	
	// INFO
	@GetMapping("info-invoice/{id}")
	public String infoInvoice(Model model, @PathVariable(name = "id") int id)
	{
		InvoiceDTO invoiceDTO = invoiceService.getByID(id);
		float moneyExisted =  invoiceDTO.getTotalPrice() - invoiceDTO.getMoneyPaid();
		List<InvoiceDetailDTO> invoiceDetailDTOs = invoiceDetailService.getByidInvoie(id);
		
		model.addAttribute("invoice", invoiceDTO);
		model.addAttribute("moneyExisted", moneyExisted);
		model.addAttribute("details", invoiceDetailDTOs);
		return "invoice/infoInvoice";
	}
	
	// SEARCH INVOICE
	@GetMapping("search-invoice")
	public String searchInvoice(Model model, HttpServletRequest request)
	{
		List<InvoiceDTO> invoiceDTOs = invoiceService.getAll();
		List<InvoiceDTO> results = new ArrayList<InvoiceDTO>();
		String info = String.valueOf(request.getParameter("infoSearch"));
		
		if(info == null) {
			return "redirect:/admin/invoice-list/";
		}
		
		for(InvoiceDTO invoiceDTO: invoiceDTOs) {
			if(invoiceDTO.getStudentDTO().getName().compareToIgnoreCase(info) == 0 ||
					invoiceDTO.getStudentDTO().getPhone().compareToIgnoreCase(info) == 0) {
				results.add(invoiceDTO);
			}
		}
		model.addAttribute("results", results);
		return "invoice/searchInvoice";
	}
	
	@GetMapping("update-invoice/{id}")
	public String updateInvoice(Model model, @PathVariable(name = "id") int id)
	{
		InvoiceDTO invoiceDTO = invoiceService.getByID(id);
		model.addAttribute("invoice", invoiceDTO);
		return "invoice/updateInvoice";
	}
	
	@PostMapping("update-invoice")
	public String updateInvoice(Model model, @ModelAttribute(name = "invoice") InvoiceDTO invoiceDTO,
			HttpServletRequest request)
	{
		Invoice invoice = invoiceDao.getByID(invoiceDTO.getId());
		float money = Float.valueOf(request.getParameter("money"));
		invoice.setMoneyPaid(invoice.getMoneyPaid() + money);
		invoice.setNote(invoiceDTO.getNote());
		invoice.setStatus(1);
		if(invoice.getTotalPrice() - invoice.getMoneyPaid() <= 0) {
			invoice.setStatus(0);
		}
		
		invoiceDao.update(invoice);
		return "redirect:/admin/info-invoice/"+ invoice.getId();
	}
	
	// LOAI BO KHOA HOC TRONG HOA DON
	@PostMapping("remove-invoice-detail/{id}")
	public String updateInvoice(@PathVariable(name = "id") int id)
	{
		InvoiceDetailDTO invoiceDetailDTO = invoiceDetailService.getByID(id);
		InvoiceDTO invoiceDTO = invoiceDetailDTO.getInvoiceDTO();
		
		float totalPrice = invoiceDTO.getTotalPrice() - invoiceDetailDTO.getUnitPrice();
		invoiceDTO.setTotalPrice(totalPrice);
		invoiceService.update(invoiceDTO);
		invoiceDetailService.delete(id);
		return "redirect:/admin/info-invoice/" + invoiceDTO.getId();
	}
	
	// Xoa bo hoa don
	@PostMapping("delete-invoice/{id}")
	public String deleteInvoice(@PathVariable(name = "id") int id)
	{
		invoiceService.delete(id);
		return "redirect:/admin/invoice-list/" + id;
	}
}
