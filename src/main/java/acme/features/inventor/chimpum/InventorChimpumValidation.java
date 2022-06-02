package acme.features.inventor.chimpum;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
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
		
		if(!errors.hasErrors("code")) {
			final String code = entity.getCode();
			final String[] splitter = code.split("-");
			final Integer yy = Integer.valueOf(splitter[0]);
			final Integer mm = Integer.valueOf(splitter[1]);
			final Integer dd = Integer.valueOf(splitter[2]);
			
			final Calendar calendar = new GregorianCalendar();

			calendar.setTime(entity.getCreationMoment());
			final Integer yyCreation = Calendar.getInstance().get(Calendar.YEAR)-2000;
			final Integer mmCreation = Calendar.getInstance().get(Calendar.MONTH) + 1;
			final Integer ddCreation = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			
			errors.state(request, yy.equals(yyCreation) && mm.equals(mmCreation) && dd.equals(ddCreation), "code", "inventor.chimpum.form.error.code-not-pattern");
		}
		
		if(!errors.hasErrors("startDate")) {
			final Date minimumStartDate = DateUtils.addMonths(entity.getCreationMoment(), 1);
			
			errors.state(request, entity.getStartDate().after(minimumStartDate), "startDate", "inventor.chimpum.form.error.startDate-too-close-to-creationDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			final Date minimumFinishDate = DateUtils.addWeeks(entity.getStartDate(), 1);
	
			errors.state(request, entity.getFinishDate().after(minimumFinishDate), "finishDate", "inventor.chimpum.form.error.finishDate-too-close-to-startDate"); 
		}
		
		if (!errors.hasErrors("budget")) {
			final Double amount=entity.getBudget().getAmount();
			final String currency=entity.getBudget().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>0., "budget", "inventor.chimpum.form.error.negative-budget");
			errors.state(request, acceptedCurrencies.contains(currency), "budget", "inventor.chimpum.form.error.wrongCurrency");
		}
	}

}
