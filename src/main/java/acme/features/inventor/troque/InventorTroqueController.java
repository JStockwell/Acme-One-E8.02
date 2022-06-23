package acme.features.inventor.troque;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.troque.Troque;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorTroqueController extends AbstractController<Inventor, Troque> {
	
	@Autowired
	protected InventorTroqueShowService showService;
	
	@Autowired
	protected InventorTroqueListService listService;
	
	@Autowired
	protected InventorTroqueCreateService createService;
	
	@Autowired
	protected InventorTroqueUpdateService updateService;
	
	@Autowired
	protected InventorTroqueDeleteService deleteService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
}
