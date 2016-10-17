package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.PositionServiceBean;
import dao.PositionServiceDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlPositionServiceDAO implements PositionServiceDAO {

	

	
	public ArrayList<Integer> getAllPositionsIdtoServiceStation(int serviceId,int stationId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Integer> positionsId = new ArrayList<Integer>();
		
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT position_service.fk_position_id_ FROM position_service, services_to_stations "
					+ "WHERE services_to_stations.fk_service_id_=position_service.fk_serviceid_ AND services_to_stations.fk_station_id_=? "
					+ "AND fk_serviceid_=?");
			ps.setInt(1, stationId);
			ps.setInt(2, serviceId);
			rs = ps.executeQuery();
			while(rs.next()){
				positionsId.add(rs.getInt(1));
			}
			return positionsId;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public ArrayList<PositionServiceBean> getPositionServiceTable(){
		
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PositionServiceBean bean;
		ArrayList<PositionServiceBean> positionServiceBeans = new ArrayList<PositionServiceBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT position_service.position_service_id, position_service.fk_position_id_,position_service.fk_serviceid_,"
					+ " employee_position.position, services.service_type FROM employee_position, services,position_service WHERE "
					+ "position_service.fk_position_id_=employee_position.position_id AND position_service.fk_serviceid_=services.service_id "
					+ "ORDER BY employee_position.position");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createPositionServiceBean(rs);
				positionServiceBeans.add(bean);
			}
			return positionServiceBeans;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
			}
	}

	
	public void createPositionService(int positionId, int serviceId) {
		int positionServiceId = UtilsDAO.getMaxTableId("position_service_id", "position_service");
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO position_service(position_service_id, fk_position_id_, fk_serviceid_) VALUES(?,?,?)");
			ps.setInt(1, positionServiceId);
			ps.setInt(2, positionId);
			ps.setInt(3, serviceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updatePosition(int positionServiceId, int positionId) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE position_service SET fk_position_id_ = ? WHERE position_service_id = ?");
			ps.setInt(1, positionId);
			ps.setInt(2, positionServiceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateService(int positionServiceId, int serviceId) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE position_service SET fk_serviceid_ = ? WHERE position_service_id = ?");
			ps.setInt(1, serviceId);
			ps.setInt(2, positionServiceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void deletePositionService(int positionServiceId) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM position_service WHERE position_service_id=?");
			ps.setInt(1, positionServiceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
}
