package com.example.TrungTamTA.Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateUtils {
	public static String convertDateToDateOfVN(LocalDate localDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(Date.valueOf(String.valueOf(localDate)));
	}
	
	public static String convertDateToDateOfVN(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(Date.valueOf(String.valueOf(date)));
	}
}
