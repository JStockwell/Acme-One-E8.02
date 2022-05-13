package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.utility.TextValidator;

@Service
public class InventorItemValidation {
	
	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository sysConfRepository;
	
	@Autowired
	protected TextValidator validator;
	
	public void validateItem(final Request<Item> request, final Item entity, final Errors errors) {
		Item existing;
		
		if (!errors.hasErrors("code")) {
			existing = this.repository.findItemByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.item.code.duplicated");
		}
			
		final String technology = entity.getTechnology();
		final String description = entity.getDescription();
		final String name = entity.getName();
		
		errors.state(request, !this.validator.checkSpam(technology), "technology", "validator.spam");
		errors.state(request, !this.validator.checkSpam(description), "description", "validator.spam");
		errors.state(request, !this.validator.checkSpam(name), "name", "validator.spam");
		
		if (!errors.hasErrors("price")) {
			final Double amount=entity.getPrice().getAmount();
			final String currency=entity.getPrice().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>=0, "price", "inventor.item.money.negative");
			errors.state(request, acceptedCurrencies.contains(currency), "price", "inventor.item.money.wrongCurrency");
		}
	}

}
