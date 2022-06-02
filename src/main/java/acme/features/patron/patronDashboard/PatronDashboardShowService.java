package acme.features.patron.patronDashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{

	@Autowired
	protected PatronDashboardRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request!=null;
		
		final String[] currencies = this.configurationRepository.findSystemConfiguration().getAcceptedCurrencies().split(",");
		
		PatronDashboard result;
		final Map<String, Long> totalPatronages;
		final Map<String, Map<String, Double>> averagePatronageBudgetPerState = new HashMap<String, Map<String, Double>>();
		final Map<String, Map<String, Double>> deviationPatronageBudgetPerState = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> minimumPatronageBudgetPerState = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> maximumPatronageBudgetPerState = new HashMap<String, Map<String,Double>>();
		
		result = new PatronDashboard();
		
		totalPatronages = this.repository.totalPatronage(request.getPrincipal().getActiveRoleId()).stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Long)p.get(1)));
		for(final String currency: currencies) {
			final String c = currency.replace('"', ' ').trim();
			final List<Tuple> averagePatronages = this.repository.averagePatronageBudgetPerStatus(request.getPrincipal().getActiveRoleId(), c);
			averagePatronageBudgetPerState.put(c, 
				averagePatronages.stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Double)p.get(1))));
			final List<Tuple> deviationPatronages = this.repository.deviationPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), c);
			deviationPatronageBudgetPerState.put(c, 
				deviationPatronages.stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Double)p.get(1))));
			final List<Tuple> maximumPatronages = this.repository.maximumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), c);
			maximumPatronageBudgetPerState.put(c, 
				maximumPatronages.stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Double)p.get(1))));
			final List<Tuple> minimumPatronages = this.repository.minimumPatronageBudgetPerState(request.getPrincipal().getActiveRoleId(), c);
			minimumPatronageBudgetPerState.put(c, 
				minimumPatronages.stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Double)p.get(1))));
		}
		
		result.setTotalPatronages(totalPatronages);
		result.setAveragePatronageBudgetPerState(averagePatronageBudgetPerState);
		result.setDeviationPatronageBudgetPerState(deviationPatronageBudgetPerState);
		result.setMinimumPatronageBudgetPerState(minimumPatronageBudgetPerState);
		result.setMaximumPatronageBudgetPerState(maximumPatronageBudgetPerState);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"totalPatronages",
		"averagePatronageBudgetPerState",
		"deviationPatronageBudgetPerState",
		"minimumPatronageBudgetPerState",
		"maximumPatronageBudgetPerState");
		
	}

}
