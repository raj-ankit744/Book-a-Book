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
		String radio = request.getParameter("searchtype");
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(request.getParameter("isbn_text"));*/
		ArrayList<Post> result = new ArrayList<>();
		
		result = dm.searchBook(radio, request.getParameter("isbn_text"), request.getParameter("title_text"), request.getParameter("author_text"));
		request.setAttribute("result", result);
		
		/*if(result.size()>0)
		out.println(result.get(0).getB().getIsbn());*/
		request.getRequestDispatcher("/WEB-INF/views/search.jsp").include(request, response);
	}
}
