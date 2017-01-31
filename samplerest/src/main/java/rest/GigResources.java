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

import domain.model.Gig;
import rest.dto.GigDto;


@Path("/gig")
@Stateless
public class GigResources {

	Mapper mapper = new DozerBeanMapper();
	
	 @PersistenceContext
	 EntityManager entityManager;
	 
	 @GET
	  @Produces(MediaType.APPLICATION_JSON)
	    public Response getAll(){
	    	List<GigDto> result = new ArrayList<GigDto>();
	    	for(Gig g: entityManager.createNamedQuery("gig.all",Gig.class).getResultList())
	    	{
	    		result.add(mapper.map(g, GigDto.class));
	        }

	       
	        return Response.ok(new GenericEntity<List<GigDto>>(result){}).build();
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
	                .setParameter("id", id)
	                .getSingleResult();
	        if (result == null) {
	            return Response.status(404).build();
	        }
	        return Response.ok(result).build();
	    }
}