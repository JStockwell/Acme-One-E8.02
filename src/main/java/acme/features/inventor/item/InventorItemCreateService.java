package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.utility.TextValidator;

@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected TextValidator validator;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// TODO Restriccion de precio positivo
		if (!errors.hasErrors("code")) {
			Item existing;

			existing = this.repository.findItemByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.item.code.duplicated");
			
			final String technology = entity.getTechnology();
			final String description = entity.getDescription();
			final String name = entity.getName();
			
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
	public Item instantiate(final Request<Item> request) {
		assert request != null;

		Item result;
		Money mon;
		Inventor inventor;

		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		
		result = new Item();
		mon = new Money();
		mon.setAmount(.0);
		mon.setCurrency("EUR");
		result.setName("");
		result.setCode("");
		result.setTechnology("");
		result.setDescription("");
		result.setLink("");
		result.setDraft(true);
		result.setPrice(mon);
		result.setInventor(inventor);
		result.setItemType(ItemType.COMPONENT);

		return result;
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}