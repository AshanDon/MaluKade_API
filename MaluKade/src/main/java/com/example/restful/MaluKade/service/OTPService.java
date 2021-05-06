package com.example.restful.MaluKade.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OTPService {
	
	private static final Integer EXPIRE_MINS = 5;

	 private LoadingCache<String, Integer> otpCache;
	
	public OTPService() {
		super();
		
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String,Integer>(){
			public Integer load(String key) {
	             return 0;
	       }
		});
		
	}
	
	public Map<String, Integer> generateOTPCode(String userName) {
		Random random = new Random();
		Map<String, Integer> result = new HashMap<String, Integer>();
		int otp = 1000 + random.nextInt(9000);
		otpCache.put(userName, otp);
		result.put("OTP", otp);
		return result;
	}
	
	public Map<String, Boolean> validateOTP(String userName, int otpCode) {
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		
		try {
			if(otpCache.get(userName) == otpCode) {
				clearOTP(userName);
				resultMap.put("Valid", true);
			} else {
				resultMap.put("Valid", false);
			}
			return resultMap;
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("Valid", false);
			return resultMap;
		}
	}
	
	public void clearOTP(String key){ 
		otpCache.invalidate(key);
	}
}
