package acme.features.any.chinpumDashboard;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChimpumDashboardRepository extends AbstractRepository{

	@Query("select c.item.name, 1.0 * count(c)/(select count(d) from Chimpum d) from Chimpum c group by c.item")
	List<Tuple> getRatioOfChinpumArtefacts();
	
	@Query("select avg(c.budget.amount) from Chimpum c where c.budget.currency =:currency")
	Long getAverageBudget(String currency);
	
	@Query("select stddev(c.budget.amount) from Chimpum c where c.budget.currency =:currency")
	Long getDeviationBudget(String currency);
	
	@Query("select min(c.budget.amount) from Chimpum c where c.budget.currency =:currency")
	Long getMinimumBudget(String currency);
	
	@Query("select max(c.budget.amount) from Chimpum c where c.budget.currency =:currency")
	Long getMaximumBudget(String currency);
}
