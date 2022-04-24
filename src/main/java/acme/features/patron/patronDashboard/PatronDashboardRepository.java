package acme.features.patron.patronDashboard;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository{
	
	@Query("select p.status, count(p) from Patronage p where p.patron.id =:patronId group by p.status")
	List<Tuple> totalPatronage(int patronId);
	
	@Query("select p.status, avg(p.budget.amount) from Patronage p where p.patron.id =:patronId and p.budget.currency =:currency group by p.status")
	List<Tuple> averagePatronageBudgetPerStatus(int patronId, String currency);
	
	@Query("select p.status, min(p.budget.amount) from Patronage p where p.patron.id =:patronId and p.budget.currency =:currency group by p.status")
	List<Tuple> minimumPatronageBudgetPerState(int patronId, String currency);
	
	@Query("select p.status, max(p.budget.amount) from Patronage p where p.patron.id =:patronId and p.budget.currency =:currency group by p.status")
	List<Tuple> maximumPatronageBudgetPerState(int patronId, String currency);
	
	@Query("select p.status, stddev(p.budget.amount) from Patronage p where p.patron.id =:patronId and p.budget.currency =:currency group by p.status")
	List<Tuple> deviationPatronageBudgetPerState(int patronId, String currency);
}
