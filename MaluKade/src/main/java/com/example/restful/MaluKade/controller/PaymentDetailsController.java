package com.example.restful.MaluKade.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restful.MaluKade.model.PaymentDetails;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.PaymentDetailsService;

@RestController
@RequestMapping("/malukade/v1")
public class PaymentDetailsController {

	@Autowired
	private PaymentDetailsService detailsService;
	
	@PostMapping("/payment_details")
	public ResponseEntity<Respones> saveNewPayDetails(@RequestBody List<PaymentDetails> details){
		return detailsService.addNewPayDetails(details);
	}
	
	@GetMapping("/payment_details/amount")
	public Map<String, Double> getPayTotalAmount(@PathParam(value = "order_id") long order_id){
		return detailsService.getTotalPayAmount(order_id);
	}
	
	@GetMapping("/payment_details/all")
	public List<PaymentDetails> getAllPayment(@PathParam(value = "order_id") long order_id){
		return detailsService.getAllPayment(order_id);
	}
	
	@GetMapping("/payment_details/balance")
	public Map<String, Double> getPayBalanceAmount(@PathParam(value = "order_id") long order_id){
		return detailsService.getPaymentBalance(order_id);
	}
	
	@PutMapping("/payment_details/amount")
	public ResponseEntity<Respones> updatePayAmount(@PathParam(value = "id") long id,@PathParam(value = "amount") double amount){
		return detailsService.updatePaymentAmount(id, amount);
	}
	
	@DeleteMapping("/payment_details/{id}")
	public ResponseEntity<Respones> deletePaymentInfo(@PathVariable(name = "id") long id){
		return detailsService.deletePaymentInfo(id);
	}
}








