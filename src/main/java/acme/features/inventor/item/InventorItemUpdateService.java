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
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected TextValidator validator;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		// TODO Renombrar a id
		int masterId;
		Item item;
		Inventor inventor;

		masterId = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(masterId);
		// TODO Poner el assert en el resultado
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

		// TODO Cambiar con el validate del create service con los cambios
		if (!errors.hasErrors("code")) {
			Item existing;

			existing = this.repository.findItemByCode(entity.getCode());
			errors.state(request, existing.getId() == entity.getId(), "code", "inventor.item.code.duplicated");
			
			String technology;
			technology = request.getModel().getString("technology");
			String description;
			description = request.getModel().getString("description");
			String name;
			name = request.getModel().getString("name");
			
			errors.state(request, !this.validator.checkSpam(technology), "technology", "validator.spam");
			errors.state(request, !this.validator.checkSpam(description), "description", "validator.spam");
			errors.state(request, !this.validator.checkSpam(name), "name", "validator.spam");
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

		this.repository.save(entity);
	}

}
