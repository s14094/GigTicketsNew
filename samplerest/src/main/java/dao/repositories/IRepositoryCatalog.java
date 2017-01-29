package dao.repositories;

import java.sql.SQLException;

public interface IRepositoryCatalog {

	public IUserRepository User();

	public ITicketRepository Ticket();
	
	public IGigRepository Gig();
	
	public void save() throws SQLException;
}