package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.EmployeePositionBean;
import dao.EmployeePositionDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlEmployeePositionDAO implements EmployeePositionDAO {

	
	public void createPosition(String position) {
		int maxUserId = UtilsDAO.getMaxTableId("position_id", "employee_position");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO employee_position(position_id, position) VALUES(?,?)");
			ps.setInt(1, maxUserId);
			ps.setString(2, position);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updatePosition(int positionId, String position) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE employee_position SET position= ? WHERE position_id = ?");
			ps.setString(1, position);
			ps.setInt(2, positionId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	public void deletePosition(int positionId) {

		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM employee_position WHERE position_id=?");
			ps.setInt(1, positionId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

public ArrayList<EmployeePositionBean> getPositionTable(){
		
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		EmployeePositionBean bean;
		ArrayList<EmployeePositionBean> employeePositionBeans = new ArrayList<EmployeePositionBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM employee_position");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createEmployeePositionBean(rs);
				employeePositionBeans.add(bean);
			}
			return employeePositionBeans;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
			}
	}

}
