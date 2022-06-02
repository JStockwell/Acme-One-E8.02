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
	
	public void validateItem(final Request<Item> request, final Item entity, final Errors errors,final Integer op) {
		Item existing;
		
		// op=1 means update or publish
		if (!errors.hasErrors("code")) {
			if(op==0) {
				existing = this.repository.findItemByCode(entity.getCode());
				errors.state(request, existing == null, "code", "inventor.item.code.duplicated");
			}else {
				existing = this.repository.findItemByCode(entity.getCode());
				errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.item.code.duplicated");
			}
		}
		
		if (!errors.hasErrors("technology")) {
			final String technology = entity.getTechnology();
			errors.state(request, !this.validator.checkSpam(technology), "technology", "validator.spam");
		}

		if (!errors.hasErrors("description")) {
			final String description = entity.getDescription();
		errors.state(request, !this.validator.checkSpam(description), "description", "validator.spam");
		}

		if (!errors.hasErrors("name")) {
		final String name = entity.getName();
		errors.state(request, !this.validator.checkSpam(name), "name", "validator.spam");
		}
			
		if (!errors.hasErrors("price")) {
			final Double amount=entity.getPrice().getAmount();
			final String currency=entity.getPrice().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>=0, "price", "inventor.item.money.negative");
			// TODO Quitar currency.length()==3
			errors.state(request, acceptedCurrencies.contains(currency) && currency.length()==3, "price", "inventor.item.money.wrongCurrency");
		}
	}

}

