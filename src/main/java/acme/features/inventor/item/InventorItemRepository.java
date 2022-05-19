package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.SystemConfiguration;
import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	@Query("select i from Item i where i.inventor.id = :id")
	Collection<Item> findItemsByInventor(int id);

	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);

	@Query("select i from Item i where i.code = :code")
	Item findItemByCode(String code);
	
	@Query("select sysconfig from SystemConfiguration sysconfig")
	SystemConfiguration getSystemConfiguration();
}