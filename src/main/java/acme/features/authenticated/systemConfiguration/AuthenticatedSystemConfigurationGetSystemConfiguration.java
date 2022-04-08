package acme.features.authenticated.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSystemConfigurationGetSystemConfiguration implements AbstractShowService<Authenticated,SystemConfiguration>{
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository repository;

	@Override
	public boolean authorise(Request<SystemConfiguration> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public SystemConfiguration findOne(Request<SystemConfiguration> request) {
		assert request !=null;
		
		SystemConfiguration res;

        res=this.repository.findSystemConfiguration();
		
		return res;
	}

	@Override
	public void unbind(Request<SystemConfiguration> request, SystemConfiguration entity, Model model) {
		assert request!= null;
		assert entity!= null;
		assert model!= null;
		
		request.unbind(entity, model, "title", "creationMoment","body","critical","link");
		
	}
}
