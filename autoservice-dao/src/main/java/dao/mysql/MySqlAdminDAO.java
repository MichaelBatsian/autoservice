package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.AdminDAO;
import dao.mysql.db.ConnectionPool;

public class MySqlAdminDAO implements AdminDAO{
	
	public ArrayList<String> getTables(){
		
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<String> tablesAdmin = new ArrayList<String>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("show tables;");
			rs = ps.executeQuery();
				while(rs.next()){
					tablesAdmin.add(rs.getString(1));
				}
				return tablesAdmin;
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}finally{
			ConnectionPool.closeConnection(cn);
		}
	}
}
