package acme.entities.quantity;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.entities.AbstractEntity;

public class Quantity extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Toolkit toolkit;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Item item;
	
	protected int itemQuantity;

}
