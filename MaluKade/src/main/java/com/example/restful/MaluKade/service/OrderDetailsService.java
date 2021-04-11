package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.OrderDetails;
import com.example.restful.MaluKade.repository.OrderDetailsRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class OrderDetailsService {

	@Autowired
	private OrderDetailsRepository detailsRepository;
	
	@Autowired
	private ProductBatchService batchService;
	
	public ResponseEntity<Respones> addNewOrderDetails(List<OrderDetails> details){
		try {
			List<OrderDetails> saved = detailsRepository.saveAll(details);
			
			if(saved != null) {
				
				boolean result = batchService.updateProductStockDateWise(saved);
				
				if(result) {
					Respones respones = new Respones(new Date(),"201","Successfully Saved.", Boolean.toString(result));
					return new ResponseEntity<Respones>(respones,HttpStatus.CREATED);
				} else {
					return null;
				}
				
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<OrderDetails> getAllDetailById(long id){
		try {
			return detailsRepository.getAllDetailsByOrderId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ResponseEntity<Respones> updateOrderDetails(OrderDetails details){
		try {
			int updated = detailsRepository.updateProductDetails(
							details.getOrder().getId(), 
							details.getProduct().getId(),
							details.getQty(),
							details.getDiscount());
			if(updated > 0) {
				Respones respones = new Respones(new Date(),"200","Successfully Updated.","Result : "+Integer.toString(updated));
				return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}













