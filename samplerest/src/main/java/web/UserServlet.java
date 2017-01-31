package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.User;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setSurname(request.getParameter("surname"));
		HttpSession session = request.getSession();
		session.setAttribute(SessionKey.user, user);
		
		response.sendRedirect("DbServletUser");
		
	}
}