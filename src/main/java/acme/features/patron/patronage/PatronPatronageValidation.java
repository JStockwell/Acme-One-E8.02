package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
			Calendar calendar;
			Date minimumStartDate;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimumStartDate = calendar.getTime();
			
			errors.state(request, entity.getStartDate().after(minimumStartDate), "startDate", "patron.patronage.form.error.startDate-too-close-to-creationDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			Calendar calendar;
			Date minimumFinishDate;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimumFinishDate = calendar.getTime();
			
			errors.state(request, entity.getFinishDate().after(minimumFinishDate), "finishDate", "patron.patronage.form.error.finishDate-too-close-to-startDate");
		}

		if (!errors.hasErrors("legislation")) {
			final String legislation = entity.getLegislation();
			errors.state(request, !this.validator.checkSpam(legislation), "legislation", "validator.spam");
		}

		if (!errors.hasErrors("budget")) {
			final Double amount=entity.getBudget().getAmount();
			final String currency=entity.getBudget().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>0, "budget", "patron.patronage.form.error.negative-budget");
			errors.state(request, acceptedCurrencies.contains(currency) && currency.length()==3, "budget", "patron.patronage.form.error.wrongCurrency");
		}
	}

}
