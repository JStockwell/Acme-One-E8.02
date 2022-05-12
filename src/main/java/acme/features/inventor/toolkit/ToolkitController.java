package acme.features.inventor.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkit.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class ToolkitController extends AbstractController<Inventor, Toolkit>{
	@Autowired
	protected ToolkitCreateService createService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
	}
}
