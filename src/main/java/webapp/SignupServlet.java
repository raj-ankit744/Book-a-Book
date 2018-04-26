package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import helpers.DatabaseConnect;
import model.UserManager;

public class SignupServlet extends HttpServlet {
	UserManager um = new UserManager();
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		String header = (String) session.getAttribute("cur");
		if(login!=null) {
			response.sendRedirect(header);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(
				request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		String user = request.getParameter("first_name") + " " + request.getParameter("last_name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		String contact = request.getParameter("contact");
		String usertype = request.getParameter("usertype");	
		um.createUser(email,password, user , usertype, address, contact);
		response.sendRedirect("/login"); 
	}
}
