package acme.features.administrator.adminDashboard;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.AdminDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdminDashboardShowRepository implements AbstractShowService<Administrator, AdminDashboard>{

	@Autowired
	protected AdminDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<AdminDashboard> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public AdminDashboard findOne(final Request<AdminDashboard> request) {
		assert request!=null;
		
		AdminDashboard dashboard;
		Integer	totalNumberOfComponents;
		Map<String, Map<String, Double>> averageComponentsPricePerTechnology;
		
		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		averageComponentsPricePerTechnology = this.repository.averageComponentsPricePerTechnology();
		
		dashboard = new AdminDashboard();
		
		dashboard.setTotalNumberOfComponents(totalNumberOfComponents);
		dashboard.setAverageComponentsPricePerTechnology(averageComponentsPricePerTechnology);
		
		return dashboard;
	}

	@Override
	public void unbind(final Request<AdminDashboard> request, final AdminDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,
			"totalNumberOfComponents",
			"averageComponentsPricePerTechnology");
		
	}

}
