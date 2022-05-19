package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("SELECT pt FROM Patronage pt WHERE pt.patron.id = :id")
	Collection<Patronage> findPatronageByPatron(int id);

	@Query("SELECT pt FROM Patronage pt WHERE pt.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("SELECT p FROM Patron p WHERE p.id = :id")
	Patron findOnePatronById(int id);
	
	@Query("select pr from PatronageReport pr where pr.patronage.id = :id")
	Collection<PatronageReport> findPatronageReportByPatronageId(int id);
	
	@Query("SELECT p FROM Patronage p WHERE p.code = :code")
	Patronage findPatronageByCode(String code);

}
