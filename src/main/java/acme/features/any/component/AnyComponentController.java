package acme.features.any.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Component;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyComponentController extends AbstractController<Any,Component>{

	@Autowired
	protected AnyComponentListService listRecent;

	@Autowired
	protected AnyComponentShowService show;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listRecent);
		super.addCommand("show", this.show);
	}
}