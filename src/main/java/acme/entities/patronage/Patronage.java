package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
	
	@NotNull
	protected Status status;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$", message = "Por favor, inserte un código que siga el patrón: 3 letras - 3 números")
	private String code;
	
	@NotBlank
	@Length(max=255)
	private String legislation;
	
	@NotNull
	private Money budget;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	
	@URL
	private String link;	
	
	// Relationships --------------------------------------------------------------------
	
	@ManyToOne(optional=false)
	@Valid
	@NotNull
	private Patron patron;
	
	@ManyToOne(optional=false)
	@Valid
	@NotNull
	private Inventor inventor;
}
