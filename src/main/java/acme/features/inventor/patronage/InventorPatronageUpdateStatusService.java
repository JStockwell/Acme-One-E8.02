package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class InventorPatronageUpdateStatusService implements AbstractUpdateService<Inventor, Patronage>{

	
	@Autowired
	protected InventorPatronageRepository repo;
	
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		int patronageId;
		Patronage patronage;

		patronageId = request.getModel().getInteger("id");
		patronage = this.repo.findPatronageById(patronageId);
		result = request.getPrincipal().getActiveRoleId() == patronage.getInventor().getId();

		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "legislation", "budget", "status","creationDate", "startDate", "finishDate", "link");

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage patronage, final Model model) {
		assert request != null;
		assert patronage != null;
		assert model != null;
		
		final Patron patron = patronage.getPatron();
		final UserAccount patronAccount = patron.getUserAccount();
		final String patronLink = patron.getLink();
		
		request.unbind(patronage, model, "code", "legislation", "budget", "status","creationDate", "startDate", "finishDate", "link");

		
		model.setAttribute("company", patron.getCompany());
		model.setAttribute("statement", patron.getStatement());
		model.setAttribute("username", patronAccount.getUsername());
		model.setAttribute("patronageId", patronage.getId());
		model.setAttribute("patronLink", patronLink);
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repo.findPatronageById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage patronage, final Errors errors) {
		assert request != null;
		assert patronage != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage patronage) {
		assert request != null;
		assert patronage != null;
		
		Status status;
		
		status = Status.valueOf(request.getModel().getString("status"));
		
		patronage.setStatus(status);
		this.repo.save(patronage);
	}

}
