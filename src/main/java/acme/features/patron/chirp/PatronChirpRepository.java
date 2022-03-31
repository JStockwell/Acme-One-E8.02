package acme.features.patron.chirp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Chirp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronChirpRepository extends AbstractRepository{
	
	@Query("select c from Chirp where a.moment > :deadline")
	Collection<Chirp> findRecentChirps(Date deadline);

}
