package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	@Query("select i from Item i where i.inventor.id = :id")
	Collection<Item> findItemsByInventor(int id);

	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);
}