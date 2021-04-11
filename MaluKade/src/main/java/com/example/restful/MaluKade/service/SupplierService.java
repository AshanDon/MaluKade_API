package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.Supplier;
import com.example.restful.MaluKade.repository.SupplierRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	
	/**
	 * @param supplier
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> addNewSupplier(Supplier supplier){
		try {
			Supplier result = supplierRepository.save(supplier);
			if(result != null) {
				Respones response = new Respones(new Date(),"201","Successfully Saved.",result.toString());
				return new ResponseEntity<Respones>(response,HttpStatus.CREATED);
			} else {
				return null;
			}
		}catch(IllegalArgumentException ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * @param long id
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Object> getSupplierById(long id){
		try {
			Supplier result = supplierRepository.findById(id).get();
			return new ResponseEntity<Object>(result,HttpStatus.OK);
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones response = new Respones(new Date(),"404","Not Found Exception","Supplier not found on "+id);
			return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/**
	 * 
	 * @return List<Supplier>
	 */
	public List<Supplier> getAllSupplier(){
		return supplierRepository.findAll();
	}
	
	/**
	 * @param supplier
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> updateSupplierDetails(Supplier supplier){
		
		try {
			Supplier result = supplierRepository.findById(supplier.getId()).get();
			
			result.setName(supplier.getName());
			result.setAddress(supplier.getAddress());
			result.setMobile1(supplier.getMobile1());
			result.setMobile2(supplier.getMobile2());
			result.setEmail(supplier.getEmail());
			
			final Supplier updateSupplier = supplierRepository.save(result);
			
			if(updateSupplier != null) {
				Respones response = new Respones(new Date(),"200","Successfully Updated.",updateSupplier.toString());
				return new ResponseEntity<Respones>(response,HttpStatus.OK);
			} else {
				return null;
			}
			
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones response = new Respones(new Date(),"404","Not Found Exception","Supplier not found on "+supplier.getId());
			return new ResponseEntity<Respones>(response,HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * @param id
	 * @return ResponseEntity<Respones>
	 */
	public ResponseEntity<Respones> deleteSupplier(long id){
		try {
			Supplier result = supplierRepository.findById(id).get();
			
			supplierRepository.deleteById(result.getId());
			
			Respones response = new Respones(new Date(),"200","Successfully Deleted.",result.toString());
			return new ResponseEntity<Respones>(response,HttpStatus.OK);
			
			
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones response = new Respones(new Date(),"404","Not Found Exception","Supplier not found on "+id);
			return new ResponseEntity<Respones>(response,HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ResponseEntity<Respones> manageActiveSupplier(boolean status,long id){
		
		try {
			Supplier result = supplierRepository.findById(id).get();
			
			int updated = supplierRepository.manageActiveSupplier(status, result.getId());
			
			if(updated == 1) {
				Respones response = new Respones(new Date(),"200","Successfully Updated.",Integer.toString(updated));
				return new ResponseEntity<Respones>(response,HttpStatus.OK);
			} else {
				return null;
			}
			
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones response = new Respones(new Date(),"404","Not Found Exception","Supplier not found on "+id);
			return new ResponseEntity<Respones>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	public List<Supplier> getAllActiveSupplier(){
		try {
			return supplierRepository.getAllActiveSupplier();
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
}
