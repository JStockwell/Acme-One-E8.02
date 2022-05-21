package acme.features.inventor.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronage.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageController extends AbstractController<Inventor, Patronage> {
	
	// Internal state ---------------------
	@Autowired
	protected InventorPatronageListService list;
	
	@Autowired
	protected InventorPatronageShowService show;
	
	@Autowired
	protected InventorPatronageUpdateStatusService updateStatusService;
	
	// Constructors ---------------------------
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.show);
		super.addCommand("list", this.list);
		super.addCommand("update-status", "update", this.updateStatusService);
	}

}
