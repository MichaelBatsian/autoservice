package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import services.AdminActionService;
import services.factory.ServiceFactory;


@WebServlet("/AdminActionController")
public class AdminActionController extends BaseController {
	private static final long serialVersionUID = 1L;
       
   
    public AdminActionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		String user = (String)session.getAttribute("user");
		
		System.out.println("ADMINAaction   "+user);
		
		String adminAction = request.getParameter("adminaction");
		 
		if(user.equals("администратор")){
			String view = "AdminController";
			
			String userId = request.getParameter("userid");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String roleUser = request.getParameter("role");
			String blockedStatus = request.getParameter("blockedstatus");
			
			String roleIdRoles = request.getParameter("roleidroles");
			String roleRoles = request.getParameter("roleroles");
			
			String discountIdDiscounts = request.getParameter("discountiddiscounts");
			String discountDiscounts = request.getParameter("discountdiscounts");
			String totalSum = request.getParameter("totalsumdiscounts");
			
			String clientDiscountId = request.getParameter("clientdiscountid");
			String loginClient = request.getParameter("loginclientdiscount");
			String clientDiscount = request.getParameter("clientdiscount");
			String clientTotalsum = request.getParameter("clienttotalsum");
						
			String topicId = request.getParameter("topicid");
			String topic = request.getParameter("topic");
			
			String messageId = request.getParameter("messageid");
			String loginMessage = request.getParameter("loginmessage");
			String message = request.getParameter("message");
			String topicMessage = request.getParameter("topicmessage");
			
			String serviceId = request.getParameter("serviceid");
			String serviceType = request.getParameter("service");
			String manHours = request.getParameter("manhours");
			String servicePrice = request.getParameter("serviceprice");
			
			String stationId =  request.getParameter("stationid");
			String stationName = request.getParameter("station");
			String location = request.getParameter("location");
			
			String userdataId = request.getParameter("userdataid");
			String loginData = request.getParameter("logindata");
			String fullname = request.getParameter("fullname");
			String birthday = request.getParameter("birthday");
			String adress = request.getParameter("adress");
			String phone = request.getParameter("phone");
			String gender = request.getParameter("gender");
			
			String positionId = request.getParameter("positionId");
			String position = request.getParameter("position");
			
			String employeeId = request.getParameter("employeeId");
			String employeePosition = request.getParameter("employeePosition");
			String employeeLocation = request.getParameter("employeeLocation");
			String employeeLogin = request.getParameter("employeeLogin");
			
			String employeeIdTimeTable = request.getParameter("employeeIdTimeTable");
			String timeTableId = request.getParameter("timeTableId");
			String dateTimeTable = request.getParameter("dateTimeTable");
			String timeTimeTable = request.getParameter("timeTimeTable");
			String orderIdTimeTable = request.getParameter("orderIdTimeTable");
			
			String orderId = request.getParameter("orderId");
			String orderStatus = request.getParameter("orderStatus");
			
			String servicesToStationsId = request.getParameter("servicesToStationsId");
			System.out.println("servicesToStationsId "+servicesToStationsId);
			String stationSTS = request.getParameter("stationSTS");
			System.out.println("stationSTS "+stationSTS);
			String serviceSTS = request.getParameter("serviceSTS");
			System.out.println("serviceSTS "+serviceSTS);
			
			String positionServiceId = request.getParameter("positionServiceId");
			String positionPS = request.getParameter("positionPS");
			String servicePS = request.getParameter("servicePS");
			
			
			AdminActionService adminActionService = ServiceFactory.getFactory().getAdminActionServiceImpl();
			
			  			
			if(adminAction.equals("add")){
				
				if(isNoneEmpty(login)&&isNoneEmpty(password)&&isNoneEmpty(roleUser)){
					adminActionService.createUser(login, password, roleUser);
				}
				if(isNoneEmpty(roleRoles)){
					adminActionService.createRole(roleRoles);
				}
				if(isNoneEmpty(discountDiscounts)&&isNoneEmpty(totalSum)){
					float discount = Float.parseFloat(discountDiscounts)/100;
					adminActionService.createDiscount(discount, Integer.parseInt(totalSum));
				}
				if(isNoneEmpty(loginClient)&&isNoneEmpty(clientDiscount)&&isNoneEmpty(clientTotalsum)){
					adminActionService.createClientDiscount(loginClient, clientDiscount, clientTotalsum);
				}
				if(isNoneEmpty(topic)){
					adminActionService.createForum(topic);
				}
				if(isNoneEmpty(messageId)&&isNoneEmpty(loginMessage)&&isNoneEmpty(topicMessage)){
					adminActionService.createMessage(loginMessage, topicMessage);
				}
				if(isNoneEmpty(serviceType)&&isNoneEmpty(servicePrice)&&isNoneEmpty(manHours)){
					adminActionService.createService(serviceType, manHours, servicePrice);
				}
				if(isNoneEmpty(stationName)&&isNoneEmpty(location)){
					adminActionService.createStation(stationName, location);
				}
				if(isNoneEmpty(fullname)){
					adminActionService.createUsersdata(loginData, fullname, birthday, adress, phone, gender);
				}
				if(isNoneEmpty(position)){
					adminActionService.createPosition(position);
				}
				if(isNoneEmpty(employeePosition)){
					adminActionService.createEmployee(employeeLogin, employeePosition, employeeLocation);
				}
				if(isNoneEmpty(employeeIdTimeTable)){
					adminActionService.createTimeTable(employeeIdTimeTable, dateTimeTable, timeTimeTable, orderIdTimeTable);
				}
				if(isNoneEmpty(positionPS)&&isNoneEmpty(servicePS)){
					adminActionService.createPositionService(positionPS, servicePS);
				}
				if(isNoneEmpty(stationSTS)&&isNoneEmpty(serviceSTS)){
					adminActionService.createServicesToSttions(stationSTS, serviceSTS);
				}
								
			}
			
			if(adminAction.equals("update")){
				
				if(isNoneEmpty(userId)){
					adminActionService.updateUser(Integer.parseInt(userId), login, password, roleUser, blockedStatus);
				}	
				if(isNoneEmpty(roleIdRoles)&&isNoneEmpty(roleRoles)){
					adminActionService.updateRole(Integer.parseInt(roleIdRoles), roleRoles);
				}
				if(isNoneEmpty(discountIdDiscounts)){
					adminActionService.updateDiscount(discountIdDiscounts, discountDiscounts, totalSum);
				}
				if(isNoneEmpty(clientDiscountId)){
					adminActionService.updateClientDiscount(clientDiscountId, loginClient, clientDiscount, clientTotalsum);
				}
				if(isNoneEmpty(topicId)){
					adminActionService.updateForum(topicId, topic);
				}
				if(isNoneEmpty(messageId)){
					adminActionService.updateMessage(messageId, message);
				}
				if(isNoneEmpty(serviceId)){
					adminActionService.updateService(serviceId, serviceType, manHours, servicePrice);
				}
				if(isNoneEmpty(stationId)){
					adminActionService.updateStation(stationId, stationName, location);
				}
				if(isNoneEmpty(userdataId)){
					adminActionService.updateUsersdata(userdataId, loginData, fullname, birthday, adress, phone, gender);
				}
				if(isNoneEmpty(positionId)){
					adminActionService.updatePosition(positionId, position);
				}
				if(isNoneEmpty(employeeId)){
					adminActionService.updateEmployee(employeeId, employeeLogin, employeePosition, employeeLocation);
				}
				if(isNoneEmpty(timeTableId)){
					adminActionService.updateTimeTable(timeTableId, employeeIdTimeTable, dateTimeTable, timeTimeTable, orderIdTimeTable);
				}
				if(isNoneEmpty(orderId)&&isNoneEmpty(orderStatus)){
					adminActionService.updateOrder(orderId, orderStatus);
				}
				if(isNoneEmpty(servicesToStationsId)){
					adminActionService.updateServicesToSttions(servicesToStationsId, stationSTS, serviceSTS);
				}
				if(isNoneEmpty(positionServiceId)){
					adminActionService.updatePositionService(positionServiceId, positionPS, servicePS);
				}
			}		
			
			if(adminAction.equals("delete")){
				
				if(isNoneEmpty(userId)){
					adminActionService.deleteUser(Integer.parseInt(userId));
				}
				if(isNoneEmpty(roleIdRoles)){
					adminActionService.deleteRole(Integer.parseInt(roleIdRoles));
				}
				if(isNoneEmpty(discountIdDiscounts)){
					adminActionService.deleteDiscount(Integer.parseInt(discountIdDiscounts));
				}
				if(isNoneEmpty(clientDiscountId)){
					adminActionService.deleteClientDiscount(clientDiscountId);
				}
				if(isNoneEmpty(topicId)){
					adminActionService.deleteForum(topicId);
				}
				if(isNoneEmpty(messageId)){
					adminActionService.deleteMessage(messageId);
				}
				if(isNoneEmpty(serviceId)){
					adminActionService.deleteService(serviceId);
				}
				if(isNoneEmpty(stationId)){
					adminActionService.deleteStation(stationId);
				}
				if(isNoneEmpty(userdataId)){
					adminActionService.deleteUsersdata(userdataId);
				}
				if(isNoneEmpty(positionId)){
					adminActionService.deletePosition(positionId);
				}
				if(isNoneEmpty(employeeId)){
					adminActionService.deleteEmployee(employeeId);
				}
				if(isNoneEmpty(timeTableId)){
					adminActionService.deleteTimeTable(timeTableId);;
				}
				if(isNoneEmpty(positionServiceId)){
					adminActionService.deletePositionService(positionServiceId);
				}
				if(isNoneEmpty(servicesToStationsId)){
					adminActionService.deleteServicesToSttions(servicesToStationsId);
				}
			}
		 
			redirectToView(response, view);
			
		}
	}
}
