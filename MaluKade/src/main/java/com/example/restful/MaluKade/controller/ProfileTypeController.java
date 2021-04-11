package com.example.restful.MaluKade.controller;

import java.util.List;

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

import com.example.restful.MaluKade.model.ProfileType;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.ProfileTypeService;

@RestController
@RequestMapping("/malukade/v1")
public class ProfileTypeController {

	@Autowired
	private ProfileTypeService profileTypeService;
	
	@PostMapping("/profileType")
	public ResponseEntity<Respones> addNewProfileType(@RequestBody ProfileType profileType){
		System.out.println(profileType);
		return profileTypeService.addnewType(profileType);
	}
	
	@GetMapping("/profileType/{id}")
	public ResponseEntity<Object> getJobTypeById(@PathVariable(name = "id") long id){
		return profileTypeService.getProfileTypeById(id);
	}
	
	@GetMapping("/profileType/all")
	public List<ProfileType> getAllJobType(){
		return profileTypeService.getAllProfileType();
	}
	
	@PutMapping("/profileType")
	public ResponseEntity<Respones> updateJobType(@RequestBody ProfileType jobType){
		return profileTypeService.updateProfileType(jobType);
	}
	
	@DeleteMapping("/profileType/{id}")
	public ResponseEntity<Respones> deleteJobType(@PathVariable(name = "id") long id){
		return profileTypeService.deleteProfileType(id);
	}
}







