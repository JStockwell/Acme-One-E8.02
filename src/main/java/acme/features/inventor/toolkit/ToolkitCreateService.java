package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.utility.TextValidator;

@Service
public class ToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{
	
	@Autowired
	private ToolkitRepository repository;
	
	@Autowired
	protected TextValidator validator;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
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
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
		
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request!=null;
		
		Toolkit result;
		Inventor inventor;
		
		result = new Toolkit();
		inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
		
		result.setCode("");
		result.setAssemblyNotes("");
		result.setDescription("");
		result.setLink("");
		result.setTitle("");
		result.setInventor(inventor);
		result.setDraftMode(true);
		
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
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
