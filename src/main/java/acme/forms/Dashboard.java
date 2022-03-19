package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
//	The system must handle administrator dashboards with the following indicators: total number of components; average, deviation, minimum, and maximum retail price of components, grouped 
//	by technology and currency; total number of tools; average, deviation, minimum, and maximum retail price of tools, grouped by currency; total number of pro-posed/accepted/denied patronages; average, deviation, minimum, and maximum budget of proposed/accepted/denied patronages.

	Integer	totalNumberOfComponents;
	Double averageComponentsPerTechnology;
	Double averageComponentsPerCurrency;
	Double deviationComponentsPerTechnology;
	Double deviationComponentsPerCurrency;
	Money minimumRetailPriceOfComponentsPerTechnology;
	Money minimumRetailPriceOfComponentsPerCurrency;
	Money maximumRetailPriceOfComponentsPerTechnology;
	Money maximumRetailPriceOfComponentsPerCurrency;
								
	Integer	totalNumberOfTools;
	Double averageToolsPerCurrency;
	Double deviationToolsPerCurrency;
	Money minimumRetailPriceOfToolsPerCurrency;
	Money maximumRetailPriceOfToolsPerCurrency;
								
	Integer	proposedPatronages;
	Integer	acceptedPatronages;
	Integer	deniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
