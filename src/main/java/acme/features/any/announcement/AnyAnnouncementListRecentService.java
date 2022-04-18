package acme.features.any.announcement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyAnnouncementListRecentService implements AbstractListService<Any,Announcement>{
	
	@Autowired
	protected AnyAnnouncementRepository repository;
	
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Collection<Announcement> findMany(final Request<Announcement> request){
		assert request !=null;
		
		final Collection<Announcement> res;
		Calendar calendar;
		final Date deadline;
		
		calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline=calendar.getTime();
		
		res= this.repository.findRecentAnnouncements(deadline);
		
		return res;
	}
	
	@Override
	public void unbind(final Request<Announcement> request,final Announcement entity,final Model model) {
		assert request!= null;
		assert entity!= null;
		assert model!= null;
		
		request.unbind(entity, model, "title", "creationMoment","body","critical","link");
	}

}