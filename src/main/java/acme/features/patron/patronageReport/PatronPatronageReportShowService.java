package acme.features.patron.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron,PatronageReport> {

	@Autowired
	protected PatronPatronageReportRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		boolean res;
		int patronageReportId;
		PatronageReport patronageReport;
		
		patronageReportId = request.getModel().getInteger("id");
		patronageReport = this.repository.findPatronageReportById(patronageReportId);
		res = patronageReport != null && request.isPrincipal(patronageReport.getPatronage().getPatron());
		
		return res;
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;
		
		PatronageReport res;
		int id;
		
		id = request.getModel().getInteger("id");
		res = this.repository.findPatronageReportById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "creation", "memorandum", "link");
		model.setAttribute("patronage", entity.getPatronage().getCode());
	}

}
