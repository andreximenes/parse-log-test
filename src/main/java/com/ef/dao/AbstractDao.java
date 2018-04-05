package com.ef.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.ef.utils.DbConfigurationProperties;
import com.ef.utils.LoggerUtil;

public class AbstractDao {
	static Connection conn = null;

	private static void makeJDBCConnection() {
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LoggerUtil.error("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			e.printStackTrace();
			return;
		}
 
		try {
			Properties prop	= DbConfigurationProperties.getProp();
			String ip_mysql_server = prop.getProperty("ip_mysql_server");
			String mysql_port = prop.getProperty("mysql_port");
			String databse_name = prop.getProperty("database_name");
			String db_user = prop.getProperty("db_user");
			String user_password = prop.getProperty("user_password");
			
			conn = DriverManager.getConnection("jdbc:mysql://"+ip_mysql_server+":"+mysql_port+"/"+databse_name, db_user, user_password);
			if (conn == null) {
				LoggerUtil.error("Failed to make connection!");
			}
		} catch (SQLException e) {
			LoggerUtil.error("MySQL Connection Failed!");
			return;
		}
 
	}
	
	protected static Connection getConnection() {
		if (conn == null) {
			makeJDBCConnection();
		}
		return conn;
	}

	public static void closeConnection() {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void closeStatement(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
