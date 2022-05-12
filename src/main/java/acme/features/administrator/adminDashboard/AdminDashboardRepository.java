package acme.features.administrator.adminDashboard;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdminDashboardRepository extends AbstractRepository{

	@Query("select count(i) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT")
	int totalNumberOfComponents();
	
	@Query("select i.technology, avg(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT and i.price.currency=:currency group by i.technology")
	List<Tuple> averageComponentsPricePerTechnology(String currency);
	
	@Query("select avg(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT and i.price.currency=:currency")
	Double averageComponentsPricePerCurrency(String currency);
	
	@Query("select i.technology, stddev(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT and i.price.currency=:currency group by i.technology")
	List<Tuple> deviationComponentsPricePerTechnology(String currency);
	
	@Query("select stddev(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT and i.price.currency=:currency")
	Double deviationComponentsPricePerCurrency(String currency);
	
	@Query("select i.technology, min(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT and i.price.currency=:currency group by i.technology")
	List<Tuple> minimumRetailPriceOfComponentsPerTechnology(String currency);
	
	@Query("select min(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT and i.price.currency=:currency")
	Double minimumRetailPriceOfComponentsPerCurrency(String currency);
	
	@Query("select i.technology, max(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT and i.price.currency=:currency group by i.technology")
	List<Tuple> maximumRetailPriceOfComponentsPerTechnology(String currency);
	
	@Query("select max(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.COMPONENT and i.price.currency=:currency")
	Double maximumRetailPriceOfComponentsPerCurrency(String currency);
	
	@Query("select count(i) from Item i where i.itemType=acme.entities.item.ItemType.TOOL")
	int totalNumberOfTools();
	
	@Query("select avg(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.TOOL and i.price.currency=:currency")
	Double averageToolsPricePerCurrency(String currency);
	
	@Query("select stddev(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.TOOL and i.price.currency=:currency")
	Double deviationToolsPricePerCurrency(String currency);
	
	@Query("select min(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.TOOL and i.price.currency=:currency")
	Double minimumRetailPriceOfToolsPerCurrency(String currency);
	
	@Query("select max(i.price.amount) from Item i where i.itemType=acme.entities.item.ItemType.TOOL and i.price.currency=:currency")
	Double maximumRetailPriceOfToolsPerCurrency(String currency);
	
	@Query("select p.status, count(p) from Patronage p group by p.status")
	List<Tuple> totalPatronages();
	
	@Query("select p.status, avg(p.budget.amount) from Patronage p where p.budget.currency =:currency group by p.status")
	List<Tuple> averagePatronageBudgetPerState(String currency);
	
	@Query("select p.status, stddev(p.budget.amount) from Patronage p where p.budget.currency =:currency group by p.status")
	List<Tuple> deviationPatronageBudgetPerState(String currency);
	
	@Query("select p.status, min(p.budget.amount) from Patronage p where p.budget.currency =:currency group by p.status")
	List<Tuple> minimumPatronageBudgetPerState(String currency);
	
	@Query("select p.status, max(p.budget.amount) from Patronage p where p.budget.currency =:currency group by p.status")
	List<Tuple> maximumPatronageBudgetPerState(String currency);
}
