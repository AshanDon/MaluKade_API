package com.example.restful.MaluKade.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restful.MaluKade.model.Profile;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.ProfileService;

@RestController
@RequestMapping("/malukade/v1")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@PostMapping("/profile")
	public ResponseEntity<Respones> saveProfile(@RequestBody Profile profile){
		return profileService.addNewProfile(profile);
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<Object> findProfileById(@PathVariable(name = "id") long id){
		return profileService.getProfileById(id);
	}
	
	@GetMapping("/profile/all")
	public List<Profile> findAllProfile(){
		return profileService.getAllProfile();
	}
	
	@PutMapping("/profile")
	public ResponseEntity<Respones> updateProfileInfo(@RequestBody Profile profile){
		return profileService.updateProfile(profile);
	}
	
	@DeleteMapping("/profile/{id}")
	public ResponseEntity<Respones> deleteProfileInfo(@PathVariable(name = "id") long id){
		return profileService.deleteProfile(id);
	}
	
	@PutMapping("/profile/active")
	public ResponseEntity<Respones> manageActiveProfile(@PathParam(value = "status") boolean status,@PathParam(value = "id") long id){
		return profileService.manageActiveProfile(status, id);
	}
	
	@GetMapping("/profile/active/all")
	public List<Profile> findAllActiveProfile(){
		return profileService.getAllActiveProfile();
	}
	
	@GetMapping("/profile/register/email")
	public Map<String, Boolean> checkAvailableEmail(@PathParam(value = "email") String email){
		return profileService.checkEmail(email);
	}
	
	@GetMapping("/profile/register/user_name")
	public Map<String,Boolean> checkAvailableUserName(@PathParam(value = "userName") String userName){
		return profileService.checkUserName(userName);
	}
}
