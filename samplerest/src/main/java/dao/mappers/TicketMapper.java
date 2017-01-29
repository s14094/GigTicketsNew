package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.model.Ticket;
import domain.model.Gig;

public class TicketMapper implements IMapResultSetIntoEntity<Ticket> {
	public Ticket map(ResultSet rs) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setId(rs.getInt("id"));
		ticket.setName(rs.getString("name"));
		ticket.setDate(rs.getString("date"));
		ticket.setLocation(rs.getString("location"));
		ticket.setPrice(rs.getInt("price"));
		ticket.setQuantity(rs.getInt("quantity"));
		ticket.setInformation((Gig)rs.getObject("ADRESS"));
		return ticket;
	}
}