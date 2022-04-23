package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("SELECT pt FROM Patronage pt WHERE pt.patron.id = :id")
	Collection<Patronage> findPatronageByPatron(int id);

	@Query("SELECT pt FROM Patronage pt WHERE pt.id = :id")
	Patronage findOne(int id);

}
