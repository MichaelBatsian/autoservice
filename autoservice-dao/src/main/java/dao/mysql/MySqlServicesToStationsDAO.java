package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ServicesToStationsBean;
import dao.ServicesToStationsDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlServicesToStationsDAO implements ServicesToStationsDAO {

	
	public ArrayList<ServicesToStationsBean> getServicesToStationsTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServicesToStationsBean bean;
		ArrayList<ServicesToStationsBean> ServicesToStationsTable = new ArrayList<ServicesToStationsBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT*FROM services_to_stations");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createServicesToStationsBean(rs);
				ServicesToStationsTable.add(bean);
			}
			return ServicesToStationsTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	
	public ArrayList<ServicesToStationsBean> getServicesToStationsNameTable(){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServicesToStationsBean bean;
		ArrayList<ServicesToStationsBean> ServicesToStationsTable = new ArrayList<ServicesToStationsBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT services_to_stations.service_station_id, services_to_stations.fk_service_id_, "
					+ "services_to_stations.fk_station_id_, services.service_type, service_station.station_name, service_station.location "
					+ "FROM services_to_stations,services,service_station WHERE services_to_stations.fk_service_id_=services.service_id "
					+ "AND service_station.station_id=services_to_stations.fk_station_id_ ORDER BY service_station.station_name");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createServicesToStationsNameBean(rs);
				ServicesToStationsTable.add(bean);
			}
			return ServicesToStationsTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void deleteServiceToStation(int serviceStationId) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM services_to_stations WHERE service_station_id=?");
			ps.setInt(1, serviceStationId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void createServiceToStation(int serviceId, int stationId) {
		int maxId = UtilsDAO.getMaxTableId("service_station_id", "services_to_stations");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO services_to_stations(service_station_id, fk_station_id_, fk_service_id_) VALUES(?,?,?)");
			ps.setInt(1, maxId);
			ps.setInt(2, stationId);
			ps.setInt(3, serviceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<ServicesToStationsBean> getStationsToService(int stationId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServicesToStationsBean bean;
		ArrayList<ServicesToStationsBean> ServicesToStationsTable = new ArrayList<ServicesToStationsBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT service_station_id, station_name,location, service_type,fk_service_id_, fk_station_id_ "
					+ "FROM service_station, services_to_stations, services WHERE service_station.station_id=services_to_stations.fk_station_id_"
					+ " AND services.service_id=services_to_stations.fk_service_id_ AND services_to_stations.fk_service_id_=?");
			ps.setInt(1, stationId);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createServicesToStationsNameBean(rs);
				ServicesToStationsTable.add(bean);
			}
			return ServicesToStationsTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public int getServicesToStationsId(int serviceId, int stationId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int serviceStationId = 0;
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT service_station_id FROM services_to_stations WHERE fk_station_id_=? AND fk_service_id_=?");
			ps.setInt(1, stationId);
			ps.setInt(2, serviceId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				serviceStationId=rs.getInt("service_station_id");
			}
			
			return serviceStationId;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void updateService(int serviceStationId, int serviceId) {
		Connection cn = null;
		PreparedStatement ps = null;
		
		try{
			cn = ConnectionPool.getConnection();
			ps =cn.prepareStatement("UPDATE services_to_stations SET fk_service_id_=? WHERE service_station_id=?");
			ps.setInt(1, serviceId);
			ps.setInt(2, serviceStationId);
			ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public void updateStation(int serviceStationId, int stationId) {
		Connection cn = null;
		PreparedStatement ps = null;
		
		try{
			cn = ConnectionPool.getConnection();
			ps =cn.prepareStatement("UPDATE services_to_stations SET fk_station_id_=? WHERE service_station_id=?");
			ps.setInt(1, stationId);
			ps.setInt(2, serviceStationId);
			ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void deleteServicesToStations(int serviceStationId) {
		Connection cn = null;
		PreparedStatement ps = null;
		
		try{
			cn = ConnectionPool.getConnection();
			ps =cn.prepareStatement("DELETE FROM services_to_stations WHERE service_station_id=?");
			ps.setInt(1, serviceStationId);
			ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

}
