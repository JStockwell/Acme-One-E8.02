package acme.components;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {
	
//	Serialization identifier
	
	protected static final long serialVersionUID = 1l;
	
//	Atributes
	
//	 A system currency, which must be “EUR” by default.
//	 A list of accepted currencies, which must be initialised to “EUR”, “USD”, and “GBP”.
//	 A list of strong spam terms, which must include “sex”, “hard core”, “viagra”, “cialis”, and their Spanish translations by default.
//	 A strong spam threshold, which must be 10% by default.
//	 A list of weak spam terms, which must include “sexy”, “nigeria", “you’ve won”, “one mil-lion”, and their corresponding Spanish translations by default.
//	 A weak spam threshold, which must be 25% by default.
	
	@NotBlank
	protected SystemCurrency defaultCurrency;
	
	@NotBlank
	protected String acceptedCurrencies;
		
	@NotBlank
	protected String strongSpamTerms;
	
	@NotBlank
	protected String strongSpamTermsEsp;
	
	@NotBlank
	protected Double strongThreshold;
	
	@NotBlank
	protected String weakSpamTerms;
	
	@NotBlank
	protected String weakSpamTermsEsp;
	
	@NotBlank
	protected Double weakThreshold;
	
}
