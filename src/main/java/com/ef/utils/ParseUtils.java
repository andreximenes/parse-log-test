package com.ef.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseUtils {
	
	public static Date convertStringToDate(String dateString) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
		try {
		    return format.parse(dateString);
		} catch (ParseException e) {
		    throw new Exception("invalid date in parameter \"startDate\". tray again passing date with format: yyyy-MM-dd.hh:mm:ss");
		}
	}
	
	public static String convertDateToString(Date date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			return format.format(date);
		}catch(Exception e) {
			LoggerUtil.error("Error to convert Date in String. " + e);
		}
		return null;
	}

}
