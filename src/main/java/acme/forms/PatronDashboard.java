package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{

	// Serialisation identifier -----------------------------------------------
	
	protected static final long	serialVersionUID	= 1L;
	
	// Serialisation identifier -----------------------------------------------
	
//	total number of proposed/accepted/denied patronages
//	average deviation minimum and maximum budget of proposed/accepted/denied patronages
	
								
//	Key -> state of patronage (proposed/accepted/denied), Value -> total of patronages with this state
	Map<String, Long> totalPatronages;
	Map<String, Map<String, Double>> averagePatronageBudgetPerState;
	Map<String, Map<String, Double>> deviationPatronageBudgetPerState;
	Map<String, Map<String, Double>> minimumPatronageBudgetPerState;
	Map<String, Map<String, Double>> maximumPatronageBudgetPerState;
}
