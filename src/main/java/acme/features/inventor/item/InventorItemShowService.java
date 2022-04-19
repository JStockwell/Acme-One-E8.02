package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor,Item>{
	
	@Autowired
	protected InventorItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Item findOne(final Request<Item> request){
		assert request !=null;
		
		Item res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.repository.findOneComponentById(id);
		
		return res;
	}
	
	@Override
	public void unbind(final Request<Item> request,final Item entity,final Model model) {
		assert request!= null;
		assert entity!= null;
		assert model!= null;
		
		request.unbind(entity, model, "name", "code","technology","description","retailPrice","link","itemType");
	}

}