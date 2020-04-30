package com.pvt.dogpark.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Det som står i JSON Body ex { "username":"namn", "password":"hemligt" }
public class UserDTO {

	private String username;
	private String password;
	private List<DogDTO> dogs;
	
}