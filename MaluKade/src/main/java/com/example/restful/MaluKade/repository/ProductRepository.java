package com.example.restful.MaluKade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.restful.MaluKade.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value = "SELECT * FROM product p WHERE p.cate_id =:c_id",nativeQuery = true)
	public List<Product> getAllProductByCategory(@Param(value = "c_id") long c_id);
	
	@Query(value = "SELECT * FROM product p WHERE p.sup_id =:s_id",nativeQuery = true)
	public List<Product> getAllProductBySupplier(@Param(value = "s_id") long s_id);
}
