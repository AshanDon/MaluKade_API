package com.example.restful.MaluKade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restful.MaluKade.model.ProfileImage;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage,Long>{

	@Query(value = "SELECT * FROM profile_Image pi WHERE pi.pro_id =:proId",nativeQuery = true)
	public ProfileImage getProfileImageByProfileId(long proId);
}
