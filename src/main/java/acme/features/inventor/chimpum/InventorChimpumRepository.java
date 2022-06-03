package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository {

	@Query("SELECT ch FROM Chimpum ch WHERE ch.item.inventor.id = :id AND ch.item.itemType = 0") //Change for COMPONENT if needed
	Collection<Chimpum> findChimpumByInventorId(int id);

	@Query("SELECT ch FROM Chimpum ch WHERE ch.id = :id")
	Chimpum findOneChimpumById(int id);
	
	@Query("SELECT ch FROM Chimpum ch WHERE ch.code = :code")
	Chimpum findChimpumByCode(String code);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findOneItemById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id")
	Collection<Item> findAllItemsByInventor(int id);
}
