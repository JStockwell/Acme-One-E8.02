package acme.entities.item;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;

@Entity
public class Chimpum extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$") //copied from item
	// the regexp should contain /"^\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"   d{2} is any 2 digits to make the yy the second segment is for mm and third for dd
	private String code;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationMoment;
	
	@NotBlank
	@Length(max=100)
	private String title;
	
	@NotBlank
	@Length(max=255)
	private String description;
	
	//period is these 2 dates
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	
	@NotNull
	private Money budget;
	
	@URL
	private String link;
	
	@OneToOne(optional=true) //one to one optional true
	@Valid
	protected Item item;

}
