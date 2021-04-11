package com.example.restful.MaluKade.controller;

import java.util.List;

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

import com.example.restful.MaluKade.model.Payment;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.PaymentService;

@RestController
@RequestMapping("/malukade/v1")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/payment")
	public ResponseEntity<Respones> saveNewPaymentType(@RequestBody Payment payment){
		return paymentService.addNewPayment(payment);
	}
	
	@GetMapping("/payment")
	public List<Payment> getAllPaymentType(){
		return paymentService.getAllPaymentType();
	}
	
	@GetMapping("/payment/{id}")
	public ResponseEntity<Object> findPayTypeById(@PathVariable(value = "id") long id){
		return paymentService.getPaymentById(id);
	}
	
	@PutMapping("/payment")
	public ResponseEntity<Respones> updatePayTypeInfo(@RequestBody Payment payment){
		return paymentService.updatePaymentType(payment);
	}
	
	@DeleteMapping("/payment/{id}")
	public ResponseEntity<Respones> deletePayTypeInfo(@PathVariable(value = "id") long id){
		return paymentService.deletePaymentType(id);
	}
}














