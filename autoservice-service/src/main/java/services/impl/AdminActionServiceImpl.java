package services.impl;

import services.AdminActionService;

import static org.apache.commons.lang3.StringUtils.isNoneEmpty;

import java.sql.Date;
import java.sql.Time;
import dao.ClientDiscountDAO;
import dao.DiscountsDAO;
import dao.EmployeeListDAO;
import dao.EmployeePositionDAO;
import dao.ForumDAO;
import dao.MessagesDAO;
import dao.OrdersDAO;
import dao.PositionServiceDAO;
import dao.RolesDAO;
import dao.ServiceStationDAO;
import dao.ServicesDAO;
import dao.ServicesToStationsDAO;
import dao.TimeTableDAO;
import dao.UserdataDAO;
import dao.UsersDAO;
import dao.factory.DAOFactory;
import dao.mysql.utils.UtilsDAO;

public class AdminActionServiceImpl implements AdminActionService {


	public void updateUser(int userId, String login, String password, String role,String blockedStatus) {
		UsersDAO daoUser = DAOFactory.getFactory().getUserDAO();
		
		if(isNoneEmpty(login)){
			daoUser.updateLogin(userId, login);
		}
		
		if(isNoneEmpty(password)){
			daoUser.updatePassword(userId, password);
		}
		
		if(isNoneEmpty(role)){
			int roleId = UtilsDAO.getId("roles", "role",role);
			daoUser.updateRole(userId, roleId);
		}
		
		if(isNoneEmpty(blockedStatus)){
			daoUser.updateStatus(userId, blockedStatus);
		}
		
	}

	
	public void deleteUser(int userId) {
		UsersDAO daoUser = DAOFactory.getFactory().getUserDAO();
		daoUser.deleteUser(userId);
		
	}

	
	public void createUser(String login, String password, String role) {
		UsersDAO daoUser = DAOFactory.getFactory().getUserDAO();
		int roleId = UtilsDAO.getId("roles", "role",role);
		daoUser.createUser(login, password, roleId);
	}

	
	public void updateRole(int roleId, String role) {
		RolesDAO rolesDAO = DAOFactory.getFactory().getRoleDAO();
		rolesDAO.updateRole(roleId, role);
	}
	
	
	public void deleteRole(int roleId) {
		RolesDAO rolesDAO = DAOFactory.getFactory().getRoleDAO();
		rolesDAO.deleteRole(roleId);
	}

	
	public void createRole(String role) {
		RolesDAO rolesDAO = DAOFactory.getFactory().getRoleDAO();
		rolesDAO.createRole(role);
	}

	
	public void updateDiscount(String discountId, String discount, String totalsum) {
		DiscountsDAO discountDAO = DAOFactory.getFactory().getDiscountsDAO();
		Integer id = Integer.parseInt(discountId);
		if(isNoneEmpty(discount)){
			float discountFloat = Float.parseFloat(discount)/100;
			discountDAO.updateDiscount(id, discountFloat);
		}
		if(isNoneEmpty(totalsum)){
			Integer totalsumInt = Integer.parseInt(totalsum);
			discountDAO.updateDiscountTotalSum(id, totalsumInt);
		}
	}

	
	public void deleteDiscount(int discountId) {
		DiscountsDAO discountDAO = DAOFactory.getFactory().getDiscountsDAO();
		discountDAO.deleteDiscount(discountId);
	}

	
	public void createDiscount(float discount, int totalsum) {
		DiscountsDAO discountDAO = DAOFactory.getFactory().getDiscountsDAO();
		discountDAO.createDiscount(discount, totalsum);
	}

	
	public void updateForum(String topicId, String topic) {
		Integer id = Integer.parseInt(topicId);
		ForumDAO forumDAO = DAOFactory.getFactory().getForumDAO();
		forumDAO.updateTopic(id, topic);
	}

	
	public void deleteForum(String topicId) {
		Integer id = Integer.parseInt(topicId);
		ForumDAO forumDAO = DAOFactory.getFactory().getForumDAO();
		forumDAO.deleteTopic(id);
	}

	
	public void createForum(String topic) {
		ForumDAO forumDAO = DAOFactory.getFactory().getForumDAO();
		forumDAO.createTopic(topic);
		
	}

	
	public void updateClientDiscount(String clientDiscountId, String login, String discount, String totalSum) {
		Integer id = Integer.parseInt(clientDiscountId);
		ClientDiscountDAO clientDiscountDAO = DAOFactory.getFactory().getClientDiscountDAO();		
		if(isNoneEmpty(totalSum)){
			Integer sum = Integer.parseInt(totalSum);
			clientDiscountDAO.updateClientTotalSumAdmin(id, sum);
		}
		if(isNoneEmpty(discount)){
			Integer discountInt = Integer.parseInt(discount);
			int discountId = UtilsDAO.getId("discounts", "discount", discountInt);
			clientDiscountDAO.updateDiscountAdmin(id, discountId);
		}
		if(isNoneEmpty(login)){
			int userId = UtilsDAO.getId("users", "login", login);
			clientDiscountDAO.updateClientAdmin(id, userId);
		}
		
	}

	
	public void deleteClientDiscount(String discountId) {
		Integer id = Integer.parseInt(discountId);
		ClientDiscountDAO clientDiscountDAO = DAOFactory.getFactory().getClientDiscountDAO();
		clientDiscountDAO.deleteClientDiscount(id);
	}

	
	public void createClientDiscount(String login, String discount, String totalSum) {
		Integer sum = Integer.parseInt(totalSum);
		float discountFloat = Float.parseFloat(discount);
		Integer discountId=UtilsDAO.getId("discounts","discount_id", "discount", discountFloat);
		System.out.println("createClientDiscount login "+login);
		Integer userId=UtilsDAO.getId("users", "login", login);
		System.out.println("userId login "+userId);
		ClientDiscountDAO clientDiscountDAO = DAOFactory.getFactory().getClientDiscountDAO();
		clientDiscountDAO.createClientDiscount(userId, discountId, sum);
	
	}

	
	public void updateMessage(String messageId, String newMessage) {
		Integer id = Integer.parseInt(messageId);
		MessagesDAO messageDAO = DAOFactory.getFactory().getMessagesDAO();
		messageDAO.updateMessage(id, newMessage);
	}

	
	public void deleteMessage(String messageId) {
		Integer id = Integer.parseInt(messageId);
		MessagesDAO messageDAO = DAOFactory.getFactory().getMessagesDAO();
		messageDAO.deleteMessage(id);
	}

	
	public void createMessage(String loginMessage, String message) {
		MessagesDAO messageDAO = DAOFactory.getFactory().getMessagesDAO();
		int userId = UtilsDAO.getId("users", "user_id", loginMessage); 
		int topicId = UtilsDAO.getId("messages", "topic_id", message);
		messageDAO.createMessage(userId, message, topicId);
	}

	
	public void updateService(String serviceId, String service, String manHours, String price) {
		Integer id = Integer.parseInt(serviceId);
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		if(isNoneEmpty(service)){
			servicesDAO.updateService(id, service);
		}
		
		if(isNoneEmpty(price)){
			Integer servicePrice = Integer.parseInt(price);
			servicesDAO.updateServicePrice(id, servicePrice);
		}
		
		if(isNoneEmpty(manHours)){
			Integer labor = Integer.parseInt(manHours);
			servicesDAO.updateServiceHours(id, labor);
		}
	}

	
	public void deleteService(String serviceId) {
		Integer id = Integer.parseInt(serviceId);
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		servicesDAO.deleteService(id);
	}

	
	public void createService(String service, String manHours, String price) {
		Integer servicePrice = Integer.parseInt(price);
		Integer labor = Integer.parseInt(manHours);
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		servicesDAO.createService(service, labor, servicePrice);
	}

	
	public void updateStation(String stationId, String station, String location) {
		Integer id = Integer.parseInt(stationId);
		ServiceStationDAO stationDAO = DAOFactory.getFactory().getServiceStationDAO();
		if(isNoneEmpty(station)){
			stationDAO.updateStationName(id, station);
		}
		
		if(isNoneEmpty(location)){
			stationDAO.updateStationLocation(id, location);
		}
	}

	
	public void deleteStation(String stationId) {
		Integer id = Integer.parseInt(stationId);
		ServiceStationDAO stationDAO = DAOFactory.getFactory().getServiceStationDAO();
		stationDAO.deleteStation(id);
	}

	
	public void createStation(String station, String location) {
		ServiceStationDAO stationDAO = DAOFactory.getFactory().getServiceStationDAO();
		stationDAO.createStation(station, location);
	}


	public void updateUsersdata(String userdataId, String login, String fullname, String birthday, String adress,
			String phone, String gender) {
		Integer id = Integer.parseInt(userdataId);
		UserdataDAO userdataDAO = DAOFactory.getFactory().getUserdataDAO();		
		if(isNoneEmpty(login)){
			int userId = UtilsDAO.getId("users", "login", login);
			System.out.println("userId in the method "+userId);
			userdataDAO.updateDataUserId(id, userId);
		}
		
		if(isNoneEmpty(fullname)){
			userdataDAO.updateUserdataFullname(id, fullname);
		}
		
		if(isNoneEmpty(birthday)){
			Date date = Date.valueOf(birthday);
			userdataDAO.updateUserdataBirthday(id, date);
		}
		
		if(isNoneEmpty(adress)){
			userdataDAO.updateUserdataAdress(id, adress);
		}

		if(isNoneEmpty(gender)){
			userdataDAO.updateUserdataGender(id, gender);
		}

		if(isNoneEmpty(phone)){
			userdataDAO.updateUserdataPhone(id, phone);
		}
	}

	
	public void deleteUsersdata(String userdataId) {
		Integer id = Integer.parseInt(userdataId);
		UserdataDAO userdataDAO = DAOFactory.getFactory().getUserdataDAO();
		userdataDAO.deleteUserdata(id);
		
	}

	
	public void createUsersdata(String login, String fullname, String birthday, String adress,String phone, String gender) {
		int userId = UtilsDAO.getId("users", "login", login);
		Date date = Date.valueOf(birthday);
		UserdataDAO userdataDAO = DAOFactory.getFactory().getUserdataDAO();
		userdataDAO.createUserdata(userId, fullname, date, adress, phone, gender);
	}

	
	public void createPosition(String position) {
		EmployeePositionDAO emplPositionDAO = DAOFactory.getFactory().getEmployeePositionDAO();
		emplPositionDAO.createPosition(position);
	}

	
	public void updatePosition(String positionId, String position) {
		Integer id = Integer.parseInt(positionId);
		EmployeePositionDAO emplPositionDAO = DAOFactory.getFactory().getEmployeePositionDAO();
		emplPositionDAO.updatePosition(id, position);
	}

	
	public void deletePosition(String positionId) {
		Integer id = Integer.parseInt(positionId);
		EmployeePositionDAO emplPositionDAO = DAOFactory.getFactory().getEmployeePositionDAO();
		emplPositionDAO.deletePosition(id);
	}

	
	public void createEmployee(String login, String position, String location) {
		EmployeeListDAO employeeListDAO = DAOFactory.getFactory().getEmployeeListDAO();
		int userId = UtilsDAO.getId("users", "login", login);
		int userdataId = UtilsDAO.getId("usersdata", "data_user_id", userId);
		int positionId = UtilsDAO.getId("employee_position", "position", position);
		int stationId = UtilsDAO.getId("service_station", "location", location);
		employeeListDAO.createEmployee(userdataId, positionId, userId, stationId);
	}

	
	public void updateEmployee(String employeeIdstr,String login, String position, String location) {
		Integer employeeId = Integer.parseInt(employeeIdstr);
		EmployeeListDAO employeeListDAO = DAOFactory.getFactory().getEmployeeListDAO();
		if(isNoneEmpty(login)){
			int userId = UtilsDAO.getId("users", "login", login);
			int userdataId = UtilsDAO.getId("usersdata", "data_user_id", userId);
			employeeListDAO.updateEmployeeName(employeeId, userdataId);
		}
		if(isNoneEmpty(position)){
			int positionId = UtilsDAO.getId("employee_position", "position", position);
			employeeListDAO.updateEmployeePosition(employeeId, positionId);
		}
		if(isNoneEmpty(location)){
			int stationId = UtilsDAO.getId("service_station", "location", location);
			employeeListDAO.updateEmployeeStation(employeeId, stationId);
		}
	}

	
	public void deleteEmployee(String employeeIdstr) {
		Integer employeeId = Integer.parseInt(employeeIdstr);
		EmployeeListDAO employeeListDAO = DAOFactory.getFactory().getEmployeeListDAO();
		employeeListDAO.deleteEmployee(employeeId);
	}

	
	public void createTimeTable(String employeeIdTimeTable, String dateTimeTable, String timeTimeTable, String orderIdTimeTable) {
		int employeeId = Integer.parseInt(employeeIdTimeTable);
		int orderId = Integer.parseInt(orderIdTimeTable);
		Time time = Time.valueOf(timeTimeTable);
		Date date = Date.valueOf(dateTimeTable);
		TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
		timeTableDAO.createTimeTable(employeeId, date, time, orderId);
		
	}

	
	public void updateTimeTable(String timeTableId, String employeeIdTimeTable, String dateTimeTable,String timeTimeTable,
								String orderIdTimeTable) {
		int id = Integer.parseInt(timeTableId);
		TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
		System.out.println("updateTimeTable dateTimeTable before" +dateTimeTable);
		if(isNoneEmpty(dateTimeTable)){
			System.out.println("updateTimeTable dateTimeTable" +dateTimeTable);
			Date date = Date.valueOf(dateTimeTable);
			timeTableDAO.updateDate(id, date);
		}
		if(isNoneEmpty(employeeIdTimeTable)){
			int employeeId = Integer.parseInt(employeeIdTimeTable);
			timeTableDAO.updateEmployee(id, employeeId);
		}
		if(isNoneEmpty(timeTimeTable)){
			Time time = Time.valueOf(timeTimeTable+":00");
			timeTableDAO.updateTime(id, time);
		}
		if(isNoneEmpty(orderIdTimeTable)){
			int orderId = Integer.parseInt(orderIdTimeTable);
			timeTableDAO.updateOrder(id, orderId);
		}
	}

	
	public void deleteTimeTable(String timeTableId) {
		int id = Integer.parseInt(timeTableId);
		TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
		timeTableDAO.deleteTimeTable(id);
	}

	
	public void updateOrder(String orderId, String status) {
		Integer id = Integer.parseInt(orderId);
		OrdersDAO ordersDAO = DAOFactory.getFactory().getOrdersDAO();
		ordersDAO.updateOrderStatus(id, status);
		
	}

	
	public void createServicesToSttions(String stationSTS, String serviceSTS) {
		int stationId = UtilsDAO.getId("service_station", "station_name", stationSTS);
		int serviceId = UtilsDAO.getId("services", "service_type", serviceSTS);
		ServicesToStationsDAO servicesToStationsDAO = DAOFactory.getFactory().getServicesToStationsDAO();
		servicesToStationsDAO.createServiceToStation(serviceId, stationId);
	}

	
	public void updateServicesToSttions(String servicesToStationsId, String stationSTS, String serviceSTS) {
		Integer id = Integer.parseInt(servicesToStationsId);
		int stationId = UtilsDAO.getId("service_station", "station_name", stationSTS);
		System.out.println("stationId "+stationId);
		int serviceId = UtilsDAO.getId("services", "service_type", serviceSTS);
		System.out.println("serviceId "+serviceId);
		ServicesToStationsDAO servicesToStationsDAO = DAOFactory.getFactory().getServicesToStationsDAO();
		if(isNoneEmpty(serviceSTS)){
			servicesToStationsDAO.updateService(id, serviceId);
		}
		if(isNoneEmpty(stationSTS)){
			servicesToStationsDAO.updateStation(id, stationId);
		}
	}

	
	public void deleteServicesToSttions(String servicesToStationsId) {
		Integer id = Integer.parseInt(servicesToStationsId);
		ServicesToStationsDAO servicesToStationsDAO = DAOFactory.getFactory().getServicesToStationsDAO();
		servicesToStationsDAO.deleteServicesToStations(id);
	}

	
	public void createPositionService(String positionPS, String servicePS) {
		int positionId = UtilsDAO.getId("employee_position", "position", positionPS);
		int serviceId = UtilsDAO.getId("services", "service_type", servicePS);
		PositionServiceDAO positionServiceDAO = DAOFactory.getFactory().getPositionServiceDAO();
		positionServiceDAO.createPositionService(positionId, serviceId);
	}

	
	public void updatePositionService(String positionServiceId, String positionPS, String servicePS) {
		Integer id = Integer.parseInt(positionServiceId);
		PositionServiceDAO positionServiceDAO = DAOFactory.getFactory().getPositionServiceDAO();
		if(isNoneEmpty(positionPS)){
			int positionId = UtilsDAO.getId("employee_position", "position", positionPS);
			positionServiceDAO.updatePosition(id, positionId);
		}
		if(isNoneEmpty(servicePS)){
			int serviceId = UtilsDAO.getId("services", "service_type", servicePS);
			positionServiceDAO.updateService(id, serviceId);
		}
	}

	
	public void deletePositionService(String positionServiceId) {
		Integer id = Integer.parseInt(positionServiceId);
		PositionServiceDAO positionServiceDAO = DAOFactory.getFactory().getPositionServiceDAO();
		positionServiceDAO.deletePositionService(id);
	}
	

}
