package acme.entities.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "code")
public class Item extends AbstractEntity{

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
	
	@NotNull
	private ItemType itemType;
	
	@URL
	private String link;
}
