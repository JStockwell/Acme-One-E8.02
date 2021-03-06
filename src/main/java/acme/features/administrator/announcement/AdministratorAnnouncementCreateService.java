package acme.features.administrator.announcement;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Announcement;
import acme.features.any.userAccount.AnyUserAccountRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;
import acme.utility.TextValidator;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {

	@Autowired
	protected AdministratorAnnouncementRepository repository;
	
	@Autowired
	AnyUserAccountRepository userrepo;
	
	@Autowired
	protected TextValidator validator;

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		if (!errors.hasErrors("title")) {
			final String title = entity.getTitle();
			errors.state(request, !this.validator.checkSpam(title), "title", "validator.spam");
			}
		
		if (!errors.hasErrors("body")) {
			final String body = entity.getBody();
			errors.state(request, !this.validator.checkSpam(body), "body", "validator.spam");
		}

	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title","link","body","critical");
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title","link","body","critical","creationMoment");
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		Announcement result;
		Date moment;
		Calendar calendar;
		
		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		result = new Announcement();
		result.setTitle("");
		result.setBody("");
		result.setLink("");
		result.setCritical(false);
		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}