package com.example.restful.MaluKade.repository;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restful.MaluKade.model.ProductBatch;

@Repository
public interface ProductBatchRepository extends JpaRepository<ProductBatch, Long>{

	@Query(value = "SELECT SUM(b.available_qty) FROM products_batch b WHERE b.pro_id =:p_id AND b.available_qty >= 1",nativeQuery = true)
	public int getStockCountProductWise(@PathParam(value = "p_id") long p_id);
	
	@Query(value = "SELECT "
					+ "* "
				 + "FROM "
				 	+ "products_batch "
				 + "WHERE "
				 	+ "pro_id =:p_id "
				 + "AND "
				 	+ "available_qty > 0 "
				 + "ORDER BY purchase_date ASC;",nativeQuery = true)
	public List<ProductBatch> getProductById(@Param(value = "p_id") long p_id);
	
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products_batch SET available_qty = (available_qty - :qty) WHERE b_id = :b_id",nativeQuery = true)
	public int updateAvailableStockProId(@Param(value = "b_id") long b_id, @Param(value = "qty") double qty);
}
