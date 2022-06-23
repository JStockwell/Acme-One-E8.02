package acme.features.inventor.troque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.troque.Troque;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorTroqueDeleteService implements AbstractDeleteService<Inventor, Troque>{
	
	@Autowired
	protected InventorTroqueRepository repository;

	@Override
	public boolean authorise(final Request<Troque> request) {
		assert request != null;

		boolean res;
		int id;
		Troque troque;
		Inventor inventor;

		id = request.getModel().getInteger("id");
		troque = this.repository.findOneTroqueById(id);
		inventor = troque.getItem().getInventor();
		res = request.isPrincipal(inventor);

		return res;
	}

	@Override
	public void bind(final Request<Troque> request, final Troque entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "creationMoment","theme", "explanation", "startDate", "finishDate", "quantity", "moreInfo");
	}

	@Override
	public void unbind(final Request<Troque> request, final Troque entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creationMoment","theme", "explanation", "startDate", "finishDate", "quantity", "moreInfo");
	}

	@Override
	public Troque findOne(final Request<Troque> request) {
		assert request != null;

		Troque res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneTroqueById(id);

		return res;
	}

	@Override
	public void validate(final Request<Troque> request, final Troque entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Troque> request, final Troque entity) {
		assert request != null;
		assert entity != null;
				
		this.repository.delete(entity);
	}

}
