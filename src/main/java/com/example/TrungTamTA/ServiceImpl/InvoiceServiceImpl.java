package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.InvoiceDao;
import com.example.TrungTamTA.Entity.Invoice;
import com.example.TrungTamTA.Model.InvoiceDTO;
import com.example.TrungTamTA.Model.StudentDTO;
import com.example.TrungTamTA.Service.InvoiceService;
import com.example.TrungTamTA.Service.StudentService;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired InvoiceDao invoiceDao;
	
	@Autowired StudentService studentService;
	
	private void getInfo(InvoiceDTO invoiceDTO, Invoice invoice) {
		StudentDTO studentDTO = studentService.getByID(invoice.getIdStudent());		
		invoiceDTO.setId(invoice.getId());
		invoiceDTO.setStudentDTO(studentDTO);
		invoiceDTO.setMoneyPaid(invoice.getMoneyPaid());
		invoiceDTO.setTotalPrice(invoice.getTotalPrice());
		invoiceDTO.setCreatedDate(invoice.getCreatedDate());
		invoiceDTO.setUpdatedDate(invoice.getUpdatedDate());
		invoiceDTO.setStatus(invoice.getStatus());
		invoiceDTO.setNote(invoice.getNote());
	}
	
	private void setInfo(InvoiceDTO invoiceDTO, Invoice invoice) {
		invoice.setIdStudent(invoiceDTO.getStudentDTO().getId());
		invoice.setMoneyPaid(invoiceDTO.getMoneyPaid());
		invoice.setTotalPrice(invoiceDTO.getTotalPrice());
		invoice.setNote(invoiceDTO.getNote());
		invoice.setStatus(1);
	}
	
	@Override
	public List<InvoiceDTO> getAll() {
		List<Invoice> invoices = invoiceDao.getAll();
		List<InvoiceDTO> invoiceDTOs = new ArrayList<InvoiceDTO>();
		
		for(Invoice invoice: invoices) {
			InvoiceDTO invoiceDTO = new InvoiceDTO();
			getInfo(invoiceDTO, invoice);
			invoiceDTOs.add(invoiceDTO);
		}
		return invoiceDTOs;
	}

	@Override
	public List<InvoiceDTO> getByidStudent(int idStudent) {
		List<Invoice> invoices = invoiceDao.getByidStudent(idStudent);
		List<InvoiceDTO> invoiceDTOs = new ArrayList<InvoiceDTO>();
		
		for(Invoice invoice: invoices) {
			InvoiceDTO invoiceDTO = new InvoiceDTO();
			getInfo(invoiceDTO, invoice);
			invoiceDTOs.add(invoiceDTO);
		}
		return invoiceDTOs;
	}

	@Override
	public void add(InvoiceDTO invoiceDTO) {
		Invoice invoice = new Invoice();
		setInfo(invoiceDTO, invoice);
		invoice.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		invoice.setUpdatedDate(null);
		invoiceDao.add(invoice);
	}

	@Override
	public void update(InvoiceDTO invoiceDTO) {
		Invoice invoice = invoiceDao.getByID(invoiceDTO.getId());
		if(invoice != null) {
			setInfo(invoiceDTO, invoice);
			invoiceDao.update(invoice);
		}
		
	}

	@Override
	public void delete(int id) {
		Invoice invoice = invoiceDao.getByID(id);
		if(invoice != null) {
			invoiceDao.delete(invoice);
		}
	}

	@Override
	public InvoiceDTO getByID(int id) {
		Invoice invoice = invoiceDao.getByID(id);
		if(invoice != null) {
			InvoiceDTO invoiceDTO = new InvoiceDTO();
			getInfo(invoiceDTO, invoice);
			return invoiceDTO;
		}
		return null;
	}
	
}
