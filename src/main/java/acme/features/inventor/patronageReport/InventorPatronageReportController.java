package acme.features.inventor.patronageReport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronage.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageReportController extends AbstractController<Inventor, PatronageReport> {
	
	// Internal state ---------------------
	@Autowired
	protected InventorPatronageReportListService list;
	
	@Autowired
	protected InventorPatronageReportShowService show;
	
	// Constructors ---------------------------
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.show);
		super.addCommand("list", this.list);
	}

}
