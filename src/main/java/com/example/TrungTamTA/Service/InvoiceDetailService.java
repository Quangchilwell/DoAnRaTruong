package com.example.TrungTamTA.Service;

import java.util.List;
import com.example.TrungTamTA.Model.InvoiceDetailDTO;

public interface InvoiceDetailService {
	public List<InvoiceDetailDTO> getAll();

	public List<InvoiceDetailDTO> getByidInvoie(int idInvoie);

	public void add(InvoiceDetailDTO invoiceDetailDTO);

	public void update(InvoiceDetailDTO invoiceDetailDTO);

	public void delete(int id);

	public InvoiceDetailDTO getByID(int id);
}
