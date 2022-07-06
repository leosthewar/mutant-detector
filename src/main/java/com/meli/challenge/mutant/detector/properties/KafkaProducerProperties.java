package com.meli.challenge.mutant.detector.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * DBProducerProperties </br>
 * Class with the properties to mongo db 
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
@Configuration
@ConfigurationProperties(prefix = "productor.kafka")
public class KafkaProducerProperties {

	private String camelEndpoint;

	/**
	 * @return the camelEndpoint
	 */
	public String getCamelEndpoint() {
		return camelEndpoint;
	}

	/**
	 * @param camelEndpoint the camelEndpoint to set
	 */
	public void setCamelEndpoint(String camelEndpoint) {
		this.camelEndpoint = camelEndpoint;
	}
	
	
}
