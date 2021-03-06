package com.meli.challenge.mutant.detector.domain.model;

/**
 * 
 * ResponseError </br>
 * Class to generate a Response error for the API
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
public class ResponseError  {

	private String error;

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	
}
