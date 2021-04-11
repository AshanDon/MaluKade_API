package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.Order;
import com.example.restful.MaluKade.repository.OrderRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * @param order
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> addNewOrder(Order order){
		try {
			Order saved = orderRepository.save(order);
			
			if(saved != null) {
				Respones respones = new Respones(new Date(), "201","Successfully Saved.",saved.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.CREATED);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param long id
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> findOrderById(long id){
		try {
			Order result = orderRepository.findById(id).get();
			
			return new ResponseEntity<Object>(result,HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Order not found on "+id);
			return new ResponseEntity<Object>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @return List<Order>
	 */
	public List<Order> getAllOrders(){
		try {
			return orderRepository.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param order
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> updateOrderInfo(Order order){
		try {
			Order result = orderRepository.findById(order.getId()).get();
			
			result.setAmount(order.getAmount());
			result.setDiscount(order.getDiscount());
			
			final Order updated = orderRepository.saveAndFlush(result);
			
			if(updated != null) {
				Respones respones = new Respones(new Date(),"200","Successfully Updated.",updated.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			} else {
				return null;
			}
			
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Order not found on "+order.getId());
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @param long id
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> deleteOrderInfo(long id){
		try {
			Order result = orderRepository.findById(id).get();
			
			orderRepository.deleteById(result.getId());
			
			Respones respones = new Respones(new Date(),"200","Successfully Deleted.",result.toString());
			return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Order not found on "+id);
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @param long id
	 * @return List<Order>
	 */
	public List<Order> allOrdersByProfile(long id){
		try {
			return orderRepository.findOrderByProfile(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}


























