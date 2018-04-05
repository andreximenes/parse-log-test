package com.ef.models;

import java.util.Date;

public class IpBlocked {
	
	public static final String COLUMN_NAME_ID = "id";
	public static final String COLUMN_NAME_IP = "ip";
	public static final String COLUMN_NAME_DATE_TIME_REQUEST = "date_time_request";
	public static final String COLUMN_NAME_INFO = "info";
	public static final String COLUMN_NAME_BLOCK_DATE = "block_date";

	private int id;
	private String ip;
	private Date dateTimeRequest;
	private String info;
	private Date block_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public Date getDateTimeRequest() {
		return dateTimeRequest;
	}
	public void setDateTimeRequest(Date dateTimeRequest) {
		this.dateTimeRequest = dateTimeRequest;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getBlock_date() {
		return block_date;
	}
	public void setBlock_date(Date block_date) {
		this.block_date = block_date;
	}
}
