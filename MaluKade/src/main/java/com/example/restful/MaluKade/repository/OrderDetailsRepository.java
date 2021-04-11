package com.example.restful.MaluKade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restful.MaluKade.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

	@Query(value = "SELECT * FROM order_details od WHERE od.ord_id =:o_id",nativeQuery = true)
	public List<OrderDetails> getAllDetailsByOrderId(@Param(value = "o_id") long id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE order_details od SET od.discount =:disc, od.qty =:qty WHERE od.ord_id =:o_id AND od.pro_id =:p_id",nativeQuery = true)
	public int updateProductDetails(@Param(value = "o_id") long o_id,
									@Param(value = "p_id") long p_id,
									@Param(value = "qty") double qty,
									@Param(value = "disc") double discount);
}
