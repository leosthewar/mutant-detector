package com.meli.challenge.mutant.detector.domain.model;

/**
 * 
 * Dna </br>
 * Model class to save the  dna sequence and isMutant state
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
public class Dna  extends Request {

	
	public Dna(Boolean mutant) {
		this.mutant = mutant;
	}

	private boolean mutant;

	/**
	 * @return the mutant
	 */
	public boolean isMutant() {
		return mutant;
	}

	/**
	 * @param mutant the mutant to set
	 */
	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}

}
