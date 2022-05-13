package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemPublishService implements AbstractUpdateService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int id;
		Item item;
		Inventor inventor;

		id = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(id);
		inventor = item.getInventor();
		result = item !=null && item.isDraft() && request.isPrincipal(inventor);

		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		

		if (!errors.hasErrors("code")) {
			Item existing;

			existing = this.repository.findItemByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.item.code.duplicated");
		}
		if (!errors.hasErrors("price")) {
			final Double amount=entity.getPrice().getAmount();
			final String currency=entity.getPrice().getCurrency();
			final String acceptedCurrencies=this.repository.getSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>=0 && acceptedCurrencies.contains(currency), "code", "inventor.item.money.negative");
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
