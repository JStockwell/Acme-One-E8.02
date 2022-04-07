package acme.entities.systemConfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class SystemConfigurationController extends AbstractController<Administrator, SystemConfiguration>{

	// Internal State ---------------------------------------------------------------------------------
	
	@Autowired
	protected SystemConfigurationListAllCurrencies listAllCurrencies;
	
	@Autowired
	protected SystemConfigurationGetDefaultCurrency getDefaultCurrency;
	
	
	// Constructors -----------------------------------------------------------------------------------
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-all-currencies", this.listAllCurrencies);
	}
	
}
