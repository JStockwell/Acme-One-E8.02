package acme.features.inventor.troque;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.troque.Troque;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorTroqueListService implements AbstractListService<Inventor,Troque> {

	@Autowired
	protected InventorTroqueRepository repository;
	
	@Override
	public boolean authorise(final Request<Troque> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Troque> findMany(final Request<Troque> request) {
		assert request !=null;
		
		final Collection<Troque> res;
		int id;
		
		id=request.getPrincipal().getActiveRoleId();
		res= this.repository.findTroqueByInventorId(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Troque> request, final Troque entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "theme", "explanation", "quantity");
		
	}
	
}
