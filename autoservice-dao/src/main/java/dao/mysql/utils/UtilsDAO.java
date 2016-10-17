package dao.mysql.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.mysql.db.ConnectionPool;

public class UtilsDAO {
	
	public static int getMaxTableId(String tableId,String table) {

		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int maxId = 0;
		int nextId=0;
		
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT max("+tableId+") FROM "+ table);
			rs = ps.executeQuery();
			if (rs.next()) {
					maxId = rs.getInt(1);
					nextId = maxId+1;
				}
			return nextId;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public static int getMaxId(String tableId,String table) {

		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int maxId = 0;
			
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT max("+tableId+") FROM "+ table);
			rs = ps.executeQuery();
			if (rs.next()) {
					maxId = rs.getInt(1);
				}
			return maxId;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public static int getId(String table, String columnName, String param){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id=0;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM "+table+" WHERE "+columnName+" = ?");
			ps.setString(1, param);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	public static int getId(String table,String SearchColumn,String columnNalme, int param){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id=0;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT ? FROM ? WHERE ? = ?");
			ps.setString(1, SearchColumn);
			ps.setString(2,table);
			ps.setString(3, columnNalme);
			ps.setInt(4, param);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public static int getId(String table,String SearchColumn,String columnNalme, float param){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id=0;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT "+SearchColumn+" FROM "+table+" WHERE "+columnNalme+" = ?");
			ps.setFloat(1, param);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	public static int getId(String table, String columnName, int param) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id=0;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM "+table+" WHERE "+columnName+" = ?");
			ps.setInt(1, param);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	public static int getId(String table, String columnName, float param) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id=0;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM "+table+" WHERE "+columnName+" = ?");
			ps.setFloat(1, param);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
}
