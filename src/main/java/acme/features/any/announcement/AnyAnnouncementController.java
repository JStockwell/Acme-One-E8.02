package acme.features.any.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Announcement;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyAnnouncementController extends AbstractController<Any,Announcement>{

	@Autowired
	protected AnyAnnouncementListRecentService listRecent;
	
	@Autowired
	protected AnyAnnouncementShowService show;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listRecent);
		super.addCommand("show", this.show);
	}
}