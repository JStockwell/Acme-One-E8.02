package acme.features.administrator.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SystemConfiguration;
import acme.features.any.userAccount.AnyUserAccountRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSystemConfigurationShowService implements AbstractShowService<Administrator, SystemConfiguration>{

	@Autowired
	AdministratorSystemConfigurationRepository amdminsysconfigrepo;
	
	@Autowired
	AnyUserAccountRepository userrepo;

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		final Integer id = request.getPrincipal().getAccountId();
		final UserAccount user = this.userrepo.findUserAccountById(id);
		if(user.hasRole(Administrator.class)) {
			return true;

		}
		else {
			return false;
		}
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		return this.amdminsysconfigrepo.getSystemConfiguration();
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "currency", "acceptedCurrencies","strongSpamTerms",
			"strongSpamThreshold","weakSpamTerms","weakSpamThreshold");
	}

}