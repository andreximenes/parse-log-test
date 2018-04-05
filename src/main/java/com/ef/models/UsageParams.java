package com.ef.models;

import com.beust.jcommander.Parameter;

public class UsageParams {
	
	public static final String DURATION_HOURLY = "hourly";
	public static final String DURATION_DAILY = "daily";


	@Parameter(names={"--startDate", "--startDate=", "--startdate","--startdate=", "-sd", "-sd="}, description = "Start Date used to find log register", required= true)
	private String startDate;

	@Parameter(names= {"--duration", "--duration=", "-d", "-d="}, description="durtion can be hourly or daily", required= true)
	private String duration;

	@Parameter(names= {"--threshold", "--threshold=", "-th", "-th="}, description="numbers of requests", required= true)
	private Integer threshold;
	
	@Parameter(names = {"--accessLog", "--accessLog=", "--accesslog", "--accesslog=", "-al"}, description="log file path")
	private String accessLog;

	@Parameter(names = {"--help", "-h"}, help = true, description="show program usage")
	private boolean help = false;
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public String getAccessLog() {
		return accessLog;
	}

	public void setAccessLog(String accessLog) {
		this.accessLog = accessLog;
	}

	public boolean isHelp() {
		return help;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}
}
