package acme.forms;

import java.io.Serializable;

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
	Integer totalPatronagesProposed;
	Integer totalPatronagesAccepted;
	Integer totalPatronagesDenied;
	Double averagePatronageBudgetPerStateProposed;
	Double averagePatronageBudgetPerStateAccepted;
	Double averagePatronageBudgetPerStateDenied;
	Double deviationPatronageBudgetPerStateProposed;
	Double deviationPatronageBudgetPerStateAccepted;
	Double deviationPatronageBudgetPerStateDenied;
	Double minimumPatronageBudgetPerStateProposed;
	Double minimumPatronageBudgetPerStateAccepted;
	Double minimumPatronageBudgetPerStateDenied;
	Double maximumPatronageBudgetPerStateProposed;
	Double maximumPatronageBudgetPerStateAccepted;
	Double maximumPatronageBudgetPerStateDenied;
}
