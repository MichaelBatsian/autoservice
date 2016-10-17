package dao;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import beans.TimeTableBean;

public interface TimeTableDAO {
	
	public void createTimeTable(int employeeId, Date date, Time time, Integer orderId);
	public void updateTime(int timeTableId, Time newTime);
	public void updateDate(int timeTableId, Date date);
	public void updateEmployee(int timeTableId, int employeeId);
	public void updateOrder(int orderId,int timeTableId);
	public void updateOrder(int orderId, int timeTableId, int employeeId);
	public void deleteTimeTable(int timeTableId);
	public ArrayList<TimeTableBean> getTimeTable();
	public ArrayList<TimeTableBean> getTimeTablePeriod(String location, Date from, Date to);
	public ArrayList<TimeTableBean> getTimeTablePeriod(Date from, Date to);
	public ArrayList<TimeTableBean> getEmployeeTimeTablePeriod(String fullname, Date from, Date to);
	
	public ArrayList<TimeTableBean> getTimeTableToEmployee(int employeeId, int orderId,Date date, int manHours);
	public int getTimeTableId(int employeeId, Date date, Time time, int getTimeTableId);
	public TimeTableBean getTimeTableBean(Date date, Time time, int orderId);
	public int getEmployeeIdToTimeTable(int timeTableId);
	
	
	
}
