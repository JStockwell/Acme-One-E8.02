package acme.features.any.component;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Component;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyComponentRepository extends AbstractRepository{
	
	@Query("select c from Component c")
	Collection<Component> findComponents();

	@Query("select c from Component c where c.id = :id")
	Component findOneComponentById(int id);
}