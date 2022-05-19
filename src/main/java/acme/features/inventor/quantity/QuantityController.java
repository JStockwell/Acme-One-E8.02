package acme.features.inventor.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.quantity.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class QuantityController extends AbstractController<Inventor, Quantity>{
	
	@Autowired
	protected QuantityCreateService createService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
	}
}
