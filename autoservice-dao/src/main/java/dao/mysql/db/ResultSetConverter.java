package dao.mysql.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import beans.ClientDiscountBean;
import beans.DiscountsBean;
import beans.EmployeeListBean;
import beans.EmployeePositionBean;
import beans.ForumBean;
import beans.InvoiceBean;
import beans.MessagesBean;
import beans.OrdersBean;
import beans.PositionServiceBean;
import beans.RoleBean;
import beans.ServiceStationBean;
import beans.ServicesBean;
import beans.ServicesToStationsBean;
import beans.TimeTableBean;
import beans.UserBean;
import beans.UserdataBean;

public class ResultSetConverter {

	public ResultSetConverter() {
				
	}

	public static ClientDiscountBean createClientDiscountBean(ResultSet rs) throws SQLException {
		Integer clientDiscountId = rs.getInt("client_discount_id");
		Integer userId = rs.getInt("user_id");
		Integer discountId = rs.getInt("discount_id");
		Integer clientTotalSum = rs.getInt("client_totalsum");
				
		ClientDiscountBean bean = new ClientDiscountBean();
		
		bean.setClientDiscountId(clientDiscountId);
		bean.setUserId(userId);
		bean.setDiscountId(discountId);
		bean.setClientTotalSum(clientTotalSum);
				
		return bean;
	}
	
	public static ClientDiscountBean createClientDiscountNameBean(ResultSet rs) throws SQLException {
		Integer clientDiscountId = rs.getInt("client_discount_id");
		Integer clientTotalSum = rs.getInt("client_totalsum");
		String login = rs.getString("login");
		float discount =  rs.getFloat("discount");
		
		ClientDiscountBean bean = new ClientDiscountBean();
		
		bean.setClientDiscountId(clientDiscountId);
		bean.setClientTotalSum(clientTotalSum);
		bean.setLogin(login);
		bean.setDiscount(discount);
		
		return bean;
	}
	
	public static UserBean createUserBean(ResultSet rs) throws SQLException {
		Integer userId = rs.getInt("user_id");
		String login = rs.getString("login");
		String password = rs.getString("password");
		Integer roleId = rs.getInt("role_id");
		String status = rs.getString("blocked_status");
		
		UserBean bean = new UserBean();
		
		bean.setUserId(userId);;
		bean.setLogin(login);
		bean.setPassword(password);
		bean.setRoleId(roleId);
		bean.setBlockedStatus(status);
		
		return bean;
		
	}
	public static UserBean createUserNameBean(ResultSet rs) throws SQLException {
		Integer userId = rs.getInt("user_id");
		String login = rs.getString("login");
		String password = rs.getString("password");
		String role = rs.getString("role");
		String status = rs.getString("blocked_status");
		
		UserBean bean = new UserBean();
		
		bean.setUserId(userId);;
		bean.setLogin(login);
		bean.setPassword(password);
		bean.setBlockedStatus(status);
		bean.setRole(role);
		
		return bean;
		
	}
	
	public static RoleBean createRoleBean(ResultSet rs) throws SQLException {
		Integer roleId = rs.getInt("role_id");
		String role = rs.getString("role");
		
		RoleBean bean = new RoleBean();
		
		bean.setRoleId(roleId);
		bean.setRole(role);
		
		return bean;
	}
	
	public static DiscountsBean createDiscountsBean(ResultSet rs) throws SQLException {
		int discountId = rs.getInt("discount_id");
		float discount = rs.getFloat("discount");
		int totalSum = rs.getInt("totalsum");
		
	
		DiscountsBean bean = new DiscountsBean();
		
		bean.setDiscountId(discountId);
		bean.setDiscount(discount);
		bean.setTotalSum(totalSum);
		
		return bean;
	}
	
	public static EmployeeListBean createEmployeeListBean(ResultSet rs) throws SQLException {
		int employeeId = rs.getInt("employee_id");
		String fullname = rs.getString("fullname");
		String position = rs.getString("position");
		int userId = rs.getInt("fk_user_id");
		int stationId = rs.getInt("fk_station_id");
		String staionName = rs.getString("station_name");
		String login = rs.getString("login");
		String location = rs.getString("location"); 
				
		EmployeeListBean bean = new EmployeeListBean();
		
		bean.setEmployeeId(employeeId);
		bean.setFullname(fullname);
		bean.setPosition(position);
		bean.setStationId(stationId);
		bean.setUserId(userId);
		bean.setLogin(login);
		bean.setStationName(staionName);
		bean.setLocation(location);
		return bean;
	}
	
	public static EmployeeListBean createEmployeeListNameBean(ResultSet rs) throws SQLException {
		int employeeId = rs.getInt("employee_id");
		String login = rs.getString("login");
		String fullname = rs.getString("fullname");
		String position = rs.getString("position");
		String staionName = rs.getString("station_name");
		String location = rs.getString("location"); 
				
		EmployeeListBean bean = new EmployeeListBean();
		
		bean.setEmployeeId(employeeId);
		bean.setFullname(fullname);
		bean.setPosition(position);
		bean.setLogin(login);
		bean.setStationName(staionName);
		bean.setLocation(location);
		return bean;
	}
	
	public static MessagesBean createMessagesBean(ResultSet rs) throws SQLException{
		
		int messageId = rs.getInt("message_id");
		int userId = rs.getInt("fk_user_id_");
		String message = rs.getString("message");
		int topicId = rs.getInt("fk_topic_id");
		
		MessagesBean bean = new MessagesBean();
		
		bean.setMessageId(messageId);
		bean.setUserIdMessage(userId);
		bean.setMessage(message);
		bean.setTopicIdMessage(topicId);
			
		return bean;
	}
	
public static MessagesBean createMessagesNameBean(ResultSet rs) throws SQLException{
		
		int messageId = rs.getInt("messages.message_id");
		String login = rs.getString("users.login");
		String message = rs.getString("messages.message");
		String topic = rs.getString("forum.topic");
		String role = rs.getString("roles.role");
		Date messageDateTime = rs.getDate("message_datetime");
		String fullname = rs.getString("usersdata.fullname");
		Time messageTime = rs.getTime("message_datetime");
		
		MessagesBean bean = new MessagesBean();
		
		bean.setMessageId(messageId);
		bean.setLogin(login);
		bean.setMessage(message);
		bean.setTopic(topic);
		bean.setRole(role);
		bean.setMessageDateTime(messageDateTime);
		bean.setMessage(message);
		bean.setFullname(fullname);
		bean.setMessageTime(messageTime);
			
		return bean;
	}
	
	public static ForumBean createForumBean(ResultSet rs) throws SQLException{
		int topicId = rs.getInt("topic_id");
		String topic = rs.getString("topic");
		
		ForumBean bean = new ForumBean();
		
		bean.setTopicId(topicId);
		bean.setTopic(topic);
		
		return bean;
		
	}
	
	public static TimeTableBean creteTimeTableBean(ResultSet rs) throws SQLException{
		int timeTableId = rs.getInt("time_table_id");
		int employeeId= rs.getInt("employee_id");
		Date date=rs.getDate("date");
		Time time=rs.getTime("time");
		String fullname = rs.getString("fullname");
		int orderId=rs.getInt("order_id");
		
		TimeTableBean bean = new TimeTableBean();
		
		bean.setDate(date);
		bean.setEmployeeId(employeeId);
		bean.setOrderId(orderId);
		bean.setTime(time);
		bean.setTimeTableId(timeTableId);
		bean.setFullname(fullname);
		
		return bean;
	}
	
	public static TimeTableBean createFullTimeTableBean(ResultSet rs) throws SQLException{
		int employeeId= rs.getInt("employee_id");
		Date date=rs.getDate("date");
		Time time=rs.getTime("time");
		String fullname = rs.getString("fullname");
		int orderId=rs.getInt("order_id");
		String location = rs.getString("location");
		String position = rs.getString("position");
		
		TimeTableBean bean = new TimeTableBean();
		
		bean.setDate(date);
		bean.setEmployeeId(employeeId);
		bean.setOrderId(orderId);
		bean.setTime(time);
		bean.setFullname(fullname);
		bean.setLocation(location);
		bean.setPosition(position);
		
		return bean;
	}
	
	public static ServiceStationBean createServiceStationBean(ResultSet rs) throws SQLException{
		int stationId = rs.getInt("station_id");
		String stationName = rs.getString("station_name");
		String location = rs.getString("location");
		
		ServiceStationBean bean = new ServiceStationBean();
		
		bean.setLocation(location);
		bean.setStationId(stationId);
		bean.setStationName(stationName);
		
		return bean;
	}
	
	public static ServicesBean createServicesBean(ResultSet rs) throws SQLException{
		int serviceId = rs.getInt("service_id");
		String service = rs.getString("service_type");
		int manHours = rs.getInt("man_hours");
		int price = rs.getInt("price");
		
		ServicesBean bean = new ServicesBean();
		
		bean.setManHours(manHours);
		bean.setPrice(price);
		bean.setServiceId(serviceId);
		bean.setServiceType(service);
		
		return bean;
	}
	
	public static ServicesToStationsBean  createServicesToStationsBean(ResultSet rs) throws SQLException{
		
		int servicesToStationId = rs.getInt("service_station_id");
		int stationId = rs.getInt("fk_station_id_");
		int serviceId = rs.getInt("fk_service_id_");
		
		
		ServicesToStationsBean bean = new ServicesToStationsBean();
		
		bean.setServiceIdStS(serviceId);
		bean.setServiceToStationId(servicesToStationId);
		bean.setStationIdStS(stationId);
		
		return bean;
	}
	public static ServicesToStationsBean  createServicesToStationsNameBean(ResultSet rs) throws SQLException{
		
		int servicesToStationId = rs.getInt("service_station_id");
		String service = rs.getString("service_type");
		String station = rs.getString("station_name");
		String location = rs.getString("location");
		int serviceIdStS = rs.getInt("fk_service_id_");
		int stationIdStS =  rs.getInt("fk_station_id_"); 
		
		ServicesToStationsBean bean = new ServicesToStationsBean();
		
		bean.setService(service);
		bean.setLocation(location);
		bean.setStationName(station);
		bean.setServiceToStationId(servicesToStationId);
		bean.setServiceIdStS(serviceIdStS);
		bean.setStationIdStS(stationIdStS);
				
		return bean;
	}
	
	public static OrdersBean createOrderBean(ResultSet rs) throws SQLException{
		int orderId = rs.getInt("orders.order_id");
		String fullname = rs.getString("usersdata.fullname");
		String service = rs.getString("services.service_type");
		int price = rs.getInt("services.price");
		String location = rs.getString("service_station.location");
		int invoiceId = rs.getInt("orders.fk_invoice_id");
		int manHours = rs.getInt("services.man_hours");
		int currentTotalsum = rs.getInt("orders.current_totalsum");
		String status = rs.getString("orders.status");
		int discountPrice = rs.getInt("orders.discount_price");
				
		OrdersBean bean=new OrdersBean();
		
		bean.setOrderId(orderId);
		bean.setFullname(fullname);
		bean.setService(service);
		bean.setPrice(price);
		bean.setStationLocation(location);
		bean.setInvoiceId(invoiceId);
		bean.setManHours(manHours);
		bean.setCurrentTotalsum(currentTotalsum);
		bean.setStatus(status);
		bean.setDiscountPrice(discountPrice);
		return bean;
	}	

	public static OrdersBean createOrderNameBean(ResultSet rs) throws SQLException{
	
		int orderId = rs.getInt("order_id");
		int invoiceId = rs.getInt("invoice_id");
		String role = rs.getString("role");
		String login = rs.getString("login");
		Time time = rs.getTime("time");
		Date date = rs.getDate("date");
		String status = rs.getString("status");
	
		OrdersBean bean = new OrdersBean();	
		
		bean.setOrderId(orderId);
		bean.setInvoiceId(invoiceId);
		bean.setRole(role);
		bean.setLogin(login);
		bean.setTime(time);
		bean.setDate(date);
		bean.setStatus(status);
		
		return bean;
	}
	
	public static InvoiceBean createInvoiceBean(ResultSet rs) throws SQLException{
		
		int invoiceId = rs.getInt("invoice_id");
		boolean status = rs.getBoolean("status");
		
		InvoiceBean bean = new InvoiceBean();
		
		bean.setInvoiceId(invoiceId);
		bean.setStatus(status);
		
		return bean;
	}
	
	public static UserdataBean  createUserdataNameBean(ResultSet rs) throws SQLException{
		
		int userdataId = rs.getInt("userdata_id");
		String login = rs.getString("login");
		String fullname = rs.getString("fullname");
		Date birthday = rs.getDate("birthday");
		String adress =  rs.getString("adress");
		String phone = rs.getString("phone");
		String gender =  rs.getString("gender");
		
		UserdataBean bean = new UserdataBean();
		
		bean.setAdress(adress);
		bean.setBirthday(birthday);
		bean.setFullname(fullname);
		bean.setGender(gender);
		bean.setLogin(login);
		bean.setPhone(phone);
		bean.setUserdataId(userdataId);
				
		return bean;
	}
	
	public static EmployeePositionBean createEmployeePositionBean(ResultSet rs) throws SQLException{
		int positionId = rs.getInt("position_id");
		String position = rs.getString("position");
		
		EmployeePositionBean bean = new EmployeePositionBean();
		
		bean.setPositionId(positionId);
		bean.setPosition(position);
		return bean;
	}
	
	public static PositionServiceBean createPositionServiceBean(ResultSet rs) throws SQLException{
		int positionServiceId = rs.getInt("position_service.position_service_id");
		int positionId = rs.getInt("position_service.fk_position_id_");
		int serviceId = rs.getInt("position_service.fk_serviceid_");
		String position = rs.getString("employee_position.position");
		String service = rs.getString("services.service_type");
		
		PositionServiceBean bean = new PositionServiceBean();
		
		bean.setPosition(position);
		bean.setPositionId(positionId);
		bean.setPositionServiceId(positionServiceId);
		bean.setService(service);
		bean.setServiceId(serviceId);
		return bean;
	}
	
	
	
	
	

	
}
