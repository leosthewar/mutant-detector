package com.meli.challenge.mutant.detector.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.meli.challenge.mutant.detector.configuration.PatternsDNAValidationsConfiguration;
import com.meli.challenge.mutant.detector.domain.model.MutantDetector;

/**
 * 
 * ValidateMutantComponent </br>
 * Class to validate a DNA Sequence by rows
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 *
 */
@Component
public class ValidatorDNAByRows extends ValidatorDNA {

	private static final Logger logger = LoggerFactory.getLogger(ValidatorDNAByRows.class);

	public ValidatorDNAByRows(PatternsDNAValidationsConfiguration patterns) {
		this.patternsDNAValidations = patterns;
	}

	/**
	 * Method to validate DNA by rows.This method iterate the DNA sequence and
	 * evaluate the IsMutant Pattern for each nitrogen base. If one row matches,
	 * stop the iteration and update  state Mutant. Otherwise if no column
	 * matches, invoke the next validation (if exists)
	 * 
	 * @param MutantDetector mutantDetector
	 */
	public void validate(MutantDetector mutantDetector) {
		List<String> dna = mutantDetector.getDna();
		boolean isMutant = false;
		int i = 0;
		while (!isMutant && i < dna.size()) {
			String row = dna.get(i);
			isMutant = patternsDNAValidations.getPatternIsMutant().matcher(row).matches();
			i++;
		}
		mutantDetector.setMutant(isMutant);
		logger.debug("validation Is Mutant by rows:{}", isMutant);
		if (!isMutant && this.nextValidation != null) {
			this.nextValidation.validate(mutantDetector);
		}
	}
}
