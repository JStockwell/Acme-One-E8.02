package acme.features.inventor.troque;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.troque.Troque;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.utility.TextValidator;

@Service
public class InventorTroqueValidation {
	
	@Autowired
	protected InventorTroqueRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository sysConfRepository;
	
	@Autowired
	protected TextValidator validator;
	
	public void validateTroque(final Request<Troque> request, final Troque entity, final Errors errors) {
		
		if(!errors.hasErrors("startDate")) {
			final Date minimumStartDate = DateUtils.addMonths(entity.getCreationMoment(), 1);
			
			errors.state(request, entity.getStartDate().after(minimumStartDate), "startDate", "inventor.chimpum.form.error.startDate-too-close-to-creationDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			final Date minimumFinishDate = DateUtils.addWeeks(entity.getStartDate(), 1);
	
			errors.state(request, entity.getFinishDate().after(minimumFinishDate), "finishDate", "inventor.chimpum.form.error.finishDate-too-close-to-startDate"); 
		}
		
		if (!errors.hasErrors("quantity")) {
			final Double amount=entity.getQuantity().getAmount();
			final String currency=entity.getQuantity().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>0., "quantity", "inventor.chimpum.form.error.negative-quantity");
			errors.state(request, acceptedCurrencies.contains(currency), "quantity", "inventor.chimpum.form.error.wrongCurrency");
		}
	}

}
