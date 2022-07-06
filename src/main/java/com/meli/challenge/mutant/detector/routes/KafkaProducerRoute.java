package com.meli.challenge.mutant.detector.routes;

import java.net.ConnectException;
import java.net.UnknownHostException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.meli.challenge.mutant.detector.configuration.ConfigurationRoute;

/**
 * 
 * KafkaProducerRoute </br>
 * Route to save send the message to kafka
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 *
 */
@Component
public class KafkaProducerRoute extends ConfigurationRoute {
	

	@Override
	public void configure() throws Exception {

		onException(UnknownHostException.class).handled(true)
			.maximumRedeliveries(3)
			.redeliveryDelay(2000)
			.to(ROUTE_EXCEPTION);

		onException(ConnectException.class).handled(true)
			.maximumRedeliveries(3)
			.redeliveryDelay(2000)
			.to(ROUTE_EXCEPTION);

		onException(Exception.class).handled(true)
			.to(ROUTE_EXCEPTION);

		
		from("direct:producer-kafka-route").routeId("producer-kafka-route")
			.log(LoggingLevel.DEBUG," Send to db ${body}")
			.marshal().json(JsonLibrary.Jackson)
			.to("kafka:dna")
			.log(LoggingLevel.INFO,"DNA saved successfully :${body} ")
			.setBody(simple(""))
			.end();
		

	}

}
