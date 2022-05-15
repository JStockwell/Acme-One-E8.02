package acme.features.patron.patronage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.Status;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected InventorItemRepository inventorRepository;

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

		request.bind(entity, errors, "status", "code", "legislation", "budget", "creationDate", "startDate", "finishDate", "link", "draft");
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
		Inventor inventor;
		Money money;
		Date creationDate = null;
		Date startDate = null;
		Date finishDate = null;
		
		patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());
		inventor = this.inventorRepository.findOneInventorById(1);
		money = new Money();
				
		final String creationDate_string = "01-01-2022";
		final String startDate_string = "15-02-2022";
		final String finishDate_string = "30-03-2022";
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    try {
	    	creationDate = formatter.parse(creationDate_string);
			startDate = formatter.parse(startDate_string);
			finishDate = formatter.parse(finishDate_string);
		} catch (final ParseException e) {} 
		
	    money.setAmount(.0);
	    money.setCurrency("EUR");
		
		res = new Patronage();
		res.setStatus(Status.Proposed);
		res.setCode("PTG-999");
		res.setLegislation("blabla");
		res.setBudget(money);
		res.setCreationDate(creationDate);
		res.setStartDate(startDate);
		res.setFinishDate(finishDate);
		res.setLink("");
		res.setDraft(true);
		res.setPatron(patron);
		res.setInventor(inventor);
		
		return res;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative-budget");
		}
		
		if(!errors.hasErrors("startDate")) {
			final Date creationDate = entity.getCreationDate();
			final Date startDate = entity.getStartDate();
			final Long monthToMiliseconds = 2592000000l;
			
			errors.state(request, (startDate.getTime() - creationDate.getTime())/monthToMiliseconds > 1, "startDate", "patron.patronage.form.error.startDate-too-close-to-creationDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			final Date startDate = entity.getStartDate();
			final Date finishDate = entity.getFinishDate();
			final Long monthToMiliseconds = 2592000000l;
			
			errors.state(request, (finishDate.getTime() - startDate.getTime())/monthToMiliseconds > 1, "finishDate", "patron.patronage.form.error.finishDate-too-close-to-startDate");
		}
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
}
