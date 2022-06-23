package acme.entities.troque;

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

import acme.entities.item.Item;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Troque extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp="^\\w{6}:\\d{2}\\d{2}:\\d{2}$")
//	objetivo -> ^\\w{6}:mmyy:dd$
//	^[A-Z]{3}-\\d{2}(-\\d{1,2}){3}$
	// el patron es /^\w{3}-yy:\d{1,2}:mm:dd$
	// XXX-22:00:05:31 sería un código válido hoy
	// ABC-22:0:05:31 sería otro código válido hoy
	// XXX-23:00:05:31 no sería un código válido hoy
	// Example: ^[A-Z]{3}-\\d{2}(/\\d{1,2}){3}$ valida ABC-22/0/05/31
	// the regexp should contain /"^\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"   d{2} is any 2 digits to make the yy the second segment is for mm and third for dd
	private String code;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationMoment;
	
	@NotBlank
	@Length(max=100)
	private String theme;
	
	@NotBlank
	@Length(max=255)
	private String explanation;
	
	//period is these 2 dates
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	
	@NotNull
	@Valid
	private Money quantity;
	
	@URL
	private String moreInfo;
	
	@OneToOne(optional=false) //one to one optional false as the relation is convulsory
	@Valid
	@NotNull
	private Item item;

}
