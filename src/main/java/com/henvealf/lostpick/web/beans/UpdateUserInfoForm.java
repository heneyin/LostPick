package com.henvealf.lostpick.web.beans;

import javax.validation.constraints.Size;

import com.henvealf.lostpick.myvalidations.MyNotNull;
import com.henvealf.lostpick.myvalidations.NotEquels;
import com.henvealf.lostpick.myvalidations.Number;

public class UpdateUserInfoForm {
	
	@MyNotNull
	@Size	 ( min = 2, max = 15, message="{userName.size}")
	private String userName;
	
	@NotEquels(value="ps",message="{academy.notChooes}")
	private String academy;
	
	@MyNotNull
	@Number
	@Size 	 ( min = 11, max = 11, message="{phonecode.size}")
	private String phonecode;      //

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}
}
