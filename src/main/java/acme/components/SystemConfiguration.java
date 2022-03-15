package acme.components;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity{
	
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
	protected static final SystemCurrency defaultCurrency = SystemCurrency.EUR;
	
	@NotBlank
	protected static final String acceptedCurrencies = SystemCurrency.EUR.toString() + SystemCurrency.GBP.toString() + SystemCurrency.USD.toString();
		
	@NotBlank
	protected static final String strongSpamTerms = "sex, hard core, viagra, cialis";
	
	@NotBlank
	protected static final String strongSpamTermsEsp = "sexo, hard core, viagra, cialis";
	
	@NotBlank
	protected static final Double strongThreshold = .1;
	
	@NotBlank
	protected static final String weakSpamTerms = "sexy, nigeria, you’ve won, one mil-lion";
	
	@NotBlank
	protected static final String weakSpamTermsEsp = "sexy, nigeria, has ganado, un millón";
	
	@NotBlank
	protected static final Double weakThreshold = .25;
	

}
