package com.ef.models;

import java.util.Date;

public class Log {
	
	public static final String COLUMN_NAME_ID = "id";
	public static final String COLUMN_NAME_DATETIME = "date_time";
	public static final String COLUMN_NAME_IP = "ip";
	public static final String COLUMN_NAME_PROTOCOL = "protocol";
	public static final String COLUMN_NAME_STATUS = "status";
	public static final String COLUMN_NAME_DETAIL = "detail";
	
	private int id;
	private Date dateTime;
	private String ip;
	private String protocol;
	private int status;
	private String detail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
