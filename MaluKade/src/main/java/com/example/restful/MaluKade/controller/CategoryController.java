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

import com.example.restful.MaluKade.model.Category;
import com.example.restful.MaluKade.response.Respones;
import com.example.restful.MaluKade.service.CategoryService;

@RestController
@RequestMapping("/malukade/v1")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("category")
	public Respones saveCategory(@RequestBody Category category) {
		return categoryService.addNewCategory(category);
	}
	
	@GetMapping("category/{id}")
	public ResponseEntity<Object> findCategoryById(@PathVariable(name = "id") long id){
		return categoryService.getCategoryById(id);
	}
	
	@GetMapping("category/all")
	public List<Category> allCategory(){
		return categoryService.getAllCategory();
	}
	
	@PutMapping("category")
	public ResponseEntity<Respones> updateCategory(@RequestBody Category category) {
		return categoryService.updateCategory(category);
	}
	
	@DeleteMapping("category/{id}")
	public ResponseEntity<Respones> deleteCategory(@PathVariable(name = "id") long id){
		return categoryService.deleteCategory(id);
	}
}
