package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import helpers.DatabaseConnect;

public class SignupServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		long contact = Long.parseLong(request.getParameter("contact"));
		String usertype = request.getParameter("usertype");		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
				request, response); 
		
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "insert into user (uid, password, name, usertype, contact, address)" 
					+ " values (?,?,?,?,?,?)";
			
			if(conn == null)	
				return ;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, user);
			ps.setString(4, usertype);
			ps.setLong(5, contact);
			ps.setString(6, address);
			ps.execute();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
