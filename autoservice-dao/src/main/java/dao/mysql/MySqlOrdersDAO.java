package dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.OrdersBean;
import dao.OrdersDAO;
import dao.mysql.db.ConnectionPool;
import dao.mysql.db.ResultSetConverter;
import dao.mysql.utils.UtilsDAO;

public class MySqlOrdersDAO implements OrdersDAO {

	
	public void createOrder(int userId, int serviceStationId, int invoiceId) {
		int maxId = UtilsDAO.getMaxTableId("order_id", "orders");
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("INSERT INTO orders(order_id, fk_user_id, fk_service_station_id, fk_invoice_id)"
					+ " VALUES(?,?,?,?)");
			ps.setInt(1, maxId);
			ps.setInt(2, userId);
			ps.setInt(3, serviceStationId);
			ps.setInt(4, invoiceId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void deleteOrder(int orderId) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("DELETE FROM orders WHERE order_id=?");
			ps.setInt(1, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateOrderStatus(int orderId, String status) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE orders SET status = ? WHERE order_id = ?");
			ps.setString(1, status);
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateOrderService(int orderId, int serviceStationId) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE orders SET fk_service_station_id = ? WHERE order_id = ?");
			ps.setInt(1, serviceStationId);
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public void updateOrderTimeTable(int orderId, int timeTableId) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE orders SET fk_time_table_id = ? WHERE order_id = ?");
			ps.setInt(1, timeTableId);
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	
	public void updateDiscountPrice (int orderId, int discountPrice) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE orders SET discount_price = ? WHERE order_id = ?");
			ps.setInt(1, discountPrice);
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	
	public void updateOrderInvoice(int orderId, int invoiceId) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE orders SET fk_invoice_id = ? WHERE order_id = ?");
			ps.setInt(1, invoiceId);
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}
	
	
	public void updateOrderCurrentTotalSum(int orderId,int currentTotalsum) {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("UPDATE orders SET current_totalsum = ? WHERE order_id = ?");
			ps.setInt(1,currentTotalsum);
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
		
	}

	
	public ArrayList<OrdersBean> getOrdersTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<OrdersBean> entityOrdersBean = new ArrayList<OrdersBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT orders.order_id, usersdata.fullname, services.service_type, services.price, services.man_hours, "
					+ "service_station.location,orders.current_totalsum, orders.fk_invoice_id, orders.status, orders.discount_price FROM orders, services, "
					+ "usersdata, service_station, services_to_stations WHERE orders.fk_user_id=usersdata.data_user_id "
					+ "AND orders.fk_service_station_id=services_to_stations.service_station_id "
					+ "AND services_to_stations.fk_service_id_=services.service_id  "
					+ "AND services_to_stations.fk_station_id_=service_station.station_id ORDER BY orders.order_id DESC");
			rs = ps.executeQuery();
			
			while(rs.next()){
				OrdersBean bean = ResultSetConverter.createOrderBean(rs);
				entityOrdersBean.add(bean);
			}
			return entityOrdersBean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public OrdersBean getOrderBeanForPeriod(int orderId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrdersBean bean = new OrdersBean();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT orders.order_id, usersdata.fullname, services.service_type, services.price, services.man_hours, "
					+ "service_station.location,orders.current_totalsum, orders.fk_invoice_id, orders.status, orders.discount_price FROM orders, services, "
					+ "usersdata, service_station, services_to_stations WHERE orders.fk_user_id=usersdata.data_user_id "
					+ "AND orders.fk_service_station_id=services_to_stations.service_station_id "
					+ "AND services_to_stations.fk_service_id_=services.service_id  "
					+ "AND services_to_stations.fk_station_id_=service_station.station_id AND orders.order_id=? ORDER BY orders.order_id DESC");
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				 bean = ResultSetConverter.createOrderBean(rs);
			}
			return bean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	

	
	public ArrayList<OrdersBean> getOrdersNameStatus(boolean status) {
		
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrdersBean bean;
		ArrayList<OrdersBean> ordersTable = new ArrayList<OrdersBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT order_id, role, login,service_type, time_table.time, time_table.date, orders.status, invoice_id"
					+ " FROM orders, time_table, users, services, roles,invoice WHERE orders.fk_invoice_id=invoice.invoice_id "
					+ "AND orders.fk_service_id=services.service_id AND orders.fk_time_table_id=time_table.time_table_id "
					+ "AND orders.fk_user_id=users.user_id AND users.role_id=roles.role_id AND orders.status=?");
			ps.setBoolean(1, status);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createOrderNameBean(rs);
				ordersTable.add(bean);
			}
			return ordersTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	public ArrayList<OrdersBean> getOrdersNameStatusPeriod(boolean status, Date from, Date to){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrdersBean bean;
		ArrayList<OrdersBean> ordersTable = new ArrayList<OrdersBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT order_id, role, login,service_type, time_table.time, time_table.date, orders.status, invoice_id"
					+ " FROM orders, time_table, users, services, roles,invoice WHERE orders.fk_invoice_id=invoice.invoice_id "
					+ "AND orders.fk_service_id=services.service_id AND orders.fk_time_table_id=time_table.time_table_id "
					+ "AND orders.fk_user_id=users.user_id AND users.role_id=roles.role_id AND orders.status=? "
					+ "AND time_table.date between ? and ?");
			ps.setBoolean(1, status);
			ps.setDate(2, from);
			ps.setDate(3,to);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createOrderNameBean(rs);
				ordersTable.add(bean);
			}
			return ordersTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public ArrayList<OrdersBean> getOrdersNameTable() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrdersBean bean;
		ArrayList<OrdersBean> ordersTable = new ArrayList<OrdersBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT order_id, role, login,service_type, time_table.time, time_table.date, orders.status,"
					+ " invoice_id FROM orders, time_table, users, services, roles,invoice WHERE orders.fk_invoice_id=invoice.invoice_id"
					+ " AND orders.fk_service_id=services.service_id AND orders.fk_time_table_id=time_table.time_table_id"
					+ " AND orders.fk_user_id=users.user_id AND users.role_id=roles.role_id");
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createOrderNameBean(rs);
				ordersTable.add(bean);
			}
			return ordersTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}
	
	
	public ArrayList<OrdersBean> getOrdersNameTablePeriod(Date from, Date to) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrdersBean bean;
		ArrayList<OrdersBean> ordersTable = new ArrayList<OrdersBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT orders.order_id, usersdata.fullname, services.service_type, services.price, services.man_hours, "
					+ "service_station.location,orders.current_totalsum, orders.fk_invoice_id, orders.status,orders.discount_price "
					+ "FROM orders, services, usersdata, service_station, services_to_stations WHERE orders.fk_user_id=usersdata.data_user_id  "
					+ "AND orders.fk_service_station_id=services_to_stations.service_station_id "
					+ "AND services_to_stations.fk_service_id_=services.service_id "
					+ "AND services_to_stations.fk_station_id_=service_station.station_id AND orders.fk_invoice_id=? ORDER BY orders.order_id"
					+ ""
					+ ""
					+ ""
					+ ""
					+ ""
					+ ""
					+ ""
					+ ""
					+ ""
					+ "SELECT order_id, role, login,service_type, time_table.time, time_table.date, orders.status,"
					+ " invoice_id FROM orders, time_table, users, services, roles,invoice WHERE orders.fk_invoice_id=invoice.invoice_id"
					+ " AND orders.fk_service_id=services.service_id AND orders.fk_time_table_id=time_table.time_table_id"
					+ " AND orders.fk_user_id=users.user_id AND users.role_id=roles.role_id "
					+ " AND time_table.date between ? and ?");
			ps.setDate(1,from);
			ps.setDate(2, to);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createOrderNameBean(rs);
				ordersTable.add(bean);
			}
			return ordersTable;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public ArrayList<OrdersBean> getUserOrders(int userId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrdersBean bean;
		ArrayList<OrdersBean> userOrders = new ArrayList<OrdersBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT order_id, role, login,service_type, time_table.time, time_table.date, orders.status,"
					+ " invoice_id FROM orders, time_table, users, services, roles,invoice WHERE orders.fk_user_id=users.user_id "
					+ "AND users.user_id=?");
			ps.setInt(1,userId);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bean = ResultSetConverter.createOrderNameBean(rs);
				userOrders.add(bean);
			}
			return userOrders;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	
	public ArrayList <OrdersBean> getEntityForUserOrder(int invoiceId) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<OrdersBean> entityOrdersBean = new ArrayList<OrdersBean>();
		
		try {
			cn = ConnectionPool.getConnection();
			ps = cn.prepareStatement("SELECT orders.order_id, usersdata.fullname, services.service_type, services.price, services.man_hours, "
					+ "service_station.location,orders.current_totalsum, orders.fk_invoice_id, orders.status,orders.discount_price "
					+ "FROM orders, services, usersdata, service_station, services_to_stations WHERE orders.fk_user_id=usersdata.data_user_id  "
					+ "AND orders.fk_service_station_id=services_to_stations.service_station_id "
					+ "AND services_to_stations.fk_service_id_=services.service_id "
					+ "AND services_to_stations.fk_station_id_=service_station.station_id AND orders.fk_invoice_id=? ORDER BY orders.order_id");
			ps.setInt(1, invoiceId);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				OrdersBean bean = ResultSetConverter.createOrderBean(rs);
				entityOrdersBean.add(bean);
			}
			return entityOrdersBean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.closeConnection(cn);
		}
	}

	

	
	
	
	
	

}
