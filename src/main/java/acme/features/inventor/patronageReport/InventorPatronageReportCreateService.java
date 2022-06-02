package acme.features.inventor.patronageReport;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	@Autowired
	protected InventorPatronageReportRepository repository;
	protected InventorPatronageRepository patronageRepository;
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		boolean result;
		int patronageReportId;
		PatronageReport report;
		Inventor inventor;
		
		patronageReportId = request.getModel().getInteger("id");
		report = this.repository.findPatronageReportById(patronageReportId);
		inventor = report.getPatronage().getInventor();
		result = request.isPrincipal(inventor);
		
		return result;
	}
	
	

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation","memorandum","link","patronage");
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creation","memorandum","link","patronage");
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		Date moment;
		Calendar calendar;
		Patronage patronage;
		Integer masterId;
		Collection<PatronageReport> patronageReports;
		String patronageCode;
		
		masterId = request.getModel().getInteger("patronageId");
		patronage = this.patronageRepository.findPatronageById(masterId);
		patronageReports = this.repository.findAllPatronageReportByPatronage(patronage.getId());
		
		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		final StringBuilder bld = new StringBuilder();
		bld.append(String.valueOf(patronageReports.size()));
		while(bld.length() < 4) {
			bld.insert(0, 0);
		}
		patronageCode = patronage.getCode() + ":" + bld.toString();


		result = new PatronageReport();
		result.setMemorandum("");
		result.setLink("");
		result.setPatronage(patronage);
		result.setCreation(moment);
		result.setCode(patronageCode);

		return result;
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
