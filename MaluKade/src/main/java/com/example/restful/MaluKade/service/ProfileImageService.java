package com.example.restful.MaluKade.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.restful.MaluKade.exception.ResourceNotFoundException;
import com.example.restful.MaluKade.model.Profile;
import com.example.restful.MaluKade.model.ProfileImage;
import com.example.restful.MaluKade.repository.ProfileImageRepository;
import com.example.restful.MaluKade.repository.ProfileRepository;
import com.example.restful.MaluKade.response.Respones;

@Service
@Transactional
public class ProfileImageService {

	@Autowired
	private ProfileImageRepository imageRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	/**
	 * @param MultipartFile file
	 * @param Long pro_id
	 * @return Boolean
	 */
	public Boolean uploadProfileImage(MultipartFile file,long pro_id){
		try {
			Profile profile = profileRepository.findById(pro_id).get();
			
			ProfileImage image = new ProfileImage(profile,file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
			System.out.println(image.getImageByte().length);
			ProfileImage saved = imageRepository.save(image);
			
			if(saved != null) {
				return true;
			} else {
				return false;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
//	public ResponseEntity<Respones> getImageByProId(long proId){
//		
//		try {
//			Profile profile = profileRepository.findById(proId).get();
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//	}
	
	/**
	 * compress the image bytes before storing it in the database
	 * @param byte[] data
	 * @return byte[]
	 */
	private byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
	
	/**
	 * uncompress the image bytes before returning it to the controller
	 * @param byte[] data
	 * @return byte[]
	 */
	private byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();

		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];
		try {

			while (!inflater.finished()) {

				int count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);

			}

			outputStream.close();

		} catch (IOException ex) {

		} catch (DataFormatException ex) {

		}

		return outputStream.toByteArray();

	}

}
