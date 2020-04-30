//package com.pvt.dogpark;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.pvt.dogpark.dao.DogParkDAO;
//import com.pvt.dogpark.dto.DogParkDTO;
//
//import lombok.val;
//
//@SpringBootTest
//public class ModelMapperTest {
//
//	private ModelMapper modelMapper = new ModelMapper();
//
//	@Test
//	public void whenConvertDogParkEntityToDogParkDtoThenEquals() {
//		var dogpark = new DogParkDAO();
//		dogpark.setId(1L);
//		dogpark.setName("Alvik");
//		dogpark.setDescription("Fint");
//		dogpark.setLatitude(123.45);
//		dogpark.setLongitude(543.21);
//
//		var dogparkDto = modelMapper.map(dogpark, DogParkDTO.class);
//		assertEquals(dogparkDto.getName(), dogpark.getName());
//		assertEquals(dogpark.getDescription(), dogparkDto.getDescription());
//		assertEquals(dogpark.getLatitude(), dogparkDto.getLatitude());
//		assertEquals(dogpark.getLongitude(), dogparkDto.getLongitude());
//	}
//
//	@Test
//	public void whenConvertDogParkDtoToDogParkEntityThenEquals() {
//		var dogDto = new DogParkDTO();
//		dogDto.setName("Alvik");
//		dogDto.setDescription("Fint");
//		dogDto.setLatitude(123.45);
//		dogDto.setLongitude(543.21);
//
//		val dog = modelMapper.map(dogDto, DogParkDAO.class);
//		assertEquals(dogDto.getName(), dog.getName());
//		assertEquals(dogDto.getDescription(), dog.getDescription());
//		assertEquals(dogDto.getLatitude(), dog.getLatitude());
//		assertEquals(dogDto.getLongitude(), dog.getLongitude());
//	}
//
//}