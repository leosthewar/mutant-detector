package com.meli.challenge.mutant.detector.transformation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.meli.challenge.mutant.detector.configuration.PatternsDNAValidationsConfiguration;
import com.meli.challenge.mutant.detector.exceptions.DNAStructureException;
import com.meli.challenge.mutant.detector.model.RequestDTO;

/**
 * 
 * ValidateMutantComponent </br>
 * Class to validae DNA Structure 
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 *
 */
@Component
public class ValidateDNAStructureComponent {
	
	

	private PatternsDNAValidationsConfiguration patterns;
	
	
	public ValidateDNAStructureComponent(PatternsDNAValidationsConfiguration patterns) {
		this.patterns = patterns;
	}


	/**
	 * Method to valide DNA Structure
	 * @param requestDTO
	 * @throws DNAStructureException
	 */
	public void validate(RequestDTO requestDTO) throws DNAStructureException {
		
		List<String> dna= requestDTO.getDna();
		// Validate minimun length of dna array  
		if(dna.size()<4 ) {
			throw new DNAStructureException("The DNA sequence minimum length is 4");
		}else {
			for (String baseDNA : dna) {
				//Validate Nitrogen base, The only valid characters are A,T,C,G
				if(!patterns.getPatternValidBase().matcher(baseDNA).matches()) {
					throw new DNAStructureException(
							String.format("Nitrogen base:  %s invalid. The only valid characters are A,T,C,G ",baseDNA));
							
				}
				// Validate size of nitrogen base, the matrix is NxN, so the length of nitrogen base must be equals to DNA sequence length
				if(baseDNA.length() !=  dna.size() ) {
					throw new DNAStructureException(String.format("Nitrogen base:  %s invalid. The nitrongen base length must be equal to DNA Sequence length",baseDNA));
				}
			}
		}
	}
}
