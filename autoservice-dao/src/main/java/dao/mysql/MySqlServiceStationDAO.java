package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ServiceStationBean;
import dao.ServiceStationDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlServiceStationDAO implements ServiceStationDAO {

	
	public void createStation(String stationName, String Location) {
		int maxId = UtilsDAO.getMaxTableId("station_id", "service_station");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO service_station(station_id, station_name, location) VALUES(?,?,?)");
			ps.setInt(1, maxId);
			ps.setString(2, stationName);
			ps.setString(3, Location);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void updateStationName(int stationId, String newName) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE service_station SET station_name = ? WHERE station_id = ?");
			ps.setString(1, newName);
			ps.setInt(2, stationId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateStationLocation(int stationId, String newLocation) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE service_station SET location = ? WHERE station_id = ?");
			ps.setString(1, newLocation);
			ps.setInt(2, stationId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<ServiceStationBean> getStationsTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServiceStationBean bean;
		ArrayList<ServiceStationBean> stationTable = new ArrayList<ServiceStationBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM service_station");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createServiceStationBean(rs);
				stationTable.add(bean);
			}
			return stationTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void deleteStation(int stationId) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM service_station WHERE station_id = ?"); 
			ps.setInt(1, stationId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	

}
