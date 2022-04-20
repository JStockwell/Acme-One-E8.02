package acme.features.any.userAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;



@Service
public class AnyUserAccountListService implements AbstractListService<Any, UserAccount> {


	@Autowired
	protected AnyUserAccountRepository repository;

	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;
		final List<UserAccount> filtered = new ArrayList<UserAccount>();
		final Collection<UserAccount> accounts = this.repository.findEnabledUserAccounts();
		for(final UserAccount ua: accounts) {
			if(!ua.isAnonymous() && !ua.hasRole(Administrator.class)){
				filtered.add(ua);
				
			}
		}
		return filtered;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		StringBuilder buffer;
		Collection<UserRole> roles;
		request.unbind(entity, model, "identity.name", "identity.surname", "identity.email");
		roles = entity.getRoles();
		buffer = new StringBuilder();
		for (final UserRole role : roles) {
			buffer.append(role.getAuthorityName());
			buffer.append(" ");
		}
		model.setAttribute("roleList", buffer.toString());
	}

}