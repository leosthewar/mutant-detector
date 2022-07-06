package com.meli.challenge.mutant.detector.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.meli.challenge.mutant.detector.configuration.PatternsDNAValidationsConfiguration;
import com.meli.challenge.mutant.detector.domain.model.MutantDetector;

/**
 * 
 * ValidateMutantComponent </br>
 * Class to validate a DNA Sequence by diagonals.
 *
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 *
 */
@Component
public class ValidatorDNAByDiagonals extends ValidatorDNA {

	private static final Logger logger = LoggerFactory.getLogger(ValidatorDNAByDiagonals.class);

	public ValidatorDNAByDiagonals(PatternsDNAValidationsConfiguration patterns) {
		this.patternsDNAValidations = patterns;
	}

	/**
	 * Method to validate DNA by diagonals. This method iterate the DNA matrix to
	 * obtain all the diagonals.<br>
	 * First iterate rigth to left sencondly left to rigth diagonals. <br>
	 * By each diagonal evaluate the IsMutant Pattern. If one diagonal matches, stop
	 * the iteration and update  state Mutant. Otherwise if no diagonal matches,
	 * invoke the next validation ( if exists )
	 * 
	 * @param MutantDetector mutantDetector
	 */
	public void validate(MutantDetector mutantDetector) {
		char[][] matrixDNA = mutantDetector.getMatrixDNA();
		boolean isMutant = false;
		int n = matrixDNA.length;

		// rigth to left
		int j = n - 1;
		for (int i = 0; i < n && !isMutant; ++i) {
			isMutant = patternsDNAValidations.getPatternIsMutant().matcher(getReverseDiagonal(matrixDNA, i, j))
					.matches();
		}

		int i = n - 1;
		for (j = n - 2; j >= 0 && !isMutant; --j) {
			isMutant = patternsDNAValidations.getPatternIsMutant().matcher(getReverseDiagonal(matrixDNA, i, j))
					.matches();
		}

		// left to rigth

		j = 0;
		for (i = 0; i < n && !isMutant; ++i) {
			isMutant = patternsDNAValidations.getPatternIsMutant().matcher(getDiagonal(matrixDNA, i, j)).matches();
		}

		i = n - 1;
		for (j = 1; j < n && !isMutant; ++j) {
			isMutant = patternsDNAValidations.getPatternIsMutant().matcher(getDiagonal(matrixDNA, i, j)).matches();
		}
		mutantDetector.setMutant(isMutant);
		logger.debug("validation Is Mutant by diagonal:{}", isMutant);
		if (!isMutant && this.nextValidation != null) {
			this.nextValidation.validate(mutantDetector);
		}
	}

	/**
	 * Return a reverse diagonal ( rigth to left)  for i j positiion of matrix
	 * @param matrix
	 * @param i
	 * @param j
	 * @return
	 */
	private String getReverseDiagonal(char[][] matrix, int i, int j) {
		StringBuilder d = new StringBuilder().append(matrix[i][j]);
		for (int row = i - 1, column = j - 1; row >= 0 && column >= 0; --row, --column) {
			d.append(matrix[row][column]);
		}
		return d.toString();
	}

	/**
	 * Return a  diagonal ( left to rigth )  for i j positiion of matrix
	 * @param matrix
	 * @param i
	 * @param j
	 * @return
	 */
	private String getDiagonal(char[][] matrix, int i, int j) {
		StringBuilder d = new StringBuilder().append(matrix[i][j]);
		for (int row = i - 1, column = j + 1; row >= 0 && column < matrix.length; --row, ++column) {
			d.append(matrix[row][column]);
		}
		return d.toString();
	}

}
