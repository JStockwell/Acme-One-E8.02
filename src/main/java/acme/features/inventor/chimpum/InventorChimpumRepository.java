package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Chimpum;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface InventorChimpumRepository extends AbstractRepository {

	@Query("SELECT pt FROM Chimpum pt WHERE pt.item.inventor.id = :id")
	Collection<Chimpum> findChimpumByInventorId(int id);

	@Query("SELECT pt FROM Chimpum pt WHERE pt.id = :id")
	Chimpum findOneChimpumById(int id);
	
	@Query("SELECT p FROM Patron p WHERE p.id = :id")
	Patron findOnePatronById(int id);
	
	@Query("SELECT p FROM Chimpum p WHERE p.code = :code")
	Chimpum findChimpumByCode(String code);
	
	@Query("SELECT ch FROM Chimpum ch")
	Collection<Chimpum> findAllChimpums();
	
	@Query("SELECT i FROM Inventor i WHERE i.id = :id")
	Inventor findOneInventorById(int id);
	
	@Query("SELECT i FROM Inventor i")
	Collection<Inventor> findAllInventors();

}
