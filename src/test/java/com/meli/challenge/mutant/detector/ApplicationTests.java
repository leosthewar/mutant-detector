package com.meli.challenge.mutant.detector;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.meli.challenge.mutant.detector.domain.model.Request;

/**
 * 
 * ApplicationTests </br>
 * Class with integration tests 
 *
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests{


	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	String randomServerPort;
	
	private  URI uri;


	@Before
	public void init() throws URISyntaxException {
		String baseUrl = String.join("", "http://localhost:", randomServerPort,"/mutant/");
		this.uri = new URI(baseUrl);
	}
	

	

	/**
	 * Test to validate No Mutant DNA
	 * @throws URISyntaxException
	 */
	@Test
	public void testNoMutant()  {

		Request request = new Request();
		List<String> dna = new ArrayList<String>();
		String[] array = { "ATCCG", "CAGCG", "TTCTG", "ACAAT", "TCCAT" };
		Collections.addAll(dna, array);
		request.setDna(dna);

		HttpEntity<Request> requestHttp = new HttpEntity<>(request, null);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, requestHttp, String.class);

		// Verify request succeed
		Assert.assertEquals(403, result.getStatusCodeValue());
	}

	
	/**
	 * Test to validate Mutant DNA when pattern matches by rows
	 * @throws URISyntaxException
	 */
	@Test
	public void testMutantValidateByRows() throws URISyntaxException {

		Request request = new Request();
		List<String> dna = new ArrayList<String>();
		String[] array = { "CCCCG", "CAGCG", "TTCTG", "ACAAT", "CCCAT" };
		Collections.addAll(dna, array);
		request.setDna(dna);

		HttpEntity<Request> requestHttp = new HttpEntity<>(request, null);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, requestHttp, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	/**
	 * Test to validate Mutant DNA when pattern matches by columns
	 * @throws URISyntaxException
	 */
	@Test
	public void testMutantValidateByColumns() {

		Request request = new Request();
		List<String> dna = new ArrayList<String>();
		String[] array = { "CTGAG", "CAGCG", "CTCTG", "CCAAT", "CCCAT" };
		Collections.addAll(dna, array);
		request.setDna(dna);

		HttpEntity<Request> requestHttp = new HttpEntity<>(request, null);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, requestHttp, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	/**
	 * Test to validate Mutant DNA when pattern matches by diagonals
	 */
	@Test
	public void testMutantValidateByDiagonals() {

		Request request = new Request();
		List<String> dna = new ArrayList<String>();
		String[] array = { "ATCCG", "CAGCG", "TTCTG", "ACAAT", "CCCAT" };
		Collections.addAll(dna, array);
		request.setDna(dna);

		HttpEntity<Request> requestHttp = new HttpEntity<>(request, null);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, requestHttp, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	/**
	 * Test to validate a DNA with a nitrogen base length no equals to DNA sequence length
	 */
	@Test
	public void testMutantDNABadStructure() {

		Request request = new Request();
		List<String> dna = new ArrayList<String>();
		String[] array = { "ATCCG", "CAGCG", "TTCTG", "ACAAT", "CCCATT" };
		Collections.addAll(dna, array);
		request.setDna(dna);

		HttpEntity<Request> requestHttp = new HttpEntity<>(request, null);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, requestHttp, String.class);

		// Verify request succeed
		Assert.assertEquals(403, result.getStatusCodeValue());
	}
	
	/**
	 * Test to validate a DNA with a nitrogen base length with invalid characters
	 */
	@Test
	public void testMutantDNABadStructure2() {

		Request request = new Request();
		List<String> dna = new ArrayList<String>();
		String[] array = { "ATCCG", "CAGCG", "TTCTG", "ACAAT", "CCCAB" };
		Collections.addAll(dna, array);
		request.setDna(dna);

		HttpEntity<Request> requestHttp = new HttpEntity<>(request, null);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, requestHttp, String.class);

		// Verify request succeed
		Assert.assertEquals(403, result.getStatusCodeValue());
	}
	
	/**
	 * Test to validate a DNA sequence with invalid length
	 */
	@Test
	public void testMutantDNABadStructure3() {

		Request request = new Request();
		List<String> dna = new ArrayList<String>();
		String[] array = { "ATC", "CAG", "TTC"};
		Collections.addAll(dna, array);
		request.setDna(dna);

		HttpEntity<Request> requestHttp = new HttpEntity<>(request, null);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, requestHttp, String.class);

		// Verify request succeed
		Assert.assertEquals(403, result.getStatusCodeValue());
	}
}
