package com.ef.utils;

import com.ef.models.UsageParams;

public class ValidadeParams {
	
	public static void validate(UsageParams args) throws Exception {
		validateStarDate(args.getStartDate());
		validateDuration(args.getDuration());
		validateThreshold(args.getThreshold());
	}
	
	private static void validateStarDate(final String dateString) throws Exception {
		try {
			ParseUtils.convertStringToDate(dateString);
		}catch(Exception e) {
			throw new Exception("invalid date in parameter \"startDate\". tray again passing date with format: yyyy-MM-dd.hh:mm:ss");
		}
	}
	
	private static void validateDuration(final String duration) throws Exception {
		if(duration == null || (!duration.equals("daily") && !duration.equals("hourly"))) {
			throw new Exception("invalid value in parameter \"duration\".The parameter value must be daily or hourly");
		}
	}
	
	private static void validateThreshold(final Integer threshold) throws Exception {
		if(threshold <= 0) {
			throw new Exception("invalid value in parameter \"threshold\".The parameter value must be greater than zero");
		}
	}
}
