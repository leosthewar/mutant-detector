package com.meli.challenge.mutant.detector.routes;

import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.meli.challenge.mutant.detector.configuration.ConfigurationRoute;
import com.meli.challenge.mutant.detector.model.RequestDTO;

/**
 * 
 * RestConsumerRoute </br>
 * Route to create a API Rest to detect mutant DNA 
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 *
 */
@Component
public class RestConsumerRoute extends ConfigurationRoute {

	
	@Override
	public void configure() throws Exception {
		

		onException(UnrecognizedPropertyException.class).handled(true)
			.to(ROUTE_EXCEPTION);

		onException(JsonMappingException.class).handled(true)
			.to(ROUTE_EXCEPTION);
		
		onException(Exception.class).handled(true)
			.to(ROUTE_EXCEPTION);
		
        restConfiguration()
        	.apiContextPath("/api-docs")
            .component("servlet")
            .bindingMode(RestBindingMode.auto);

        rest()
    		.consumes(MediaType.APPLICATION_JSON_VALUE)
    		.produces(MediaType.APPLICATION_JSON_VALUE)
    		.post("/mutant/")
    		.type(RequestDTO.class)
            .to("direct:mutant-detector-route");
        

    }
}
