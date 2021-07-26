package masterThesis;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class NormStyle extends DefaultStyleOGL2D  {

	@Override
	public Color getColor(Object agent) {

		Agent agentN = (Agent) agent;
	
		if (agentN.getAgentsNorm() < 0.2) {
			return Color.black;
		} else if (agentN.getAgentsNorm() >= 0.2 && agentN.getAgentsNorm() < 0.4) {
			return Color.blue;
		} else if (agentN.getAgentsNorm() >= 0.4 && agentN.getAgentsNorm() < 0.6) {
			return Color.green;
		} else if (agentN.getAgentsNorm() >= 0.6 && agentN.getAgentsNorm() < 0.8) {
			return Color.orange;
		} else if (agentN.getAgentsNorm() > 0.8) {
			return Color.red;
		} else {
			return Color.yellow;
		}
	}	
	
	@Override
	public float getScale(Object agent) {
		
		Agent agent1 = (Agent) agent;
		
		if ( agent1.getIsThreated() ) {
			return 1.2f;
		} else {
			return 0.5f;
		}
		
	
	}
}
