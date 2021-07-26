package masterThesis;

import java.util.Random;


import repast.simphony.engine.schedule.ScheduledMethod;

public class Agent {

	double behavior;
	double meanAction;
	double perceivedThreat;
	double decreaseValue;
	boolean localSearch;
	boolean isThreated;
	
	boolean individualThreat;
	
	double f;							//probability to observe own patch
	
	SocialIdentity identity;
	ArrayHelper helper;
	PolarizationCalculator calculator;
	
	double personalIdentity;
	int externalThreat;
	double threat;
	int threatCounter;
	double k  = 0.05
			; 					//factor of growth
	double probToInfect;
	
	double proObs;						//probability to observe
	int numObs;							//number of observed agents						
	int patchNumber;
	int arrayID;
	int timer = 0;
	
	public Agent(double my, double basicNorm1, double basicNorm2, int externalthreat, ArrayHelper helper, int arrayID, int patchNumber, 
					boolean localSearch, boolean individualThreat, double f, double infectionProb, PolarizationCalculator calculator) {
		
		this.proObs = my;
		this.numObs = 30;
		
		this.identity = new SocialIdentity(basicNorm1, basicNorm2);
		
		this.personalIdentity = new Random().nextDouble();
		this.externalThreat = externalthreat;
		this.threat = externalThreat;
		this.decreaseValue = 1.0;
		this.helper = helper;
		this.arrayID = arrayID;
		this.patchNumber = patchNumber;
		this.localSearch = localSearch;
		this.individualThreat = individualThreat;
		this.f = f;
		this.probToInfect = infectionProb;
		this.calculator = calculator;
	}
	
	@ScheduledMethod(start =1, interval = 0)
	public void step() {
		action();	
		
	}
	
	/**
	 * observe an action
	 * Decrease identity over time
	 * 
	 */
	@ScheduledMethod(start =2, interval = 1)
	public void step2() {
		timer++;
		if (threatCounter > 0) {
			//this.threat = threat-1;
			//this.perceivedThreat = calcPerceivedThreat();
			//identity.influenceIdentification(perceivedThreat);
			threatCounter--;
			threat--;
			identity.decreaseIdentification(decreaseValue);
			if(new Random().nextDouble() < 0.01 ) {
				threatCounter=0;
			}
			//identity.decreaseIdentification(decreaseValue);
		} else {
			this.setIsThreated(false);
			this.threat = 0;
			identity.setBasicIdentification();
		}
		
		
		
		if ( proObs > new Random().nextDouble()) {
			action();
			
		}
		
	}
	
	
	@ScheduledMethod(start =1, interval = 100)
	public void step3() {
		if (!individualThreat) {
			threatImpact();
		}
		
		
	}
	
	protected void action() {
		
		double norm = identity.getNorm();
		double ident = identity.getIdentification();
		
		behavior = (ident * norm) + ((1-ident) * personalIdentity ); //Handeln abhängig von der identifikation 
		double meanBehavior = observeAction();
		double dif;
		//if (ident > 0.5 || perceivedThreat > 0.5 ) {
		//if (ident > new Random().nextDouble()) {
		//if(perceivedThreat > new Random().nextDouble()) {
		if (behavior > meanBehavior) {
			dif = behavior - meanBehavior;
			identity.decreaseNorm(dif);
		} else if (behavior < meanBehavior) {
			dif = meanBehavior - behavior;
			identity.increaseNorm(dif);
		}
		
		
	}
	
	protected void setIsThreated(boolean b) {
		this.isThreated = b;
		if (b) {
			threatCounter=100;
		}
	}

	
	
	/**
	 *  Observe neighbors and influence own norm
	 *  
	 * @return mean behavior
	 */
	public double observeAction() {
		
		double sumBehavior=0;
		double counter=0;
		
		if(localSearch) {
				for (int i = 0; i < numObs; i++) {
					
					
					if (f >= new Random().nextDouble()) {
						
						int lowerBoundInclusive=0;
						int upperBoundInclusive=48;
		
						int random = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
						while (helper.getAgent(this.patchNumber, random).getArrayID() == this.arrayID) {
							random = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
						}
						Agent tmp = helper.getAgent(this.patchNumber, random); 
						if (tmp.isThreated == true) {
							if (new Random().nextDouble() < probToInfect) {
								threatImpact();
							}
						}
						if (tmp.getAgentsGroup() == this.getAgentsGroup()) {
							sumBehavior += tmp.getBehavior();
							counter++;
						}
						
					} else {
						int lowerBoundInclusive=0;
						int upperBoundInclusive=48;
						int random = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
						int obsPatch;
						
						if (this.patchNumber == 1 ) {
							obsPatch = 2;
						}
						
						else if (this.patchNumber == 49) {
							obsPatch = 48;
						}
						else {
							
							int randomizer = new Random().nextInt(2)+1;
							 if (randomizer == 1) {
								 obsPatch = this.patchNumber-1;;
							 } else {
								 obsPatch = this.patchNumber + 1;
							 }
						}
						Agent tmp2 = helper.getAgent(obsPatch, random);
						if (tmp2.isThreated == true) {
							if (new Random().nextDouble() < probToInfect) {
								threatImpact();
							}
						}
						if (tmp2.getAgentsGroup() == this.getAgentsGroup()) {
							sumBehavior += tmp2.getBehavior();
							counter++;
						}
						
					}
				}
				return sumBehavior/counter;
				
		}else {
			for (int i = 0; i < numObs; i++) {
				
				
				if (f >= new Random().nextDouble()) {
					
					int lowerBoundInclusive=0;
					int upperBoundInclusive=48;
	
					int random = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
					while (helper.getAgent(this.patchNumber, random).getArrayID() == this.arrayID) {
						random = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
					}
					Agent tmp = helper.getAgent(this.patchNumber, random);
					if (tmp.isThreated == true) {
						if (new Random().nextDouble() < probToInfect) {
							threatImpact();
						}
					}
					if (tmp.getAgentsGroup() == this.getAgentsGroup()) {
						sumBehavior += tmp.getBehavior();
						counter++;
					}
					
				} else {
					int lowerBoundInclusive=0;
					int upperBoundInclusive=48;
					int random = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
					int random2 = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
				
					Agent tmp2 = helper.getAgent(random+1, random2); 
					if (tmp2.isThreated == true) {
						if (new Random().nextDouble() < probToInfect) {
							threatImpact();
						}
					}
					if (tmp2.getAgentsGroup() == this.getAgentsGroup()) {
						sumBehavior += tmp2.getBehavior();
						counter++;
					}
					
				}
			}
			return sumBehavior/counter;
		}
	}
	
	private void threatImpact() {
		if (externalThreat > 0 && timer <35000) {
			this.threat = this.threat + this.externalThreat;
			this.perceivedThreat = calcPerceivedThreat();
			identity.influenceIdentification(perceivedThreat);
			this.setIsThreated(true);
		}
		
	}
		
	private double calcPerceivedThreat() {
		
		double denom = 0.1 + ( 0.9 * ( Math.exp(- (k * this.threat ))));
		double itv = (0.1 * 1) / denom;									//itv = individual threat value
		return itv;
	}
	
	public Group getAgentsGroup(){
		return identity.getGroup();
	}
	
	public double getAgentsNorm() {
		return identity.getNorm();
	}
	
	public double getBehavior() {
		return this.behavior;
	}
	
	public int getArrayID() {
		return arrayID;
	}
	
	
	
	
	/********************************************************************************************************
	*
	* Methods for Repasts output query
	*
	********************************************************************************************************/
	
	
	public boolean getIsThreated() {
		return this.isThreated;
	}
	
	public boolean getNorm02() {
		if (identity.getNorm() < 0.2) {
			return true;
		} else return false;
	}
	
	public boolean getNorm04() {
		if (identity.getNorm() >= 0.2 && identity.getNorm() < 0.4) {
			return true;
		} else return false;
	}
	
	public boolean getNorm06() {
		if (identity.getNorm() >= 0.4 && identity.getNorm() < 0.6) {
			return true;
		} else return false;
	}
	
	public boolean getNorm08() {
		if (identity.getNorm() >= 0.6 && identity.getNorm() < 0.8) {
			return true;
		} else return false;
	}
	
	public boolean getNorm1() {
		if (identity.getNorm() > 0.8) {
			return true;
		} else return false;
	}
	
	public boolean G1getNorm02() {
		if (identity.getNorm() < 0.2 && identity.getGroup() == Group.Group1) {
			
			return true;
		} else return false;
	}
	
	public boolean G1getNorm04() {
		if (identity.getNorm() >= 0.2 && identity.getNorm() < 0.4 && identity.getGroup() == Group.Group1) {
			return true;
		} else return false;
	}
	
	public boolean G1getNorm06() {
		if (identity.getNorm() >= 0.4 && identity.getNorm() < 0.6 && identity.getGroup() == Group.Group1) {
			return true;
		} else return false;
	}
	
	public boolean G1getNorm08() {
		if (identity.getNorm() >= 0.6 && identity.getNorm() < 0.8 && identity.getGroup() == Group.Group1) {
			return true;
		} else return false;
	}
	
	public boolean G1etNorm1() {
		if (identity.getNorm() > 0.8 && identity.getGroup() == Group.Group1) {
			return true;
		} else return false;
	}
	
	public boolean G2getNorm02() {
		if (identity.getNorm() < 0.2 && identity.getGroup() == Group.Group2) {
			return true;
		} else return false;
	}
	
	public boolean G2getNorm04() {
		if (identity.getNorm() >= 0.2 && identity.getNorm() < 0.4 && identity.getGroup() == Group.Group2) {
			return true;
		} else return false;
	}
	
	public boolean G2getNorm06() {
		if (identity.getNorm() >= 0.4 && identity.getNorm() < 0.6 && identity.getGroup() == Group.Group2) {
			return true;
		} else return false;
	}
	
	public boolean G2getNorm08() {
		if (identity.getNorm() >= 0.6 && identity.getNorm() < 0.8 && identity.getGroup() == Group.Group2) {
			return true;
		} else return false;
	}
	
	public boolean G2getNorm1() {
		if (identity.getNorm() > 0.8 && identity.getGroup() == Group.Group2) {
			return true;
		} else return false;
	}

	public int getexternalThreat() {
		return this.externalThreat;
	}
	
	public double getProbabilityOfWithinPatchObservation() {
		return this.f;
	}
	
	public boolean extrema() {
		if (this.identity.getNorm() < 0.2 | this.identity.getNorm() > 0.8) {
			return true;
		} else return false;
	}
	
	public double getInfectionProb() {
		return probToInfect;
	}
}
