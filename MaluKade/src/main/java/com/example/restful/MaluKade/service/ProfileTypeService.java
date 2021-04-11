package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.ProfileType;
import com.example.restful.MaluKade.repository.ProfileTypeRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class ProfileTypeService {
	
	@Autowired
	private ProfileTypeRepository profileTypeRepository;
	
	/**
	 * @param ProfileType profileType
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> addnewType(ProfileType profileType){
		try {
			System.out.println(profileType);
			ProfileType saved = profileTypeRepository.saveAndFlush(profileType);
			
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
	public ResponseEntity<Object> getProfileTypeById(long id){
		try{
			ProfileType result = profileTypeRepository.findById(id).get();
			return new ResponseEntity<Object>(result,HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found", "Profile type not found on "+id);
			return new ResponseEntity<Object>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @return List<ProfileType>
	 */
	public List<ProfileType> getAllProfileType(){
		try {
			return profileTypeRepository.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param ProfileType profileType
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> updateProfileType(ProfileType profileType){
		try {
			ProfileType result = profileTypeRepository.findById(profileType.getId()).get();
			
			result.setType(profileType.getType());
			
			final ProfileType updated = profileTypeRepository.saveAndFlush(result);
			
			if(updated != null) {
				Respones respones = new Respones(new Date(),"200","Successfully Updated.",updated.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			} else {
				return null;
			}
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found", "Profile type not found on "+profileType.getId());
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND); 
		}
	}
	
	/**
	 * @param long id
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> deleteProfileType(long id){
		
		try {
			ProfileType result = profileTypeRepository.findById(id).get();
			
			profileTypeRepository.deleteById(result.getId());
			
			Respones respones = new Respones(new Date(),"200","Successfully Deleted.",result.toString());
			return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found", "Profile type not found on "+id);
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND); 
		}
		
	}
}





















