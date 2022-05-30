package acme.features.inventor.chimpum;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Chimpum;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.utility.TextValidator;

@Service
public class InventorChimpumValidation {
	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository sysConfRepository;
	
	@Autowired
	protected TextValidator validator;
	
	public void validateChimpum(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
				
		if(!errors.hasErrors("startDate")) {
			final Date minimumStartDate = DateUtils.addMonths(entity.getCreationMoment(), 1);
			
			errors.state(request, entity.getStartDate().after(minimumStartDate), "startDate", "patron.patronage.form.error.startDate-too-close-to-creationDate"); //jsp change
		}
		
		if(!errors.hasErrors("finishDate")) {
			final Date minimumFinishDate = DateUtils.addWeeks(entity.getStartDate(), 1);
	
			errors.state(request, entity.getFinishDate().after(minimumFinishDate), "finishDate", "patron.patronage.form.error.finishDate-too-close-to-startDate"); //jsp change
		}

//		if (!errors.hasErrors("legislation")) {
//			final String legislation = entity.getLegislation();
//			errors.state(request, !this.validator.checkSpam(legislation), "legislation", "validator.spam");
//		}

		if (!errors.hasErrors("budget")) {
			final Double amount=entity.getBudget().getAmount();
			final String currency=entity.getBudget().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>0., "budget", "patron.patronage.form.error.negative-budget"); //jsp change
			errors.state(request, acceptedCurrencies.contains(currency), "budget", "patron.patronage.form.error.wrongCurrency"); //jsp change
		}
	}

}
