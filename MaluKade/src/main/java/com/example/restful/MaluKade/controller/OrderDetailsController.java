package com.example.restful.MaluKade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restful.MaluKade.model.OrderDetails;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.OrderDetailsService;

@RestController
@RequestMapping("/malukade/v1")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@PostMapping("/order_details")
	public ResponseEntity<Respones> saveOrderDetails(@RequestBody List<OrderDetails> details){
		return orderDetailsService.addNewOrderDetails(details);
	}
	
	@GetMapping("/order_details/{id}")
	public List<OrderDetails> findAllDetailsByOrder(@PathVariable(name = "id") long id){
		return orderDetailsService.getAllDetailById(id);
	}
	
	@PutMapping("/order_details")
	public ResponseEntity<Respones> updateOrderDetails(@RequestBody OrderDetails details){
		return orderDetailsService.updateOrderDetails(details);
	}
}
