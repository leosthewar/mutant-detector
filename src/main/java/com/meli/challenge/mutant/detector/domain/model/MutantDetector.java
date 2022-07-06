/**
 */
package com.meli.challenge.mutant.detector.domain.model;

/**
 * MutantDetector </br>
 * Model class to save the  dna sequence, isMutant state and matrix of characters for the DNA
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 4/07/2022
 * 
 */
public class MutantDetector extends Dna {

	public MutantDetector(Boolean mutant) {
		super(mutant);
	}
	private char[][] matrixDNA;
	
	public char[][] getMatrixDNA() {
		return matrixDNA;
	}
	public void setMatrixDNA(char[][] matrixDNA) {
		this.matrixDNA = matrixDNA;
	}

}
