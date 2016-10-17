package dao;

import java.util.ArrayList;

import beans.EmployeeListBean;

public interface EmployeeListDAO {
	
	public ArrayList<EmployeeListBean> getEmployeeListTable();
	public ArrayList<EmployeeListBean> getEmployeeListNameTable();
	public void updateEmployeeName(int employeeId, int userdataId);
	public void updateEmployeePosition(int employeeId, int newPosition);
	public void updateEmployeeStation(int employeeId, int newStationId);
	public void createEmployee(int userdataId, int positionId, int userId, int stationId);
	public void deleteEmployee(int employeeId);
		
	public ArrayList<Integer> getEmployeesIdToPosition(int positionId, int stationId);
	

}
