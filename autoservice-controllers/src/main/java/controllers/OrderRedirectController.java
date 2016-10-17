package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderRedirectController")
public class OrderRedirectController extends BaseController {
	private static final long serialVersionUID = 1L;
       

    public OrderRedirectController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view ="/WEB-INF/pages/order.jsp";
		
		forwardToView(request, response, view);
		
		
		
		
	}

}
