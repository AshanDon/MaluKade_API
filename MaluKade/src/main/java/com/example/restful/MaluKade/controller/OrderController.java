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

import com.example.restful.MaluKade.model.Order;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.OrderService;

@RestController
@RequestMapping("/malukade/v1")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<Respones> saveOrder(@RequestBody Order order){
		return orderService.addNewOrder(order);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Object> getOrderById(@PathVariable(name = "id") long id){
		return orderService.findOrderById(id);
	}
	
	@GetMapping("/order/all")
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	@PutMapping("/order")
	public ResponseEntity<Respones> updateOrderDetails(@RequestBody Order order){
		return orderService.updateOrderInfo(order);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Respones> deleteOrder(@PathVariable(name = "id") long id){
		return orderService.deleteOrderInfo(id);
	}

	@GetMapping("/order/profile/{id}")
	public List<Order> getAllOrdersBySupplier(@PathVariable(name = "id") long id){
		return orderService.allOrdersByProfile(id);
	}
}
