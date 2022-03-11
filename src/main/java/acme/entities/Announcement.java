package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends AbstractEntity{
	
//	Serialization identifier
	
	protected static final long serialVersionUID = 1l;
	
//	Atributes
	
//	An announcement is a formal piece of news. The system must store the following data about them: a creation moment (in the past), a title (not blank, shorter 
//	than 101 characters), a body (not blank, shorter than 256 characters), a flag to indicate whether they are critical or not, and an optional link with 
//	further information.
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	@Past
	protected Date creationMoment;
	
	@NotBlank
	@Length(max = 101)
	protected String title;
	
	@NotBlank
	@Length(max = 256)
	protected String body;
	
	@NotBlank
	protected Boolean critical;
	
	@URL
	protected String link;

}
