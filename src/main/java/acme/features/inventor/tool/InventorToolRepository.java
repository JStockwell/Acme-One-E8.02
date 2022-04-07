package acme.features.inventor.tool;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolRepository extends AbstractRepository{
	
	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);
	
	@Query("select item from Item item where item.item_type = TOOL")
	List<Item> findAllTools();

}
