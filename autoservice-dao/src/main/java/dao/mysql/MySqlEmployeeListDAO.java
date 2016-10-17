package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.EmployeeListBean;
import dao.EmployeeListDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlEmployeeListDAO implements EmployeeListDAO {

	
	public ArrayList<EmployeeListBean> getEmployeeListTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		EmployeeListBean bean;
		ArrayList<EmployeeListBean> employeeListTable = new ArrayList<EmployeeListBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM employee_list");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createEmployeeListBean(rs);
				employeeListTable.add(bean);
			}
			return employeeListTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void updateEmployeeName(int employeeId, int userdataId) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE employee_list SET fk_userdata_id = ? WHERE employee_id = ?");
			ps.setInt(1, userdataId);
			ps.setInt(2, employeeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateEmployeePosition(int employeeId, int newPosition) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE employee_list SET fk_position_id = ? WHERE employee_id = ?");
			ps.setInt(1, newPosition);
			ps.setInt(2, employeeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateEmployeeStation(int employeeId, int newStationId) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE employee_list SET fk_station_id = ? WHERE employee_id = ?");
			ps.setInt(1, newStationId);
			ps.setInt(2, employeeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void createEmployee(int userdataId, int positionId, int userId, int stationId) {
		int maxId = UtilsDAO.getMaxTableId("employee_id", "employee_list");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO employee_list(employee_id, fk_userdata_id, fk_position_id, fk_station_id) VALUES(?,?,?,?)");
			ps.setInt(1, maxId);
			ps.setInt(2, userdataId);
			ps.setInt(3, positionId);
			ps.setInt(4, stationId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void deleteEmployee(int employeeId) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM employee_list WHERE employee_id=?");
			ps.setInt(1, employeeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<EmployeeListBean> getEmployeeListNameTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		EmployeeListBean bean;
		ArrayList<EmployeeListBean> employeeListTable = new ArrayList<EmployeeListBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT employee_list.employee_id,users.login, usersdata.fullname, employee_position.position, "
					+ "service_station.station_name, service_station.location FROM employee_list,users,usersdata,service_station, "
					+ "employee_position WHERE employee_list.fk_station_id=service_station.station_id "
					+ "AND employee_list.fk_userdata_id = usersdata.userdata_id "
					+ "AND employee_list.fk_position_id =employee_position.position_id AND usersdata.data_user_id = users.user_id;");
			rs = ps.executeQuery();
			while(rs.next()){
				bean = ResultSetConverter.createEmployeeListNameBean(rs);
				employeeListTable.add(bean);
			}
			return employeeListTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public ArrayList<Integer> getEmployeesIdToPosition(int positionId,int stationId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> employeeIdList = new ArrayList<Integer>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT employee_list.employee_id FROM employee_list WHERE fk_position_id=? AND fk_station_id=?");
			ps.setInt(1, positionId);
			ps.setInt(2, stationId);
			rs = ps.executeQuery();
			while(rs.next()){
				employeeIdList.add(rs.getInt("employee_id"));
			}
			return employeeIdList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

}
