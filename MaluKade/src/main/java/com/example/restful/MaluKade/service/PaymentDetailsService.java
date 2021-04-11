package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.PaymentDetails;
import com.example.restful.MaluKade.repository.PaymentDetailsRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class PaymentDetailsService {

	@Autowired
	private PaymentDetailsRepository payDetailsRepository;
	
	/**
	 * @param details
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> addNewPayDetails(List<PaymentDetails> details){
		try {
			List<PaymentDetails> saved = payDetailsRepository.saveAllAndFlush(details);
			
			if(saved != null) {
				Respones respones = new Respones(new Date(),"201","Successfully Saved.",saved.toString());
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
	 * @param long order_id
	 * @return Map<String, Double>
	 */
	public Map<String, Double> getTotalPayAmount(long order_id){
		try {
			Map<String, Double> responseMap = new HashMap<String, Double>();
			double totalAmount = payDetailsRepository.totalPayAmountByOderId(order_id);
			responseMap.put("Total Amount", totalAmount);
			return responseMap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param long byOrderId
	 * @return List<PaymentDetails>
	 */
	public List<PaymentDetails> getAllPayment(long byOrderId){
		try {
			return payDetailsRepository.getAllPayment(byOrderId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param byOrderId
	 * @return
	 */
	public Map<String, Double> getPaymentBalance(long byOrderId){
		try {
			Map<String, Double> responseMap = new HashMap<String, Double>();
			double balanceAmount = payDetailsRepository.getOrderPaymentBalance(byOrderId);
			responseMap.put("Payment Balance", balanceAmount);
			return responseMap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param long id
	 * @param double amount
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> updatePaymentAmount(long id,double amount){
		try {
			PaymentDetails result = payDetailsRepository.findById(id).get();
			
			result.setPay_Amount(amount);
			
			final PaymentDetails updated = payDetailsRepository.save(result);
			
			if(updated != null) {
				Respones respones = new Respones(new Date(),"200","Successfully Updated.",updated.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			} else {
				return null;
			}
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Payment not found on "+id);
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @param id
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> deletePaymentInfo(long id){
		try {
			PaymentDetails result = payDetailsRepository.findById(id).get();
			
			payDetailsRepository.deleteById(result.getId());
			
			Respones respones = new Respones(new Date(),"200","Successfully Deleted.",result.toString());
			return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(),"404","Not Found","Payment not found on "+id);
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
}









