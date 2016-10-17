package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ClientDiscountBean;
import beans.DiscountsBean;
import beans.ForumBean;
import beans.MessagesBean;
import beans.OrderServiceBean;
import beans.OrdersBean;
import beans.ServiceStationBean;
import beans.ServicesBean;
import beans.ServicesToStationsBean;
import services.EmailService;
import services.UsersService;
import services.factory.ServiceFactory;
import utils.HttpUtils;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;

@WebServlet("/UserController")
public class UserController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String view = "";
		HttpSession session = request.getSession(true);
		
		String registration = request.getParameter("registration");
		String feedback = request.getParameter("feedback");
		String home = request.getParameter("home");
		String forum = request.getParameter("forum");
		String profile = request.getParameter("profile");
		String order = request.getParameter("order");
		String login = (String)session.getAttribute("login");
		String priceList = request.getParameter("priceList");
		String exit = request.getParameter("exit");
		
		String userAccount = request.getParameter("userAccount");
		
		//order page parameters
		String allUserServices  = request.getParameter("allUserServices");
		String service = request.getParameter("service");
		String stationLocation = request.getParameter("stationLocation");
		String orderDate = request.getParameter("orderDate");
		String orderTime = request.getParameter("orderTime");
		String createOrder = request.getParameter("createOrder");
		String createInvoice = request.getParameter("createInvoice");
		//feedback page parameters
		String nameFeedback = request.getParameter("nameFeedback");
		String mail = request.getParameter("mail");
		String feedbackMessage = request.getParameter("feedbackMessage");
		//registration page parameters
		String regUser = request.getParameter("regUser");
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String middlename = request.getParameter("middlename");
		String birthday = request.getParameter("birthday");
		String regLogin = request.getParameter("regLogin");
		String passwordReg = request.getParameter("passwordReg");
		String phone = request.getParameter("phone");
		String adress = request.getParameter("adress");
		String gender = request.getParameter("gender");
		//forum pages
		String forumTopicId = request.getParameter("forumTopicId");
		String sendMessage = request.getParameter("sendMessage");
		String deleteMessage = request.getParameter("deleteMessage");
		String messageId = request.getParameter("messageId");
		
		UsersService usersService = ServiceFactory.getFactory().getUsersServiceImpl();
		System.out.println("createOrder afterRedirect "+createOrder);

		ArrayList<ServiceStationBean> stations = usersService.getAllStations();
		session.setAttribute("stations", stations);
		
		ArrayList<ServicesBean> servicesBean = usersService.getAllServices();
		session.setAttribute("ourServices", servicesBean);
		ArrayList<DiscountsBean> discountsBeansMenu = usersService.getTable("discounts");
		session.setAttribute("discountsBeansMenu", discountsBeansMenu);
		System.out.println(home);
		if (isNoneEmpty(home)){
			view = "/WEB-INF/pages/home.jsp";
		}
		
		if (isNoneEmpty(registration)){
			view="/WEB-INF/pages/registration.jsp";
		}
		
		if(isNoneEmpty(feedback)){
			view="/WEB-INF/pages/feedback.jsp";
		}
		
		if(isNoneEmpty(forum)){
			ArrayList<ForumBean> forumBeans = usersService.getTable("forum");
			session.setAttribute("forumBeans", forumBeans);
			
			view="/WEB-INF/pages/forum.jsp";
		}
		
		if(isNoneEmpty(profile)){
			view="/WEB-INF/pages/profile.jsp";
			session.setAttribute("profile", profile );
			session.setAttribute("login", login);
			
		} 
		
		if(isNoneEmpty(order)){
			view="/WEB-INF/pages/order.jsp";
			ArrayList<ServicesBean> allServices=null;
			
			if(isEmpty(service)){
				allServices = usersService.getAllServices();
				session.setAttribute("allServices", allServices);
			}
			
			if(isNoneEmpty(service)){
				request.setAttribute("service", service);
				ArrayList<ServicesToStationsBean> stationsToService = usersService.getStationsToService(service);
				session.setAttribute("stationsToService", stationsToService);
				System.out.println("stationsToService array "+stationsToService);
				String station="";
					for(int i=0;i<stationsToService.size();i++){
						station=stationsToService.get(i).getStationName();
						System.out.println("Station into UserController "+station);
				}
			}
			
			if(isNoneEmpty(orderDate)&&isNoneEmpty(stationLocation)){
				ArrayList<String> freeTimeToOrder = usersService.getFreeTimeToOrder(service, stationLocation, orderDate);
				System.out.println("freeTimeToOrder в контроллере "+freeTimeToOrder);
					if(freeTimeToOrder!=null){
						session.setAttribute("freeTimeToOrder", freeTimeToOrder);
					}
					if(freeTimeToOrder.isEmpty()){
						request.setAttribute("dateError", "на эту дату записи нет");
					}
					session.setAttribute("orderDate", orderDate);
					session.setAttribute("stationLocation", stationLocation);
			  }		
		} 
		
		if(isNoneEmpty(userAccount)){
			ClientDiscountBean userDiscountData = usersService.getClientDsicount(login);
			session.setAttribute("userDiscountData", userDiscountData);
			view="/WEB-INF/pages/profile.jsp";
			System.out.println("userAccount login "+login);
		}
		//allUserServices- this is the login, which we have to get from profile page
		if(isNoneEmpty(allUserServices)){
			view="/WEB-INF/pages/profile.jsp";
			login = (String)session.getAttribute("login");
			ArrayList<OrdersBean> userServices = usersService.getUserOrders(login);
			request.setAttribute("userServices", userServices);
		}
		
		if(isNoneEmpty(createOrder)){
			view="/WEB-INF/pages/order.jsp";
			login = (String)session.getAttribute("login");
			session.removeAttribute("stationsToService");
			session.removeAttribute("orderDate");
			request.removeAttribute("service");
			session.removeAttribute("freeTimeToOrder");
			System.out.println("login in createOrder "+login);
			usersService.createOrder(login, service, stationLocation, orderDate, orderTime);
			if(isNoneEmpty(orderDate)){
				ArrayList<OrderServiceBean> ordersToLastInvoice = usersService.getEntityForUserOrder(login,orderDate,orderTime);
				int invoiceTotalSum =usersService.getInvoiceTotalSum(login, orderDate, orderTime);
				//ArrayList<TimeTableBean> timeTableBeanToOrder = usersService.getTimeTableBeanForOrder(orderDate,orderTime,service);
				session.setAttribute("ordersToLastInvoice", ordersToLastInvoice);
				//session.setAttribute("timeTableBeanToOrder", timeTableBeanToOrder);
				session.setAttribute("invoiceTotalSum", invoiceTotalSum);
			}	
			
			redirectToView(response, "OrderRedirectController");
		}
		
		if(isNoneEmpty(createInvoice)){
			int invoiceId = usersService.createInvoice();
			session.setAttribute("invoiceId", invoiceId);
		}
		
		if(isNoneEmpty(exit)){
			session.invalidate();
			view="/WEB-INF/pages/home.jsp";
			
			session = request.getSession(true);
			session.setAttribute("stations", stations);
			session.setAttribute("ourServices", servicesBean);
			session.setAttribute("discountsBeansMenu", discountsBeansMenu);
		}
		
		if(isNoneEmpty(feedbackMessage)&&isNoneEmpty(mail)&&isNoneEmpty(nameFeedback)){
			String emailTo="aservice.by@gmail.com";
			EmailService emailService = ServiceFactory.getFactory().getEmailService();
			emailService.sendEmail(emailTo, nameFeedback, mail, feedbackMessage);
			String answer ="Ваше письмо отправлено";
			request.setAttribute("answer", answer);
			view="/WEB-INF/pages/home.jsp";
		}
		
		if(isNoneEmpty(regUser)){
			view="/WEB-INF/pages/registration.jsp";
			UsersService userservice = ServiceFactory.getFactory().getUsersServiceImpl();
			if(isEmpty(regLogin)){
				request.setAttribute("emptyLogin", "Введите Ваш e-mail");
			}else{
				if(userservice.isExistUser(regLogin)==true){
				request.setAttribute("existLogin", "Данный логин уже существует");
				}else{
					request.setAttribute("regLogin", regLogin);
					StringBuilder fullname = new StringBuilder();
					fullname.append(lastname);
					fullname.append(" ");
					fullname.append(firstname);
					fullname.append(" ");
					fullname.append(middlename);
					System.out.println("fullname to reg user "+fullname);
					userservice.createUser(fullname.toString(), birthday, regLogin, passwordReg, phone, adress, gender);
					session.setAttribute("login", regLogin);
					session.setAttribute("profile", fullname.toString());
					session.setAttribute("userName", fullname.toString());
					session.setAttribute("user", "пользователь");
					view="/WEB-INF/pages/profile.jsp";
				}
			}
		}
	
		if(isNoneEmpty(priceList)){
			HttpUtils.getPriceList(response);
			view="/WEB-INF/pages/home.jsp";
		}
		
		if(isNoneEmpty(forumTopicId)){
			String forumTopic = request.getParameter("forumTopic");
			ArrayList<MessagesBean> messagesForTopic = usersService.getMessagesForTopic(forumTopicId);
			session.setAttribute("forumTopicId", forumTopicId);
			session.setAttribute("forumTopic", forumTopic);
			session.setAttribute("messagesForTopic",messagesForTopic); 
			view="/WEB-INF/pages/forumtopic.jsp";
		}
		
		if(isNoneEmpty(sendMessage)){
			forumTopicId = (String)session.getAttribute("forumTopicId");
			UsersService userservice = ServiceFactory.getFactory().getUsersServiceImpl();
			userservice.createMessage(login, sendMessage, forumTopicId);
			ArrayList<MessagesBean> messagesForTopic = usersService.getMessagesForTopic(forumTopicId);
			session.setAttribute("messagesForTopic",messagesForTopic); 
			view="/WEB-INF/pages/forumtopic.jsp";
		}
		
		if(isNoneEmpty(deleteMessage)&&isNoneEmpty(messageId)){
			forumTopicId = (String)session.getAttribute("forumTopicId");
			UsersService userservice = ServiceFactory.getFactory().getUsersServiceImpl();
			userservice.deleteMessage(messageId);
			ArrayList<MessagesBean> messagesForTopic = usersService.getMessagesForTopic(forumTopicId);
			session.setAttribute("messagesForTopic",messagesForTopic); 
			view="/WEB-INF/pages/forumtopic.jsp";
		}
		
		forwardToView(request,response,view);
	}
	
}
