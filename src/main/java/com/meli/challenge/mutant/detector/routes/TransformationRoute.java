package com.meli.challenge.mutant.detector.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.springframework.stereotype.Component;

import com.meli.challenge.mutant.detector.configuration.ConfigurationRoute;
import com.meli.challenge.mutant.detector.exceptions.DNAStructureException;

/**
 * 
 * TransformationRoute </br>
 * Route to  make transformations, in this to case  to validate request and validate DNA Mutant
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 *
 */
@Component
public class TransformationRoute extends ConfigurationRoute {

	@Override
	public void configure() throws Exception {
		
		onException(DNAStructureException.class).handled(true)
			.to(ROUTE_EXCEPTION);
		
		onException(Exception.class).handled(true)
			.to(ROUTE_EXCEPTION);

		/**
		 * Route to validate request, mutant validation and save the results 
		 */
		from("direct:mutant-detector-route").routeId("mutant-detector-route")
			.log(LoggingLevel.DEBUG,"Start DNA Validation: ${body.dna}")
			.bean("validateDNAStructureComponent","validate")
			.bean("transformationComponent","mutantValidation")
			.to("direct:producer-db-route")
			.end();
		
		
		/**
		 * Route to handle exceptions and generate response error 
		 */
		from(ROUTE_EXCEPTION).routeId("ruta_exception")
			.setProperty("exceptionMessage",simple("${exception.message}"))
			.log(LoggingLevel.DEBUG, "ExceptionClass: ${exchangeProperty.CamelExceptionCaught.class}")
			.log(LoggingLevel.DEBUG, "StackTrace: ${exception.stacktrace}")
			.bean("transformationComponent","generareResponseError")
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant("403"))
			.end();

	}

}
