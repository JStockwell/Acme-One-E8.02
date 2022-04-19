package acme.entities.toolkit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Toolkit extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*A toolkit is a bundle with components and tools that are expected to work as a whole. 
	 * The system must store the following data about them: a code (pattern “^[A-Z]{3}-[0-9]{3}(-[A-Z])?$”, unique)
	 * , title (not blank, shorter than 101 characters), description (not blank, shorter than 256 characters), 
	 * assembly notes (not blank, shorter than 256 characters), and an optional link with further information. 
	 * A toolkit may have several instances of the same component, but only one instance of a given tool.*/
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank
	@Length(max = 101)
	protected String title;
	
	@NotBlank
	@Length(max = 256)
	protected String description;
	
	@NotBlank
	@Length(max = 256)
	protected String assemblyNotes;
	
	@URL
	protected String link;

}
