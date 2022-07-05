package com.meli.challenge.mutant.detector.model;

/**
 * 
 * DnaDTO </br>
 * DTO to save the  dna sequence and isMutant state
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
public class DnaDTO  extends RequestDTO{

	
	public DnaDTO(Boolean mutant) {
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
