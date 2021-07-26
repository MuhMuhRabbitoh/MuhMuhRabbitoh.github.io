package masterThesis;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class DisplayStyleG2 extends DefaultStyleOGL2D {

	
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
		
		Group gr = ((Agent) agent1).getAgentsGroup();
		
		switch(gr)
		{
			case Group1:
				return 0f;
			case Group2:
				return 1.2f;
			default: 
				return 0f;
		}
			
	}

}
