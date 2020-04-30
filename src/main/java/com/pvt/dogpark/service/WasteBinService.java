package com.pvt.dogpark.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvt.dogpark.dao.WasteBinDAO;
import com.pvt.dogpark.dto.WasteBinDTO;
import com.pvt.dogpark.repository.WasteBinRepository;

import lombok.val;

@Service
public class WasteBinService {

	@Autowired
	private WasteBinRepository wasteBinRepository;

	@Autowired
	private ModelMapper modelMapper;

	
//	public WasteBinService() {
//		modelMapper.createTypeMap(WasteBinDAO.class, WasteBinDTO.class);
//		modelMapper.createTypeMap(WasteBinDTO.class, WasteBinDAO.class);
//	}
	
	public Long count() {
		return wasteBinRepository.count();
	}

	public Optional<List<WasteBinDTO>> findAll() {
		var result = wasteBinRepository.findAll();
		return buildOptional(result);
	}

	public Optional<WasteBinDAO> findById(Long id) {
		return wasteBinRepository.findById(id);
	}

	public Optional<List<WasteBinDTO>> findByDistance(	Double latitude,
														Double longitude,
														Double distance) {
		var result = wasteBinRepository.findByPositionWithinDistance(latitude, longitude, distance);
		return buildOptional(result);
	}

	private Optional<List<WasteBinDTO>> buildOptional(List<WasteBinDAO> list) {
		if (list.isEmpty()) {
			return Optional.empty();
		}
		val result = list.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
		return Optional.of(result);
	}

	private WasteBinDTO convertToDto(WasteBinDAO wastebin) {
		return modelMapper.map(wastebin, WasteBinDTO.class);
	}
	
}
