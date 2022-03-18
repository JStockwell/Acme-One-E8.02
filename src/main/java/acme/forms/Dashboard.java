package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
//	The system must handle administrator dashboards with the following indicators: 
//	total number of components; average, deviation, minimum, and maximum retail price of components, grouped technology and currency; 
//	total number of tools; average, deviation, minimum, and maximum retail price of tools, grouped by currency; 
//	total number of pro-posed/accepted/denied patronages; average, deviation, minimum, and maximum budget of proposed/accepted/denied patronages.

	Integer	totalNumberOfComponents;
	Map<String, Map<String, Double>> averageComponentsPricePerTechnology;			// Map<Technology, Map<Currency, Price>>
	Map<String, Double> averageComponentsPricePerCurrency;							// Map<Currency, Price>
	Map<String, Map<String, Double>> deviationComponentsPricePerTechnology;			//		.
	Map<String, Double> deviationComponentsPricePerCurrency;						//		.
	Map<String, Map<String, Double>> minimumRetailPriceOfComponentsPerTechnology;	//		.
	Map<String, Double> minimumRetailPriceOfComponentsPerCurrency;
	Map<String, Map<String, Double>> maximumRetailPriceOfComponentsPerTechnology;
	Map<String, Double> maximumRetailPriceOfComponentsPerCurrency;
								
	Integer	totalNumberOfTools;
	Map<String, Double> averageToolsPricePerCurrency;
	Map<String, Double> deviationToolsPricePerCurrency;
	Map<String, Double> minimumRetailPriceOfToolsPerCurrency;
	Map<String, Double> maximumRetailPriceOfToolsPerCurrency;
								
//	Key -> state of patronage (proposed/accepted/denied), Value -> total of patronages with this state
	Map<String, Integer> totalPatronages;
	Map<String, Map<String, Double>> averagePatronageBudgetPerState;
	Map<String, Map<String, Double>> deviationPatronageBudgetPerState;
	Map<String, Map<String, Double>> minimumPatronageBudgetPerState;
	Map<String, Map<String, Double>> maximumPatronageBudgetPerState;
	

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
