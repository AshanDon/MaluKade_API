package com.example.restful.MaluKade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restful.MaluKade.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query(value = "SELECT * FROM orders o WHERE o.profile_id =:p_id",nativeQuery = true)
	public List<Order> findOrderByProfile(@Param(value = "p_id") long id);
}
