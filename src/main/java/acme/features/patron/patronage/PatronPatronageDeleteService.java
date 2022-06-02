package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Patron;

@Service
public class PatronPatronageDeleteService implements AbstractDeleteService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean res;
		int id;
		Patronage patronage;
		Patron patron;

		id = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(id);
		patron = patronage.getPatron();
		res = patronage.getStatus().equals(Status.DRAFT) && request.isPrincipal(patron);

		return res;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status", "code", "legislation", "budget", "creationDate", "startDate", "finishDate", "link");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legislation", "budget", "creationDate", "startDate", "finishDate", "link");
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOnePatronageById(id);

		return res;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
				
		this.repository.delete(entity);
	}

}
