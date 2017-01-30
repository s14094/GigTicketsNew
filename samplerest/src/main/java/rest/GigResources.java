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


import domain.model.Gig;


@Path("/gig")
@Stateless
public class GigResources {

	
	
	 @PersistenceContext
	 EntityManager entityManager;
	 
	 @GET
	  @Produces(MediaType.APPLICATION_JSON)
	    public Response getAll(){
	    	List<Gig> result = new ArrayList<Gig>();
	    	for(Gig g: entityManager.createNamedQuery("gig.all",Gig.class).getResultList())
	    	{
	        	result.add(g);
	        }

	       
	        return Response.ok(new GenericEntity<List<Gig>>(result){}).build();
	    }
	 
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response Add(Gig gig)
	 {
		 entityManager.persist(gig);
		 return Response.ok(gig.getId()).build();	
	 }
	 
	 
	 @GET
	    @Path("/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response get(@PathParam("id") int id) {
		 Gig result = entityManager
	                .createNamedQuery("gig.id", Gig.class)
	                .setParameter("gigId", id)
	                .getSingleResult();
	        if (result == null) {
	            return Response.status(404).build();
	        }
	        return Response.ok(result).build();
	    }
}