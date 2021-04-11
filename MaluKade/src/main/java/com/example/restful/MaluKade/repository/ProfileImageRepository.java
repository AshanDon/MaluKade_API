package com.example.restful.MaluKade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restful.MaluKade.model.ProfileImage;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage,Long>{

}
