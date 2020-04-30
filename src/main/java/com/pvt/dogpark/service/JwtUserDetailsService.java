package com.pvt.dogpark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pvt.dogpark.dao.UserDAO;
import com.pvt.dogpark.dto.UserDTO;
import com.pvt.dogpark.repository.UserRepository;

import lombok.val;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private DogService dogService;

	public Optional<List<UserDTO>> findAll() {
		List<UserDAO> daoUsers = repository.findAll();
		List<UserDTO> dtoUsers = new ArrayList<>();

		for (UserDAO daoUser : daoUsers) {
			var dtoUser = new UserDTO();
			dtoUser.setUsername(daoUser.getUsername());
			var daoDogs = daoUser.getDogs();
			var dtoDogs = dogService.convertToDto(daoDogs);
			dtoUser.setDogs(dtoDogs);
			dtoUsers.add(dtoUser);
		}
		return Optional.of(dtoUsers);
	}

	public Optional<UserDTO> findByName(String username) {
		val daoUser = repository.findByUsername(username);
		if (daoUser == null) {
			return Optional.empty();
		}
		var dtoUser = new UserDTO();
		dtoUser.setUsername(daoUser.getUsername());
		val daoDogs = daoUser.getDogs();
		var dtoDogs = dogService.convertToDto(daoDogs);
		// var dtoDogs = daoDogs.stream()
//				.map(dogService::convertToDTO)
//				.collect(Collectors.toList());
		dtoUser.setDogs(dtoDogs);
		return Optional.of(dtoUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDAO user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	public UserDAO save(UserDTO user) {
		UserDAO newUser = new UserDAO();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return repository.save(newUser);
	}

}