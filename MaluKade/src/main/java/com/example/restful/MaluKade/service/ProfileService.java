package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.Profile;
import com.example.restful.MaluKade.repository.ProfileRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	/**
	 * @param profile
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> addNewProfile(Profile profile){
		try {
			Profile saved = profileRepository.save(profile);
			
			if(saved != null) {
				Respones respones = new Respones(new Date(),"201","Successfully Saved.", saved.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.CREATED);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param long id
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> getProfileById(long id){
		try {
			Profile result = profileRepository.findById(id).get();
			return new ResponseEntity<Object>(result,HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Profile not found on "+id);
			return new ResponseEntity<Object>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @return List<Profile>
	 */
	public List<Profile> getAllProfile(){
		return profileRepository.findAll();
	}
	
	/**
	 * @param profile
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> updateProfile(Profile profile){
		try {
			Profile result = profileRepository.findById(profile.getId()).get();
			
			result.setFirst_Name(profile.getFirst_Name());
			result.setLast_Name(profile.getLast_Name());
			result.setMobile(profile.getMobile());
			result.setEmail(profile.getEmail());
			result.setPassword(profile.getPassword());
			
			final Profile updated = profileRepository.saveAndFlush(result);
			
			if(updated != null) {
				Respones respones = new Respones(new Date(),"200","Successfully Updated.",updated.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			} else {
				return null;
			}
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Profile not found on "+profile.getId());
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @param long id
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> deleteProfile(long id){
		try {
			Profile result = profileRepository.findById(id).get();
			
			profileRepository.deleteById(result.getId());
			
			Respones respones = new Respones(new Date(),"200","Successfully Deleted.",result.toString());
			return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Profile not found on "+id);
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @param Boolean status
	 * @param Long id
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> manageActiveProfile(Boolean status,long id){
		try {
			int updated = profileRepository.manageActiveProfile(status, id);
			
			if(updated == 1) {
				Respones respones = new Respones(new Date(),"200","Successfully Updated.","Result : "+Integer.toString(updated));
				return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			} else {
				Respones respones = new Respones(new Date(),"500","Error","Result : "+Integer.toString(updated));
				return new ResponseEntity<Respones>(respones,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Profile not found on "+id);
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @return List<Profile>
	 */
	public List<Profile> getAllActiveProfile(){
		try {
			return profileRepository.showAllActiveProfile();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}























