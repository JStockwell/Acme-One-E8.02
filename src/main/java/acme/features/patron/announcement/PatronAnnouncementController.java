package acme.features.patron.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Announcement;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronAnnouncementController extends AbstractController<Patron,Announcement>{

	@Autowired
	protected PatronAnnouncementListRecentService listRecent;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listRecent);
	}
}