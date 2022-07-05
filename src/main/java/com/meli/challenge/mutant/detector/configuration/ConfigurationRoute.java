/**
 */
package com.meli.challenge.mutant.detector.configuration;

import org.apache.camel.builder.RouteBuilder;

/**
 * ConfigurationRoute </br>
 * Configuration class to create routes 
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 4/07/2022
 * 
 */
public abstract class ConfigurationRoute  extends RouteBuilder{


	protected static final String ROUTE_EXCEPTION= "direct:exception-route";
	
	@Override
	public abstract void  configure() throws Exception;

}
