package com.meli.challenge.mutant.detector.exceptions;

/**
 * DNAStructureException </br>
 * Exception to throw when a DNA Sequence not have a valid structure
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 * 
 */
public class DNAStructureException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4814004394749118790L;

	public DNAStructureException(String errorMessage) {
		super(errorMessage);
	}
}
