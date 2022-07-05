package com.meli.challenge.mutant.detector.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.meli.challenge.mutant.detector.properties.DBProducerProperties;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


/**
 * 
 * MongoConfig </br>
 * Class to create mongo client connection
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 1/07/2022
 *
 */
@Configuration
public class MongoConfig {

	private DBProducerProperties dbProperties;
	
	public MongoConfig(DBProducerProperties dbProperties) {
		this.dbProperties=dbProperties;
	}
	
	@Bean("mongoBean")
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://"+dbProperties.getHost()+"/?retryWrites=true&w=majority");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .credential(MongoCredential.createScramSha1Credential(dbProperties.getUser(), "source", dbProperties.getPassword().toCharArray()))
            .build();
        return MongoClients.create(mongoClientSettings);
    }
 
}
