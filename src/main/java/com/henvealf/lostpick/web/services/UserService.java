package com.henvealf.lostpick.web.services;

import java.util.ArrayList;
import java.util.List;

import com.henvealf.lostpick.data.UserRepository;
import com.henvealf.lostpick.web.beans.LoginForm;

public class UserService {
	
	private static List<String> academys;
	private static List<String> sexs;
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	protected boolean isUserIdRepeat(String userId) {
		return true;
	}
	
	public static List<String> getAllAcademys (){
		academys = new ArrayList<String>();
		academys.add("音乐学院");
		academys.add("文学院");
		academys.add("外国语学院");
		academys.add("政治与公共管理学院");
		academys.add("体育学院");
		academys.add("政治与公共管理学院");
		academys.add("数学科学学院");
		academys.add("生命科学学院");
		academys.add("药学院");
		academys.add("国际教育交流学院");
		academys.add("商学院");
		academys.add("机械与汽车工程学院");
		academys.add("农学院");
		academys.add("美术学院");
		academys.add("历史文化与旅游学院");
		academys.add("物理科学与信息工程学院");
		academys.add("教育科学学院");
		academys.add("建筑工程学院");
		academys.add("计算机学院");
		academys.add("环境与规划学院");
		academys.add("化学与化工学院");
		academys.add("季羡林学院");
		academys.add("法学院");
		academys.add("传媒技术学院");
		academys.add("材料科学与工程学院学院");
		academys.add("计算机学院");
		
		return academys;
	}
	
	public static List<String> getAllSexs(){
		sexs = new ArrayList<String>();
		sexs.add("男");
		sexs.add("女");
		sexs.add("保密");
		return sexs;
	}
}
 