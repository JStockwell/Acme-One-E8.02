package acme.entities.patronage;

import java.time.LocalDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronageReport extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Past
	protected LocalDate creation;
	
	@NotBlank
	@Length(max = 256)
	protected String memorandum;
	
	@URL
	protected String link;
	
	@ManyToOne
	protected Patronage patronage;
	
	public String getCode() {
		String code = this.patronage.getCode();
		final String id = String.valueOf(this.getId());
		code = code + ":";
		for(int i=0;i<(4-id.length());i++) {
			code = code + "0";
		}
		code = code + id;
		return code;
	}

}
