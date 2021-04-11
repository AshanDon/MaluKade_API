package com.example.restful.MaluKade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restful.MaluKade.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE profile p SET p.active_Status =:status WHERE p.id =:id",nativeQuery = true)
	public int manageActiveProfile(@Param(value = "status") boolean status,@Param("id") long id);
	
	@Query(value = "SELECT * FROM profile p WHERE p.active_Status = true",nativeQuery = true)
	public List<Profile> showAllActiveProfile();
	
	@Query(value = "SELECT * FROM profile p WHERE p.user_name =:userName AND p.active_Status = true",nativeQuery = true)
	public Profile getProfileByUserName(@Param(value = "userName") String userName);

}
