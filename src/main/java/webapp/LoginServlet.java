package webapp;

import java.io.IOException;
import java.sql.*;
import helpers.DatabaseConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/post.jsp").forward(
				request, response);
	}

//	@Override
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		// user@google.com, password
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//
//		request.setAttribute("email", email);
//		request.setAttribute("password", password);
//
//		if (email.equalsIgnoreCase("user@google.com")
//				&& password.equalsIgnoreCase("password")) {
//			request.getRequestDispatcher("/first").forward(request, response);
//		} else {
//			request.setAttribute("error", "Invalid ID/Password");
//			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
//					request, response);
//		}
//	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		String user = "admin";
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		System.out.println(author);
		System.out.println(isbn);
		System.out.println(title);
		System.out.println(description);
		request.getRequestDispatcher("/WEB-INF/views/post.jsp").forward(
				request, response); 
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection("jdbc:mysql://localhost:3306/"
					, "project", "root", "1234", "com.mysql.jdbc.Driver");
			String query = "insert into postad (user, author, isbn, title, description)" 
					+ " values (?,?,?,?,?)";
			
			if(conn == null)	
				return ;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, author);
			ps.setString(3, isbn);
			ps.setString(4, title);
			ps.setString(5, description);
			ps.execute();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
