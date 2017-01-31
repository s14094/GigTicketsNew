package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.Gig;

@WebServlet("/gigServlet")
public class GigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response)
			throws ServletException, IOException {

		Gig gig = new Gig();
		gig.setCategory(request.getParameter("category"));
		gig.setTitle(request.getParameter("title"));
		gig.setDescription(request.getParameter("description"));
		HttpSession session = request.getSession();
		session.setAttribute(SessionKey.gig, gig);
		
		response.sendRedirect("DbServletGig");
		
	}
}