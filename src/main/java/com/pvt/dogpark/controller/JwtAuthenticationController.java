package com.pvt.dogpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pvt.dogpark.config.JwtTokenUtil;
import com.pvt.dogpark.dto.UserDTO;
import com.pvt.dogpark.payload.request.JwtRequest;
import com.pvt.dogpark.payload.response.JwtResponse;
import com.pvt.dogpark.service.JwtUserDetailsService;

import lombok.val;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	/**
	 * Authentication manager
	 *
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * @varible JwtUserDetailsService
	 * The reason why userDetailService instance is here
	 * is becuase to be able to ACCESS that user item
	 * and CONFIRM
	 */
	@Autowired
	private JwtUserDetailsService userDetailsService;


	/**
	 *
	 * @param authenticationRequest
	 * takes in request, that is made in the post
	 *
	 * @return The responseEntity(The acutal object essentially)
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		val token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	/**
	 * Gues what it does? xD
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	/**
	 * Authenticates
	 * @param username
	 * @param password
	 * which then sends it to the authentication manager.
	 * @throws Exception
	 *
	 *
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}