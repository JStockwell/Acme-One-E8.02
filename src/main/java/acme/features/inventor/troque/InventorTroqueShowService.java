package acme.features.inventor.troque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.troque.Troque;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorTroqueShowService implements AbstractShowService<Inventor,Troque> {

	@Autowired
	protected InventorTroqueRepository repository;
	
	@Override
	public boolean authorise(final Request<Troque> request) {
		assert request != null;
		
		boolean res;
		int troqueId;
		Troque troque;
		
		troqueId = request.getModel().getInteger("id");
		troque = this.repository.findOneTroqueById(troqueId);
		res = troque != null && request.isPrincipal(troque.getItem().getInventor());
		
		return res;
	}

	@Override
	public Troque findOne(final Request<Troque> request) {
		assert request != null;
		
		Troque res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.repository.findOneTroqueById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Troque> request, final Troque entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "creationMoment","theme", "explanation", "startDate", "finishDate", "quantity", "moreInfo");
		model.setAttribute("itemName", entity.getItem().getName());
		model.setAttribute("inventorName", entity.getItem().getInventor().getIdentity().getFullName());
	}
}
