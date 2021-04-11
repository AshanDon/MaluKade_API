package com.example.restful.MaluKade.service;

import java.util.ArrayList;
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

import com.example.restful.MaluKade.model.OrderDetails;
import com.example.restful.MaluKade.model.ProductBatch;
import com.example.restful.MaluKade.repository.ProductBatchRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class ProductBatchService {
	
	@Autowired
	private ProductBatchRepository batchRepository;
	
	public ResponseEntity<Respones> addNewProBatch(ProductBatch batch){
		try {
			ProductBatch saved = batchRepository.saveAndFlush(batch);
			
			if(saved != null) {
				Respones respones = new Respones(new Date(), "201","Successfully saved.",saved.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.CREATED);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ResponseEntity<Object> getBatchById(long id){
		
		try {
			ProductBatch result = batchRepository.findById(id).get();
			return new ResponseEntity<Object>(result,HttpStatus.OK);
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(), "404", "Not Found", "Product not found on "+id);
			return new ResponseEntity<Object>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	public List<ProductBatch> getAllBatchDetails(){
		return batchRepository.findAll();
	}
	
	public ResponseEntity<Respones> updateBatchDetails(ProductBatch batch){
		try {
			ProductBatch result = batchRepository.findById(batch.getId()).get();
			
			result.setAmount(batch.getAmount());
			result.setQty(batch.getQty());
			
			final ProductBatch saved = batchRepository.saveAndFlush(result);
			
			if(saved != null) {
				Respones respones = new Respones(new Date(), "200", "Successfully Updated.",saved.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			} else {
				return null;
			}
			
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(), "404", "Not Found", "Product not found on "+batch.getId());
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Respones> deleteBatchDetail(long id){
		try {
			ProductBatch result = batchRepository.findById(id).get();
			
			batchRepository.deleteById(result.getId());
			
			Respones respones = new Respones(new Date(), "200", "Successfully Deleted.",result.toString());
			return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones respones = new Respones(new Date(), "404", "Not Found", "Product not found on "+id);
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	public Map<String, Integer> getStockByProductWise(long id){
		Map<String, Integer> stockDetailMap = new HashMap<String, Integer>();
		int qty = batchRepository.getStockCountProductWise(id);
		stockDetailMap.put("Qty", qty);
		return stockDetailMap;
	}
	
	public Boolean updateProductStockDateWise(List<OrderDetails> detailsList) {
		try {
			
			List<ProductBatch> stockList = new ArrayList<ProductBatch>();
			
			for(OrderDetails details : detailsList) {
				
				List<ProductBatch> productList = batchRepository.getProductById(details.getProduct().getId());
				
				double qty = details.getQty();
				
				for(ProductBatch batch : productList) {
					if(batch.getAvailable_qty() < qty && qty > 0) {
						qty = qty - batch.getAvailable_qty();
						batch.setAvailable_qty(0);
						stockList.add(batch);
					} else if(batch.getAvailable_qty() > qty && qty > 0) {
						double balance = batch.getAvailable_qty() - qty;
						batch.setAvailable_qty(balance);
						stockList.add(batch);
						qty = 0;
					}
				}
				
			}
			
			List<ProductBatch> updated = batchRepository.saveAllAndFlush(stockList);
			
			if(updated != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}












