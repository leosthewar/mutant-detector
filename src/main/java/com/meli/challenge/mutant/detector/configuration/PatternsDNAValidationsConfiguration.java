package com.meli.challenge.mutant.detector.configuration;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * PatternsDNAValidationsConfiguration </br>
 * 
 * Class to config patterns to validate isMutant and valid nitrogen base 
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
@Configuration
public class PatternsDNAValidationsConfiguration {

	
	@Value("${mutant.validator.regexValidBase}")
	private String regexValidBase;
	
	
	@Value( "${mutant.validator.regexIsMutant}" )
	private String regexIsMutant;
	
	
	@Bean("patternValidaBase")
	public Pattern getPatternValidBase() {
		return  Pattern.compile(regexValidBase, Pattern.CASE_INSENSITIVE);
	}
	
	@Bean("patternIsMutant")
	public Pattern getPatternIsMutant() {
		return  Pattern.compile(regexIsMutant, Pattern.CASE_INSENSITIVE);
	}
}
