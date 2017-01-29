package dao.repositories;

import java.util.List;

import domain.model.Ticket;

public interface ITicketRepository extends IRepository<Ticket> {

	public List<Ticket> withName(String name);

	public List<Ticket> withLocation(String location);

	public List<Ticket> withDate(String date);

	public List<Ticket> byPrice(Integer price);

	public List<Ticket> byQuantity(Integer quantity);

}