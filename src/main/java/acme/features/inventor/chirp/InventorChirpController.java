package acme.features.inventor.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Chirp;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChirpController extends AbstractController<Inventor,Chirp>{

	@Autowired
	protected InventorChirpListRecentService listRecent;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listRecent);
	}
}
