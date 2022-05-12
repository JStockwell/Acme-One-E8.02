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
		
		boolean res;
		int itemId;
		Item item;
		
		itemId = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(itemId);
		res = item != null && !item.isDraft() || (item.isDraft() && request.isPrincipal(item.getInventor()));
		
		return res;
	}
	
	@Override 
	public Item findOne(final Request<Item> request){
		assert request !=null;
		
		Item res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.repository.findOneItemById(id);
		
		return res;
	}
	
	@Override
	public void unbind(final Request<Item> request,final Item entity,final Model model) {
		assert request!= null;
		assert entity!= null;
		assert model!= null;
		
		request.unbind(entity, model, "name", "code","technology","description","price","link","itemType");
		model.setAttribute("draft", entity.isDraft());
		model.setAttribute("id", entity.getId());
	}

}