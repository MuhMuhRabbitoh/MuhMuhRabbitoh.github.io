package masterThesis;

import java.awt.Color;


import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;


public class GroupStyle extends DefaultStyleOGL2D {
	

	@Override
	public Color getColor(Object agent) {

		Agent agentN = (Agent) agent;
		Group gr = ((Agent) agentN).getAgentsGroup();
		
		switch(gr)
		{
			case Group1:
				return Color.red;
			case Group2:
				return Color.blue;
			default: 
				return Color.black;
	
		}
	}
	
	@Override
	public float getScale(Object agent) {
		
		Agent agent1 = (Agent) agent;
		
		if ( agent1.getIsThreated()) {
			return 1.2f;
		} else {
			return 0.5f;
		}
		
	
	}
}