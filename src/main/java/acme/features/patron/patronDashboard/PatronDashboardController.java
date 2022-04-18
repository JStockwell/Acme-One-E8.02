package acme.features.patron.patronDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.forms.PatronDashboard;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

public class PatronDashboardController extends AbstractController<Patron, PatronDashboard>{
	@Autowired
	protected PatronDashboardShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}
}
