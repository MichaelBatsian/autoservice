package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.AuthService;
import services.UsersService;
import services.factory.ServiceFactory;

import static org.apache.commons.lang3.StringUtils.isNoneEmpty;


@WebServlet("/AuthController")
public class AuthController extends BaseController {
	private static final long serialVersionUID = 1L;
        
    public AuthController() {
      super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String view = "";
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String userPage = request.getParameter("userpage");
		String user = null;
		
		HttpSession session = request.getSession(true);
		
		if(isNoneEmpty(login)&&isNoneEmpty(password)){
			System.out.println("login  "+login);
			System.out.println("password  "+password);
			AuthService authorization = ServiceFactory.getFactory().getAuthServiceImpl();
			String role = authorization.getAuthorzation(login, password);
			view="AdminController";	
			
			if(role.equals("wrong")){
				user = "wrong";
				view="/WEB-INF/adminpages/loginpage.jsp";
				if(isNoneEmpty(userPage)){
					view="/WEB-INF/pages/home.jsp";
					request.setAttribute("login", login);
					request.setAttribute("password", password);
				}
				request.setAttribute("user", user);
				forwardToView(request, response, view);
			}
			
			if(role.equals("blocked")){
				user = "blocked";
				view="/WEB-INF/adminpages/loginpage.jsp";
				if(isNoneEmpty(userPage)){
					view="/WEB-INF/pages/home.jsp";
					request.setAttribute("login", login);
					request.setAttribute("password", password);
				}
				request.setAttribute("user", user);
				forwardToView(request, response, view);
			}	
			if(!role.equals("blocked")&&!role.equals("wrong")){
				if(role.equals("пользователь")){
					user = "user";
					view="/WEB-INF/pages/home.jsp";
				}
	
				if(role.equals("администратор")){
					user = "администратор";
				}
				
				if(role.equals("менеджер")){
					user ="менеджер";
				}
				
				if(role.equals("коллцентр")){
					user = "коллцентр";
				}
				
				if(role.equals("мастерская")){
					user = "мастерская";
				}
				
				UsersService usersServiceImpl = ServiceFactory.getFactory().getUsersServiceImpl();
				String userName=usersServiceImpl.getUserName(login);
				session.setAttribute("userName",userName);
				session.setAttribute("login", login);
				session.setAttribute("user",user);
				session.setAttribute("role", role);
				System.out.println("view: "+view);
				System.out.println("userPage: "+userPage);
			
				forwardToView(request, response, view);
			}
			
		}else{
			view="/WEB-INF/adminpages/loginpage.jsp";
			session.setAttribute("user", user);
			if(isNoneEmpty(userPage)){
				view="/WEB-INF/pages/home.jsp";
			}
			forwardToView(request, response, view);
		}
	}
}
