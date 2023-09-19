package com.example.TrungTamTA.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TrungTamTA.Model.ClassDetailDTO;
import com.example.TrungTamTA.Model.ReservationDTO;
import com.example.TrungTamTA.Model.TeacherDTO;
import com.example.TrungTamTA.Service.ClassDetailService;
import com.example.TrungTamTA.Service.ReservationService;
import com.example.TrungTamTA.Service.TeacherService;

@Controller
@RequestMapping("/qlhv/export-excel/")
public class ExportExcelController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ClassDetailService classDetailService;

	@Autowired
	private TeacherService teacherService;

	@GetMapping("export-reservation-list")
	public void exportReservationList(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposit";
		String headerValue = "attachment;filename=ReservationsList.xlsx";
		response.setHeader(headerKey, headerValue);

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Reservation list");
		HSSFRow titleRow = sheet.createRow(1);
		titleRow.createCell(3).setCellValue("Danh sách bảo lưu");

		HSSFRow contentRow = sheet.createRow(2);
		contentRow.createCell(1).setCellValue("Mã bảo lưu");
		contentRow.createCell(2).setCellValue("Mã học viên");
		contentRow.createCell(3).setCellValue("Họ tên học viên");
		contentRow.createCell(4).setCellValue("Mã lớp học bảo lưu");
		contentRow.createCell(5).setCellValue("Lớp học bảo lưu");
		contentRow.createCell(6).setCellValue("Khóa học bảo lưu");
		contentRow.createCell(7).setCellValue("Ngày bảo lưu");
		contentRow.createCell(8).setCellValue("Thời hạn bảo lưu (tháng)");

		// Lay danh sach bao luu
		List<ReservationDTO> dtos = reservationService.getAll();
		int rowIndex = 3;
		for (ReservationDTO dto : dtos) {
			HSSFRow row = sheet.createRow(rowIndex);
			row.createCell(1).setCellValue(dto.getId());
			row.createCell(2).setCellValue(dto.getStudentDTO().getId());
			row.createCell(3).setCellValue(dto.getStudentDTO().getName());
			row.createCell(4).setCellValue(dto.getClassOpeningDTO().getId());
			row.createCell(5).setCellValue(dto.getClassOpeningDTO().getName());
			row.createCell(6).setCellValue(dto.getClassOpeningDTO().getCourseDTO().getName());
			row.createCell(7).setCellValue(dto.getReservationDate());
			row.createCell(8).setCellValue(dto.getNote());
			rowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
	}

	@GetMapping("export-time-table")
	public void exportTimeTable(HttpServletResponse response) {
		try {
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposit";
			String headerValue = "attachment;filename=TimeTable.xlsx";
			response.setHeader(headerKey, headerValue);

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Time Table");
			HSSFRow titleRow = sheet.createRow(1);
			titleRow.createCell(3).setCellValue("Thời khóa biển của trung tâm");

			// Danh sach cot
			HSSFRow contentRow = sheet.createRow(2);
			titleTimeTable(contentRow);

			// Noi dung chi tiet
			// Lay lich hoc
			List<ClassDetailDTO> classDetailDTOs = classDetailService.getAll();
			getTimeTable(classDetailDTOs, sheet, 3, 1);

			ServletOutputStream ops = response.getOutputStream();
			workbook.write(ops);
			workbook.close();
			ops.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@GetMapping("/export-time-table-of-teacher")
	public void exportTimeTableOfTeacher(HttpServletResponse response,
			@RequestParam(name = "teacherId") int teacherId) {

		try {
			TeacherDTO teacherDTO = teacherService.getByID(teacherId);

			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposit";
			String headerValue = "attachment;filename=TimeTableOfTeacher.xlsx";
			response.setHeader(headerKey, headerValue);

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Time table of teacher");
			HSSFRow titleRow = sheet.createRow(1);
			titleRow.createCell(3).setCellValue("Thời khóa biển của giáo viên " + teacherDTO.getName());
			sheet.createRow(2).createCell(1).setCellValue("Mã giáo viên: " + teacherDTO.getId());
			sheet.createRow(3).createCell(1).setCellValue("Ngày tạo: " + LocalDate.now());

			// Danh sach cot
			HSSFRow contentRow = sheet.createRow(5);
			titleTimeTable(contentRow);

			// Noi dung chi tiet
			// Lay lich hoc
			List<ClassDetailDTO> classDetailDTOs = classDetailService.getAllByTeacherIdAndStatus0(teacherId);
			getTimeTable(classDetailDTOs, sheet, 6, 1);
			
			ServletOutputStream ops = response.getOutputStream();
			workbook.write(ops);
			workbook.close();
			ops.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void titleTimeTable(HSSFRow row) {
		row.createCell(1).setCellValue("Thứ 2");
		row.createCell(2).setCellValue("Thứ 3");
		row.createCell(3).setCellValue("Thứ 4");
		row.createCell(4).setCellValue("Thứ 5");
		row.createCell(5).setCellValue("Thứ 6");
		row.createCell(6).setCellValue("Thứ 7");
	}

	private HSSFSheet getTimeTable(List<ClassDetailDTO> classDetailDTOs, HSSFSheet sheet, int rowNumber,
			int cellIndex) {
		// Noi dung chi tiet
		// Lay lich hoc
		List<ClassDetailDTO> classes = new ArrayList<ClassDetailDTO>();

		if (classDetailDTOs.size() > 0) {
			for (ClassDetailDTO classDetailDTO : classDetailDTOs) {
				if (classDetailDTO.getClassOpeningDTO().getStatus() == 0) {
					classes.add(classDetailDTO);
				}
			}
		}

		// In vao cell
		int rowMondayNumber = rowNumber;
		int rowTuesdayNumber = rowNumber;
		int rowWednesdayNumber = rowNumber;
		int rowThusdayNumber = rowNumber;
		int rowFridayNumber = rowNumber;
		int rowrowSaturdayNumber = rowNumber;

		HSSFRow rowMonday;
		HSSFRow rowTuesday;
		HSSFRow rowWednesday;
		HSSFRow rowThusday;
		HSSFRow rowFriday;
		HSSFRow rowSaturday;

		for (ClassDetailDTO dto : classes) {
			if (dto.getDayOfWeekDTO().getDay() == 2) {
				rowMonday = sheet.createRow(rowMondayNumber);
				rowMonday.createCell(cellIndex).setCellValue(
						dto.getClassOpeningDTO().getName() + " - " + dto.getClassOpeningDTO().getShiftDTO().getName());
				rowMondayNumber++;

			} else if (dto.getDayOfWeekDTO().getDay() == 3) {
				rowTuesday = sheet.getRow(rowTuesdayNumber);
				rowTuesday.createCell(cellIndex + 1).setCellValue(
						dto.getClassOpeningDTO().getName() + " - " + dto.getClassOpeningDTO().getShiftDTO().getName());
				rowTuesdayNumber++;

			} else if (dto.getDayOfWeekDTO().getDay() == 4) {
				rowWednesday = sheet.getRow(rowWednesdayNumber);
				rowWednesday.createCell(cellIndex + 2).setCellValue(
						dto.getClassOpeningDTO().getName() + " - " + dto.getClassOpeningDTO().getShiftDTO().getName());
				rowWednesdayNumber++;

			} else if (dto.getDayOfWeekDTO().getDay() == 5) {
				rowThusday = sheet.getRow(rowThusdayNumber);
				rowThusday.createCell(cellIndex + 3).setCellValue(
						dto.getClassOpeningDTO().getName() + " - " + dto.getClassOpeningDTO().getShiftDTO().getName());
				rowThusdayNumber++;

			} else if (dto.getDayOfWeekDTO().getDay() == 6) {
				rowFriday = sheet.getRow(rowFridayNumber);
				rowFriday.createCell(cellIndex + 4).setCellValue(
						dto.getClassOpeningDTO().getName() + " - " + dto.getClassOpeningDTO().getShiftDTO().getName());
				rowFridayNumber++;

			} else if (dto.getDayOfWeekDTO().getDay() == 7) {
				rowSaturday = sheet.getRow(rowrowSaturdayNumber);
				rowSaturday.createCell(cellIndex + 5).setCellValue(
						dto.getClassOpeningDTO().getName() + " - " + dto.getClassOpeningDTO().getShiftDTO().getName());
				rowrowSaturdayNumber++;
			}
		}

		return sheet;
	}
}
