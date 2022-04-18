package acme.features.patron.patronDashboard;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import acme.framework.repositories.AbstractRepository;

public interface PatronDashboardRepository extends AbstractRepository{
	
	@Query("select p.status, count(p) from Patronage p where p.patron.id =:patronId group by p.status")
	Map<String, Integer> totalPatronage(int patronId);
	
	@Query("select p.status, sum(p.budget.amount)/(select count(q) from Patronage q where q.patron.id =:patronId) from Patronage p where p.patron.id =:patronId group by p.status")
	Map<String, Double> averagePatronageBudgetPerStatus(int patronId);
	
	@Query("select p.status, min(p.budget.amount) from Patronage p where p.patron.id =:patronId group by p.status")
	Map<String, Double> minimumPatronageBudgetPerState(int patronId);
	
	@Query("select p.status, max(p.budget.amount) from Patronage p where p.patron.id =:patronId group by p.status")
	Map<String, Double> maximumPatronageBudgetPerState(int patronId);
	
	@Query("select p.status, stddev(p.budget.amount) from Patronage p where p.patron.id =:patronId group by p.status")
	Map<String, Double> deviationPatronageBudgetPerState(int patronId);
}
