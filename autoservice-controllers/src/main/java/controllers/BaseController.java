package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BaseController")
public class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BaseController() {
        super();
        // TODO Auto-generated constructor stub
    }

protected static void forwardToView(HttpServletRequest request, HttpServletResponse response, String view){
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			throw new RuntimeException(e);
		} catch(IllegalStateException e){
			System.out.println("IllegalStateException");
		}
		
	}

protected static void redirectToView(HttpServletResponse response, String view){
	try {
		response.sendRedirect(view);
	} catch (IOException e) {
		throw new RuntimeException();
	}
	}
}
