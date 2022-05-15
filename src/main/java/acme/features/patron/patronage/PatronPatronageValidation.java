package acme.features.patron.patronage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.utility.TextValidator;

@Service
public class PatronPatronageValidation {
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository sysConfRepository;
	
	@Autowired
	protected TextValidator validator;
	
	public void validatePatronage(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		
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

		if (!errors.hasErrors("legislation")) {
			final String legislation = entity.getLegislation();
			errors.state(request, !this.validator.checkSpam(legislation), "legislation", "validator.spam");
		}

		if (!errors.hasErrors("budget")) {
			final Double amount=entity.getBudget().getAmount();
			final String currency=entity.getBudget().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>=0, "budget", "patron.patronage.form.error.negative-budget");
			errors.state(request, acceptedCurrencies.contains(currency) && currency.length()==3, "budget", "patron.patronage.form.error.wrongCurrency");
		}
	}

}
