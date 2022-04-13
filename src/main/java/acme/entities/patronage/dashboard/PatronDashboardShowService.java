package acme.entities.patronage.dashboard;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

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
		Map<String, Integer> totalPatronages;
		Map<String, Double> averagePatronageBudgetPerState;
		//Map<String, Double> deviationPatronageBudgetPerState;
		Map<String, Double> minimumPatronageBudgetPerState;
		Map<String, Double> maximumPatronageBudgetPerState;
		
		totalPatronages = this.repository.totalPatronage(request.getPrincipal().getActiveRoleId());
		averagePatronageBudgetPerState = this.repository.averagePatronageBudgetPerStatus(request.getPrincipal().getActiveRoleId());
		minimumPatronageBudgetPerState = this.repository.minimumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId());
		maximumPatronageBudgetPerState = this.repository.maximumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId());
		
		result = new PatronDashboard();
		result.setTotalPatronages(totalPatronages);
		result.setAveragePatronageBudgetPerState(averagePatronageBudgetPerState);
		result.setMinimumPatronageBudgetPerState(minimumPatronageBudgetPerState);
		result.setMaximumPatronageBudgetPerState(maximumPatronageBudgetPerState);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

}
