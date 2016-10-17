package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.RoleBean;
import dao.RolesDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;


public class MySqlRolesDAO implements RolesDAO {

	public MySqlRolesDAO() {
		
	}
	
	public RoleBean getUser(int roleId) {

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT role_id, role FROM roles WHERE role_id=?");
			ps.setInt(1, roleId);
			rs = ps.executeQuery();
			RoleBean bean = ResultSetConverter.createRoleBean(rs);
			return bean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	

	public void updateRole(int roleId, String newRole) {

		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE roles SET role = ? WHERE role_id = ?");
			ps.setString(1, newRole);
			ps.setInt(2, roleId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	public void createRole(String role) {
		
		int maxRoleId = UtilsDAO.getMaxTableId("role_id","roles");
		System.out.println("Role id po itogu  "+maxRoleId);
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO roles(role_id, role) VALUES(?,?)");
			ps.setInt(1,maxRoleId);
			ps.setString(2,role);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionPool.closeConnection(cn);
		}
	}

	

	public void deleteRole(int roleId) {

		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM roles WHERE role_id=?");
			ps.setInt(1, roleId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public ArrayList<RoleBean> getRolesTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RoleBean bean;
		ArrayList<RoleBean> rolesTable = new ArrayList<RoleBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM roles");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createRoleBean(rs);
				rolesTable.add(bean);
			}
			return rolesTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}


}
