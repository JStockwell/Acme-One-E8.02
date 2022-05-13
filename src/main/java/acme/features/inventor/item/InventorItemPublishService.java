package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.utility.TextValidator;

@Service
public class InventorItemPublishService implements AbstractUpdateService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;

	@Autowired
	protected TextValidator validator;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int masterId;
		Item item;
		Inventor inventor;

		masterId = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(masterId);
		assert item != null;
		inventor = item.getInventor();
		result = item.isDraft() && request.isPrincipal(inventor);

		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		// TODO Añadir validacion del update
		if (!errors.hasErrors("code")) {
			Item existing;

			existing = this.repository.findItemByCode(entity.getCode());
			errors.state(request, existing.getId() == entity.getId(), "code", "inventor.item.code.duplicated");
			
			final String technology = entity.getTechnology();
			final String description = entity.getDescription();
			final String name = entity.getName();
			
			errors.state(request, !this.validator.checkSpam(technology), "technology", "validator.spam");
			errors.state(request, !this.validator.checkSpam(description), "description", "validator.spam");
			errors.state(request, !this.validator.checkSpam(name), "name", "validator.spam");
			
			existing = this.repository.findItemByCode(entity.getCode());
			errors.state(request, existing.getId() == entity.getId(), "code", "inventor.item.code.duplicated");
		}
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name","description","technology","link","itemType","price","code");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name","description","technology","link","itemType","price","code");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);

		return result;
	}

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		entity.setDraft(false);
		this.repository.save(entity);
	}

}
