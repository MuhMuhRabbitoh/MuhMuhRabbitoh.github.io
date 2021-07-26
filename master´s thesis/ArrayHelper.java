package masterThesis;

public class ArrayHelper {
	
	//private PolarizationCalculator calculator;
	
	private Agent[][]  array;
	
	public ArrayHelper() {
		
	}
	
	public void setArray(Agent[][] array) {
		this.array = array;
	}
	
	public Agent getAgent(int patch, int num) {
		
		return array[patch-1][num];
	}
	
	public void changeArray(int patch, int num, Agent agent) {
		array[patch-1][num] = agent;
		//calculator.setCalculator(this);
	}
	
	/*
	 * public void setCalculator(PolarizationCalculator calc) {
		this.calculator = calc;
		}	
	 */
	
}
