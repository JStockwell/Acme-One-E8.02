package acme.features.administrator.adminDashboard;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdminDashboardRepository extends AbstractRepository{

	@Query("select count(i) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT")
	int totalNumberOfComponents();
	
	@Query("select i.technology, i.price.currency, avg(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT group by i.price.currency, i.technology")
	Map<String, Map<String, Double>> averageComponentsPricePerTechnology();
}
