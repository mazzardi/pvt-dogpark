package com.pvt.dogpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvt.dogpark.service.JwtUserDetailsService;

@RestController
@RequestMapping(path = "/user")
public class UserControll {

	@Autowired
	JwtUserDetailsService userService;
	
	@GetMapping(path = "/find", params = "all")
	public ResponseEntity<?> findAll() {
		var result = userService.findAll();
		return ResponseEntity.of(result);
	}
	
	@GetMapping(path = "/find", params = "name")
	public ResponseEntity<?> findByName(@RequestParam String name) {
		var result = userService.findByName(name);
		return ResponseEntity.of(result);
	}
}
