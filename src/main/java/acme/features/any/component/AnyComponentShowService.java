package acme.features.any.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Component;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyComponentShowService implements AbstractShowService<Any,Component>{
	
	@Autowired
	protected AnyComponentRepository repository;
	
	@Override
	public boolean authorise(final Request<Component> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Component findOne(final Request<Component> request){
		assert request !=null;
		
		Component res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.repository.findOneComponentById(id);
		
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