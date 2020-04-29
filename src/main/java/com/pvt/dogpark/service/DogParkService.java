package com.pvt.dogpark.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvt.dogpark.dao.DogParkDAO;
import com.pvt.dogpark.dto.DogParkDTO;
import com.pvt.dogpark.repository.DogParkRepository;

@Service
public class DogParkService {

	@Autowired
	private DogParkRepository repository;

	public Optional<List<DogParkDTO>> findAll() {
		var result = repository.findAll();
		return buildOptional(result);
	}

	public Optional<DogParkDAO> findById(Long id) {
		return repository.findById(id);
	}

	public Optional<List<DogParkDTO>> findByName(String name) {
		var result = repository.findByName(name);
		return buildOptional(result);
	}

	public Optional<List<DogParkDTO>> findByDistance(Double latitude, Double longitude, Double distance) {
		var result = repository.findByPositionWithinDistance(latitude, longitude, distance);
		return buildOptional(result);
	}

	private Optional<List<DogParkDTO>> buildOptional(List<DogParkDAO> list) {
		if (list.isEmpty()) {
			return Optional.empty();
		}
		var result = list.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
		return Optional.of(result);
	}

	private DogParkDTO convertToDTO(DogParkDAO dogpark) {
		return new DogParkDTO(dogpark.getLatitude(),
				dogpark.getLongitude(),
				dogpark.getName(),
				dogpark.getDescription());
	}

}
