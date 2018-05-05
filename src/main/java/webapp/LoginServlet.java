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

public class LoginServlet extends HttpServlet {
	UserManager um = new UserManager();
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		String header = (String) session.getAttribute("cur");
		if(login!=null) {
			response.sendRedirect(header);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
				request, response);
	}
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		HttpSession session = request.getSession(true);
		if(um.verifyuser(uid, password, usertype)) {
			if(usertype.equalsIgnoreCase("Seller")) {
				session.setAttribute("username", uid);
				session.setAttribute("type","seller");
				response.sendRedirect("/post");
			}
			else if(usertype.equalsIgnoreCase("Buyer")) {
				session.setAttribute("username", uid);
				session.setAttribute("type","buyer");
				response.sendRedirect("/search");
			}
		}
		else {
			session.invalidate();
			response.sendRedirect("/login");
		}
	}
}
