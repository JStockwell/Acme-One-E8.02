package acme.features.any.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any,Toolkit>{
	
	@Autowired
	protected AnyToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Toolkit findOne(final Request<Toolkit> request){
		assert request !=null;
		
		Toolkit res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.repository.findToolkitById(id);
		
		return res;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request,final Toolkit entity,final Model model) {
		assert request!= null;
		assert entity!= null;
		assert model!= null;
		
		request.unbind(entity, model, "code","title","description","assemblyNotes","link");
	}

}