package com.henvealf.lostpick.web.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Admin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8681420213501641449L;
	
	@NotNull
	private String name;
	
	@NotNull
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
