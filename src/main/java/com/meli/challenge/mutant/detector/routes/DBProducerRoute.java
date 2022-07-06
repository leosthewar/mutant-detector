package com.meli.challenge.mutant.detector.routes;

import java.net.ConnectException;
import java.net.UnknownHostException;

import org.apache.camel.LoggingLevel;
import org.springframework.stereotype.Component;

import com.meli.challenge.mutant.detector.configuration.ConfigurationRoute;
import com.meli.challenge.mutant.detector.properties.DBProducerProperties;

/**
 * 
 * TransformationRoute </br>
 * Route to save the message in mongo db
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 *
 */
@Component
public class DBProducerRoute extends ConfigurationRoute {
	
	private DBProducerProperties dbProperties;
	public DBProducerRoute(DBProducerProperties dbProperties) {
		this.dbProperties = dbProperties;
	}

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

		
		from("direct:producer-db-route").routeId("producer-db-route")
			.log(LoggingLevel.DEBUG," Send to db ${body}")
			.to(dbProperties.getRoute()+"?database="+dbProperties.getDatabase()+"&collection="+dbProperties.getCollection()+"&operation=insert")
			.setBody(simple(""))
			.log(LoggingLevel.INFO,"DNA saved successfully :${body} ")
			.end();
		

	}

}
