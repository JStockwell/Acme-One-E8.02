package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportListService implements AbstractListService<Patron,PatronageReport> {
	
	@Autowired
	protected PatronPatronageReportRepository repository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;
		
		Collection<PatronageReport> res;
		int id;
		
		id =  request.getPrincipal().getActiveRoleId();
		res = this.repository.findAllPatronageReportByPatronId(id);
		
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
