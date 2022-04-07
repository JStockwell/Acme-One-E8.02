package acme.features.patron.tool;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronToolController extends AbstractController<Patron, Item>{
	
	
	// Internal state --------------------------------------------
	
	@Autowired
	protected PatronToolListAllService	listAllService;
	
	@Autowired
	protected PatronToolShowService		showService;
	
	// Constructors ----------------------------------------------
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showService);
		super.addCommand("list-all", this.listAllService);
		
	}

}