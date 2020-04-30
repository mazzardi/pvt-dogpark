package com.pvt.dogpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvt.dogpark.service.WasteBinService;

@RestController
@RequestMapping(path = "/wastebin")
public class WasteBinController {

	@Autowired
	private WasteBinService service;

	@GetMapping(path = "/count")
	public ResponseEntity<?> count(){
		return ResponseEntity.ok(service.count());
	}
	
	@GetMapping(path = "/find", params = "all")
	public ResponseEntity<?> findAll() {
		var result = service.findAll();
		return ResponseEntity.of(result);
	}

	@GetMapping(path = "/find", params = "id")
	public ResponseEntity<?> findById(@RequestParam Long id) {
		var result = service.findById(id);
		return ResponseEntity.of(result);
	}

	@GetMapping(path = "/find", params = { "latitude", "longitude", "distance" })
	public ResponseEntity<?> findByPosition(@RequestParam Double latitude,
											@RequestParam Double longitude,
											@RequestParam Double distance) {
		var result = service.findByDistance(latitude, longitude, distance);
		return ResponseEntity.of(result);
	}

}
