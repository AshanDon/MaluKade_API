package com.example.restful.MaluKade.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.restful.MaluKade.model.Profile;
import com.example.restful.MaluKade.repository.ProfileRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		try {
			Profile result = profileRepository.getProfileByUserName(userName);
			
			if(result != null) {
				return new User(result.getUserName(),result.getPassword(),new ArrayList<>());
			} else {
				return null;
			}
		} catch (UsernameNotFoundException ex) {
			throw new UsernameNotFoundException(userName+" Not found.");
		}
	}
}
