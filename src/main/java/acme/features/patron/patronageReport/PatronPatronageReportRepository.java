package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository {

	@Query("SELECT pr FROM PatronageReport pr WHERE pr.patronage.patron.id = :id")
	Collection<PatronageReport> findAllPatronageReportByPatronId(int id);

	@Query("SELECT pr FROM PatronageReport pr WHERE pr.id = :id")
	PatronageReport findPatronageReportById(int id);

}
