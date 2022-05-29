package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor,Chimpum> {

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		boolean res;
		int patronageId;
		Chimpum chimpum;
		
		patronageId = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(patronageId);
		res = chimpum != null && request.isPrincipal(chimpum.getItem().getInventor());
		
		return res;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		
		Chimpum res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.repository.findOneChimpumById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "");
		
	}
}
