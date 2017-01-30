package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import domain.model.Ticket;


@Path("/ticket")
@Stateless
public class TicketResources {

	
	
	 @PersistenceContext
	 EntityManager entityManager;
	 
	 @GET
	  @Produces(MediaType.APPLICATION_JSON)
	    public Response getAll(){
	    	List<Ticket> result = new ArrayList<Ticket>();
	    	for(Ticket t: entityManager.createNamedQuery("ticket.all",Ticket.class).getResultList())
	    	{
	        	result.add(t);
	        }

	       
	        return Response.ok(new GenericEntity<List<Ticket>>(result){}).build();
	    }
	 
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response Add(Ticket ticket)
	 {
		 entityManager.persist(ticket);
		 return Response.ok(ticket.getId()).build();	
	 }
	 
	 
	 @GET
	    @Path("/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response get(@PathParam("id") int id) {
		 Ticket result = entityManager
	                .createNamedQuery("ticket.id", Ticket.class)
	                .setParameter("ticketId", id)
	                .getSingleResult();
	        if (result == null) {
	            return Response.status(404).build();
	        }
	        return Response.ok(result).build();
	    }
}