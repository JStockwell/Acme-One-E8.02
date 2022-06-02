package acme.entities.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Length(max=100)
	private String name;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	private String code;
	
	@NotBlank
	@Length(max=100)
	private String technology;
	
	@NotBlank
	@Length(max=255)
	private String description;
	
	@NotNull
	private Money price;
	
	@NotNull
	private ItemType itemType;
	
	@URL
	private String link;
	
	private boolean draft;
	
	@ManyToOne(optional=false)
	@Valid
	@NotNull
	protected Inventor inventor;
}
