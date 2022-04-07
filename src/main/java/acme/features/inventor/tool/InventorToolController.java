package acme.features.inventor.tool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorToolController extends AbstractController<Inventor, Item>{
	
	
	// Internal state --------------------------------------------
	
	@Autowired
	protected InventorToolListAllService	listAllService;
	
	@Autowired
	protected InventorToolShowService		showService;
	
	// Constructors ----------------------------------------------
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showService);
		super.addCommand("list-all", this.listAllService);
		
	}

}
