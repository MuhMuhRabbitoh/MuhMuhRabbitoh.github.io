package masterThesis;


import java.util.Random;

import repast.simphony.context.Context;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.RandomGridAdder;


public class ContextCreator implements ContextBuilder<Object>{

	@Override
	public Context build(Context<Object> context) {
		
		context.setId("masterThesis");
		
		Parameters params = RunEnvironment.getInstance().getParameters();
		
		
		//int nStart = (int) params.getValue("earlyAdopters");
		double my = (double) params.getValue("interactionRate");
		int externalThreat = (int) params.getValue("externalThreat");
		double basicNorm1 = (double) params.getValue("basicNorm1");
		double basicNorm2 = (double) params.getValue("basicNorm2");
		boolean localSearch = (boolean) params.getValue("localSearch");
		boolean individualThreat = (boolean) params.getValue("individualThreat");
		double f = (double) params.getValue("f");
		int numberThreatened = (int) params.getValue("numberThreatened");
		double infectionProb = (double) params.getValue("infectionProb");
	
		
		int xdim = 57; 					
		int ydim = 57; 
		
		

		Agent[][] allAgents = new Agent[49][49]; 
		
		ArrayHelper helper = new ArrayHelper();
		PolarizationCalculator calc = new PolarizationCalculator();
		
		
		
	
		Grid<Object> grid = GridFactoryFinder.createGridFactory(null).createGrid("Grid", context,
				new GridBuilderParameters<Object>(new repast.simphony.space.grid.WrapAroundBorders(),
						new RandomGridAdder<Object>(), true, xdim, ydim));
	
		
		int patch = 0;
		for (int m= 0; m < 7; m++) {
			
			for (int n = 0; n < 7; n++) {
				patch++;
				int counter = 0;
			
				for ( int y = 0; y < 8; y++) {
					for (int x = 0; x < 8; x++) {
						int xpos = x+8*n;
						int ypos = ydim-1-y-8*m;
						
						if (xpos % 8 == 0 | ypos % 8 == 0 )  {
							Block block = new Block();
							context.add(block);
							grid.moveTo(block, xpos, ypos);
						} else {
							
							
						Agent agent = new Agent(my, basicNorm1, basicNorm2, externalThreat, helper, counter, patch, localSearch, 
								individualThreat, f, infectionProb, calc);
							
						context.add(agent);
						allAgents[patch-1][counter] = agent; 
						grid.moveTo(agent, xpos, ypos);
						counter++;
					
						
						}
					}
				}
			}
		}
		

		/*
		 * die beiden For-Schleifen dienen nur der Optik...Grenzen der Patches
		 */
		
		for (int x = 0; x < xdim; x++) {
			Block block = new Block();
			context.add(block);
			grid.moveTo(block, x, 0);
		
		}
		
		for (int y= 0; y < ydim; y++) {
			Block block = new Block();
			context.add(block);
			grid.moveTo(block, xdim-1, y);
		}
		
		helper.setArray(allAgents);
		calc.setCalculator(helper);
		context.add(calc);
		//helper.setCalculator(calc);
		
		if (individualThreat) {
			
			
			for (int i = 0; i < numberThreatened; i++) {
				int lowerBoundInclusive=0;
				int upperBoundInclusive=49;
				//int random = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
				int random =24;
				int random2 = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
				while (helper.getAgent(random+1, random2).getIsThreated() == true) {
					random2 = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
				}
				helper.getAgent(random+1, random2).setIsThreated(true);
			}
			 	
		}
			
		if (RunEnvironment.getInstance().isBatch()){
			RunEnvironment.getInstance().endAt(20000);
		}
		
		return context;
	
	}
		
}
