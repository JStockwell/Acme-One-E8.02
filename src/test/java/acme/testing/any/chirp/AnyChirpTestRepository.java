package acme.testing.any.chirp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Chirp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyChirpTestRepository extends AbstractRepository{
	@Query("select c from Chirp c where year(c.moment) < 1901 order by c.title")
	Collection<Chirp> findChirpsToPatch();
	
	@Query("delete from Chirp c where c.moment > :deadline")
	void deleteByMoment(Date deadline);
}