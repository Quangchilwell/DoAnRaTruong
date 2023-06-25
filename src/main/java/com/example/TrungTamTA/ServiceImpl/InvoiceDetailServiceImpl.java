package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.InvoiceDetailDao;
import com.example.TrungTamTA.Entity.InvoiceDetail;
import com.example.TrungTamTA.Model.ComboDTO;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Model.InvoiceDTO;
import com.example.TrungTamTA.Model.InvoiceDetailDTO;
import com.example.TrungTamTA.Service.ComboService;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.InvoiceDetailService;
import com.example.TrungTamTA.Service.InvoiceService;

@Service
@Transactional
public class InvoiceDetailServiceImpl implements InvoiceDetailService{
	
	@Autowired InvoiceDetailDao invoiceDetailDao;
	
	@Autowired InvoiceService invoiceService;
	
	@Autowired CourseService courseService;
	
	@Autowired ComboService comboService;
	
	private void getInfo(InvoiceDetailDTO invoiceDetailDTO, InvoiceDetail invoiceDetail) {
		InvoiceDTO invoiceDTO = invoiceService.getByID(invoiceDetail.getIdInvoice());
		CourseDTO courseDTO = courseService.getByID(invoiceDetail.getIdCourse());
		ComboDTO comboDTO = comboService.getByID(invoiceDetail.getIdCombo());
		
		invoiceDetailDTO.setId(invoiceDetail.getId());
		invoiceDetailDTO.setInvoiceDTO(invoiceDTO);
		invoiceDetailDTO.setCourseDTO(courseDTO);
		invoiceDetailDTO.setComboDTO(comboDTO);
		invoiceDetailDTO.setUnitPrice(invoiceDetail.getUnitPrice());
		invoiceDetailDTO.setCreatedDate(invoiceDetail.getCreatedDate());
		invoiceDetailDTO.setUpdatedDate(invoiceDetail.getUpdatedDate());
	}

	private void setInfo(InvoiceDetailDTO invoiceDetailDTO, InvoiceDetail invoiceDetail) {
		invoiceDetail.setIdInvoice(invoiceDetailDTO.getInvoiceDTO().getId());
		invoiceDetail.setIdCourse(invoiceDetailDTO.getCourseDTO().getId());
		if(invoiceDetailDTO.getComboDTO() != null) {
			invoiceDetail.setIdCombo(invoiceDetailDTO.getComboDTO().getId());
		}
		invoiceDetail.setUnitPrice(invoiceDetailDTO.getUnitPrice());
		invoiceDetail.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		invoiceDetail.setUpdatedDate(null);
	}
	
	@Override
	public List<InvoiceDetailDTO> getAll() {
		List<InvoiceDetail> invoiceDetails = invoiceDetailDao.getAll();
		List<InvoiceDetailDTO> invoiceDetailDTOs = new ArrayList<InvoiceDetailDTO>();
		
		for(InvoiceDetail invoiceDetail: invoiceDetails) {
			InvoiceDetailDTO invoiceDetailDTO = new InvoiceDetailDTO();
			getInfo(invoiceDetailDTO, invoiceDetail);
			invoiceDetailDTOs.add(invoiceDetailDTO);
		}
		return invoiceDetailDTOs;
	}

	@Override
	public List<InvoiceDetailDTO> getByidInvoie(int idInvoie) {
		List<InvoiceDetail> invoiceDetails = invoiceDetailDao.getByidInvoie(idInvoie);
		List<InvoiceDetailDTO> invoiceDetailDTOs = new ArrayList<InvoiceDetailDTO>();
		
		for(InvoiceDetail invoiceDetail: invoiceDetails) {
			InvoiceDetailDTO invoiceDetailDTO = new InvoiceDetailDTO();
			getInfo(invoiceDetailDTO, invoiceDetail);
			invoiceDetailDTOs.add(invoiceDetailDTO);
		}
		return invoiceDetailDTOs;
	}

	@Override
	public void add(InvoiceDetailDTO invoiceDetailDTO) {
		InvoiceDetail invoiceDetail = new InvoiceDetail();
		setInfo(invoiceDetailDTO, invoiceDetail);
		invoiceDetailDao.add(invoiceDetail);
	}

	@Override
	public void update(InvoiceDetailDTO invoiceDetailDTO) {
		InvoiceDetail invoiceDetail = invoiceDetailDao.getByID(invoiceDetailDTO.getId());
		if(invoiceDetail != null) {
			setInfo(invoiceDetailDTO, invoiceDetail);
			invoiceDetailDao.update(invoiceDetail);
		}
	}

	@Override
	public void delete(int id) {
		InvoiceDetail invoiceDetail = invoiceDetailDao.getByID(id);
		if(invoiceDetail != null) {
			invoiceDetailDao.delete(invoiceDetail);
		}
	}

	@Override
	public InvoiceDetailDTO getByID(int id) {
		InvoiceDetail invoiceDetail = invoiceDetailDao.getByID(id);
		if(invoiceDetail != null) {
			InvoiceDetailDTO invoiceDetailDTO = new InvoiceDetailDTO();
			getInfo(invoiceDetailDTO, invoiceDetail);
			return invoiceDetailDTO;
		}
		return null;
	}
}
