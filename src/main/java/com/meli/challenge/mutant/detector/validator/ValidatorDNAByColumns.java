package com.meli.challenge.mutant.detector.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.meli.challenge.mutant.detector.configuration.PatternsDNAValidationsConfiguration;
import com.meli.challenge.mutant.detector.model.MutantDetectorDTO;

/**
 * 
 * ValidatorDNAByColumns </br>
 * Class to validate a DNA Sequence by columns.
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 *
 */
@Component
public class ValidatorDNAByColumns extends ValidatorDNA {

	private static final Logger logger = LoggerFactory.getLogger(ValidatorDNAByColumns.class);

	private MatrixDNAGenerator matrixDNAGenerator;

	/**
	 * 
	 * @param patterns
	 * @param matrixDNAGenerator
	 */
	public ValidatorDNAByColumns(PatternsDNAValidationsConfiguration patterns, MatrixDNAGenerator matrixDNAGenerator) {
		this.patternsDNAValidations = patterns;
		this.matrixDNAGenerator = matrixDNAGenerator;
	}

	/**
	 * Method to validate DNA by columns.This method iterate the DNA matrix column
	 * by column and evaluate the IsMutant Pattern for each column. If one column
	 * matches, stop the iteration and update dto state Mutant. Otherwise if no column matches, invoke the next
	 * validation (if exists)
	 * 
	 * @param MutantDetectorDTO mutantDetectorDTO
	 */
	public void validate(MutantDetectorDTO mutantDetectorDTO) {
		char[][] matrixDNA = matrixDNAGenerator.generateMatrixDNA(mutantDetectorDTO.getDna());
		mutantDetectorDTO.setMatrixDNA(matrixDNA);
		boolean isMutant = false;
		for (int col = 0; col < matrixDNA.length && !isMutant; col++) {
			StringBuilder column = new StringBuilder();
			for (int row = 0; row < matrixDNA[col].length; row++) {
				column.append(matrixDNA[row][col]);
			}
			isMutant = patternsDNAValidations.getPatternIsMutant().matcher(column).matches();
		}
		mutantDetectorDTO.setMutant(isMutant);
		logger.debug("validation Is Mutant by columns:{}", isMutant);
		if (!isMutant && this.nextValidation != null) {
			this.nextValidation.validate(mutantDetectorDTO);
		}
	}
}
