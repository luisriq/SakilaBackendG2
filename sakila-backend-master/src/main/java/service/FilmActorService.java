package service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ejb.ActorFacadeEJB;
import facade.ActorFacade;
import facade.FilmActorFacade;
import facade.FilmFacade;
import model.Actor;
import model.Film;
import model.FilmActor;

@Path("/film-actor")
public class FilmActorService {

	@EJB 
	FilmActorFacade filmActorFacadeEJB;
	Logger logger = Logger.getLogger(FilmActorService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<FilmActor> findAll(){
		return filmActorFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}/{id2}")
    @Produces({"application/xml", "application/json"})
    public FilmActor find(@PathParam("id") Integer id, @PathParam("id2") Integer id2) {
		List<FilmActor> all = filmActorFacadeEJB.findAll();
		for(FilmActor f:all){
			if(f.getFilmId() == id && f.getActorId() == id2){
				return f;
			}
		}
        return null;
    }
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public List<FilmActor> find(@PathParam("id") Integer id) {
		List<FilmActor> all = filmActorFacadeEJB.findAll();
		List<FilmActor> result = new ArrayList<>();
		for(FilmActor f:all){
			if(f.getFilmId() == id){
				result.add(f);
			}
		} 
        return result;
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(FilmActor entity) {
        filmActorFacadeEJB.create(entity);
    }
	
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, FilmActor entity) {
    	entity.setFilmId(id.intValue());
        filmActorFacadeEJB.edit(entity);
    }
	

}
