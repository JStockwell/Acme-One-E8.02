package acme.features.any.chirp;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import acme.utility.TextValidator;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {

	@Autowired
	protected AnyChirpRepository repository;
	
	@Autowired
	protected TextValidator validator;

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

		String title;
		title = request.getModel().getString("title");
		String body;
		body = request.getModel().getString("body");
		
		errors.state(request, !this.validator.checkSpam(title), "title", "validator.spam");
		errors.state(request, !this.validator.checkSpam(body), "body", "validator.spam");
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title","author","body","mail");
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title","author","body","mail");
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;

		Chirp result;
		Date moment;
		Calendar calendar;
		
		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		result = new Chirp();
		result.setTitle("");
		result.setAuthor("");
		result.setBody("");
		result.setMail("");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}