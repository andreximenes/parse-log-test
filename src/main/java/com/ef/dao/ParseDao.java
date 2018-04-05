package com.ef.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ef.models.IpBlocked;
import com.ef.models.Log;
import com.ef.models.UsageParams;
import com.ef.utils.LoggerUtil;
import com.ef.utils.ParseUtils;

public class ParseDao extends AbstractDao {

	public void loadLogFileInDatabase(String filePath) throws SQLException {
		PreparedStatement ps = null;
		String sql = "LOAD DATA INFILE '" + filePath + "' INTO TABLE log " 
				+ "COLUMNS TERMINATED BY '|' LINES TERMINATED BY '\\r\\n'  " 
				+ "(@col1, @col2, @col3, @col4, @col5) " 
				+ "set " 
				+ "id = null, "  
				+ "date_time = @col1, " 
				+ "ip = @col2, " 
				+ "protocol = REPLACE(TRIM(@col3), '\"', '') , " 
				+ "status = @col4, " 
				+ "detail = TRIM(REPLACE(@col5, '\"', ''))";
		try {
			ps = getConnection().prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			LoggerUtil.error("ParseDao::loadLogFileInDatabase -> SQLException:" + e.getMessage());
			throw e;
		} finally {
			closeStatement(ps);
		}
	}
	
	
	/**
	 * Select ip's that will be marked as blocked in table ip_blocked
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<IpBlocked> getIpsBlocked() throws SQLException {
		List<IpBlocked> ipList = new ArrayList<IpBlocked>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT id, ip, date_time_request, info, block_date FROM ip_blocked";
		
		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ipList.add(getIpBlockedObject(rs));
			}
		} catch (SQLException e) {
			LoggerUtil.error("ParseDao::getIpsBlocked -> SQLException:" + e.getMessage());
			throw e;
		} finally {
			closeStatement(ps);
		}
		return ipList;
	}
	
	/**
	 * Find and insert ip in table ip_blocked
	 * @param ipList
	 * @param params
	 * @throws SQLException
	 */
	public void findAndMarkIpsAsBlocked(UsageParams params) throws SQLException {
		PreparedStatement ps = null;
		String blockCause = getBlockCauseMsg(params);
		String duration = params.getDuration().equalsIgnoreCase(UsageParams.DURATION_DAILY) ? "DAY" : "HOUR";
		
		String sql = "INSERT INTO ip_blocked (ip, date_time_request, info, block_date) " + 
				"SELECT ip, date_time, ?, CURRENT_TIMESTAMP FROM log " + 
				"WHERE date_time BETWEEN ? AND (? + INTERVAL 1 "+duration+") " + 
				"GROUP BY ip HAVING COUNT(*) > ?";
		try {
			Date startDate = ParseUtils.convertStringToDate(params.getStartDate());
			
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, blockCause);
			ps.setTimestamp(2, new Timestamp(startDate.getTime()));
			ps.setTimestamp(3, new Timestamp(startDate.getTime()));
			ps.setInt(4, params.getThreshold());
			ps.executeUpdate();
		} catch (SQLException e) {
			LoggerUtil.error("ParseDao::findAndMarkIpsAsBlocked -> SQLException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			LoggerUtil.error("ParseDao::findAndMarkIpsAsBlocked -> Exception:" + e.getMessage());
		} finally {
			closeStatement(ps);
		}
	}
	
	public void deleteAllipBlocked() throws SQLException {
		PreparedStatement ps = null;
		String sql = "DELETE FROM ip_blocked";
		try {
			ps = getConnection().prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			LoggerUtil.error("ParseDao::deleteAllipBlocked -> SQLException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			LoggerUtil.error("ParseDao::deleteAllipBlocked -> Exception:" + e.getMessage());
		} finally {
			closeStatement(ps);
		}
	}
	
	public void deleteAllLog() throws SQLException {
		PreparedStatement ps = null;
		String sql = "DELETE FROM log";
		try {
			ps = getConnection().prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			LoggerUtil.error("ParseDao::deleteAllLog -> SQLException:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			LoggerUtil.error("ParseDao::deleteAllLog -> Exception:" + e.getMessage());
		} finally {
			closeStatement(ps);
		}
	}
	
	
	public List<Log> getAllLogs() throws SQLException {
		List<Log> logList = new ArrayList<Log>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT ID, DATE_TIME, IP, PROTOCOL, STATUS, DETAIL FROM LOG";
		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				logList.add(getLogObject(rs));
			}
		} catch (SQLException e) {
			LoggerUtil.error("ParseDao::getAllLogs -> SQLException:" + e.getMessage());
			throw e;
		} finally {
			closeStatement(ps);
		}
		return logList;
	}
	
	private Log getLogObject (ResultSet rs) throws SQLException {
		Log log = new Log();
		try {
			log.setId(rs.getInt(Log.COLUMN_NAME_ID));
			log.setDateTime(rs.getTimestamp(Log.COLUMN_NAME_DATETIME));
			log.setIp(rs.getString(Log.COLUMN_NAME_IP));
			log.setProtocol(rs.getString(Log.COLUMN_NAME_PROTOCOL));
			log.setStatus(rs.getInt(Log.COLUMN_NAME_STATUS));
			log.setDetail(rs.getString(Log.COLUMN_NAME_DETAIL));
		} catch (SQLException e) {
			LoggerUtil.error("ParseDao::getLogObject -> SQLException:" + e.getMessage());
			throw e;
		}
		return log;
	}
	
	private IpBlocked getIpBlockedObject (ResultSet rs) throws SQLException {
		IpBlocked ipBlocked = new IpBlocked();
		try {
			ipBlocked.setId(rs.getInt(IpBlocked.COLUMN_NAME_ID));
			ipBlocked.setIp(rs.getString(IpBlocked.COLUMN_NAME_IP));
			ipBlocked.setDateTimeRequest(rs.getTimestamp(IpBlocked.COLUMN_NAME_DATE_TIME_REQUEST));
			ipBlocked.setInfo(rs.getString(IpBlocked.COLUMN_NAME_INFO));
			ipBlocked.setBlock_date(rs.getTimestamp(IpBlocked.COLUMN_NAME_BLOCK_DATE));
		} catch (SQLException e) {
			LoggerUtil.error("ParseDao::getIpBlockedObject -> SQLException:" + e.getMessage());
			throw e;
		}
		return ipBlocked;
	}
	
	private String getBlockCauseMsg(UsageParams usageParams) {
		return "block cause: threshold > "+ usageParams.getThreshold()
		+ ", start date: "+usageParams.getStartDate()
		+ ", duration: " + usageParams.getDuration();
	}
}
