package acme.features.inventor.troque;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.troque.Troque;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorTroqueCreateService implements AbstractCreateService<Inventor, Troque>{
	
	@Autowired
	protected InventorTroqueRepository repository;
	
	@Autowired
	protected InventorTroqueValidation validator;
	
	@Autowired
	protected InventorItemRepository inventorRepository;

	@Override
	public boolean authorise(final Request<Troque> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Troque> request, final Troque entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setItem(this.repository.findOneItemById(request.getModel().getInteger("itemId")));
		request.bind(entity, errors, "code", "creationMoment","theme", "explanation", "startDate", "finishDate", "quantity", "moreInfo");
	}

	@Override
	public void unbind(final Request<Troque> request, final Troque entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Set<Item> all=new HashSet<>(this.repository.findAllItemsByInventor(request.getPrincipal().getActiveRoleId()));
		final Set<Item> used=new HashSet<>(this.repository.findAllItemsByInventorIfUsed(request.getPrincipal().getActiveRoleId()));
		all.removeAll(used);
		
		request.unbind(entity, model, "code", "creationMoment","theme", "explanation", "startDate", "finishDate", "quantity", "moreInfo");
		model.setAttribute("items", all);
	}

	@Override
	public Troque instantiate(final Request<Troque> request) {
		assert request != null;
		
		Troque res;
		final Date creationDate = new Date(System.currentTimeMillis()-1);
		
		res = new Troque();
		res.setCreationMoment(creationDate);
		
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
			
			errors.state(request, existing == null, "code", "inventor.troque.code.duplicated"); //TODO replace for a jsp valid thingy
			errors.state(request, mmyy.equals(mmyyCreationString) && dd.equals(ddCreationString), "code", "inventor.troque.form.error.code-not-pattern");
		}
			
		this.validator.validateTroque(request, entity, errors);
	}

	@Override
	public void create(final Request<Troque> request, final Troque entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
}
