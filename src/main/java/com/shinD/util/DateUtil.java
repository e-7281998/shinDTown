package com.shinD.util;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	public static Date convert_date(String dd) {
		SimpleDateFormat simdate = new SimpleDateFormat("yyyy-MM-dd");
		Date sqlDate = null;
		
		try {
			java.util.Date da = simdate.parse(dd);
			sqlDate = new Date(da.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlDate;
	}
	

}
