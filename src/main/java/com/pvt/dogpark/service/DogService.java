package com.pvt.dogpark.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pvt.dogpark.dao.DogDAO;
import com.pvt.dogpark.dto.DogDTO;
import com.pvt.dogpark.repository.DogRepository;

import lombok.val;

@Service
public class DogService {

	@Autowired
	private DogRepository repository;

	@Autowired
	private JwtUserDetailsService userService;

	@Autowired
	private ModelMapper modelMapper;

	public Optional<List<DogDTO>> findAll() {
		var result = repository.findAll();
		return buildOptional(result);
	}

	public Optional<DogDTO> findById(Long id) {
		var result = repository.findById(id);
		if (result.isPresent()) {
			return Optional.of(convertToDTO(result.get()));
		} else {
			return Optional.empty();
		}
	}

	public Optional<List<DogDTO>> findByName(String name) {
		var result = repository.findByName(name);
		return buildOptional(result);
	}

	public Optional<List<DogDTO>> findByOwner(String owner) {
		var result = repository.findByOwner(owner);
		return buildOptional(result);
	}

	public Optional<DogDTO> deleteById(Long id) {
		var result = findById(id);
		if (result.isPresent()) {
			repository.deleteById(id);
			return result;
		} else {
			return Optional.empty();
		}
	}

	public String save(DogDTO dog) {
		val owner = userService.findByName(dog.getOwner());
		if (owner.isEmpty()) {
			throw new UsernameNotFoundException("Owner not found with username: " + dog.getOwner());
		}

		repository.save(convertToDAO(dog));
		return "Dog registered";
	}

	private Optional<List<DogDTO>> buildOptional(List<DogDAO> dogs) {
		if (dogs.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(convertToDto(dogs));
	}

	private DogDAO convertToDAO(DogDTO dog) {
		return modelMapper.map(dog, DogDAO.class);
	}

	public List<DogDAO> convertToDAO(List<DogDTO> dogs) {
		return dogs.stream()
				.map(this::convertToDAO)
				.collect(Collectors.toList());
	}

	public DogDTO convertToDTO(DogDAO dog) {
		return modelMapper.map(dog, DogDTO.class);
	}

	public List<DogDTO> convertToDto(List<DogDAO> dogs) {
		return dogs.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
}
