package acme.features.inventor.troque;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.troque.Troque;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorTroqueRepository extends AbstractRepository {

	@Query("SELECT ch FROM Troque ch WHERE ch.item.inventor.id = :id") //Change for COMPONENT if needed
	Collection<Troque> findTroqueByInventorId(int id);

	@Query("SELECT ch FROM Troque ch WHERE ch.id = :id")
	Troque findOneTroqueById(int id);
	
	@Query("SELECT ch FROM Troque ch WHERE ch.code = :code")
	Troque findTroqueByCode(String code);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findOneItemById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id and i.itemType = 1")
	Collection<Item> findAllItemsByInventor(int id);
	
	@Query("SELECT c.item FROM Troque c WHERE c.item.inventor.id = :id")
	Collection<Item> findAllItemsByInventorIfUsed(int id);
}
