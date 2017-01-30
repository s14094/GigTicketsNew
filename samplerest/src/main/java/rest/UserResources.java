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


import domain.model.User;


@Path("/user")
@Stateless
public class UserResources {

	
	 @PersistenceContext
	 EntityManager entityManager;
	 
	 @GET
	  @Produces(MediaType.APPLICATION_JSON)
	    public Response getAll(){
	    	List<User> result = new ArrayList<User>();
	    	for(User u: entityManager.createNamedQuery("user.all",User.class).getResultList())
	    	{
	        	result.add(u);
	        }

	       
	        return Response.ok(new GenericEntity<List<User>>(result){}).build();
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
	                .setParameter("userId", id)
	                .getSingleResult();
	        if (result == null) {
	            return Response.status(404).build();
	        }
	        return Response.ok(result).build();
	    }
}