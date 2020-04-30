package com.pvt.dogpark.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvt.dogpark.dao.WasteBinDAO;
import com.pvt.dogpark.dto.WasteBinDTO;
import com.pvt.dogpark.repository.WasteBinRepository;

@Service
public class WasteBinService {

	@Autowired
	private WasteBinRepository repository;

	public Long count() {
		return repository.count();
	}
	
	public Optional<List<WasteBinDTO>> findAll() {
		var result = repository.findAll();
		return buildOptional(result);
	}

	public Optional<WasteBinDAO> findById(Long id) {
		return repository.findById(id);
	}

	public Optional<List<WasteBinDTO>> findByDistance(Double latitude, Double longitude, Double distance) {
		var result = repository.findByPositionWithinDistance(latitude, longitude, distance);
		return buildOptional(result);
	}

	private Optional<List<WasteBinDTO>> buildOptional(List<WasteBinDAO> list) {
		if (list.isEmpty()) {
			return Optional.empty();
		}
		var result = list.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
		return Optional.of(result);
	}

	private WasteBinDTO convertToDTO(WasteBinDAO wastebin) {
		return new WasteBinDTO(wastebin.getLatitude(),
				wastebin.getLongitude());
	}

}
