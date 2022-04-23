package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron,Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean res;
		int patronageId;
		Patronage patronage;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOne(patronageId);
		res = patronage != null && request.isPrincipal(patronageReport.getPatronage().getPatron());
		
		return res;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.repository.findOne(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code","legislation","budget","creationDate","startDate","finishDate","link");
		model.setAttribute("inventorName", entity.getInventor().getIdentity().getFullName());
		model.setAttribute("inventorEmail", entity.getInventor().getIdentity().getEmail());
		model.setAttribute("inventorCompany", entity.getInventor().getCompany());
		model.setAttribute("inventorStatement", entity.getInventor().getStatement());
		model.setAttribute("inventorLink", entity.getInventor().getLink());
		
	}
}
