package com.sapient.demo.creditcard;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CreditcardAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditCardControllerIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testAddCreditCard_withValidDeatils() throws JSONException {

		String card = "{\"name\": \"Sravani\",\"cardNumber\": \"79927398713\", \"limit\" : \"500\"}";
		ResponseEntity<String> response = makePOST(card);
		String expected = "{\"id\":1,\"name\":\"Sravani\",\"cardNumber\":\"79927398713\",\"balance\":0.0,\"limit\":500.0}";
		Assertions.assertEquals(expected, response.getBody());

	}

	@Test
	public void testAddCreditCard_withMaxExceedCardNumber() throws JSONException {

		String card = "{\"name\": \"Sravani\",\"cardNumber\": \"79927398713765269583\", \"limit\" : \"500\"}";
		ResponseEntity<String> response = makePOST(card);
		String expected = "[\"Invalid creditcard\"]";
		Assertions.assertEquals(expected, response.getBody());
	}

	@Test
	public void testAddCreditCard_withInvalidCardNumber() throws JSONException {

		String card = "{\"name\": \"Sravani\",\"cardNumber\": \"123\", \"limit\" : \"500\"}";
		ResponseEntity<String> response = makePOST(card);
		String expected = "[\"Invalid creditcard\"]";
		Assertions.assertEquals(expected, response.getBody());
	}

	@Test
	public void testAddCreditCard_EmptyUserName() throws JSONException {

		String card = "{\"name\": \"\",\"cardNumber\": \"79927398713\", \"limit\" : \"500\"}";
		ResponseEntity<String> response = makePOST(card);
		String expected = "[\"Valid 'name' should be provided\"]";
		Assertions.assertEquals(expected, response.getBody());
	}

	@Test
	public void testAddCreditCard_InvalidLimit() throws JSONException {

		String card = "{\"name\": \"Sravani\",\"cardNumber\": \"79927398713\", \"limit\" : \"\"}";
		ResponseEntity<String> response = makePOST(card);
		String expected = "[\"Valid 'creditLimit' should be provided\"]";
		Assertions.assertEquals(expected, response.getBody());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	private ResponseEntity<String> makePOST(String card) {
		List<MediaType> mediaType = new ArrayList<MediaType>();
		mediaType.add(MediaType.APPLICATION_JSON);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> entity = new HttpEntity<>(card, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/add"), HttpMethod.POST, entity,
				String.class);
		return response;

	}
}
