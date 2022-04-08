package acme.features.inventor.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Component;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorComponentController extends AbstractController<Inventor,Component>{

	@Autowired
	protected InventorComponentListService listRecent;

	@Autowired
	protected InventorComponentShowService show;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listRecent);
		super.addCommand("show", this.show);
	}
}