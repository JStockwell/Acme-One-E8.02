package acme.features.any.announcement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyAnnouncementShowService implements AbstractShowService<Any,Announcement>{
	
	@Autowired
	protected AnyAnnouncementRepository repository;
	
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Announcement findOne(final Request<Announcement> request){
		assert request !=null;
		
		Announcement res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.repository.findOneAnnouncementById(id);
		
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