package com.example.restful.MaluKade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restful.MaluKade.model.JobCard;

@Repository
public interface JobCardRepository extends JpaRepository<JobCard, Long>{

}
