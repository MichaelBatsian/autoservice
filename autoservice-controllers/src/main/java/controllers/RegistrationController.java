package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.AdminActionService;
import services.factory.ServiceFactory;

@WebServlet("/RegistrationController")
public class RegistrationController extends BaseController {
	private static final long serialVersionUID = 1L;
 
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String middlename = request.getParameter("middlename");
		String birthday = request.getParameter("birthday");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String adress = request.getParameter("adress");
				
		StringBuilder fullname = new StringBuilder();
		fullname.append(lastname);
		fullname.append(firstname);
		fullname.append(middlename);
		
		HttpSession session = request.getSession(true);
		String confirmRegistration = "";
		String view = "";
		
		if(password.equals(confirmPassword)){
			AdminActionService adminActionService = ServiceFactory.getFactory().getAdminActionServiceImpl();
			adminActionService.createUser(login, password, "пользователь");
			adminActionService.createUsersdata(login, fullname.toString(), birthday, adress, phone, gender);
			confirmRegistration="sucessfull";
			session.setAttribute("user", login);
			view = "/WEB-INF/pages/home.jsp";
		}else{
			confirmRegistration="wrongPassword";
			view = "/WEB-INF/pages/registration.jsp";
		}
		request.setAttribute("confirmPassword", confirmRegistration);
		forwardToView(request, response, view);
			
	}

}
