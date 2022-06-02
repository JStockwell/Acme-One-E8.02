package acme.features.patron.patronage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.Status;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected PatronPatronageValidation validator;
	
	@Autowired
	protected InventorItemRepository inventorRepository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setInventor(this.repository.findOneInventorById(request.getModel().getInteger("inventorId")));
		request.bind(entity, errors, "code", "legislation", "budget", "creationDate", "startDate", "finishDate", "link", "inventorId");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legislation", "budget", "creationDate", "startDate", "finishDate", "link");
		model.setAttribute("inventors", this.repository.findAllInventors());
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		Patronage res;
		Patron patron;
		final Date creationDate = new Date(System.currentTimeMillis()-1);
		
		patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());

		res = new Patronage();
		res.setStatus(Status.Draft);
		res.setCreationDate(creationDate);
		res.setPatron(patron);
		
		return res;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Patronage existing;
			existing = this.repository.findPatronageByCode(entity.getCode());
			errors.state(request, existing == null, "code", "patron.patronage.code.duplicated");
		}
		
		this.validator.validatePatronage(request, entity, errors);
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
}
