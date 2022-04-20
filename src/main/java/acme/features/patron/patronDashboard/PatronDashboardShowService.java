package acme.features.patron.patronDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{

	@Autowired
	protected PatronDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request!=null;
		
		PatronDashboard result;
		Integer totalPatronagesProposed;
		Integer totalPatronagesAccepted;
		Integer totalPatronagesDenied;
		Double averagePatronageBudgetPerStateProposed;
		Double averagePatronageBudgetPerStateAccepted;
		Double averagePatronageBudgetPerStateDenied;
		Double deviationPatronageBudgetPerStateProposed;
		Double deviationPatronageBudgetPerStateAccepted;
		Double deviationPatronageBudgetPerStateDenied;
		Double minimumPatronageBudgetPerStateProposed;
		Double minimumPatronageBudgetPerStateAccepted;
		Double minimumPatronageBudgetPerStateDenied;
		Double maximumPatronageBudgetPerStateProposed;
		Double maximumPatronageBudgetPerStateAccepted;
		Double maximumPatronageBudgetPerStateDenied;
		
		result = new PatronDashboard();
		
		totalPatronagesProposed = this.repository.totalPatronage(request.getPrincipal().getActiveRoleId(), Status.Proposed);
		totalPatronagesAccepted = this.repository.totalPatronage(request.getPrincipal().getActiveRoleId(), Status.Accepted);
		totalPatronagesDenied = this.repository.totalPatronage(request.getPrincipal().getActiveRoleId(), Status.Denied);
		averagePatronageBudgetPerStateProposed = this.repository.averagePatronageBudgetPerStatus(request.getPrincipal().getActiveRoleId(), Status.Proposed);
		averagePatronageBudgetPerStateAccepted = this.repository.averagePatronageBudgetPerStatus(request.getPrincipal().getActiveRoleId(), Status.Accepted);
		averagePatronageBudgetPerStateDenied = this.repository.averagePatronageBudgetPerStatus(request.getPrincipal().getActiveRoleId(), Status.Denied);
		deviationPatronageBudgetPerStateProposed = this.repository.deviationPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), Status.Proposed);
		deviationPatronageBudgetPerStateAccepted = this.repository.deviationPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), Status.Accepted);
		deviationPatronageBudgetPerStateDenied = this.repository.deviationPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), Status.Denied);
		minimumPatronageBudgetPerStateProposed = this.repository.minimumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), Status.Proposed);
		minimumPatronageBudgetPerStateAccepted = this.repository.minimumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), Status.Accepted);
		minimumPatronageBudgetPerStateDenied = this.repository.minimumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), Status.Denied);
		maximumPatronageBudgetPerStateProposed = this.repository.maximumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), Status.Proposed);
		maximumPatronageBudgetPerStateAccepted = this.repository.maximumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), Status.Accepted);
		maximumPatronageBudgetPerStateDenied = this.repository.maximumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), Status.Denied);
		
		result.setTotalPatronagesProposed(totalPatronagesProposed);
		result.setTotalPatronagesAccepted(totalPatronagesAccepted);
		result.setTotalPatronagesDenied(totalPatronagesDenied);
		result.setAveragePatronageBudgetPerStateProposed(averagePatronageBudgetPerStateProposed);
		result.setAveragePatronageBudgetPerStateAccepted(averagePatronageBudgetPerStateAccepted);
		result.setAveragePatronageBudgetPerStateDenied(averagePatronageBudgetPerStateDenied);
		result.setDeviationPatronageBudgetPerStateProposed(deviationPatronageBudgetPerStateProposed);
		result.setDeviationPatronageBudgetPerStateAccepted(deviationPatronageBudgetPerStateAccepted);
		result.setDeviationPatronageBudgetPerStateDenied(deviationPatronageBudgetPerStateDenied);
		result.setMinimumPatronageBudgetPerStateProposed(minimumPatronageBudgetPerStateProposed);
		result.setMinimumPatronageBudgetPerStateAccepted(minimumPatronageBudgetPerStateAccepted);
		result.setMinimumPatronageBudgetPerStateDenied(minimumPatronageBudgetPerStateDenied);
		result.setMaximumPatronageBudgetPerStateProposed(maximumPatronageBudgetPerStateProposed);
		result.setMaximumPatronageBudgetPerStateAccepted(maximumPatronageBudgetPerStateAccepted);
		result.setMaximumPatronageBudgetPerStateDenied(maximumPatronageBudgetPerStateDenied);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"totalPatronagesProposed",
		"totalPatronagesAccepted",
		"totalPatronagesDenied",
		"averagePatronageBudgetPerStateProposed",
		"averagePatronageBudgetPerStateAccepted",
		"averagePatronageBudgetPerStateDenied",
		"deviationPatronageBudgetPerStateProposed",
		"deviationPatronageBudgetPerStateAccepted",
		"deviationPatronageBudgetPerStateDenied",
		"minimumPatronageBudgetPerStateProposed",
		"minimumPatronageBudgetPerStateAccepted",
		"minimumPatronageBudgetPerStateDenied",
		"maximumPatronageBudgetPerStateProposed",
		"maximumPatronageBudgetPerStateAccepted",
		"maximumPatronageBudgetPerStateDenied");
		
	}

}
