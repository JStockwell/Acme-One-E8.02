package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity{

	// Serialisation identif
	protected static final long serialVersionUID = 1L;
	
	
	// Attributes
	
	protected Status status;
	
	@Column(unique = true)
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	private String code;
	
	@NotBlank
	@Length(max=256) //still needs testing for the 255 256 length
	private String legislation;
	
	@Positive //not sure if this is enough
	private Money budget;
	
	//TODO at least one month after its creation
	private Date patronageDate;
	
	@URL
	private String link;
	
}