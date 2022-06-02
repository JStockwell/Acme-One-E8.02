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
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class QuantityUpdateService implements AbstractUpdateService<Inventor, Quantity>{

	@Autowired
	protected QuantityRepository repository;
	
	@Autowired
	protected ToolkitRepository toolkitRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request!=null;
		
		boolean result;
		int quantityId;
		Inventor inventor;
		Toolkit toolkit;
		
		quantityId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findQuantityById(quantityId).getToolkit();
		inventor = toolkit.getInventor();
		
		result = (toolkit.isDraftMode())&&(request.isPrincipal(inventor));
		
		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		
		final Item item;
		final Toolkit toolkit;
		
		final Integer itemId = Integer.valueOf(request.getModel().getAttribute("itemId").toString());
		final Integer toolkitId = Integer.valueOf(request.getModel().getAttribute("toolkitId").toString());
		
		item = this.repository.findItemById(itemId);
		toolkit = this.repository.findToolkitById(toolkitId);
		
		entity.setItem(item);
		entity.setToolkit(toolkit);
		
		request.bind(entity, errors, "toolkitId", "itemId", "itemQuantity");
		
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		model.setAttribute("draftMode", entity.getToolkit().isDraftMode());
		model.setAttribute("masterId", entity.getId());
		model.setAttribute("toolkitId", entity.getToolkit().getId());
		model.setAttribute("itemId", entity.getItem().getId());
		model.setAttribute("toolkits", this.repository.getToolkitsNotPublishedFromInventor(
			request.getPrincipal().getActiveRoleId()));
		model.setAttribute("items", this.repository.getItemsPublished());
		request.unbind(entity, model, "itemQuantity");
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request!=null;
		
		Quantity result;
		int masterId;
		
		masterId = request.getModel().getInteger("masterId");
		result = this.repository.findQuantityById(masterId);
		
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
				"itemQuantity", "quantity.tool.quantity");
			errors.state(request, itemQuantity>=1, "itemQuantity", "quantity.wrongQuantity");
		}
		final List<Quantity> quantities = this.repository.getAllQuantities();
		for(final Quantity q: quantities) {
			final Item item = q.getItem();
			final Toolkit toolkit = q.getToolkit();
			errors.state(request, !(entity.getItem().equals(item)&&entity.getToolkit().equals(toolkit)), 
				"itemQuantity", "quantity.pair.exists");
		}
		
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request!=null;
		assert entity!=null;
		
		this.repository.save(entity);
		
	}

}
