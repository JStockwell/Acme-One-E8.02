package acme.features.tool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class InventorToolController extends AbstractController<Any, Item>{
	
	
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
