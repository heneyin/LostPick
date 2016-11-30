package com.henvealf.lostpick.web.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.henvealf.lostpick.utils.EncryptUtil;

public class LoginForm {
	
	@NotNull
	@Size(min=6,max=12)
	private String userId;
	@NotNull 
	private String password;
	
	public LoginForm(){
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
