package acme.features.administrator.adminDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.AdminDashboard;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdminDashboardController extends AbstractController<Administrator, AdminDashboard>{
	@Autowired
	protected AdminDashboardShowRepository showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}
}
