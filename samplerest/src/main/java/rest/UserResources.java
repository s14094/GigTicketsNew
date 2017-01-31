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

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import domain.model.Ticket;
import domain.model.User;
import rest.dto.TicketDto;
import rest.dto.UserDto;


@Path("/user")
@Stateless
public class UserResources {

	Mapper mapper = new DozerBeanMapper();
	
	 @PersistenceContext
	 EntityManager entityManager;
	 
	 @GET
	  @Produces(MediaType.APPLICATION_JSON)
	    public Response getAll(){
	    	List<UserDto> result = new ArrayList<UserDto>();
	    	for(User u: entityManager.createNamedQuery("user.all",User.class).getResultList())
	    	{
	    		result.add(mapper.map(u, UserDto.class));
	        }

	       
	        return Response.ok(new GenericEntity<List<UserDto>>(result){}).build();
	    }
	 
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response Add(User user)
	 {
		 entityManager.persist(user);
		 return Response.ok(user.getId()).build();	
	 }
	 
	    @GET
	    @Path("/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response get(@PathParam("id") int id) {
	    	User result = entityManager.createNamedQuery("user.id", User.class)
	                .setParameter("id", id)
	                .getSingleResult();
	        if (result == null) {
	            return Response.status(404).build();
	        }
	        return Response.ok(result).build();
	    }
}