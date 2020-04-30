package com.pvt.dogpark.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pvt.dogpark.dao.UserDAO;
import com.pvt.dogpark.dto.DogDTO;
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

	public Optional<UserDTO> findByName(String username) {
		val daoUser = repository.findByUsername(username);
		if (daoUser == null) {
			return Optional.empty();
		}
		var dtoUser = new UserDTO();
		dtoUser.setUsername(daoUser.getUsername());
		val daoDogs = daoUser.getDogs();
		var dtoDogs = daoDogs.stream()
				.map(dogService::convertToDTO)
				.collect(Collectors.toList());
		// dtoDogs = new ArrayList<>();
		// dtoDogs.add(new DogDTO("Muffin", "Mattias"));
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