package domain;

//  system rezerwacji biletow na koncerty

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.repositories.IRepositoryCatalog;
import dao.uow.IUnitOfWork;
import dao.uow.RepositoryCatalog;
import dao.uow.UnitOfWork;
import domain.model.Gig;
import domain.model.Ticket;
import domain.model.User;

public class Main {

	public static void main(String[] args) {

		// IRepositoryCatalog catalogOf;
		Gig gig1 = new Gig();
		gig1.setCategory("Rap");
		gig1.setTitle("KEPTN");
		gig1.setDescription("Zawiera sladowe ilosci przekazu");

		Ticket ticket1 = new Ticket();
		ticket1.setName("tede");
		ticket1.setDate("11.11.2016");
		ticket1.setLocation("Warszawa");
		ticket1.setPrice(30);
		ticket1.setQuantity(500);
		ticket1.setInformation(gig1);
		
		//User user1 = new User();
		//user1.setUsername("imie");
		//user1.setSurname("nazwisko");
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			IUnitOfWork uow = new UnitOfWork(connection);
			IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
			catalog.Gig().add(gig1);
			catalog.Ticket().add(ticket1);
			//catalog.User().add(user1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}