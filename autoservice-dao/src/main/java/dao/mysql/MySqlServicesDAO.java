package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ServicesBean;
import dao.ServicesDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlServicesDAO implements ServicesDAO {

	
	public void createService(String service, int manHours, int price) {
		
		int maxId = UtilsDAO.getMaxTableId("service_id", "services");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO services(service_id, service_type, man_hours, price) VALUES(?,?,?,?)");
			ps.setInt(1, maxId);
			ps.setString(2, service);
			ps.setInt(3, manHours);
			ps.setInt(4, price);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
		
	

	
	public void updateService(int serviceId, String service) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE services SET service_type = ? WHERE service_id = ?");
			ps.setString(1, service);
			ps.setInt(2, serviceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateServiceHours(int serviceId, int manHours) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE services SET man_hours = ? WHERE service_id = ?");
			ps.setInt(1, manHours);
			ps.setInt(2, serviceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateServicePrice(int serviceId, int price) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE services SET price = ? WHERE service_id = ?");
			ps.setInt(1, price);
			ps.setInt(2, serviceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
		
	}

	
	public void deleteService(int serviceid) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM services WHERE service_id=?");
			ps.setInt(1, serviceid);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<ServicesBean> getServiceTable() {
		
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServicesBean bean;
		ArrayList<ServicesBean> servicesTable = new ArrayList<ServicesBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM services");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createServicesBean(rs);
				servicesTable.add(bean);
			}
			return servicesTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	
	public int getManHoursToService(int serviceId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int mH=0;
				
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT man_hours FROM services WHERE service_id=?");
			ps.setInt(1, serviceId);
			rs = ps.executeQuery();
			if(rs.next()){
				mH=rs.getInt("man_hours");
			}
			
			return mH;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
		
		public int getManHoursToService(String service) {
			Connection cn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int mH=0;
					
			try {
				cn = ConnectionPool.getConnection();
				ps = cn.prepareStatement("SELECT man_hours FROM services WHERE service_type=?");
				ps.setString(1, service);
				rs = ps.executeQuery();
				if(rs.next()){
					mH=rs.getInt("man_hours");
				}
				
				return mH;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				ConnectionPool.closeConnection(cn);
			}

	}
		
		
		public int getServicePrice(String service) {
			Connection cn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int price=0;
			try {
				cn = ConnectionPool.getConnection();
				ps = cn.prepareStatement("SELECT price FROM services WHERE service_type=?");
				ps.setString(1, service);
				rs = ps.executeQuery();
				if(rs.next()){
					price=rs.getInt("price");
				}
				return price;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				ConnectionPool.closeConnection(cn);
			}
		}

}
