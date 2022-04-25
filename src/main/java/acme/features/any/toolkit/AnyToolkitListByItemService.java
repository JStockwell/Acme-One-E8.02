package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

public class AnyToolkitListByItemService implements AbstractListService<Any, Toolkit>{

	@Autowired
	protected AnyToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Collection<Toolkit> findMany(final Request<Toolkit> request){
		assert request !=null;
		
		final Collection<Toolkit> res;
		
		res= this.repository.findToolkits();
		
		return res;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request,final Toolkit entity,final Model model) {
		assert request!= null;
		assert entity!= null;
		assert model!= null;
		
		request.unbind(entity, model, "title", "assemblyNotes");
	}
	
}
