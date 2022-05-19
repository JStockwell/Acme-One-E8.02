package acme.features.inventor.quantity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.inventor.toolkit.ToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class QuantityCreateService implements AbstractCreateService<Inventor, Quantity>{
	
	@Autowired
	protected QuantityRepository repository;
	
	@Autowired
	protected ToolkitRepository toolkitRepository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		
		request.bind(entity, errors, "toolkit", "item", "itemQuantity");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "toolkit", "item", "itemQuantity");
		model.setAttribute("toolkits", this.repository.getToolkitsNotPublishedFromInventor(
				request.getPrincipal().getActiveRoleId()));
		model.setAttribute("items", this.repository.getItemsPublished());
		model.setAttribute("draftMode", true);
		
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request!=null;
		
		Quantity result;
		Item item;
		Toolkit toolkit;
		
		result = new Quantity();
		
		final List<Item> items = this.repository.getItemsPublished();
		if(items.isEmpty()) {
			item = null;
		} else {
			item = items.get(0);
		}
		final List<Toolkit> toolkits = this.repository.getToolkitsNotPublishedFromInventor(
			request.getPrincipal().getActiveRoleId());
		if(toolkits.isEmpty()) {
			toolkit=null;
		} else {
			toolkit=toolkits.get(0);
		}
		result.setItem(item);
		result.setToolkit(toolkit);
		result.setItemQuantity(1);
		
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		
		if(!errors.hasErrors("itemQuantity")) {
			final Item item = entity.getItem();
			final Integer itemQuantity = entity.getItemQuantity();
			
			errors.state(request, !(item.getItemType().equals(ItemType.TOOL)&&itemQuantity>1), 
				"item", "item.tool.quantity");
			errors.state(request, !(itemQuantity<1), "item", "item.wrongQuantity");
		}
		
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request!=null;
		assert entity!=null;
		
		this.repository.save(entity);
		
	}

}
