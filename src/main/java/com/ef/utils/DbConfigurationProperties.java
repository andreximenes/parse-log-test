package com.ef.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DbConfigurationProperties {
	private static final String FILE_PATH = System.getProperty("user.dir") + File.separator + "dbConfiguration.properties";
	public static String IP_MYSQL_SERVER;
	public static String MYSQL_PORT;
	public static String DATABASE_NAME;
	
	public static Properties getProp() {
		
		Properties props = new Properties();
		try {
			FileInputStream file;
			file = new FileInputStream(FILE_PATH);
			props.load(file);
		} catch (FileNotFoundException e) {
			LoggerUtil.error("DbConfigurationProperties :: getProp -> Load database configuration failed. " + e.getMessage());
		} catch (IOException e) {
			LoggerUtil.error("DbConfigurationProperties :: getProp -> Load database configuration failed. " + e.getMessage());
		}
		return props;
	}
}
