package acme.features.authenticated.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class AuthenticatedPatronGenerationService implements AbstractCreateService<Authenticated, Patron> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedPatronRepository repo;

	// AbstractCreateService<Authenticated, Patron> interface ---------------


	@Override
	public boolean authorise(final Request<Patron> request) {
		assert request != null;

		boolean result;

		result = !request.getPrincipal().hasRole(Patron.class); 

		return result;
	}

	@Override
	public void bind(final Request<Patron> request, final Patron patron, final Errors errors) {
		assert request != null;
		assert patron != null;
		assert errors != null;

		request.bind(patron, errors, "company", "statement","link");
	}

	@Override
	public void unbind(final Request<Patron> request, final Patron patron, final Model model) {
		assert request != null;
		assert patron != null;
		assert model != null;

		request.unbind(patron, model, "company", "statement","link");
	}

	@Override
	public Patron instantiate(final Request<Patron> request) {
		assert request != null;

		Patron generate;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repo.findOneUserAccountById(userAccountId);

		generate = new Patron();
		generate.setUserAccount(userAccount);

		return generate;
	}

	@Override
	public void validate(final Request<Patron> request, final Patron patron, final Errors errors) {
		assert request != null;
		assert patron != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Patron> request, final Patron patron) {
		assert request != null;
		assert patron != null;

		this.repo.save(patron);
	}

	@Override
	public void onSuccess(final Request<Patron> request, final Response<Patron> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
