/**
 */
package com.meli.challenge.mutant.detector.transformation;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.meli.challenge.mutant.detector.model.RequestDTO;
import com.meli.challenge.mutant.detector.model.DnaDTO;
import com.meli.challenge.mutant.detector.model.ResponseErrorDTO;
import com.meli.challenge.mutant.detector.validator.MutantValidator;

/**
 * TransformationComponent </br>
 * Component class with the methods for transformation route
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 4/07/2022
 * 
 */
@Component
public class TransformationComponent {

	private MutantValidator mutantValidator;

	public TransformationComponent(MutantValidator mutantValidator) {
		this.mutantValidator = mutantValidator;
	}

	/**
	 * Method to make mutant validation. This method receives the body of the
	 * request and performs the validation through the mutantValidator component.
	 * 
	 * @param request
	 * @param exchange
	 */
	public void mutantValidation(RequestDTO request, Exchange exchange) {

		boolean isMutant = mutantValidator.isMutant(request.getDna());
		DnaDTO dna = new DnaDTO(isMutant);
		dna.setDna(request.getDna());
		exchange.getIn().setBody(dna);
		if (!isMutant) {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 403);
		}
	}

	/**
	 * Method generate the reponse error
	 * 
	 * @param exchange
	 */
	public void generareResponseError(Exchange exchange) {
		ResponseErrorDTO dnaResponseErrorDTO = new ResponseErrorDTO();
		dnaResponseErrorDTO.setError(exchange.getProperty("exceptionMessage", String.class));
		exchange.getIn().setBody(dnaResponseErrorDTO);
	}

}
