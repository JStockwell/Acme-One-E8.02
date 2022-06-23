package acme.features.any.troqueDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.TroqueDashboard;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class TroqueDashboardController extends AbstractController<Any, TroqueDashboard>{

	@Autowired
	protected TroqueDashboardShowRepository showRepository;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showRepository);
	}
}
