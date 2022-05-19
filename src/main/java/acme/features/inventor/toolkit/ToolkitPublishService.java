package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.utility.TextValidator;

public class ToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit>{

	@Autowired
	protected ToolkitRepository repository;
	
	@Autowired
	protected TextValidator validator;
	
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int toolkitId;
		Toolkit toolkit;
		Inventor inventor;
		
		toolkitId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findToolkitById(toolkitId);
		inventor = toolkit.getInventor();
		
		result = toolkit.isDraftMode() && request.isPrincipal(inventor);
		
		
		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("draftMode", entity.isDraftMode());
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		model.setAttribute("draftMode", entity.isDraftMode());
		
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("masterId");
		result = this.repository.findToolkitById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final String assemblyNotes = entity.getAssemblyNotes();
			final String description = entity.getDescription();
			final String title = entity.getTitle();
			
			errors.state(request, !this.validator.checkSpam(assemblyNotes), "assemblyNotes", "validator.spam");
			errors.state(request, !this.validator.checkSpam(description), "description", "validator.spam");
			errors.state(request, !this.validator.checkSpam(title), "title", "validator.spam");
		}
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		entity.setDraftMode(false);
		this.repository.save(entity);
		
	}

}
