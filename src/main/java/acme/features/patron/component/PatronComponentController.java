package acme.features.patron.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Component;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronComponentController extends AbstractController<Patron,Component>{

	@Autowired
	protected PatronComponentListService listRecent;

	@Autowired
	protected PatronComponentShowService show;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listRecent);
		super.addCommand("show", this.show);
	}
}