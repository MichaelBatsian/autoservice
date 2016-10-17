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

public abstract class DAOFactory {

	public DAOFactory() {
		
	}
	
	public abstract UsersDAO getUserDAO();
	public abstract RolesDAO getRoleDAO();
	public abstract AdminDAO getAdminDAO();
	public abstract DiscountsDAO getDiscountsDAO();
	public abstract EmployeeListDAO getEmployeeListDAO();
	public abstract ClientDiscountDAO getClientDiscountDAO();
	public abstract MessagesDAO getMessagesDAO();
	public abstract ForumDAO getForumDAO();
	public abstract TimeTableDAO getTimeTableDAO();
	public abstract ServiceStationDAO getServiceStationDAO();
	public abstract ServicesDAO getServiceDAO();
	public abstract ServicesToStationsDAO getServicesToStationsDAO();
	public abstract OrdersDAO getOrdersDAO();
	public abstract InvoiceDAO getInvoiceDAO();
	public abstract UserdataDAO  getUserdataDAO();
	public abstract PositionServiceDAO getPositionServiceDAO();
	public abstract EmployeePositionDAO getEmployeePositionDAO();
	
	public static DAOFactory getFactory(){
		return new MySqlDAOFactory();
	}
	
	

	
	

}
