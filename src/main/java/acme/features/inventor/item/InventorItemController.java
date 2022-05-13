package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor,Item>{

	@Autowired
	protected InventorItemListService listMine;

	@Autowired
	protected InventorItemShowService show;
	
	@Autowired
	protected InventorItemCreateService	create;

	@Autowired
	protected InventorItemUpdateService	update;

	@Autowired
	protected InventorItemDeleteService	delete;

	@Autowired
	protected InventorItemPublishService publish;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listMine);
		super.addCommand("show", this.show);
		super.addCommand("create", this.create);
		super.addCommand("update", this.update);
		super.addCommand("publish", "update", this.publish);
		super.addCommand("delete", this.delete);
	}
}