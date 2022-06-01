package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository {

	@Query("SELECT ch FROM Chimpum ch WHERE ch.item.inventor.id = :id") //AND pt.item.itemType = TOOL/COMPONENT
	Collection<Chimpum> findChimpumByInventorId(int id);

	@Query("SELECT pt FROM Chimpum pt WHERE pt.id = :id")
	Chimpum findOneChimpumById(int id);
	
	@Query("SELECT p FROM Chimpum p WHERE p.code = :code")
	Chimpum findChimpumByCode(String code);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findOneItemById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id")
	Collection<Item> findAllItemsByInventor(int id);
}
