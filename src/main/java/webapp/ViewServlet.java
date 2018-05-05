package webapp;

import java.io.IOException;
import model.OrderManager;
import entity.Order;
import entity.Post;
import entity.Book;
import java.util.*;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		String header = (String) session.getAttribute("cur");						
		OrderManager om = new OrderManager();
		ArrayList<Order> ord = new ArrayList<Order>();
		ArrayList<Post> pst = new ArrayList<Post>();
		ArrayList<Book> book = new ArrayList<Book>();
		String username = (String)session.getAttribute("username");
		User u = User.getUser(username);		
		if(u.getType().equalsIgnoreCase("Seller"))	{
			request.setAttribute("type", "seller");
			ord = om.getOrderForSeller(username);
		}
		else {
			request.setAttribute("type", "buyer");
			ord = om.getOrder(username);
		}
		for(Order o: ord) {
			pst.add(o.getPost());
			book.add(o.getPost().getB());	
//			System.out.println(o.getPost().getPrice());
		}
//		System.out.println("hello1234");
		request.setAttribute("bookdata", book);
		request.setAttribute("postdata", pst);
		request.setAttribute("orderdata", ord);		
		request.getRequestDispatcher("/WEB-INF/views/viewpost.jsp").forward(
				request, response);
//		if( login!=null) {
//			response.sendRedirect(header);
//			return;
//		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		String header = (String) session.getAttribute("cur");			
		String orderID = request.getParameter("oid");
//		System.out.println("12");
		System.out.println(orderID + "kj");
		String userName = (String)session.getAttribute("username");
		String postID = request.getParameter("pid");
		
		ArrayList<Order> ord = new ArrayList<Order>();
		ArrayList<Post> pst = new ArrayList<Post>();
		ArrayList<Book> book = new ArrayList<Book>();
		
		OrderManager om = new OrderManager();	
		String username = (String)session.getAttribute("username");
		User u = User.getUser(username);		
		if(u.getType().equalsIgnoreCase("Seller"))	{
			request.setAttribute("type", "seller");
			ord = om.getOrderForSeller(username);
		}
		else {
			request.setAttribute("type", "buyer");
			om.cancelOrder(orderID, postID);
			ord = om.getOrder(username);
		}
				
		for(Order o: ord) {
			pst.add(o.getPost());
			book.add(o.getPost().getB());
//			System.out.println(o.getOid());
		}
		
		request.setAttribute("bookdata", book);
		request.setAttribute("postdata", pst);
		request.setAttribute("orderdata", ord);
		request.getRequestDispatcher("/WEB-INF/views/viewpost.jsp").forward(
				request, response);
//		if(login!=null) {
//			response.sendRedirect(header);
//			return;
//		}
	}
}
