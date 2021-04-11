package com.example.restful.MaluKade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.restful.MaluKade.service.ProfileImageService;

@RestController
@RequestMapping("/malukade/v1")
public class ImageController {
	
	@Autowired
	private ProfileImageService profileImageService;
	
	@PostMapping("/profile_image")
	public boolean uploadProfileImage(@RequestParam("pro_id") long pro_id, @RequestParam("imageFile") MultipartFile file) {
		return profileImageService.uploadProfileImage(file, pro_id);
	}

}
