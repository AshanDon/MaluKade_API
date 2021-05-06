package com.example.restful.MaluKade.controller;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restful.MaluKade.service.OTPService;

@RestController
@RequestMapping("/malukade/v1")
public class OtpController {

	@Autowired
	private OTPService otpService;
	
	@GetMapping("/OTP/generate/{userName}")
	public Map<String, Integer> generateOTP(@PathVariable(name = "userName") String userName) {
		return otpService.generateOTPCode(userName);
	}
	
	@GetMapping("/OTP/validate")
	public Map<String, Boolean> validateOTP(@PathParam(value = "userName") String userName,@PathParam(value = "code") int code) {
		return otpService.validateOTP(userName, code);
	}
	
}
