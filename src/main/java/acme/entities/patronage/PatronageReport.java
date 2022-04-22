package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Entity
public class PatronageReport extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date creation;
	
	@NotBlank
	@Length(max = 255)
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
