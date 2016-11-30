package com.henvealf.lostpick.web.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DayWord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7622303457069867856L;

	private int id;
	
	private String imgUrl;
	@NotNull
	private String sentence;
	@NotNull
	private String author;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
