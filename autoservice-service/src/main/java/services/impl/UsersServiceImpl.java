package services.impl;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;

import beans.ClientDiscountBean;
import beans.DiscountsBean;
import beans.MessagesBean;
import beans.OrderServiceBean;
import beans.OrdersBean;
import beans.ServiceStationBean;
import beans.ServicesBean;
import beans.ServicesToStationsBean;
import beans.TimeTableBean;
import beans.UserBean;
import dao.AdminDAO;
import dao.ClientDiscountDAO;
import dao.DiscountsDAO;
import dao.EmployeeListDAO;
import dao.EmployeePositionDAO;
import dao.ForumDAO;
import dao.InvoiceDAO;
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
import services.UsersService;
import services.utils.UtilsService;

public class UsersServiceImpl implements UsersService{
	
	public UsersServiceImpl(){
		
	}

	public ArrayList<String> getUserAttributes(String user) {
		
		ArrayList<String> userTables = new ArrayList<String>();
		
		if(user.equals("администратор")){
		AdminDAO adminDAO = DAOFactory.getFactory().getAdminDAO(); 
		userTables = adminDAO.getTables();
		}
		
		if(user.equals("менеджер")){
			userTables.add("Расписание за период");
			userTables.add("Расписание работника");
			userTables.add("Заказы за период");
		}
		
		if(user.equals("мастерская")){
			userTables.add("Расписание работника");
		}
		
		if(user.equals("коллцентр")){
			userTables.add("Перейти на форум");
		}
			
		return userTables;
	}


	
	@SuppressWarnings("unchecked")
	public <T> ArrayList <T> getTable (String returnParametrName){
		
		ArrayList<T> table=null; 
	
		try{	
			if(returnParametrName.equals("users")){
				UsersDAO userDAO = DAOFactory.getFactory().getUserDAO();
				table = (ArrayList<T>) userDAO.getUserNameTable();
			}

			if(returnParametrName.equals("time_table")){
				TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
				table = (ArrayList<T>) timeTableDAO.getTimeTable();
			}

			if(returnParametrName.equals("roles")){
				RolesDAO rolesDAO = DAOFactory.getFactory().getRoleDAO();
				table = (ArrayList<T>) rolesDAO.getRolesTable();
			}
			if(returnParametrName.equals("discounts")){
				DiscountsDAO discountDAO = DAOFactory.getFactory().getDiscountsDAO();
				table = (ArrayList<T>) discountDAO.getDiscountsTable();
			} 
			if(returnParametrName.equals("client_discount")){
				ClientDiscountDAO clientDiscountDAO = DAOFactory.getFactory().getClientDiscountDAO();
				table = (ArrayList<T>) clientDiscountDAO.getClientDiscountNameTable();
			}
			if(returnParametrName.equals("employee_list")){
				EmployeeListDAO employeeListDAO = DAOFactory.getFactory().getEmployeeListDAO();
				table = (ArrayList<T>)  employeeListDAO.getEmployeeListNameTable();
			}
			if(returnParametrName.equals("messages")){
				MessagesDAO messagesDAO = DAOFactory.getFactory().getMessagesDAO();
				table = (ArrayList<T>) messagesDAO.getMessageNameTable();
			}
			if(returnParametrName.equals("forum")){
				ForumDAO forumDAO = DAOFactory.getFactory().getForumDAO();
				table = (ArrayList<T>) forumDAO.getForumTable();
			}
			if(returnParametrName.equals("service_station")){
				ServiceStationDAO stationDAO = DAOFactory.getFactory().getServiceStationDAO();
				table = (ArrayList<T>) stationDAO.getStationsTable();
			}
			if(returnParametrName.equals("services_to_stations")){
				ServicesToStationsDAO stsDAO = DAOFactory.getFactory().getServicesToStationsDAO();
				table = (ArrayList<T>) stsDAO.getServicesToStationsNameTable();
			}
			if(returnParametrName.equals("services")){
				ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
				table = (ArrayList<T>) servicesDAO.getServiceTable();
			}
			if(returnParametrName.equals("orders")){
				OrdersDAO ordersDAO = DAOFactory.getFactory().getOrdersDAO();
				table = (ArrayList<T>) ordersDAO.getOrdersTable();
			}
			if(returnParametrName.equals("invoice")){
				InvoiceDAO invoiceDAO = DAOFactory.getFactory().getInvoiceDAO();
				table = (ArrayList<T>) invoiceDAO.getInvoiceTable();
			}
			if(returnParametrName.equals("usersdata")){
				UserdataDAO userdataDAO = DAOFactory.getFactory().getUserdataDAO();
				table = (ArrayList<T>) userdataDAO.getUserdataNameTable();
			}
			if(returnParametrName.equals("Расписание за период")){
				TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
				table = (ArrayList<T>) timeTableDAO.getTimeTable();
			}
			if(returnParametrName.equals("employee_position")){
				EmployeePositionDAO employeePositionDAO = DAOFactory.getFactory().getEmployeePositionDAO();
				table=(ArrayList<T>)employeePositionDAO.getPositionTable();
			}
			if(returnParametrName.equals("position_service")){
				PositionServiceDAO positionServiceDAO = DAOFactory.getFactory().getPositionServiceDAO();
				table=(ArrayList<T>)positionServiceDAO.getPositionServiceTable();
			}
		
		}catch(NullPointerException e){
		 
		}
		
		return table;
	}
	
	
	public ArrayList<TimeTableBean> getTimeTablePeriod(String location, String from, String to ){
		Date fromSql = Date.valueOf(from);
		Date toSql = Date.valueOf(to);
		TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
		ArrayList<TimeTableBean> timeTablePeriods = timeTableDAO.getTimeTablePeriod(location, fromSql, toSql);
		return timeTablePeriods;
	}
	
	public ArrayList<TimeTableBean> getEmployeeTimeTablePeriod(String fullname, String from, String to ){
		Date fromSql = Date.valueOf(from);
		Date toSql = Date.valueOf(to);
		TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
		ArrayList<TimeTableBean> timeTablePeriods = timeTableDAO.getEmployeeTimeTablePeriod(fullname, fromSql, toSql);
		return timeTablePeriods;
	}
	
	public ArrayList<OrdersBean> getOrdersForPeriod(String location, String from, String to){
		Date fromSql = Date.valueOf(from);
		Date toSql = Date.valueOf(to);
		OrdersDAO ordersDAO = DAOFactory.getFactory().getOrdersDAO();
		TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
		ArrayList<OrdersBean> orderBeans = new ArrayList<OrdersBean>();
		ArrayList<TimeTableBean> timeTablePeriods=null;
		if(isNoneEmpty(location)){
			timeTablePeriods = timeTableDAO.getTimeTablePeriod(location, fromSql, toSql);
		}else{
			timeTablePeriods = timeTableDAO.getTimeTablePeriod(fromSql, toSql);
		}
		Map<Integer,Date> orderDate = new HashMap<Integer,Date>();
			for(TimeTableBean bean:timeTablePeriods){
				int orderId =bean.getOrderId();
				Date date = bean.getDate();
				orderDate.put(orderId, date);
			}
			for (Map.Entry<Integer, Date> entry : orderDate.entrySet()){
				int orderId = entry.getKey();
				Date date = entry.getValue();
				OrdersBean orderBean = ordersDAO.getOrderBeanForPeriod(orderId);
				orderBean.setDate(date);
				orderBeans.add(orderBean);
			}
		return orderBeans;	
	}
	
	public int getSumOfOrdersFoPeriod(ArrayList<OrdersBean> orderBeans){
		int totalSum=0;
		for(OrdersBean bean:orderBeans){
			totalSum+=bean.getDiscountPrice();
		}
		return totalSum;
	}

	
	public String getUserName(String login) {
		UserdataDAO userdataDAO = DAOFactory.getFactory().getUserdataDAO();
		return userdataDAO.getUserName(login);
	}

	
	public ArrayList<OrdersBean> getUserOrders(String login) {
		System.out.println("login in getUserOrders: "+login);
		OrdersDAO ordersDAO = DAOFactory.getFactory().getOrdersDAO();
		int userId=UtilsDAO.getId("users", "login", login);
		return ordersDAO.getUserOrders(userId);
	}

	
	public ArrayList<ServicesBean> getAllServices() {
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		return servicesDAO.getServiceTable();
	}
	
	
	public ArrayList<ServiceStationBean> getAllStations() {
		ServiceStationDAO stationsDAO = DAOFactory.getFactory().getServiceStationDAO();
		return stationsDAO.getStationsTable();
	}
	

	
	public ArrayList<ServicesToStationsBean> getStationsToService(String service) {
		ServicesToStationsDAO servisesToStationsDAO = DAOFactory.getFactory().getServicesToStationsDAO();
		int stationId = UtilsDAO.getId("services", "service_type", service);
		ArrayList<ServicesToStationsBean> stationsToservice = servisesToStationsDAO.getStationsToService(stationId);
		return stationsToservice;
	}

	
	public ArrayList<String> getFreeTimeToOrder(String service, String location, String date) {
		
		PositionServiceDAO positionServiceDAO = DAOFactory.getFactory().getPositionServiceDAO();
		EmployeeListDAO employeeListDAO = DAOFactory.getFactory().getEmployeeListDAO();
		TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		
		int serviceId = UtilsDAO.getId("services", "service_type", service);
		int stationId = UtilsDAO.getId("service_station", "location", location);
		Date dateSql = Date.valueOf(date);
		System.out.println("Service в IMPL "+service);
		System.out.println("dateSql в IMPL "+dateSql);
		System.out.println("stationId в IMPL "+stationId);
		int orderId=1;//default value in DB(for empty order)
		int manHoursToService = servicesDAO.getManHoursToService(serviceId);
		ArrayList<Integer> positionsId = positionServiceDAO.getAllPositionsIdtoServiceStation(serviceId, stationId);
		ArrayList<String> freeTimeToOrder=new ArrayList<String>();
		
		//get all employees for positions to service	
		for(int i = 0; i<positionsId.size();i++){
				Integer positionId = positionsId.get(i);
				ArrayList<Integer> employeesIdToPosition = employeeListDAO.getEmployeesIdToPosition(positionId,stationId);
				DateFormat format = new SimpleDateFormat("HH:mm");
					for(int j=0;j<employeesIdToPosition.size();j++){
						int employeeId = employeesIdToPosition.get(j);
						
						//В данном методе мы берем все расписание по подходящим к выполнению заказ рабочим
						//теперь надо пропарсить расписание и в соответствии с затратами человекочасов на услугу выбрать первого рабочего
						ArrayList<TimeTableBean> timeTableBeans = timeTableDAO.getTimeTableToEmployee(employeeId, orderId, dateSql, manHoursToService);
						//get timeTable for all employees
							for(int k = 0;k<timeTableBeans.size();k++){
								TimeTableBean timeTableBean = timeTableBeans.get(k);
								Time employeeFreeTime = timeTableBean.getTime();
								freeTimeToOrder.add(format.format(employeeFreeTime));
							}
					}
			}
		//convert all available times to order in sort time line
			ArrayList<String> freeTimeToOrderSort =(ArrayList<String>) UtilsService.sortUniqueElements(freeTimeToOrder);
			return freeTimeToOrderSort;
	}

	
	
	public void createOrder(String login, String service , String location, String date, String time){
		TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
		OrdersDAO ordersDAO = DAOFactory.getFactory().getOrdersDAO();
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		ServicesToStationsDAO servicesToStationsDAO = DAOFactory.getFactory().getServicesToStationsDAO();
		ClientDiscountDAO clientDiscountDAO =  DAOFactory.getFactory().getClientDiscountDAO();
	
		int serviceId = UtilsDAO.getId("services", "service_type", service);
		int stationId = UtilsDAO.getId("service_station", "location", location);
		Date dateSql = Date.valueOf(date);
		Time timeSql = Time.valueOf(time+":00");
		int orderId = 1;//default value
		int manHoursTosService = servicesDAO.getManHoursToService(serviceId);
		int userId = UtilsDAO.getId("users", "login", login);
		int serviceStationId = servicesToStationsDAO.getServicesToStationsId(serviceId, stationId);
		int timeTableId = getTimeTableIdToOrder(serviceId, stationId, dateSql, timeSql, orderId);
		int invoiceId = UtilsDAO.getMaxId("invoice_id", "invoice");
		
		ordersDAO.createOrder(userId, serviceStationId,invoiceId);
		orderId = UtilsDAO.getMaxId("order_id", "orders");
		
		setClientDiscount(login, service);
		
		ClientDiscountBean clientDiscountbean = clientDiscountDAO.getUserDiscount(userId);
		int currentTotalSum = clientDiscountbean.getClientTotalSum();
		ordersDAO.updateOrderCurrentTotalSum(orderId, currentTotalSum);	
		
		int discountPrice = getCurrentDiscountPrice(login, service);
		System.out.println("discountPrice in createOrder "+discountPrice);
		ordersDAO.updateDiscountPrice(orderId, discountPrice);
		
		int employeeId = timeTableDAO.getEmployeeIdToTimeTable(timeTableId);
		System.out.println("Order id = "+orderId+" timeTableId="+timeTableId+" employeeId ="+employeeId);
		for(int i=0;i<manHoursTosService;i++){
			timeTableDAO.updateOrder(orderId,timeTableId);
			timeTableId++;
		}
	}
	
	//the firstId must be created in the database
	
	public int createInvoice(){
		boolean status = false;
		InvoiceDAO invoiceDAO = DAOFactory.getFactory().getInvoiceDAO();
		invoiceDAO.createInvoice(status);
		int invoiceId = UtilsDAO.getMaxId("invoice_id", "invoice");
		return invoiceId;
	}
		
	private int getTimeTableIdToOrder(int serviceId, int stationId, Date dateSql, Time timeSql, Integer orderId) {
		PositionServiceDAO positionServiceDAO = DAOFactory.getFactory().getPositionServiceDAO();
		EmployeeListDAO employeeListDAO = DAOFactory.getFactory().getEmployeeListDAO();
		TimeTableDAO timeTableDAO = DAOFactory.getFactory().getTimeTableDAO();
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		
		ArrayList<Integer> positionsId = positionServiceDAO.getAllPositionsIdtoServiceStation(serviceId, stationId);
		ArrayList<Integer> availableTimeTableIdToOrder=new ArrayList<Integer>();
		int manHoursTosService = servicesDAO.getManHoursToService(serviceId);
		//get all employees for positions to service	
		for(int i = 0; i<positionsId.size();i++){
				Integer positionId = positionsId.get(i);
				ArrayList<Integer> employeesIdToPosition = employeeListDAO.getEmployeesIdToPosition(positionId, stationId);
					for(int j=0;j<employeesIdToPosition.size();j++){
						int employeeId = employeesIdToPosition.get(j);
						ArrayList<TimeTableBean> timeTableBeans = timeTableDAO.getTimeTableToEmployee(employeeId, orderId, dateSql,manHoursTosService);
						//get timeTable for all employees
							for(int k = 0;k<timeTableBeans.size();k++){
								TimeTableBean timeTableBean = timeTableBeans.get(k);
								Time employeeFreeTime = timeTableBean.getTime();
								Date employeeFreeDate =  timeTableBean.getDate();
								int timeTableId = timeTableBean.getTimeTableId();
									if(employeeFreeTime.equals(timeSql)&&employeeFreeDate.equals(dateSql)){
										availableTimeTableIdToOrder.add(timeTableId);
									}
								
							}
					}
			}
		
		Integer timeTableIdToOrder = availableTimeTableIdToOrder.get(0);
		return timeTableIdToOrder;
		
	}

	
	public ArrayList<OrdersBean> getEntityForUserOrder(String date,String time) {
		Date dateSql = Date.valueOf(date);
		Time timeSql = Time.valueOf(time+":00");
		int invoiceId = UtilsDAO.getMaxId("fk_invoice_id", "orders");
		OrdersDAO ordersDAO = DAOFactory.getFactory().getOrdersDAO();
		ArrayList<OrdersBean> ordersBean = ordersDAO.getEntityForUserOrder(invoiceId);
			for(int i=0;i<ordersBean.size();i++){
				OrdersBean bean = ordersBean.get(i);
				bean.setTime(timeSql);
				bean.setDate(dateSql);
			}
		
		return ordersBean;
	}
	
	
	public ArrayList<OrderServiceBean> getEntityForUserOrder(String login, String date,String time){
		Date dateSql = Date.valueOf(date);
		Time timeSql = Time.valueOf(time+":00");
		int invoiceId = UtilsDAO.getMaxId("fk_invoice_id", "orders");
		OrdersDAO ordersDAO = DAOFactory.getFactory().getOrdersDAO();
		ArrayList<OrdersBean> ordersBean = ordersDAO.getEntityForUserOrder(invoiceId);
		
		ArrayList<OrderServiceBean> orderServiceBeans = new ArrayList<OrderServiceBean>();
		
		UsersDAO  usersDAO = DAOFactory.getFactory().getUserDAO();
		UserBean userBean = usersDAO.getUser(login);
		int userId = userBean.getUserId();
		
		ClientDiscountDAO clientDiscountDAO = DAOFactory.getFactory().getClientDiscountDAO();
		DiscountsDAO discountsDAO = DAOFactory.getFactory().getDiscountsDAO();
		ArrayList<DiscountsBean> allDiscountsBeans = discountsDAO.getDiscountsTable();
		
		for(int i=0;i<ordersBean.size();i++){
			String fullname = ordersBean.get(i).getFullname();
			int manHours = ordersBean.get(i).getManHours();
			int orderId = ordersBean.get(i).getOrderId();
			int price = ordersBean.get(i).getPrice();
			String service = ordersBean.get(i).getService();
			String location = ordersBean.get(i).getStationLocation();
			int currentTotalSum = ordersBean.get(i).getCurrentTotalsum();
						
			OrderServiceBean orderServiceBean = new OrderServiceBean();
			ClientDiscountBean clientDiscountBean = clientDiscountDAO.getUserDiscount(userId);
			clientDiscountBean.getClientTotalSum();
		
			for(int j=(allDiscountsBeans.size()-1);j>=0;j--){
				int sumForDiscount = allDiscountsBeans.get(j).getTotalSum();
				if(currentTotalSum>=sumForDiscount){
					float currentDiscount = allDiscountsBeans.get(j).getDiscount();
					orderServiceBean.setDiscount(currentDiscount);
					break;
				}
			}
			orderServiceBean.setLocation(location);
			orderServiceBean.setFullname(fullname);
			orderServiceBean.setManHours(manHours);
			orderServiceBean.setOrderId(orderId);
			orderServiceBean.setPrice(price);
			orderServiceBean.setService(service);
			orderServiceBean.setDate(dateSql);
			orderServiceBean.setTime(timeSql);
			orderServiceBeans.add(orderServiceBean);
		}
		return orderServiceBeans;
	}
	
	
	public int getInvoiceTotalSum(String login, String date,String time){
		ArrayList<OrderServiceBean> orderServiceBean =  getEntityForUserOrder(login, date,time);
		int invoiceTotoalSum = 0;
		for(int i=0;i<orderServiceBean.size();i++){
			invoiceTotoalSum+=orderServiceBean.get(i).getDiscountPrice();
		}
		return invoiceTotoalSum;
	}
	
	
	public Time addIntHoursToTime(String time, String service){
		Time timeSql = Time.valueOf(time+":00");
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		int manHours = servicesDAO.getManHoursToService(service);
		Time endTime =UtilsService.addIntHoursToTime(timeSql, manHours);
		return endTime;
	}
	
	
	public void setClientDiscount(String login, String service){
		UsersDAO  usersDAO = DAOFactory.getFactory().getUserDAO();
		UserBean userBean = usersDAO.getUser(login);
		int userId = userBean.getUserId();
		ClientDiscountDAO clientDiscountDAO = DAOFactory.getFactory().getClientDiscountDAO();
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		int servicePrice = servicesDAO.getServicePrice(service);
		int clientDiscountId = UtilsDAO.getId("client_discount", "user_id", userId);
		
		clientDiscountDAO.updateClientTotalSum(clientDiscountId, servicePrice);
		
		DiscountsDAO discountsDAO = DAOFactory.getFactory().getDiscountsDAO();
		ClientDiscountBean clientDiscountBean = clientDiscountDAO.getUserDiscount(userId);
		ArrayList<DiscountsBean> allDiscountsBeans = discountsDAO.getDiscountsTable();
		
		for(int i=(allDiscountsBeans.size()-1);i>=0;i--){
			int sumForDiscount = allDiscountsBeans.get(i).getTotalSum();
			System.out.println("sumForDiscount  "+sumForDiscount);
			
			int clientTotalSum =clientDiscountBean.getClientTotalSum();
			System.out.println("clientTotalSum  "+clientTotalSum);
			
			if(clientTotalSum>=sumForDiscount){
				float newClientDiscount = allDiscountsBeans.get(i).getDiscount();
				int discountId = UtilsDAO.getId("discounts", "discount", newClientDiscount);
				clientDiscountDAO.updateClientDiscount(discountId,userId);	
				break;
			}
		}
	}
	
	public int getCurrentDiscountPrice(String login,String service){
		UsersDAO  usersDAO = DAOFactory.getFactory().getUserDAO();
		UserBean userBean = usersDAO.getUser(login);
		int userId = userBean.getUserId();
		ClientDiscountDAO clientDiscountDAO = DAOFactory.getFactory().getClientDiscountDAO();
		ClientDiscountBean clientDiscountBean = clientDiscountDAO.getUserDiscount(userId);
		int discountId = clientDiscountBean.getDiscountId();
		System.out.println("getCurrentDiscountPrice login "+login);
		System.out.println("getCurrentDiscountPrice userId "+userId);
	
		System.out.println("getCurrentDiscountPrice discountId "+discountId);
		DiscountsDAO discountDAO = DAOFactory.getFactory().getDiscountsDAO();
		float userDiscount = discountDAO.getDiscountBean(discountId).getDiscount();
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		int servicePrice = servicesDAO.getServicePrice(service);
		int discountprice = (int) (servicePrice-(int)servicePrice*userDiscount);
		return discountprice;
	}
	
	
	public void createUser(String fullname,String birthday, String login, String password, String phone,String adress,String gender) {
		UserdataDAO userDataDAO = DAOFactory.getFactory().getUserdataDAO();
		UsersDAO usersDAO = DAOFactory.getFactory().getUserDAO();
		ClientDiscountDAO clientDiscountDAO = DAOFactory.getFactory().getClientDiscountDAO();
		int roleId = UtilsDAO.getId("roles", "role", "пользователь");
		
		usersDAO.createUser(login, password, roleId);
		int userId = UtilsDAO.getId("users", "login",login);
		Date birthdaySql = Date.valueOf(birthday);
		userDataDAO.createUserdata(userId, fullname, birthdaySql, adress, phone, gender);
		
		clientDiscountDAO.createClientDiscount(userId, 1, 0);
	}
	
	
	public boolean isExistUser(String login){
		int userId = UtilsDAO.getId("users", "login", login);
		if(userId>0){
			return true;
		}
		return false;
	}

	
	public ArrayList<MessagesBean> getMessagesForTopic(String topicId) {
		Integer id = Integer.parseInt(topicId);
		MessagesDAO messagesDAO = DAOFactory.getFactory().getMessagesDAO();
		ArrayList<MessagesBean> beans = messagesDAO.getMessagesForTopic(id);
		return beans;
		}
	
	public void createMessage(String login, String message, String topicId) {
		System.out.println("topicId"+topicId);
		Integer id = Integer.parseInt(topicId);
		int userId = UtilsDAO.getId("users", "login", login);
		MessagesDAO messagesDAO = DAOFactory.getFactory().getMessagesDAO();
		messagesDAO.createMessage(userId, message, id);
		}
	
	
	public void deleteMessage(String messageId){
		Integer id = Integer.parseInt(messageId);
		MessagesDAO messagesDAO = DAOFactory.getFactory().getMessagesDAO();
		messagesDAO.deleteMessage(id);
	}
	
	public ClientDiscountBean getClientDsicount(String login){
		int userId = UtilsDAO.getId("users", "login", login);
		ClientDiscountDAO clientDiscountDAO = DAOFactory.getFactory().getClientDiscountDAO();
		System.out.println("getClientDsicount "+userId);
		ClientDiscountBean clientDiscountBean = clientDiscountDAO.getUserDiscount(userId);
		return clientDiscountBean;
		
	}
}
