package acme.features.inventor.toolkit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface ToolkitRepository extends AbstractRepository{
	@Query("select i from Inventor i where i.id=:id")
	Inventor findInventorById(int id);
	
	@Query("select t from Toolkit t where t.id=:id")
	Toolkit findToolkitById(int id);
}
