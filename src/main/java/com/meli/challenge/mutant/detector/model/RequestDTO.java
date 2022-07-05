package com.meli.challenge.mutant.detector.model;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * RequestDTO </br>
 * Class to save DNA sequence for de API Request 
 *
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
public class RequestDTO {

	@Valid
    @JsonProperty(value = "dna", required = true)
	private List<String> dna;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}
	
}
