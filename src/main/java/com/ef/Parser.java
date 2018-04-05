package com.ef;

import java.sql.SQLException;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.ef.dao.ParseDao;
import com.ef.models.IpBlocked;
import com.ef.models.UsageParams;
import com.ef.utils.LoggerUtil;
import com.ef.utils.ValidadeParams;

public class Parser {
	public static void main(String[] args) {
		UsageParams passedParams = new UsageParams();

		JCommander jc = JCommander.newBuilder()
				.addObject(passedParams)
				.programName("Parse")
				.build();

		try {
			jc.parse(args);
			if(passedParams.isHelp()) {
				jc.usage();
			} else {
				long startTime = System.currentTimeMillis();
				ValidadeParams.validate(passedParams);
				LoggerUtil.info("Starting...");
				parseLogFile(passedParams);
				long endTime = System.currentTimeMillis();
				LoggerUtil.info("Execution time: "+ (endTime - startTime) + " milliseconds.");
			}
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		} finally {
			System.exit(0);
		}
	}
	
	private static void parseLogFile(UsageParams passedParams) {
		ParseDao dao = new ParseDao();
		try {
			if(passedParams.getAccessLog() != null) {
				LoggerUtil.info("Cleaning tables for import the new accessLog...");
				dao.deleteAllipBlocked();
				dao.deleteAllLog();
				LoggerUtil.info("Clean finished!");
				LoggerUtil.info("...");
				LoggerUtil.info("Loading accessLog file into the table...");
				dao.loadLogFileInDatabase(passedParams.getAccessLog());
				LoggerUtil.info("Parsing data...");
			}
			
			dao.findAndMarkIpsAsBlocked(passedParams);
			LoggerUtil.info("IP's blocked:");
			List<IpBlocked> ipList = dao.getIpsBlocked();
			for(IpBlocked ipBlocked : ipList) {
				LoggerUtil.print(ipBlocked.getIp());
			}
		} catch (SQLException e) {
			LoggerUtil.error("error during parse log file. Exception!");
			e.printStackTrace();
		} finally {
			ParseDao.closeConnection();
		}
	}
}
