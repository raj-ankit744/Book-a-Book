package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Post;
import model.DisplayManager;
import model.OrderManager;

public class DisplayBookServlet extends HttpServlet{
	DisplayManager dm = new DisplayManager();
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String logout = request.getParameter("logout");
		HttpSession session = request.getSession();
		session.setAttribute("login","true");
		session.setAttribute("cur", request.getRequestURI());
		if(logout!=null || session.getAttribute("username")==null) {
			session.invalidate();
			response.sendRedirect("/signup");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("confirmSearch")!=null) {
			String radio = request.getParameter("searchtype");
			ArrayList<Post> result = new ArrayList<>();
			result = dm.searchBook(radio, request.getParameter("isbn_text"), request.getParameter("title_text"), request.getParameter("author_text"));
			request.setAttribute("result", result);
		}
		if(request.getParameter("req")!=null) {
			String isbn = request.getParameter("isbn_request_text");
			String uid = (String) request.getSession().getAttribute("username");
			boolean b = dm.requestBook(isbn,uid);
			request.setAttribute("sqlError", b);
		}
		request.getRequestDispatcher("/WEB-INF/views/search.jsp").include(request, response);
	}
}
