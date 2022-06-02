package acme.features.any.chinpumDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.ChimpumDashboard;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class ChimpumDashboardController extends AbstractController<Any, ChimpumDashboard>{

	@Autowired
	protected ChimpumDashboardShowRepository showRepository;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showRepository);
	}
}
