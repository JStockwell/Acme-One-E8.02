package acme.features.administrator.systemConfiguration;

import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration> {

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		// TODO Auto-generated method stub
		// BigDecimal.valueOf(value).scale() > 2
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		// TODO Auto-generated method stub
		
	}

}
