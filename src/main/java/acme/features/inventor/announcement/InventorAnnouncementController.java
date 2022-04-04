package acme.features.inventor.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Announcement;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorAnnouncementController extends AbstractController<Inventor,Announcement>{

	@Autowired
	protected InventorAnnouncementListRecentService listRecent;

	@Autowired
	protected InventorAnnouncementShowService show;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listRecent);
		super.addCommand("show", this.show);
	}
}