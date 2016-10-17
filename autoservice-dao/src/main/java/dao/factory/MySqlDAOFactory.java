package dao.factory;

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
import dao.mysql.MySqlAdminDAO;
import dao.mysql.MySqlClientDiscountDAO;
import dao.mysql.MySqlDiscountsDAO;
import dao.mysql.MySqlEmployeeListDAO;
import dao.mysql.MySqlEmployeePositionDAO;
import dao.mysql.MySqlForumDAO;
import dao.mysql.MySqlInvoiceDAO;
import dao.mysql.MySqlMessageDAO;
import dao.mysql.MySqlOrdersDAO;
import dao.mysql.MySqlPositionServiceDAO;
import dao.mysql.MySqlRolesDAO;
import dao.mysql.MySqlServiceStationDAO;
import dao.mysql.MySqlServicesDAO;
import dao.mysql.MySqlServicesToStationsDAO;
import dao.mysql.MySqlTimeTableDAO;
import dao.mysql.MySqlUserdataDAO;
import dao.mysql.MySqlUsersDAO;

public class MySqlDAOFactory extends DAOFactory {

	public MySqlDAOFactory() {
		super();
		
	}
	
	public UsersDAO getUserDAO(){
		return new MySqlUsersDAO();
	}
	
	public RolesDAO getRoleDAO(){
		return new MySqlRolesDAO();
	}
	
	public AdminDAO getAdminDAO(){
		return new MySqlAdminDAO();
		
	}
	
	public DiscountsDAO getDiscountsDAO(){
		return new MySqlDiscountsDAO();
	}

	public EmployeeListDAO getEmployeeListDAO() {
		return new MySqlEmployeeListDAO();
	}

	@Override
	public ClientDiscountDAO getClientDiscountDAO() {
		return new MySqlClientDiscountDAO();
	}

	@Override
	public MessagesDAO getMessagesDAO() {
		return new MySqlMessageDAO();
	}

	@Override
	public ForumDAO getForumDAO() {
		return new MySqlForumDAO();
	}

	@Override
	public TimeTableDAO getTimeTableDAO() {
		return new MySqlTimeTableDAO();
	}

	@Override
	public ServiceStationDAO getServiceStationDAO() {
		return new MySqlServiceStationDAO();
	}

	@Override
	public ServicesDAO getServiceDAO() {
		return new MySqlServicesDAO();
	}

	@Override
	public ServicesToStationsDAO getServicesToStationsDAO() {
		return new MySqlServicesToStationsDAO();
	}

	@Override
	public OrdersDAO getOrdersDAO() {
		return new MySqlOrdersDAO();
	}

	@Override
	public InvoiceDAO getInvoiceDAO() {
		return new MySqlInvoiceDAO();
	}

	@Override
	public UserdataDAO getUserdataDAO() {
		return new MySqlUserdataDAO();
	}

	@Override
	public PositionServiceDAO getPositionServiceDAO() {
		return new MySqlPositionServiceDAO();
	}

	@Override
	public EmployeePositionDAO getEmployeePositionDAO() {
		return new MySqlEmployeePositionDAO();
	}
	
	

}
