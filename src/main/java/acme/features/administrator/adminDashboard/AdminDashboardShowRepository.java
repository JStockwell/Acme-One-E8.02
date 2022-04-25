package acme.features.administrator.adminDashboard;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.forms.AdminDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdminDashboardShowRepository implements AbstractShowService<Administrator, AdminDashboard>{

	@Autowired
	protected AdminDashboardRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<AdminDashboard> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public AdminDashboard findOne(final Request<AdminDashboard> request) {
		assert request!=null;
		
		final String[] currencies = this.configurationRepository.findSystemConfiguration().getAcceptedCurrencies().split(",");
		
		final AdminDashboard dashboard;
		final Integer	totalNumberOfComponents;
		final Map<String, Map<String, Double>> averageComponentsPricePerTechnology = new HashMap<String, Map<String,Double>>();
		final Map<String, Double> averageComponentsPricePerCurrency = new HashMap<String, Double>();
		final Map<String, Map<String, Double>> deviationComponentsPricePerTechnology = new HashMap<String, Map<String,Double>>();
		final Map<String, Double> deviationComponentsPricePerCurrency = new HashMap<String, Double>();
		final Map<String, Map<String, Double>> minimumRetailPriceOfComponentsPerTechnology = new HashMap<String, Map<String,Double>>();
		final Map<String, Double> minimumRetailPriceOfComponentsPerCurrency = new HashMap<String, Double>();
		final Map<String, Map<String, Double>> maximumRetailPriceOfComponentsPerTechnology = new HashMap<String, Map<String,Double>>();
		final Map<String, Double> maximumRetailPriceOfComponentsPerCurrency = new HashMap<String, Double>();						
		final Integer	totalNumberOfTools;
		final Map<String, Double> averageToolsPricePerCurrency = new HashMap<String, Double>();
		final Map<String, Double> deviationToolsPricePerCurrency = new HashMap<String, Double>();
		final Map<String, Double> minimumRetailPriceOfToolsPerCurrency = new HashMap<String, Double>();
		final Map<String, Double> maximumRetailPriceOfToolsPerCurrency = new HashMap<String, Double>();
		Map<String, Long> totalPatronages;
		final Map<String, Map<String, Double>> averagePatronageBudgetPerState = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> deviationPatronageBudgetPerState = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> minimumPatronageBudgetPerState = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> maximumPatronageBudgetPerState = new HashMap<String, Map<String,Double>>();
		
		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		totalNumberOfTools = this.repository.totalNumberOfTools();
		totalPatronages = this.repository.totalPatronages().stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Long)p.get(1)));
		for(final String currency: currencies) {
			final String c = currency.replace('"', ' ').trim();
			final Map<String, Double> avgComponentTech = this.repository.averageComponentsPricePerTechnology(c).stream().collect(Collectors.toMap(i->i.get(0).toString(), i->(Double)i.get(1)));
			averageComponentsPricePerTechnology.put(c, avgComponentTech);
			averageComponentsPricePerCurrency.put(c, this.repository.averageComponentsPricePerCurrency(c));
			final Map<String, Double> stdComponentTech =  this.repository.deviationComponentsPricePerTechnology(c).stream().collect(Collectors.toMap(i->i.get(0).toString(), i->(Double)i.get(1)));
			deviationComponentsPricePerTechnology.put(c, stdComponentTech);
			deviationComponentsPricePerCurrency.put(c, this.repository.deviationComponentsPricePerCurrency(c));
			final Map<String, Double> minComponentTech = this.repository.minimumRetailPriceOfComponentsPerTechnology(c).stream().collect(Collectors.toMap(i->i.get(0).toString(), i->(Double)i.get(1)));
			minimumRetailPriceOfComponentsPerTechnology.put(c, minComponentTech);
			minimumRetailPriceOfComponentsPerCurrency.put(c, this.repository.minimumRetailPriceOfComponentsPerCurrency(c));
			final Map<String, Double> maxComponentTech = this.repository.maximumRetailPriceOfComponentsPerTechnology(c).stream().collect(Collectors.toMap(i->i.get(0).toString(), i->(Double)i.get(1)));
			maximumRetailPriceOfComponentsPerTechnology.put(c, maxComponentTech);
			maximumRetailPriceOfComponentsPerCurrency.put(c, this.repository.maximumRetailPriceOfComponentsPerCurrency(c));
			averageToolsPricePerCurrency.put(c, this.repository.averageToolsPricePerCurrency(c));
			deviationToolsPricePerCurrency.put(c, this.repository.deviationToolsPricePerCurrency(c));
			minimumRetailPriceOfToolsPerCurrency.put(c, this.repository.minimumRetailPriceOfToolsPerCurrency(c));
			maximumRetailPriceOfToolsPerCurrency.put(c, this.repository.maximumRetailPriceOfToolsPerCurrency(c));
			final Map<String, Double> avgPatronage = this.repository.averagePatronageBudgetPerState(c).stream().collect(Collectors.toMap(i->i.get(0).toString(), i->(Double)i.get(1)));
			averagePatronageBudgetPerState.put(c, avgPatronage);
			final Map<String, Double> stdPatronage = this.repository.deviationPatronageBudgetPerState(c).stream().collect(Collectors.toMap(i->i.get(0).toString(), i->(Double)i.get(1)));
			deviationPatronageBudgetPerState.put(c, stdPatronage);
			final Map<String, Double> minPatronage = this.repository.minimumPatronageBudgetPerState(c).stream().collect(Collectors.toMap(i->i.get(0).toString(), i->(Double)i.get(1)));
			minimumPatronageBudgetPerState.put(c, minPatronage);
			final Map<String, Double> maxPatronage = this.repository.maximumPatronageBudgetPerState(c).stream().collect(Collectors.toMap(i->i.get(0).toString(), i->(Double)i.get(1)));
			maximumPatronageBudgetPerState.put(c, maxPatronage);
		}
		dashboard = new AdminDashboard();
		
		dashboard.setTotalNumberOfComponents(totalNumberOfComponents);
		dashboard.setAverageComponentsPricePerTechnology(averageComponentsPricePerTechnology);
		dashboard.setAverageComponentsPricePerCurrency(averageComponentsPricePerCurrency);
		dashboard.setDeviationComponentsPricePerTechnology(deviationComponentsPricePerTechnology);
		dashboard.setDeviationComponentsPricePerCurrency(deviationComponentsPricePerCurrency);
		dashboard.setMinimumRetailPriceOfComponentsPerTechnology(minimumRetailPriceOfComponentsPerTechnology);
		dashboard.setMinimumRetailPriceOfComponentsPerCurrency(minimumRetailPriceOfComponentsPerCurrency);
		dashboard.setMaximumRetailPriceOfComponentsPerTechnology(maximumRetailPriceOfComponentsPerTechnology);
		dashboard.setMaximumRetailPriceOfComponentsPerCurrency(maximumRetailPriceOfComponentsPerCurrency);
		
		dashboard.setTotalNumberOfTools(totalNumberOfTools);
		dashboard.setAverageToolsPricePerCurrency(averageToolsPricePerCurrency);
		dashboard.setDeviationToolsPricePerCurrency(deviationToolsPricePerCurrency);
		dashboard.setMinimumRetailPriceOfToolsPerCurrency(minimumRetailPriceOfToolsPerCurrency);
		dashboard.setMaximumRetailPriceOfToolsPerCurrency(maximumRetailPriceOfToolsPerCurrency);
		
		dashboard.setTotalPatronages(totalPatronages);
		dashboard.setAveragePatronageBudgetPerState(averagePatronageBudgetPerState);
		dashboard.setDeviationPatronageBudgetPerState(deviationPatronageBudgetPerState);
		dashboard.setMinimumPatronageBudgetPerState(minimumPatronageBudgetPerState);
		dashboard.setMaximumPatronageBudgetPerState(maximumPatronageBudgetPerState);
		
		return dashboard;
	}

	@Override
	public void unbind(final Request<AdminDashboard> request, final AdminDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,
			"totalNumberOfComponents",
			"averageComponentsPricePerTechnology",
			"averageComponentsPricePerCurrency",
			"deviationComponentsPricePerTechnology",
			"deviationComponentsPricePerCurrency",
			"minimumRetailPriceOfComponentsPerTechnology",
			"minimumRetailPriceOfComponentsPerCurrency",
			"maximumRetailPriceOfComponentsPerTechnology",
			"maximumRetailPriceOfComponentsPerCurrency",
			"totalNumberOfTools",
			"averageToolsPricePerCurrency",
			"deviationToolsPricePerCurrency",
			"minimumRetailPriceOfToolsPerCurrency",
			"maximumRetailPriceOfToolsPerCurrency",
			"totalPatronages",
			"averagePatronageBudgetPerState",
			"deviationPatronageBudgetPerState",
			"minimumPatronageBudgetPerState",
			"maximumPatronageBudgetPerState");
		
	}

}
