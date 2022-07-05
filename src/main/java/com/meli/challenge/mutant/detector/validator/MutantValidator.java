package com.meli.challenge.mutant.detector.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.meli.challenge.mutant.detector.model.MutantDetectorDTO;

/**
 * 
 * MutantValidator </br>
 * Component to validate DNA Sequence. This component uses the Chain of Responsibility Design Pattern
 * 
 * TODO Extract this package to module
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 * 
 */
@Component
public class MutantValidator {
	
	private ValidatorDNA validatorDNA;
	
	public MutantValidator(ValidatorDNAByColumns validatorDNAByColumns, ValidatorDNAByRows validatorDNAByRows,
			ValidatorDNAByDiagonals validatorDNAByDiagonals) {
		validatorDNAByRows.setNextValidation(validatorDNAByColumns);
		validatorDNAByColumns.setNextValidation(validatorDNAByDiagonals);
		this.validatorDNA=validatorDNAByRows;
		
	}

	/**
	 * Method to validate a List with the DNA Sequence. 
	 * This method performs validation with the following chain of responsibility </br>
	 * 1. Validate by rows </br>
	 * 2. Validate by columns </br>
	 * 3. Validate by diagonals  </br>
	 * @param dna
	 * @return
	 */
	public boolean isMutant(List<String> dna) {
		
		MutantDetectorDTO mutantDetectorDTO = new MutantDetectorDTO(false);
		mutantDetectorDTO.setDna(dna);
		validatorDNA.validate(mutantDetectorDTO);

		return mutantDetectorDTO.isMutant();
	}
}
