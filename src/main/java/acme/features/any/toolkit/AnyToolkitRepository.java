package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t")
	Collection<Toolkit> findToolkits();

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
}