package services;

import java.sql.Time;
import java.util.ArrayList;

import beans.ClientDiscountBean;
import beans.MessagesBean;
import beans.OrderServiceBean;
import beans.OrdersBean;
import beans.ServiceStationBean;
import beans.ServicesBean;
import beans.ServicesToStationsBean;
import beans.TimeTableBean;



public interface UsersService {

	public ArrayList<String> getUserAttributes(String user);
	public <T> ArrayList<T> getTable(String returnParamName);
	public String getUserName(String login);
	public ArrayList<OrdersBean> getUserOrders(String login);
	public ArrayList<ServicesBean> getAllServices();
	public ArrayList<ServicesToStationsBean> getStationsToService(String service);
	public ArrayList<String> getFreeTimeToOrder(String service, String location, String date);
	public void createOrder(String login, String service , String location, String date, String time);
	public int createInvoice();
	public Time addIntHoursToTime(String time, String service);
	//public ArrayList<TimeTableBean> getTimeTableBeanForOrder(String date,String time,String service);
	public ArrayList<OrdersBean> getEntityForUserOrder(String date,String time);
	public ArrayList<OrderServiceBean> getEntityForUserOrder(String login, String date,String time);
	public void setClientDiscount(String login, String service);
	public int getInvoiceTotalSum(String login, String date,String time);
	public void createUser(String fullname,String birthday, String login, String password, String phone,String adress,String gender);
	public boolean isExistUser(String login);
	public ArrayList<MessagesBean> getMessagesForTopic(String topicId);
	public void createMessage(String login, String message, String topicId);
	public void deleteMessage(String messageId);
	public ArrayList<TimeTableBean> getTimeTablePeriod(String location, String from, String to );
	public ArrayList<TimeTableBean> getEmployeeTimeTablePeriod(String fullname, String from, String to );
	public ArrayList<OrdersBean> getOrdersForPeriod(String location, String from, String to);
	public ArrayList<ServiceStationBean> getAllStations();
	public int getSumOfOrdersFoPeriod(ArrayList<OrdersBean> orderBeans);
	public ClientDiscountBean getClientDsicount(String login);
	
	
	
}
