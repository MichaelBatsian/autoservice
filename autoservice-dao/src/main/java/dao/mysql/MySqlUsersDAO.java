package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.UserBean;
import dao.UsersDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlUsersDAO implements UsersDAO {

	public MySqlUsersDAO() {

	}

	public UserBean getUser(int userId) {

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT login, password, role_id, blocked_status FROM users WHERE user_id=?");
			ps.setInt(1,userId);
			rs = ps.executeQuery();
			UserBean bean = ResultSetConverter.createUserBean(rs);
			return bean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public UserBean getUser(String login) {

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM users WHERE login=?");
			ps.setString(1,login);
			rs = ps.executeQuery();
			if(rs.next()){
			UserBean bean = ResultSetConverter.createUserBean(rs);
			return bean;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		return null;
	}
	
	public ArrayList<UserBean> getUserTable(){
		
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserBean bean;
		ArrayList<UserBean> userTable = new ArrayList<UserBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM users");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createUserBean(rs);
				userTable.add(bean);
			}
			return userTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
			}
	}
	public ArrayList<UserBean> getUserNameTable(){
			
			Connection cn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			UserBean bean;
			ArrayList<UserBean> userTable = new ArrayList<UserBean>();
			
			try {
				cn = ConnectionPool.getConnection();
				ps = cn.prepareStatement("SELECT user_id, login, password, role, blocked_status FROM users,roles "
						+ "WHERE users.role_id=roles.role_id");
				rs = ps.executeQuery();
				
				while(rs.next()){
					bean = ResultSetConverter.createUserNameBean(rs);
					userTable.add(bean);
				}
				return userTable;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				ConnectionPool.closeConnection(cn);
				}
		}
	
	public void updateLogin(int userId, String newLogin) {

		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE users SET login = ? WHERE user_id = ?");
			ps.setString(1, newLogin);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public void updateRole(int userId, int newRoleId) {

		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE users SET role_id = ? WHERE user_id = ?");
			ps.setInt(1, newRoleId);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	public void updatePassword(int userId, String newPassword) {

		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE users SET password = ? WHERE user_id = ?");
			ps.setString(1, newPassword);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public void updateStatus(int userId, String status) {

		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE users SET blocked_status = ? WHERE user_id = ?");
			ps.setString(1, status);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	public void createUser(String login, String password, int roleId) {

		int maxUserId = UtilsDAO.getMaxTableId("user_id", "users");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO users(user_id, login, password, role_id) VALUES(?,?,?,?)");
			ps.setInt(1, maxUserId);
			ps.setString(2, login);
			ps.setString(3, password);
			ps.setInt(4, roleId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	public int getMaxUserId() {

		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int maxId = 0;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT user_id FROM users;");
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.last() == true) {
					maxId = rs.getInt(1);
				}
			}
			return maxId;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	public void deleteUser(int userId) {

		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM users WHERE user_id=?");
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	public boolean isUser(String login, String password) {

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM users WHERE login=?");
			ps.setString(1, login);
			rs = ps.executeQuery();
			if (rs.next()) {
				String passwordDb = rs.getString("password");
				if (passwordDb.equals(password)) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	public String getBlockedStatus(int userId){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String blockedStaus=null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT blocked_status FROM users WHERE user_id=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if(rs.next()){
			blockedStaus = rs.getString("blocked_status");
			}
			return blockedStaus;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	public String getRole(int userId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String role=null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT role FROM users, roles WHERE users.role_id=roles.role_id and users.user_id=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if(rs.next()){
			role = rs.getString("role");
			}
			return role;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}

	}

}
