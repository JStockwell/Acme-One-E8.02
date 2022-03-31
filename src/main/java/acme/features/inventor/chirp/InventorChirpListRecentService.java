package acme.features.inventor.chirp;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChirpListRecentService implements AbstractListService<Inventor,Chirp>{
	
	@Autowired
	protected InventorChirpRepository repository;
	
	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Collection<Chirp> findMany(final Request<Chirp> request){
		assert request !=null;
		
		final Collection<Chirp> res;
		Calendar calendar;
		final Date deadline;
		
		calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline=calendar.getTime();
		
		res= this.repository.findRecentChirps(deadline);
		
		return res;
	}
	
	@Override
	public void unbind(final Request<Chirp> request,final Chirp entity,final Model model) {
		assert request!= null;
		assert entity!= null;
		assert model!= null;
		
		request.unbind(entity, model, "title", "moment","autor","body","mail");
	}

}
