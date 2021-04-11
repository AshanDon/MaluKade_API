package com.example.restful.MaluKade.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AuthenticationRequest",description = "Details about the Authentication Request")
public class AuthenticationRequest {

	@ApiModelProperty(value = "The profile's userName",allowEmptyValue = false)
	private String userName;
	@ApiModelProperty(value = "The profile's password",allowEmptyValue = false)
	private String password;
	
	public AuthenticationRequest() {
		
	}

	public AuthenticationRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
