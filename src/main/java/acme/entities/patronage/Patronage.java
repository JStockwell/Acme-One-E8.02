package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity{

	// Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes
	
	protected Status status;
	
	@Column(unique = true)
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	private String code;
	
	@NotBlank
	@Length(max=255)
	private String legislation;
	
	@NotNull
	//TODO positive is not enough for this and since a complex constraint is needed we will wait until unit 4 for its implementation
	private Money budget;
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	//TODO at least one month after its creation
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	//TODO at least one month after its start
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	
	@URL
	private String link;
	
	
	// Relationships --------------------------------------------------------------------
	
	@ManyToOne
	@NotNull
	private Patron patron;
	
	@ManyToOne
	@NotNull
	private Inventor inventor;
}
