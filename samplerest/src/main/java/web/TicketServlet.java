package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.Ticket;

@WebServlet("/ticketServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response)
			throws ServletException, IOException {

		Ticket ticket = new Ticket();
		ticket.setName(request.getParameter("name"));
		ticket.setDate(request.getParameter("date"));
		ticket.setLocation(request.getParameter("location"));
		ticket.setPrice(Integer.parseInt(request.getParameter("price")));
		ticket.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		//ticket.setInformation(request.getParameter("information"));
		
		HttpSession session = request.getSession();
		session.setAttribute(SessionKey.ticket, ticket);
		
		response.sendRedirect("DbServletTicket");
		
	}
}