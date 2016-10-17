package dao;

import java.sql.Date;
import java.util.ArrayList;

import beans.OrdersBean;

public interface OrdersDAO {
	
	public void createOrder(int userId, int serviceStationId, int invoiceId);
	public void deleteOrder(int orderId);
	public void updateOrderStatus(int orderId, String status);
	public void updateOrderService(int orderId, int serviceStationId);
	public void updateOrderTimeTable(int orderId, int timeTableId);
	public void updateOrderInvoice(int orderId, int invoiceId);
	public ArrayList<OrdersBean> getOrdersTable();
	public OrdersBean getOrderBeanForPeriod(int orderId);
	public ArrayList<OrdersBean> getOrdersNameTable();
	public ArrayList<OrdersBean> getOrdersNameStatus(boolean status);
	public ArrayList<OrdersBean> getOrdersNameStatusPeriod(boolean status, Date from, Date to);
	public ArrayList<OrdersBean> getOrdersNameTablePeriod(Date from, Date to);
	public ArrayList<OrdersBean> getUserOrders(int userId);
	
	public ArrayList<OrdersBean> getEntityForUserOrder(int invoiceId);
	public void updateOrderCurrentTotalSum(int orderId,int currentTotalsum);
	public void updateDiscountPrice (int orderId, int discountPrice);
	
}
