package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class QuantityDeleteService implements AbstractDeleteService<Inventor, Quantity>{

	@Autowired
	protected QuantityRepository repository;
	
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
		
		
	}


	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request!=null;
		assert entity!=null;
		
		this.repository.delete(entity);
		
	}

}
