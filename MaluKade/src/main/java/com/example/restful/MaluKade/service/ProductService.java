package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.Product;
import com.example.restful.MaluKade.repository.ProductRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<Respones> addNewProduct(Product product){
		try {
			Product result = productRepository.save(product);
			
			if(result != null) {
				Respones respones = new Respones(new Date(), "201", "Successfully saved.", result.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.CREATED);
			} else {
				return null;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public ResponseEntity<Object> getProductById(long id) {
		try {
			Product result = productRepository.findById(id).get();
			return new ResponseEntity<Object>(result,HttpStatus.OK);
		}catch (NoSuchElementException ex) {
			Respones respones = new Respones(new Date(), "404", "Not Found", "Product not found on "+id);
			return new ResponseEntity<Object>(respones,HttpStatus.NOT_FOUND);
		}
		
	}
	
	public ResponseEntity<Respones> updateProductDetails(Product product){
		try {
			Product result = productRepository.findById(product.getId()).get();
			
			result.setName(product.getName());
			result.setDescription(product.getDescription());
			result.setUnitprice(product.getUnitprice());
			
			final Product saved = productRepository.saveAndFlush(result);
			
			if(saved != null) {
				Respones respones = new Respones(new Date(), "201", "Successfully Updated.", saved.toString());
				return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			} else {
				return null;
			}
			
		}catch (NoSuchElementException ex) {
			Respones respones = new Respones(new Date(), "404", "Not Found", "Product not found on "+product.getId());
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Respones> deleteProductDetail(long id){
		try {
			Product result = productRepository.findById(id).get();
			
			productRepository.delete(result);
			
			Respones respones = new Respones(new Date(), "201", "Successfully Deleted.", result.toString());
			return new ResponseEntity<Respones>(respones,HttpStatus.OK);
			
		}catch (NoSuchElementException ex) {
			Respones respones = new Respones(new Date(), "404", "Not Found", "Product not found on "+id);
			return new ResponseEntity<Respones>(respones,HttpStatus.NOT_FOUND);
		}
	}
	
	public List<Product> getAllProductByCategory(long id){
		try {
			return productRepository.getAllProductByCategory(id);
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<Product> getAllProductBySupplier(long id){
		try {
			return productRepository.getAllProductBySupplier(id);
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
























