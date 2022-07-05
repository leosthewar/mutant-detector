/**
 */
package com.meli.challenge.mutant.detector.validator;

import com.meli.challenge.mutant.detector.configuration.PatternsDNAValidationsConfiguration;
import com.meli.challenge.mutant.detector.model.MutantDetectorDTO;

/**
 * ValidatorDNA </br>
 * Abstract Class to define the structure of validators of DNA
 *  
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 4/07/2022
 * 
 */
public abstract class  ValidatorDNA {
	
	/**
	 * Component with the patterns to validate isMutant and valid nitrogen base
	 */
	protected PatternsDNAValidationsConfiguration patternsDNAValidations;
	
	/**
	 * Define the next validation class
	 */
	protected ValidatorDNA nextValidation;
	
	public void setNextValidation(ValidatorDNA nextValidation) {
		this.nextValidation = nextValidation;
	}

	/**
	 * Validate DNA 
	 * @param mutantDetectorDTO
	 */
	public abstract void validate(MutantDetectorDTO mutantDetectorDTO);
	
}
