package com.meli.challenge.mutant.detector.validator;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
 * MatrixDNAGenerator </br>
 * Class to generate Matrix of DNA Sequence
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
@Component
public class MatrixDNAGenerator {

	

	/**
	 * Generate matrix 
	 * @param dna
	 * @return
	 */
	public  char[][]  generateMatrixDNA(List<String> dna) {
		int size = dna.size();
		char[][] matrix = new char[size][size];
		for (int i = 0; i < size; i++) {
			String dnaRow = dna.get(i);
			matrix[i] = dnaRow.toCharArray();
		}
		return matrix;
	}
	
	
	
}
