package com.henvealf.lostpick.web.beans;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.henvealf.lostpick.myvalidations.MyNotNull;
import com.henvealf.lostpick.myvalidations.NotEquels;
import com.henvealf.lostpick.myvalidations.Number;
import com.henvealf.lostpick.myvalidations.NumberAndAbc;

/**
 * 已经注册的用户的bean
 * @author Henvealf
 *
 */

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2744588623460078005L;

	@MyNotNull
	@NumberAndAbc
	@Size	 ( min = 4, max = 16, message = "{userId.size}")
	private String userId;
	
	@MyNotNull
	@Size	 ( min = 6, max = 15, message = "{password.size}")
	private String password;
	
	@MyNotNull
	@Size	 ( min = 4, max = 15, message="{userName.size}")
	private String userName;
	
	@NotEquels(value="ps",message="{academy.notChooes}")
	private String academy;
	
	@MyNotNull
	private String sex;
	
	@MyNotNull
	@Number
	@Size 	 ( min = 11, max = 11, message="{phonecode.size}")
	private String phonecode;      //
	
	@MyNotNull
	@Number
	@Size	 ( min = 5, max = 11, message="{QQNumber.size}")
	private String QQNumber;       //
	
	@Email( message ="{email}" )
	@MyNotNull
	private String email;          //
	
	public User(){
		
	}

	public User(String userId, String password, String userName, String academy, String sex, String phonecode,
			String qQNumber, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.academy = academy;
		this.sex = sex;
		this.phonecode = phonecode;
		QQNumber = qQNumber;
		this.email = email;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhonecode() {
		return phonecode;
	}
	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}
	public String getQQNumber() {
		return QQNumber;
	}
	public void setQQNumber(String qQNumber) {
		QQNumber = qQNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
