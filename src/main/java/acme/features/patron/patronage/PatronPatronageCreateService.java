package acme.features.patron.patronage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;

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
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		Patronage res;
		Patron patron;
		
		patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());
		res = new Patronage();
		res.setDraft(true);
		res.setPatron(patron);
		
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
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
	

}
