package com.pvt.dogpark;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
	
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void dogparkCountShouldReturnCorrectValue() {
	assertThat(restTemplate.getForObject("/dogpark/count", Long.class)).isEqualTo(159);
	
		//	assertThat(restTemplate.getForObject("http://localhost:" + port + "/dogpark/count", Long.class))
	//			.isEqualByComparingTo((long) 159);

	}

//	@Test
//	public void helloShouldReturnHelloWorldMessage() throws Exception {
//		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello", String.class))
//				.contains("Hello World");
//	}
}