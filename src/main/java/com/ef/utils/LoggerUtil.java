package com.ef.utils;

import java.util.Date;

import com.ef.models.IpBlocked;
import com.ef.models.Log;

public class LoggerUtil {
	
	enum typeLog{
		ERROR,
		INFO
	}

	public static void error(String txt) {
		System.out.println(ParseUtils.convertDateToString(new Date()) +" - " + "["+typeLog.ERROR+"]" + " - "+  txt);
	}
	
	public static void info(String txt) {
		System.out.println(ParseUtils.convertDateToString(new Date()) +" - " + "["+typeLog.INFO+"]" + " - "+  txt);
	}
	
	public static void printLog(Log log) {
		String text = ParseUtils.convertDateToString(log.getDateTime()) +"|"+
		log.getIp() +"|"+ log.getProtocol() +"|"+ log.getStatus() +"|"+ log.getDetail();
		System.out.println(text);
	}
	
	public static void printIpBlocked(IpBlocked ipBlocked) {
		String text = "|"+ipBlocked.getIp() 
				+"|\""+ ipBlocked.getInfo() +"\"";
		System.out.println(text);
	}
	
	public static void print(String txt) {
		System.out.println(txt);
	}
	
}
