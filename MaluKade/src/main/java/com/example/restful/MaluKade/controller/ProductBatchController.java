package com.example.restful.MaluKade.controller;

import java.util.List;
import java.util.Map;

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

import com.example.restful.MaluKade.model.ProductBatch;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.ProductBatchService;

@RestController
@RequestMapping("/malukade/v1")
public class ProductBatchController {

	@Autowired
	private ProductBatchService batchService;
	
	@PostMapping("/batch")
	public ResponseEntity<Respones> addNewBatch(@RequestBody ProductBatch batch){
		return batchService.addNewProBatch(batch);
	}
	
	@GetMapping("/batch/{id}")
	public ResponseEntity<Object> getBatchById(@PathVariable(value = "id") long id){
		return batchService.getBatchById(id);
	}
	
	@GetMapping("/batch/all")
	public List<ProductBatch> getAllBatchDetails(){
		return batchService.getAllBatchDetails();
	}
	
	@PutMapping("/batch")
	public ResponseEntity<Respones> updateBatchDetails(@RequestBody ProductBatch batch){
		return batchService.updateBatchDetails(batch);
	}
	
	@DeleteMapping("/batch/{id}")
	public ResponseEntity<Respones> deleteBatchDetails(@PathVariable(name = "id") long id){
		return batchService.deleteBatchDetail(id);
	}
	
	@GetMapping("/batch/stock/{pro_id}")
	public Map<String, Integer> getStockByProductId(@PathVariable(value = "pro_id") long pro_id){
		return batchService.getStockByProductWise(pro_id);
	}
}











