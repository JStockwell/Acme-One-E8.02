package acme.features.any.chinpumDashboard;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.forms.ChimpumDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class ChimpumDashboardShowRepository implements AbstractShowService<Any, ChimpumDashboard>{

	@Autowired
	protected ChimpumDashboardRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<ChimpumDashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public ChimpumDashboard findOne(final Request<ChimpumDashboard> request) {
		assert request != null;
		
		ChimpumDashboard result;
		
		final String[] currencies = this.configurationRepository.findSystemConfiguration().getAcceptedCurrencies().split(",");

		Map<String, Double> ratioChinpumArtefacts;
		final Map<String, Long> averageBudgetOfChinpum = new HashMap<String, Long>();
		final Map<String, Long> deviationBudgetOfChinpum = new HashMap<String, Long>();
		final Map<String, Long> minimumBudgetOfChinpum = new HashMap<String, Long>();
		final Map<String, Long> maximumBudgetOfChinpum = new HashMap<String, Long>();
		result = new ChimpumDashboard();
		
		ratioChinpumArtefacts = this.repository.getRatioOfChinpumArtefacts().stream()
			.collect(Collectors.toMap(c->c.get(0).toString(), c->(Double)c.get(1)));
		for(final String currency: currencies) {
			averageBudgetOfChinpum.put(currency, this.repository.getAverageBudget(currency));
			deviationBudgetOfChinpum.put(currency, this.repository.getDeviationBudget(currency));
			minimumBudgetOfChinpum.put(currency, this.repository.getMinimumBudget(currency));
			maximumBudgetOfChinpum.put(currency, this.repository.getMaximumBudget(currency));
		}
		
		result.setRatioChinpumArtefacts(ratioChinpumArtefacts);
		result.setAverageBudgetOfChinpum(averageBudgetOfChinpum);
		result.setDeviationBudgetOfChinpum(deviationBudgetOfChinpum);
		result.setMinimumBudgetOfChinpum(minimumBudgetOfChinpum);
		result.setMaximumBudgetOfChinpum(maximumBudgetOfChinpum);
		
		return result;
	}

	@Override
	public void unbind(final Request<ChimpumDashboard> request, final ChimpumDashboard entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "ratioChinpumArtefacts"
			, "averageBudgetOfChinpum"
			, "deviationBudgetOfChinpum"
			, "minimumBudgetOfChinpum"
			, "maximumBudgetOfChinpum");
		
	}

}
