package acme.features.any.component;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Component;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyComponentListService implements AbstractListService<Any,Component>{
	
	@Autowired
	protected AnyComponentRepository repository;
	
	@Override
	public boolean authorise(final Request<Component> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Collection<Component> findMany(final Request<Component> request){
		assert request !=null;
		
		final Collection<Component> res;
		
		res= this.repository.findComponents();
		
		return res;
	}
	
	@Override
	public void unbind(final Request<Component> request,final Component entity,final Model model) {
		assert request!= null;
		assert entity!= null;
		assert model!= null;
		
		request.unbind(entity, model, "name", "code","technology","description","retailPrice","link");
	}

}