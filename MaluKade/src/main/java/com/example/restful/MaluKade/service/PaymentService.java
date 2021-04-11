package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.Payment;
import com.example.restful.MaluKade.repository.PaymentRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	/**
	 * @param payment
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> addNewPayment(Payment payment){
		try {
			Payment saved = paymentRepository.save(payment);
			
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
	public ResponseEntity<Object> getPaymentById(long id){
		try {
			Payment result = paymentRepository.findById(id).get();
			return new ResponseEntity<Object>(result,HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Payment not found on "+id);
			return new ResponseEntity<Object>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @return List<Payment>
	 */
	public List<Payment> getAllPaymentType(){
		try {
			return paymentRepository.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param payment
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> updatePaymentType(Payment payment){
		try {
			Payment result = paymentRepository.findById(payment.getId()).get();
			
			result.setPaymentType(payment.getPaymentType());
			
			final Payment updated = paymentRepository.save(result);
			
			if(updated != null) {
				Respones respones = new Respones(new Date(), "200","Successfully Updated.",updated.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			} else {
				return null;
			}
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(), "404","Not Found","Payment not found on "+payment.getId());
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @param id
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> deletePaymentType(long id){
		try {
			Payment result = paymentRepository.findById(id).get();
			
			paymentRepository.deleteById(result.getId());
			
			Respones respones = new Respones(new Date(), "200","Successfully Deleted.",result.toString());
			return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(), "404","Not Found","Payment not found on "+id);
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
}



















