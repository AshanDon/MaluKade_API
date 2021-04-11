package com.example.restful.MaluKade.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restful.MaluKade.model.Category;
import com.example.restful.MaluKade.repository.CategoryRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Respones addNewCategory(Category category) {
		
		try {
			Category result = categoryRepository.save(category);
			if(result != null) {
				return new Respones(new Date(),"201","Successfully Saved.",result.toString());
			} else {
				return null;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ResponseEntity<Object> getCategoryById(long id){
		
		try {
			
			Category details = categoryRepository.findById(id).get();
			
			return new ResponseEntity<Object>(details,HttpStatus.OK);
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones response = new Respones(new Date(), "404", "Not Found", "Category not found on "+id);
			return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
		}
		
	}
	
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
	
	public ResponseEntity<Respones> updateCategory(Category category) {
		try {
		
			Category details = categoryRepository.findById(category.getId()).get();
			
			details.setName(category.getName());
			final Category result = categoryRepository.save(details);
			
			if(result != null) {
				Respones response = new Respones(new Date(), "201", "Successfully Updated.", result.toString());
				return new ResponseEntity<Respones>(response,HttpStatus.CREATED);
			} else {
				return null;
			}
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones response = new Respones(new Date(), "404", "Not Found", "Category not found on "+category.getId());
			return new ResponseEntity<Respones>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	
	public ResponseEntity<Respones> deleteCategory(long id){
		try {
			
			Category details = categoryRepository.findById(id).get();
			
			categoryRepository.delete(details);
			Respones response = new Respones(new Date(), "201", "Successfully Deleted.", details.toString());
			return new ResponseEntity<Respones>(response,HttpStatus.CREATED);
		}catch (NoSuchElementException ex) {
			ex.printStackTrace();
			Respones response = new Respones(new Date(), "404", "Not Found", "Category not found on "+id);
			return new ResponseEntity<Respones>(response,HttpStatus.NOT_FOUND);
		}
	}
}
