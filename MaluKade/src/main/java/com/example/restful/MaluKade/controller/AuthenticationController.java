package com.example.restful.MaluKade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restful.MaluKade.model.AuthenticationRequest;
import com.example.restful.MaluKade.response.AuthenticationResponse;
import com.example.restful.MaluKade.service.MyUserDetailsService;
import com.example.restful.MaluKade.util.JwtUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/malukade/v1")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/authenticate")
	@ApiOperation(value = "Create an authentication by userName and password.",
	notes = "Created an Awt token using userName and password from the profile table.",response = AuthenticationController.class)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException ex) {
			throw new Exception("Incorret user name or password",ex);
		}
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());
		
		final String jwtToken = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
}
