package masterThesis;



public class PolarizationCalculator {
	
	ArrayHelper helper;
	double countAgents = 2401.0;
	
	
	
	public void setCalculator(ArrayHelper helper) {
		this.helper = helper;
	}
	
	public double localExtremum() {
	
		double tmp = 0;
		for (int patch = 1; patch < 50; patch++) {
			double extremaNorm = 0;
			for( int agent = 0; agent < 49; agent++) {
				Agent a = helper.getAgent(patch, agent);
				if (a.getAgentsNorm() < 0.2 || a.getAgentsNorm() > 0.8) {
					extremaNorm++;
				}
			}
			if (extremaNorm/49.0 > 0.5) {
				tmp++;
			}
		}if (tmp > 0) {
			return tmp/49.0;
		} else return 0;
		
	}
	
	public double globalExtremum() {
		double countExtremaNorm = 0;
		for (int patch = 1; patch < 50; patch++) {
			for( int agent = 0; agent < 49; agent++) {
				Agent a = helper.getAgent(patch, agent);
				if (a.getAgentsNorm() < 0.2 || a.getAgentsNorm() > 0.8) {
					countExtremaNorm++;
				}
			}
		}
		if (countExtremaNorm==0) {
			return 0;
		} else return countExtremaNorm/countAgents;
			 
			
		
	}
	
	
}
