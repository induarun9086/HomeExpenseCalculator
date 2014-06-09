package com.example.myfirstandroidapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.widget.DatePicker;

public class DateUtil {
	
	public static final String date_format = "yyyy/MM/dd";
	public static Date getDateFromDatePicker(DatePicker datePicker){
	    int day = datePicker.getDayOfMonth();
	    int month = datePicker.getMonth();
	    int year =  datePicker.getYear();

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(year, month, day);

	    return calendar.getTime();
	}
	
	public static Long getDateAsMilliSec(String dateStr) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);
		Date date = sdf.parse(dateStr);
		return date.getTime();
	}
}
