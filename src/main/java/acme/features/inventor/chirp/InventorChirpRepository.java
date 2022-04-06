package acme.features.inventor.chirp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Chirp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChirpRepository extends AbstractRepository{
	
	@Query("select c from Chirp c where c.moment > :deadline")
	Collection<Chirp> findRecentChirps(Date deadline);

}
