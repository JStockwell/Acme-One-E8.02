package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChimpumDashboard implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Map<String, Double> ratioChinpumArtefacts; //Artefact Name and Double is the ratio
	Map<String, Long> averageBudgetOfChinpum;
	Map<String, Long> deviationBudgetOfChinpum;
	Map<String, Long> minimumBudgetOfChinpum;
	Map<String, Long> maximumBudgetOfChinpum;
}
