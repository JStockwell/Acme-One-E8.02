package acme.features.patron.patronage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage>{
	
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
		res = patronage.isDraft() && request.isPrincipal(patron);
		
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

		request.unbind(entity, model, "status", "code", "legislation", "budget", "creationDate", "startDate", "finishDate", "link", "draft");
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
		
		if(!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative-budget"); //Se coloca los messages-view
		}
		
		if(!errors.hasErrors("startDate")) {
			final Date creationDate = entity.getCreationDate();
			final Date startDate = entity.getStartDate();
			final Long monthToMiliseconds = 2592000000l;
			
			errors.state(request, (startDate.getTime() - creationDate.getTime())/monthToMiliseconds > 1, "startDate", "patron.patronage.form.error.startDate-too-close-to-creationDate"); //Se coloca los messages-view
		}
		
		if(!errors.hasErrors("finishDate")) {
			final Date startDate = entity.getStartDate();
			final Date finishDate = entity.getFinishDate();
			final Long monthToMiliseconds = 2592000000l;
			
			errors.state(request, (finishDate.getTime() - startDate.getTime())/monthToMiliseconds > 1, "finishDate", "patron.patronage.form.error.finishDate-too-close-to-startDate"); //Se coloca los messages-view
		}
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		entity.setDraft(false);
		this.repository.save(entity);
	}

}
