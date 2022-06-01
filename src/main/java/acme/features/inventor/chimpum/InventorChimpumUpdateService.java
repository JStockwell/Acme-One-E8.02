package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, Chimpum>{
	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected InventorChimpumValidation validator;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		boolean res;
		int id;
		Chimpum chimpum;
		Inventor inventor;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(id);
		inventor = chimpum.getItem().getInventor(); //supposing a one to one relation this should suffice
		res = request.isPrincipal(inventor);
		
		return res;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

//		entity.setItem(this.repository.findOneItemById(request.getModel().getInteger("itemId")));
		request.bind(entity, errors, "code", "creationMoment", "title", "description", "startDate", "finishDate", "budget", "link");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creationMoment", "title", "description", "startDate", "finishDate", "budget", "link");
//		model.setAttribute("items", this.repository.findAllItemsByInventor(entity.getItem().getInventor().getId()));
		}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		Chimpum res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneChimpumById(id);

		return res;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Chimpum existing;
			existing = this.repository.findChimpumByCode(entity.getCode());
			errors.state(request, existing == null || existing.getCode().equals(entity.getCode()), "code", "inventor.chimpum.code.duplicated");
		}
		
		this.validator.validateChimpum(request, entity, errors);
	}

	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
