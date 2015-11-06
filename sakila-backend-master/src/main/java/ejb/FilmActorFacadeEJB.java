package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.FilmActorFacade;
import model.FilmActor;

@Stateless
public class FilmActorFacadeEJB extends AbstractFacade<FilmActor> implements FilmActorFacade {
	
	
	@PersistenceContext(unitName = "sakilaPU")
	private EntityManager em;
	
	public FilmActorFacadeEJB() {
		super(FilmActor.class);
	}

	public List<FilmActor> filtrarFilm(int id){
		String s = "";
		List<FilmActor> films = findAll();
		List<FilmActor> result = new ArrayList<FilmActor>();
		for (FilmActor i : films){
			
			if(i.getFilmId() == id){
				result.add(i);
			}
		}
		return result;
	}
	public List<FilmActor> filtrarActor(int id){
		String s = "";
		List<FilmActor> films = findAll();
		List<FilmActor> result = new ArrayList<FilmActor>();
		for (FilmActor i : films){
			
			if(i.getActorId() == id){
				result.add(i);
			}
		}
		return result;
	}
	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
