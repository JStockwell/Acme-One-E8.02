package acme.features.inventor.chimpum;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum>{
	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected InventorChimpumValidation validator;
	
	@Autowired
	protected InventorItemRepository inventorRepository;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setItem(this.repository.findOneItemById(request.getModel().getInteger("itemId")));
		request.bind(entity, errors, "code", "title", "description", "startDate", "finishDate", "budget", "link");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Set<Item> all=new HashSet<>(this.repository.findAllItemsByInventor(request.getPrincipal().getActiveRoleId()));
		final Set<Item> used=new HashSet<>(this.repository.findAllItemsByInventorIfUsed(request.getPrincipal().getActiveRoleId()));
		all.removeAll(used);
		
		request.unbind(entity, model, "code", "creationMoment", "title", "description", "startDate", "finishDate", "budget", "link");
		model.setAttribute("items", all);
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;
		
		Chimpum res;
		final Date creationDate = new Date(System.currentTimeMillis()-1);
		
		res = new Chimpum();
		res.setCreationMoment(creationDate);
		
		return res;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Chimpum existing;
			existing = this.repository.findChimpumByCode(entity.getCode());
			
			final String code = entity.getCode();
			final String[] splitter = code.split("-");
			final Integer yy = Integer.valueOf(splitter[1]);
			final Integer mm = Integer.valueOf(splitter[3]);
			final Integer dd = Integer.valueOf(splitter[4]);
			
			final Calendar calendar = new GregorianCalendar();

			calendar.setTime(entity.getCreationMoment());
			final Integer yyCreation = Calendar.getInstance().get(Calendar.YEAR)-2000;
			final Integer mmCreation = Calendar.getInstance().get(Calendar.MONTH) + 1;
			final Integer ddCreation = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			
			errors.state(request, existing == null, "code", "inventor.chimpum.code.duplicated"); //TODO replace for a jsp valid thingy
			errors.state(request, yy.equals(yyCreation) && mm.equals(mmCreation) && dd.equals(ddCreation), "code", "inventor.chimpum.form.error.code-not-pattern");
		}
			
		this.validator.validateChimpum(request, entity, errors);
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
}
