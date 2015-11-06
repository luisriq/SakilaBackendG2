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

@Path("/films")
public class FilmService {

	@EJB 
	FilmFacade filmFacadeEJB;
	@EJB 
	FilmActorFacade filmActorFacadeEJB;
	@EJB
	ActorFacade actorFacade;
	Logger logger = Logger.getLogger(FilmService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Film> findAll(){
		return filmFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Film find(@PathParam("id") Integer id) {
        return filmFacadeEJB.find(id);
    }
	@GET
    @Path("{id}/actors")
    @Produces({"application/xml", "application/json"})
    public List<Actor> get(@PathParam("id") Integer id) {
		List<FilmActor> union = filmActorFacadeEJB.filtrarFilm(id);
		String s= "";
		
		List<Actor> actores = new ArrayList<Actor>();
		for(FilmActor f: union){
			s +=  f.getFilmId()+", "+f.getActorId()+"\n";
			actores.add(actorFacade.find(f.getActorId()));
		}
        return actores;
    }
	
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Film entity) {
        filmFacadeEJB.create(entity);
    }
	
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Film entity) {
    	entity.setFilmId(id.intValue());
        filmFacadeEJB.edit(entity);
    }
	

}
