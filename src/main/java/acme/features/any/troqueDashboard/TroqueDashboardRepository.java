package acme.features.any.troqueDashboard;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface TroqueDashboardRepository extends AbstractRepository{

	@Query("select c.item.name, 1.0 * count(c)/(select count(d) from Troque d) from Troque c group by c.item")
	List<Tuple> getRatioOfTroqueArtefacts();
	
	@Query("select avg(c.quantity.amount) from Troque c where c.quantity.currency =:currency")
	Long getAverageQuantity(String currency);
	
	@Query("select stddev(c.quantity.amount) from Troque c where c.quantity.currency =:currency")
	Long getDeviationQuantity(String currency);
	
	@Query("select min(c.quantity.amount) from Troque c where c.quantity.currency =:currency")
	Long getMinimumQuantity(String currency);
	
	@Query("select max(c.quantity.amount) from Troque c where c.quantity.currency =:currency")
	Long getMaximumQuantity(String currency);
}
