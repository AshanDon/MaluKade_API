package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.JobCard;
import com.example.restful.MaluKade.repository.JobCardRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class JobCardService {

	@Autowired
	private JobCardRepository jobCardRepository;
	
	/**
	 * @param List<JobCard> list
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> addNewCard(List<JobCard> list){
		try {
			List<JobCard> saved = jobCardRepository.saveAllAndFlush(list);
			
			if(saved != null) {
				Respones respones = new Respones(new Date(),"201","Successfully Saved.",saved.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.CREATED);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
