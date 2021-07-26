package masterThesis;

import java.util.Random;

public class SocialIdentity {

												
	Group group;													//group membership
	
	double norm;													//individual norm
	double basicIdentification;										//start value
	double identification;											//current identification
	
	/**
	 * constructor to set the start value
	 * @param basic		committed basic norm for all agents
	 */
	public SocialIdentity(double basic1, double basic2) {
		int value = new Random().nextInt(2); 						//random group membership
		if (value ==0) {
			group = Group.Group1;
			this.norm = basic1 + calcValue(-0.15, 0.15);	
		} else {
			group = Group.Group2;
			this.norm = basic2 + calcValue(-0.15, 0.15);	
		}
												//set basic norm as start value

		//this.norm = this.basicNormGroup2 * calcValue(0.7, 1.3);			//range of variation for the individual norm dependent on the basic norm
		this.basicIdentification = calcValue(0.0, 1.0);				//range of variation for the basic identification
		this.identification = this.basicIdentification;				//current identification
	}
	
	/**
	 *  Calculate a random value between the parameter values
	 *  
	 * @param x1 	lower bound
	 * @param x2	upper bound
	 * @return	calculated value
	 */
	private double calcValue(double x1, double x2) {
		double f = Math.random()/Math.nextDown(1.0);
		return (x1*(1.0 - f) + x2*f);
	}
	
	/**
	 * change own norm around 0.01 dependent if the own norm is
	 * higher or lower than the mean norm
	 */
	protected void increaseNorm(double diff) {
		this.norm = this.norm + 0.02;
	}
	
	protected void decreaseNorm(double diff) {
		this.norm = this.norm - 0.02;
	}
	
	
	
	/*
	 * change identification dependent on the perceived threat
	 */
	protected void influenceIdentification(double perceivedThreat) {
		
		if (perceivedThreat < 0.2) {
			identification *= 1.0;
		} else if (perceivedThreat >= 0.2 && perceivedThreat < 0.5 ) {
			identification *= 1.1;
		} else if (perceivedThreat >= 0.5 && perceivedThreat < 0.8 ) {
			identification *= 1.25;
		} else {
			identification *= 1.5;
		}
		if (identification > 1.0) {
			identification = 1.0;
		}
	}
	
	/*
	 * Decrease identification around 1%
	 */
	protected void decreaseIdentification(double decreaseValue) {
		//double v = decreaseValue/100;
		//double v1 = 1-v;
		//identification = v1*identification;
		if (this.identification > this.basicIdentification) {
			this.identification = 0.98*this.identification;
		}
		
	}
	protected void setBasicIdentification() {
		this.identification = basicIdentification;
	}
	
	/**
	 * getter method to return the norm
	 * @return norm
	 */
	public double getNorm() {
		return this.norm;
	}
	
	public Group getGroup() {	
		return group;
	}
	/**
	 * getter method to return the identification parameter
	 * 
	 * @return identification
	 */
	public double getIdentification() {
		// TODO Auto-generated method stub
		return identification;
	}
	
	
}
