package com.example.restful.MaluKade.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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

import com.example.restful.MaluKade.model.Supplier;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.SupplierService;

@RestController
@RequestMapping("/malukade/v1")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@PostMapping("/supplier")
	private ResponseEntity<Respones> saveSupplier(@RequestBody Supplier supplier){
		return supplierService.addNewSupplier(supplier);
	}
	
	@GetMapping("/supplier/{id}")
	public ResponseEntity<Object> findSupplierById(@PathVariable(name = "id") long id){
		return supplierService.getSupplierById(id);
	}
	
	@GetMapping("/supplier/all")
	public List<Supplier> viewAllSuppliers(){
		return supplierService.getAllSupplier();
	}
	
	@PutMapping("/supplier")
	public ResponseEntity<Respones> updateSupplier(@RequestBody Supplier supplier){
		return supplierService.updateSupplierDetails(supplier);
	}
	
	@DeleteMapping("/supplier/{id}")
	public ResponseEntity<Respones> deleteSupplierById(@PathVariable(name = "id") long id){
		return supplierService.deleteSupplier(id);
	}
	
	@PutMapping("/supplier/active")
	public ResponseEntity<Respones> updateActiveStatus(@PathParam(value = "status") boolean status,@PathParam(value = "id") long id){
		return supplierService.manageActiveSupplier(status, id);
	}
	
	@GetMapping("/supplier/active")
	public List<Supplier> viewAllActiveSuppliers(){
		return supplierService.getAllActiveSupplier();
	}
}
