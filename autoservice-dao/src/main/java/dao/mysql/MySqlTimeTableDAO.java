package dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import beans.TimeTableBean;
import dao.TimeTableDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlTimeTableDAO implements TimeTableDAO {

	
	public void createTimeTable(int employeeId, Date date, Time time, Integer orderId) {
		int maxId = UtilsDAO.getMaxTableId("time_table_id", "time_table");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO time_table(time_table_id, employee_id, date, time,order_id) VALUES(?,?,?,?,?)");
			ps.setInt(1, maxId);
			ps.setInt(2, employeeId);
			ps.setDate(3, date);
			ps.setTime(4, time);
			ps.setInt(5, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateTime(int timeTableId, Time newTime) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE time_table SET time = ? WHERE time_table_id = ?");
			ps.setTime(1, newTime);
			ps.setInt(2, timeTableId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateDate(int timeTableId, Date date) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE time_table SET date = ? WHERE time_table_id = ?");
			ps.setDate(1, date);
			ps.setInt(2, timeTableId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateEmployee(int timeTableId, int employeeId) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE time_table SET employee_id = ? WHERE time_table_id = ?");
			ps.setInt(1, employeeId);
			ps.setInt(2, timeTableId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public void updateOrder(int orderId,int timeTableId){
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE time_table SET order_id = ? WHERE time_table_id = ?");
			ps.setInt(1, orderId);
			ps.setInt(2, timeTableId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public void updateOrder(int orderId, int timeTableId, int employeeId) {
		Connection cn = null;
		PreparedStatement ps = null;
		System.out.println("orderId in DAO "+orderId);
		System.out.println("timeTableId in DAO "+timeTableId);
		System.out.println("employeeId in DAO "+employeeId);
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE time_table SET order_id = ? WHERE time_table_id = ? AND employee_id=?");
			ps.setInt(1, orderId);
			ps.setInt(2, timeTableId);
			ps.setInt(3, employeeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	
	public void deleteTimeTable(int timeTableId) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM time_table WHERE time_table_id=?");
			ps.setInt(1, timeTableId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<TimeTableBean> getTimeTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TimeTableBean bean;
		ArrayList<TimeTableBean> timeTable = new ArrayList<TimeTableBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT time_table.time_table_id,time_table.employee_id, usersdata.fullname, time_table.date,"
					+ "time_table.time,time_table.order_id FROM time_table, employee_list, usersdata "
					+ "WHERE time_table.employee_id=employee_list.employee_id AND employee_list.fk_userdata_id=usersdata.userdata_id "
					+ "ORDER BY time_table.date DESC, time_table.time");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.creteTimeTableBean(rs);
				timeTable.add(bean);
			}
			return timeTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public ArrayList<TimeTableBean> getTimeTablePeriod(String location, Date from, Date to) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TimeTableBean bean;
		ArrayList<TimeTableBean> timeTable = new ArrayList<TimeTableBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT time_table.employee_id, service_station.location, usersdata.fullname, "
					+ "employee_position.position, time_table.date, time_table.time, time_table.order_id FROM time_table, usersdata, "
					+ "employee_position,employee_list, service_station WHERE time_table.employee_id=employee_list.employee_id "
					+ "AND employee_list.fk_userdata_id=usersdata.userdata_id AND employee_list.fk_position_id=employee_position.position_id "
					+ "AND employee_list.fk_station_id = service_station.station_id AND service_station.location=? AND date "
					+ "BETWEEN ? AND ? order by date,time");
			ps.setString(1, location);		
			ps.setDate(2, from);
			ps.setDate(3, to);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createFullTimeTableBean(rs);
				timeTable.add(bean);
			}
			return timeTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public ArrayList<TimeTableBean> getTimeTablePeriod(Date from, Date to) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TimeTableBean bean;
		ArrayList<TimeTableBean> timeTable = new ArrayList<TimeTableBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT time_table.employee_id, service_station.location, usersdata.fullname, "
					+ "employee_position.position, time_table.date, time_table.time, time_table.order_id FROM time_table, usersdata, "
					+ "employee_position,employee_list, service_station WHERE time_table.employee_id=employee_list.employee_id "
					+ "AND employee_list.fk_userdata_id=usersdata.userdata_id AND employee_list.fk_position_id=employee_position.position_id "
					+ "AND employee_list.fk_station_id = service_station.station_id AND date "
					+ "BETWEEN ? AND ? order by date,time");
			ps.setDate(1, from);
			ps.setDate(2, to);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createFullTimeTableBean(rs);
				timeTable.add(bean);
			}
			return timeTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public ArrayList<TimeTableBean> getEmployeeTimeTablePeriod(String fullname, Date from, Date to) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TimeTableBean bean;
		ArrayList<TimeTableBean> timeTable = new ArrayList<TimeTableBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT time_table.employee_id, service_station.location, usersdata.fullname, "
					+ "employee_position.position, time_table.date, time_table.time, time_table.order_id FROM time_table, usersdata, "
					+ "employee_position,employee_list, service_station WHERE time_table.employee_id=employee_list.employee_id "
					+ "AND employee_list.fk_userdata_id=usersdata.userdata_id AND employee_list.fk_position_id=employee_position.position_id "
					+ "AND employee_list.fk_station_id = service_station.station_id AND usersdata.fullname=? AND date "
					+ "BETWEEN ? AND ? order by date DESC,time");
			ps.setString(1, fullname);		
			ps.setDate(2, from);
			ps.setDate(3, to);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createFullTimeTableBean(rs);
				timeTable.add(bean);
			}
			return timeTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public ArrayList<TimeTableBean> getTimeTableToEmployee(int employeeId, int orderId, Date date, int manHoursToService) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TimeTableBean bean;
		int mH=0;
		int arraySize=-1;
		Integer requaredElement=0;
		ArrayList<Integer> requaredElements = new ArrayList<Integer>();
		
		ArrayList<TimeTableBean> timeTable = new ArrayList<TimeTableBean>();
		ArrayList<TimeTableBean> requaredTimeTable = new ArrayList<TimeTableBean>();
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT time_table.time_table_id, time_table.employee_id, usersdata.fullname, time_table.date,time_table.time, "
					+ "time_table.order_id FROM time_table, usersdata, employee_list WHERE time_table.employee_id=employee_list.employee_id "
					+ "AND employee_list.fk_userdata_id=usersdata.userdata_id AND employee_list.employee_id=? AND time_table.order_id=? "
					+ "AND time_table.date=?");
			ps.setInt(1, employeeId);
			ps.setInt(2, orderId);
			ps.setDate(3, date);
			rs = ps.executeQuery();
			 
			while(rs.next()){
				arraySize++;
				if (rs.getInt("order_id")==1){
					mH++;
				}
				if(rs.getInt("order_id")!=1){
					mH=0;
				}
				if(mH==manHoursToService){
					requaredElement=arraySize-manHoursToService+1;
					requaredElements.add(requaredElement);
					mH--;
				}
				bean = ResultSetConverter.creteTimeTableBean(rs);
				timeTable.add(bean);
				
			}
			for(int i=0;i<requaredElements.size();i++){
				
				requaredElement = requaredElements.get(i);
				requaredTimeTable.add(timeTable.get(requaredElement));
				
			}
		
			return requaredTimeTable;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public int getTimeTableId(int employeeId, Date date, Time time, int orderId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int time_table_id = 0;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT time_table_id FROM time_table WHERE employee_id=?, date=?, time=?, order_id=?");
			ps.setInt(1, employeeId);
			ps.setDate(2, date);
			ps.setTime(3, time);
			ps.setInt(4, orderId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				rs.getInt("time_table_id");					
			}
			return time_table_id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public TimeTableBean getTimeTableBean(Date date, Time time, int orderId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TimeTableBean bean= new TimeTableBean();
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM time_table WHERE date=? AND time=? AND order_id=?");
			ps.setDate(1, date);
			ps.setTime(2, time);
			ps.setInt(3, orderId);
			rs = ps.executeQuery();
			if(rs.next()){
				bean.setDate(rs.getDate("date"));
				bean.setTime(rs.getTime("time"));
			}
			return bean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public int getEmployeeIdToTimeTable(int timeTableId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int employeeId = 0;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT employee_id FROM time_table WHERE time_table_id=?");
			ps.setInt(1, timeTableId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				employeeId=rs.getInt("employee_id");					
			}
			return employeeId;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	

	

}
