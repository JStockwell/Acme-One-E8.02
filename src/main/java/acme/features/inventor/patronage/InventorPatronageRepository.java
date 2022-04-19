package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);
	
	@Query("select p from Patronage p where p.inventor.id = :masterId")
	Collection<Patronage> findAllByInventorId(int masterId);
	
	@Query("select i from Inventor i where i.id = :masterId")
	Inventor findInventorById(int masterId);
	
//	@Query("select i from Inventor i where i.user.id = :masterId")
//	Inventor findInventorByUserId(int masterId);

}
