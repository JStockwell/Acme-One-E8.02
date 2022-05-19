package acme.features.inventor.quantity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface QuantityRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t where t.inventor.id=:id and t.draftMode=true")
	List<Toolkit> getToolkitsNotPublishedFromInventor(int id);
	
	@Query("select i from Item i where i.draft=false")
	List<Item> getItemsPublished();
}
