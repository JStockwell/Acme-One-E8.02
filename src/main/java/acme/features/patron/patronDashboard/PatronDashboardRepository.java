package acme.features.patron.patronDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository{
	
	@Query("select count(p) from Patronage p where p.patron.id =:patronId and p.status =:status")
	Integer totalPatronage(int patronId, Status status);
	
	@Query("select avg(p.budget.amount) from Patronage p where p.patron.id =:patronId and p.status =:status")
	Double averagePatronageBudgetPerStatus(int patronId, Status status);
	
	@Query("select min(p.budget.amount) from Patronage p where p.patron.id =:patronId and p.status =:status")
	Double minimumPatronageBudgetPerState(int patronId, Status status);
	
	@Query("select max(p.budget.amount) from Patronage p where p.patron.id =:patronId and p.status =:status")
	Double maximumPatronageBudgetPerState(int patronId, Status status);
	
	@Query("select stddev(p.budget.amount) from Patronage p where p.patron.id =:patronId and p.status =:status")
	Double deviationPatronageBudgetPerState(int patronId, Status status);
}
