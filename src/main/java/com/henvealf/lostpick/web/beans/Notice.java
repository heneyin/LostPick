package com.henvealf.lostpick.web.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.henvealf.lostpick.myvalidations.NotEquels;
import com.henvealf.lostpick.myvalidations.MyNotNull;



/**
 * 存储订单的表
 * @author Henvealf
 *
 */
public class Notice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3710294778256447343L;
	
	public static final boolean LOST = true;
	public static final boolean PICK = false;
	
	//@Null  
	Long id;
	
	@MyNotNull //这是自定义的，不能为空，且不能带有空格
	private String userId;
	
	@Null
	private String noticeTime;
	
	@NotEquels(value="ps")  //please select
	private String goodsType;
	
	@NotNull
	private boolean lostOrPick;
	
	@Null
	private String lopTime;    //哪一天
	
	@NotNull( message = "不能为空！")
	@Size(min = 1, max = 50, message = "{exactTime.size}")
	private String exactTime; //丢或者捡的准确时间
	
	@NotNull ( message = "不能为空！")
	@Size(min = 1, max = 50, message = "{lopPlace.size}")
	private String lopPlace;  //丢或者捡的地点
	
	@Null
	private String imgUrl;
	
	@NotNull ( message = "不能为空！")
	@Size(min = 1, max = 300, message = "{description.size}")
	private String description;
	
	
	@NotNull
	private String YYYY;
	@NotNull
	private String MM;
	@NotNull
	private String DD;
	
	public Notice(){
		
	}
	
	
	public String produceLopTime(){
		return YYYY + "-" + MM + "-" + DD;
	}
	
	
	public String getYYYY() {
		return YYYY;
	}
	public void setYYYY(String yYYY) {
		YYYY = yYYY;
	}
	public String getMM() {
		return MM;
	}
	public void setMM(String mM) {
		MM = mM;
	}
	public String getDD() {
		return DD;
	}
	public void setDD(String dD) {
		DD = dD;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public boolean isLostOrPick() {
		return lostOrPick;
	}

	public void setLostOrPick(boolean lostOrPick) {
		this.lostOrPick = lostOrPick;
	}

	public String getLopTime() {
		return lopTime;
	}

	public void setLopTime(String lopTime) {
		this.lopTime = lopTime;
	}

	public String getExactTime() {
		return exactTime;
	}

	public void setExactTime(String exactTime) {
		this.exactTime = exactTime;
	}

	public String getLopPlace() {
		return lopPlace;
	}

	public void setLopPlace(String lopPlace) {
		this.lopPlace = lopPlace;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Notice(Long id,String userId, String noticeTime, String goodsType, boolean lostOrPick, String lopTime,
			String lopPlace, String imgUrl, String description) {
		super();
		this.id = id;
		this.userId = userId;
		this.noticeTime = noticeTime;
		this.goodsType = goodsType;
		this.lostOrPick = lostOrPick;
		this.lopTime = lopTime;
		this.lopPlace = lopPlace;
		this.imgUrl = imgUrl;
		this.description = description;
	}



	
	/*@Override
	public boolean equals(Object that) {
	return EqualsBuilder.reflectionEquals(this, that, "id", "time");
	}
	@Override
	public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, "id", "time");
	}*/
}
