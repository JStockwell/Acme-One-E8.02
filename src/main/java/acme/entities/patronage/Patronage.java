package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

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
	@Positive //not sure if this is enough
	private Money budget;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	//TODO at least one month after its creation
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	//TODO at least one month after its start
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	
	@URL
	private String link;
	
	
	// Relationships --------------------------------------------------------------------
	
	/*
	@OneToMany (dont use this)
	private Report report;
	 */
	
	@ManyToOne
	@NotNull
	private Inventor inventor;
}
