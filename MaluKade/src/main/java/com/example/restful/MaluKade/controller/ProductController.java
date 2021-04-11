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

import com.example.restful.MaluKade.model.Product;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.ProductService;

@RestController
@RequestMapping("/malukade/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<Respones> saveProduct(@RequestBody Product product){
		return productService.addNewProduct(product);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Object> findProductById(@PathVariable(name = "id") long id) {
		return productService.getProductById(id);
		
	}
	
	@PutMapping("/product")
	public ResponseEntity<Respones> updateProductDetails(@RequestBody Product product){
		return productService.updateProductDetails(product);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Respones> deleteProductDetails(@PathVariable(name = "id") long id){
		return productService.deleteProductDetail(id);
	}
	
	@GetMapping("/product/find/Cate_id/{id}")
	public List<Product> findAllProductByCategoryId(@PathVariable(name = "id") long id){
		return productService.getAllProductByCategory(id);
	}
	
	@GetMapping("/product/find/sup_id/{id}")
	public List<Product> findAllProductBySupplierId(@PathVariable(name = "id") long id){
		return productService.getAllProductBySupplier(id);
	}
}
