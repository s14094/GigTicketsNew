package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.uow.RepositoryCatalog;
import dao.repositories.IRepositoryCatalog;
import dao.uow.IUnitOfWork;
import dao.uow.UnitOfWork;
import domain.model.Gig;
import domain.model.Ticket;
import domain.model.User;


@WebServlet("/DbServletGig")
public class DbServletGig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DbServletGig() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			IUnitOfWork uow = new UnitOfWork(connection);
			IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
			HttpSession session = request.getSession();
			Gig gig = (Gig) session.getAttribute("gig");
			catalog.Gig().add(gig);
			catalog.save();
			session.removeAttribute("gig");
			response.sendRedirect("index.jsp");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
}