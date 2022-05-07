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

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyChirpRepository repository;

	// AbstractCreateService<Employer, Duty> interface -------------------------


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
		//model.setAttribute("masterId", entity.getJob().getId());
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