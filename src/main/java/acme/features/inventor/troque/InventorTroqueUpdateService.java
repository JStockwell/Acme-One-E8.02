package acme.features.inventor.troque;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.troque.Troque;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorTroqueUpdateService implements AbstractUpdateService<Inventor, Troque>{
	
	@Autowired
	protected InventorTroqueRepository repository;
	
	@Autowired
	protected InventorTroqueValidation validator;

	@Override
	public boolean authorise(final Request<Troque> request) {
		assert request != null;
		
		boolean res;
		int id;
		Troque troque;
		Inventor inventor;
		
		id = request.getModel().getInteger("id");
		troque = this.repository.findOneTroqueById(id);
		inventor = troque.getItem().getInventor(); //supposing a one to one relation this should suffice
		res = request.isPrincipal(inventor);
		
		return res;
	}

	@Override
	public void bind(final Request<Troque> request, final Troque entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

//		entity.setItem(this.repository.findOneItemById(request.getModel().getInteger("itemId")));
		request.bind(entity, errors, "code", "creationMoment","theme", "explanation", "startDate", "finishDate", "quantity", "moreInfo");
	}

	@Override
	public void unbind(final Request<Troque> request, final Troque entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creationMoment","theme", "explanation", "startDate", "finishDate", "quantity", "moreInfo");
//		model.setAttribute("items", this.repository.findAllItemsByInventor(entity.getItem().getInventor().getId()));
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
		
		if (!errors.hasErrors("code")) {
			Troque existing;
			existing = this.repository.findTroqueByCode(entity.getCode());
			
			final String code = entity.getCode();
			final String[] splitter = code.split(":");
			final String mmyy = splitter[1];
			final String dd = splitter[2];
			
			final Calendar calendar = new GregorianCalendar();

			calendar.setTime(entity.getCreationMoment());
			final Integer yyCreation = Calendar.getInstance().get(Calendar.YEAR)-2000;
			final Integer mmCreation = Calendar.getInstance().get(Calendar.MONTH) + 1;
			final Integer ddCreation = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			
			final String mmyyCreationString = mmCreation.toString().concat(yyCreation.toString());
			final String ddCreationString = ddCreation.toString();

			
			errors.state(request, existing == null || existing.getCode().equals(entity.getCode()), "code", "inventor.chimpum.code.duplicated");
			errors.state(request, mmyy.equals(mmyyCreationString) && dd.equals(ddCreationString), "code", "inventor.chimpum.form.error.code-not-pattern");
		}
		
		this.validator.validateTroque(request, entity, errors);
	}

	@Override
	public void update(final Request<Troque> request, final Troque entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
