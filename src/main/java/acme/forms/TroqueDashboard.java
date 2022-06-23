package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TroqueDashboard implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Map<String, Double> ratioTroqueComponents; //Artefact Name and Double is the ratio
	Map<String, Long> averageQuantityOfTroque;
	Map<String, Long> deviationQuantityOfTroque;
	Map<String, Long> minimumQuantityOfTroque;
	Map<String, Long> maximumQuantityOfTroque;
}
