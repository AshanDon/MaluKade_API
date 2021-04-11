package com.example.restful.MaluKade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restful.MaluKade.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE supplier s SET s.active_Status =:status WHERE s.id =:id",nativeQuery = true)
	public int manageActiveSupplier(@Param(value = "status") boolean status,@Param(value = "id") long id);
	
	@Query(value = "SELECT * FROM supplier s WHERE s.active_Status = true",nativeQuery = true)
	public List<Supplier> getAllActiveSupplier();
}
