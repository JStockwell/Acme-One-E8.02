package acme.features.inventor.patronage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	// Internal state ------------------------------------
	@Autowired
	protected InventorPatronageRepository repository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean res;
		int patronageId;
		Patronage patronage;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findPatronageById(patronageId);
		res = patronage != null && request.isPrincipal(patronage.getInventor());
		
		return res;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage res;
		int id;
		
		id = request.getModel().getInteger("id");
		res = this.repository.findPatronageById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "legislation", "budget", "status","creationDate", "startDate", "finishDate", "link");
		model.setAttribute("patronCompany", entity.getPatron().getCompany());
		model.setAttribute("patronStatement", entity.getPatron().getStatement());
		model.setAttribute("patronLink", entity.getPatron().getLink());
	}
	


}
