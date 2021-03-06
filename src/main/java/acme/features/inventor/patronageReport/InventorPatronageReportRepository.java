package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{
	
	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findPatronageReportById(int id);
	
	@Query("select pr from PatronageReport pr where pr.patronage.inventor.id = :id")
	Collection<PatronageReport> findAllPatronageReportByInventorId(int id);
	
	@Query("SELECT pr FROM PatronageReport pr WHERE pr.patronage.id = :id")
	Collection<PatronageReport> findAllPatronageReportByPatronage(int id);

}
