//package com.pvt.dogpark;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.pvt.dogpark.controller.DogController;
//import com.pvt.dogpark.controller.DogParkController;
//import com.pvt.dogpark.controller.JwtAuthenticationController;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ControllerTest {
//
//	@Autowired
//	DogController dogController;
//	
//	@Autowired
//	DogParkController dogparkController;
//	
//	@Autowired
//	JwtAuthenticationController authenticationController;
//	
//	@Test
//	public void contextLoads() {
//		assertThat(dogController).isNotNull();
//		
//		assertThat(dogparkController).isNotNull();
//
//		assertThat(authenticationController).isNotNull();
//	}
//}
