package dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.UserdataBean;
import dao.UserdataDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlUserdataDAO implements UserdataDAO{

	public MySqlUserdataDAO() {
		
	}

	
	public void createUserdata(int dataUserId, String fullname, Date birthday, String adress,String phone, String gender) {
		
		int maxUserId = UtilsDAO.getMaxTableId("userdata_id", "usersdata");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO usersdata(userdata_id, data_user_id, fullname, birthday,adress,phone,gender) "
					+ "VALUES(?,?,?,?,?,?,?)");
			ps.setInt(1, maxUserId);
			ps.setInt(2, dataUserId);
			ps.setString(3, fullname);
			ps.setDate(4, birthday);
			ps.setString(5, adress);
			ps.setString(6, phone);
			ps.setString(7, gender);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void updateUserdataFullname(int userData, String fullname) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE usersdata SET fullname = ? WHERE userdata_id = ?");
			ps.setString(1, fullname);
			ps.setInt(2, userData);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateUserdataAdress(int userData, String adress) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE usersdata SET adress = ? WHERE userdata_id = ?");
			ps.setString(1, adress);
			ps.setInt(2, userData);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateUserdataBirthday(int userData, Date birthday) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE usersdata SET birthday = ? WHERE userdata_id = ?");
			ps.setDate(1, birthday);
			ps.setInt(2, userData);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateUserdataGender(int userDataId, String gender) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE usersdata SET gender = ? WHERE userdata_id = ?");
			ps.setString(1, gender);
			ps.setInt(2, userDataId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void deleteUserdata(int userdataId) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM usersdata WHERE userdata_id=?");
			ps.setInt(1, userdataId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<UserdataBean> getUserdataNameTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserdataBean bean;
		ArrayList<UserdataBean> table = new ArrayList<UserdataBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT usersdata.userdata_id, users.login, usersdata.fullname, usersdata.birthday, usersdata.adress, "
					+ "usersdata.phone, usersdata.gender FROM usersdata, users WHERE usersdata.data_user_id=users.user_id");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createUserdataNameBean(rs);
				table.add(bean);
			}
			return table;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
			}
	}

	
	public void updateDataUserId(int userdataId, int dataUserId) {
		System.out.println("dataUserId в методе  "+dataUserId);
		
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE usersdata SET data_user_id = ? WHERE userdata_id = ?");
			ps.setInt(1, dataUserId);
			ps.setInt(2, userdataId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateUserdataPhone(int userdataId, String phone) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE usersdata SET phone = ? WHERE userdata_id = ?");
			ps.setString(1, phone);
			ps.setInt(2, userdataId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public String getUserName(String login) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String fullname = null;
		
		try {
			cn=ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT usersdata.fullname FROM usersdata,users WHERE usersdata.data_user_id = users.user_id"
					+ " AND users.login = ?");
			ps.setString(1,login);
			rs = ps.executeQuery();
			if(rs.next()){
				fullname=rs.getString(1);
			}
			return fullname;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	
}
