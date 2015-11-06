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

import facade.ActorFacade;
import facade.FilmActorFacade;
import facade.FilmFacade;
import model.Actor;
import model.Film;
import model.FilmActor;

@Path("/actors")
public class ActorService {
	@EJB 
	FilmFacade filmFacadeEJB;
	@EJB 
	FilmActorFacade filmActorFacadeEJB;
	@EJB 
	ActorFacade actorFacadeEJB;
	
	Logger logger = Logger.getLogger(ActorService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Actor> findAll(){
		return actorFacadeEJB.findAll();
	}
	@GET
    @Path("{id}/films")
    @Produces({"application/xml", "application/json"})
    public List<Film> get(@PathParam("id") Integer id) {
		List<FilmActor> union = filmActorFacadeEJB.filtrarActor(id);
		List<Film> films = new ArrayList<Film>();
		for(FilmActor f: union){
			films.add(filmFacadeEJB.find(f.getFilmId()));
		}
        return films;
    }
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Actor find(@PathParam("id") Integer id) {
        return actorFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Actor entity) {
        actorFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Actor entity) {
    	entity.setActorId(id.intValue());
        actorFacadeEJB.edit(entity);
    }
	

}
