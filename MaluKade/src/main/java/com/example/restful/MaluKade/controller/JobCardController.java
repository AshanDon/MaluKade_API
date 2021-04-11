package com.example.restful.MaluKade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restful.MaluKade.model.JobCard;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.JobCardService;

@RestController
@RequestMapping("/malukade/v1")
public class JobCardController {
	
	@Autowired
	private JobCardService jobCardService;
	
	@PostMapping("/job_card")
	private ResponseEntity<Respones> addNewCardInfo(@RequestBody List<JobCard> jobList){
		return jobCardService.addNewCard(jobList);
	}

}
