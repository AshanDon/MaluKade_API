package com.example.restful.MaluKade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restful.MaluKade.model.PaymentDetails;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long>{

	@Query(value = "SELECT SUM(pyd.pay_Amount) AS Total_Pay_Amount FROM payment_Details pyd WHERE pyd.o_id =:o_id",nativeQuery = true)
	public double totalPayAmountByOderId(@Param(value = "o_id") long id);
	
	@Query(value = "SELECT * FROM payment_Details pyd WHERE pyd.o_id =:o_id",nativeQuery = true)
	public List<PaymentDetails> getAllPayment(@Param(value = "o_id") long byOrderId);
	
	@Query(value = "SELECT "
					+ "((o.total_Amount) - SUM(pyd.pay_Amount)) AS Payment_Balance "
				 + "FROM "
					+ "payment_Details pyd,orders o "
				 + "WHERE "
					+ "pyd.o_id =:o_id "
				 + "AND "
				 	+ "O.id = pyd.o_id;",nativeQuery = true)
	public double getOrderPaymentBalance(@Param(value = "o_id") long order_id);
}
