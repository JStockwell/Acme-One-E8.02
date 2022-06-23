package acme.features.any.troqueDashboard;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.forms.TroqueDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class TroqueDashboardShowRepository implements AbstractShowService<Any, TroqueDashboard>{

	@Autowired
	protected TroqueDashboardRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<TroqueDashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public TroqueDashboard findOne(final Request<TroqueDashboard> request) {
		assert request != null;
		
		TroqueDashboard result;
		
		final String[] currencies = this.configurationRepository.findSystemConfiguration().getAcceptedCurrencies().split(",");

		Map<String, Double> ratioTroqueComponents;
		final Map<String, Long> averageQuantityOfTroque = new HashMap<String, Long>();
		final Map<String, Long> deviationQuantityOfTroque = new HashMap<String, Long>();
		final Map<String, Long> minimumQuantityOfTroque = new HashMap<String, Long>();
		final Map<String, Long> maximumQuantityOfTroque = new HashMap<String, Long>();
		result = new TroqueDashboard();
		
		ratioTroqueComponents = this.repository.getRatioOfTroqueArtefacts().stream()
			.collect(Collectors.toMap(c->c.get(0).toString(), c->(Double)c.get(1)));
		for(final String currency: currencies) {
			averageQuantityOfTroque.put(currency, this.repository.getAverageQuantity(currency));
			deviationQuantityOfTroque.put(currency, this.repository.getDeviationQuantity(currency));
			minimumQuantityOfTroque.put(currency, this.repository.getMinimumQuantity(currency));
			maximumQuantityOfTroque.put(currency, this.repository.getMaximumQuantity(currency));
		}
		
		result.setRatioTroqueComponents(ratioTroqueComponents);
		result.setAverageQuantityOfTroque(averageQuantityOfTroque);
		result.setDeviationQuantityOfTroque(deviationQuantityOfTroque);
		result.setMinimumQuantityOfTroque(minimumQuantityOfTroque);
		result.setMaximumQuantityOfTroque(maximumQuantityOfTroque);
		
		return result;
	}

	@Override
	public void unbind(final Request<TroqueDashboard> request, final TroqueDashboard entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "ratioTroqueComponents"
			, "averageQuantityOfTroque"
			, "deviationQuantityOfTroque"
			, "minimumQuantityOfTroque"
			, "maximumQuantityOfTroque");
		
	}

}
