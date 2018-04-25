package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import helpers.DatabaseConnect;
import model.PostManager;

public class LoginServlet extends HttpServlet {
	LoginManager lm = new LoginManager();
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
				request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// lm has a method verifyEmail(email, password) of return type boolean.
		if(lm.verifyEmail(email, password))		
			request.getRequestDispatcher("/WEB-INF/views/post.jsp").forward(
					request, response);
		else {
			request.setAttribute("error", "Unrecognized user or incorrect password");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(					
				request, response);
		}
	}
}
