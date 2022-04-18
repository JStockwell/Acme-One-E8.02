package acme.features.inventor.component;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Component;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorComponentRepository extends AbstractRepository{
	
	@Query("select c from Component c where c.inventor.id = :id")
	Collection<Component> findComponentsByInventor(int id);

	@Query("select c from Component c where c.id = :id")
	Component findOneComponentById(int id);
}