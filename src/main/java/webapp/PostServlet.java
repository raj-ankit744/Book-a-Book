package webapp;
import model.PostManager;
import java.io.IOException;
import java.sql.*;
import helpers.DatabaseConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(urlPatterns = "/post")
public class PostServlet extends HttpServlet {
	
	PostManager pm = new PostManager();
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/post.jsp").forward(
				request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		String pid = "RE12";
		String uid = "12";
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));
		System.out.println(author);
		System.out.println(isbn);
		System.out.println(title);
		System.out.println(description);
		request.getRequestDispatcher("/WEB-INF/views/post.jsp").forward(
				request, response); 
		pm.createPost(pid,isbn,title,author,uid,description,price);
	}
	
	
}
