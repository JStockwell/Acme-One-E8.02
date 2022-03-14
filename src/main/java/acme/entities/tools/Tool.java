package acme.entities.tools;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tool {

	@NotBlank
	@Length(max=100)
	private String name;
	
	@Column(unique = true)
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	private String code;
	
	@NotBlank
	@Length(max=100)
	private String technology;
	
	@NotBlank
	@Length(max=256)
	private String description;
	
	@Min(value = 0L)
	private Double price;
	
	private String link;
}
