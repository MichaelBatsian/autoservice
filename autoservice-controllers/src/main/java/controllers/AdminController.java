package controllers;

import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.EmployeePositionBean;
import beans.ForumBean;
import beans.OrdersBean;
import beans.ServiceStationBean;
import beans.ServicesBean;
import beans.TimeTableBean;
import beans.UserBean;
import beans.UserdataBean;
import services.UsersService;
import services.factory.ServiceFactory; 


@WebServlet("/AdminController")
public class AdminController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String view = "";
		HttpSession session = request.getSession(true);
		String user = (String)session.getAttribute("user");
		String returnParametrName="";
		
		String location = request.getParameter("location");
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		String employeeFullname = request.getParameter("employeeFullname");
		//String login = (String) session.getAttribute("login");
				
		if(isNoneEmpty(request.getParameter("returnParametrName"))){
			returnParametrName=request.getParameter("returnParametrName");
		} else {
			returnParametrName = (String) session.getAttribute("returnParametr");
			if(isEmpty(returnParametrName)){
				returnParametrName = request.getParameter("returnParametrName");
			}		
		}
		System.out.println("Админ контроллер возвращаемый параметр"+returnParametrName);
		String exit = request.getParameter("exit");
		UsersService userService = ServiceFactory.getFactory().getUsersServiceImpl();
		//generate list of tables for particular user
		ArrayList<String> userTables = userService.getUserAttributes(user);
		session.setAttribute("userTables", userTables);
		session.setAttribute("returnParametr", returnParametrName);
		
		if(user.equals("администратор")){
			session.setAttribute("userTable", userService.getTable(returnParametrName));
			view = "/WEB-INF/adminpages/adminpage.jsp";
			if(isNoneEmpty(returnParametrName)){
				if(returnParametrName.equals("client_discount")){
					ArrayList<UserBean> userBeans = userService.getTable("users");
					ArrayList<UserBean> discountBeans = userService.getTable("discounts");
					session.setAttribute("userBeans", userBeans);
					session.setAttribute("discountBeans", discountBeans);
				}
				if(returnParametrName.equals("usersdata")){
					ArrayList<UserBean> userBeans = userService.getTable("users");
					session.setAttribute("userBeans", userBeans);
				}
				if(returnParametrName.equals("employee_list")){
					ArrayList<UserBean> userBeans = userService.getTable("users");
					ArrayList<EmployeePositionBean> employeePositionBeans = userService.getTable("employee_position");
					ArrayList<ServiceStationBean> ServiceStationBeans = userService.getTable("service_station");
					session.setAttribute("userBeans", userBeans);
					session.setAttribute("employeePositionBeans", employeePositionBeans);
					session.setAttribute("ServiceStationBeans", ServiceStationBeans);
				}
				if(returnParametrName.equals("services_to_stations")){
					ArrayList<ServiceStationBean> serviceStationBeans = userService.getTable("service_station");
					ArrayList<ServicesBean> servicesBeans = userService.getTable("services");
					session.setAttribute("serviceStationBeans", serviceStationBeans);
					session.setAttribute("servicesBeans", servicesBeans);
				}
				if(returnParametrName.equals("position_service")){
					ArrayList<EmployeePositionBean> employeePositionBeans = userService.getTable("employee_position");
					ArrayList<ServicesBean> servicesBeans = userService.getTable("services");
					session.setAttribute("employeePositionBeans", employeePositionBeans);
					session.setAttribute("servicesBeans", servicesBeans);
				}
				
			}
			
		}
		
		if(user.equals("менеджер")){
			if(isNoneEmpty(returnParametrName)){
				if(returnParametrName.equals("Расписание за период")){
					ArrayList<ServiceStationBean> stations = userService.getAllStations();
					session.setAttribute("stations", stations);
					if(isNoneEmpty(dateFrom)&&isNoneEmpty(dateTo)&&isNoneEmpty(location)){
						ArrayList<TimeTableBean> timeTablePeriod = userService.getTimeTablePeriod(location, dateFrom, dateTo);
						request.setAttribute("timeTablePeriod", timeTablePeriod);
					}
				}
				if(returnParametrName.equals("Расписание работника")){
					ArrayList<UserdataBean> userdataBeans = userService.getTable("usersdata");
					session.setAttribute("userdataBeans", userdataBeans);
					if(isNoneEmpty(employeeFullname)&&isNoneEmpty(dateTo)&&isNoneEmpty(dateFrom)){
						ArrayList<TimeTableBean> timeTableEmployeePeriod = userService.getEmployeeTimeTablePeriod(employeeFullname, dateFrom, dateTo);
						request.setAttribute("timeTableEmployeePeriod", timeTableEmployeePeriod);
					}
				}
				if(returnParametrName.equals("Заказы за период")){
					ArrayList<ServiceStationBean> stations = userService.getAllStations();
					session.setAttribute("stations", stations);
					if(isNoneEmpty(dateTo)&&isNoneEmpty(dateFrom)){
						ArrayList<OrdersBean> ordersForPeriod = userService.getOrdersForPeriod(location,dateFrom, dateTo);
						int totalSumOfOrders = userService.getSumOfOrdersFoPeriod(ordersForPeriod);
						request.setAttribute("ordersForPeriod", ordersForPeriod);
						request.setAttribute("totalSumOfOrders", totalSumOfOrders);
					}
				}	
			}	
			view = "/WEB-INF/adminpages/employeepage.jsp";	
		}
		if(user.equals("коллцентр")){
			view = "/WEB-INF/adminpages/employeepage.jsp";	
			if(isNoneEmpty(returnParametrName)&&returnParametrName.equals("Перейти на форум")){
				ArrayList<ForumBean> forumBeans = userService.getTable("forum");
				session.setAttribute("forumBeans", forumBeans);	
				view = "/WEB-INF/pages/forum.jsp";	
			}
		}
		
		///В разработке
		if(user.equals("мастерская")){
			ArrayList<UserdataBean> userdataBeans = userService.getTable("usersdata");
			session.setAttribute("userdataBeans", userdataBeans);
			if(isNoneEmpty(employeeFullname)&&isNoneEmpty(dateTo)&&isNoneEmpty(dateFrom)){
				ArrayList<TimeTableBean> timeTableEmployeePeriod = userService.getEmployeeTimeTablePeriod(employeeFullname, dateFrom, dateTo);
				request.setAttribute("timeTableEmployeePeriod", timeTableEmployeePeriod);
			}
			view = "/WEB-INF/adminpages/employeepage.jsp";
		}	
		if(isNoneEmpty(exit)){
			session.invalidate();
			view="/WEB-INF/adminpages/loginpage.jsp";
		}
		
		forwardToView(request, response, view);
	}

}
