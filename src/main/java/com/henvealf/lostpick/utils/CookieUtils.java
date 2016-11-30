package com.henvealf.lostpick.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 使用Cookie的一个工具类
 * @author Henvealf
 *
 */
public class CookieUtils {
	/**
	 * 根据 cookie 的名字Key获取到Value值。
	 * @param req   
	 * @param cookieName  cookie的名字
	 * @param defaultValue 默认value
	 * @return 如果找到cookie，则返回改cookie的value，否则返回defaultValue
	 */
	public static String getCookieValue(HttpServletRequest req, 
										String cookieName,
										String defaultValue){
		Cookie cookie = CookieUtils.getCookie(req,cookieName);
		if(cookie != null){
				return cookie.getValue();
		}
		return defaultValue;
	}
	
	/**
	 * 得到指定名字的Cookie
	 * @param req
	 * @param cookieName 希望过得cookie的名字
	 * @return 如果req中存在名字为cookieName的Cookie，就返回该cookie的对象。否则返回null；
	 */
	public static Cookie getCookie(HttpServletRequest req, String cookieName){
		Cookie[] cookies  = req.getCookies();
		//System.out.println("cookies:"+ cookies);
		if(cookies != null){
			//System.out.println("cookies.length:"+ cookies.length);
			for(int i = 0; i < cookies.length; i++){
				Cookie cookie = cookies[i];
				//System.out.println("cookieName: " + cookie.getName());
				if(cookieName.equals(cookie.getName())){
					return cookie; 
				}
			} 
		}
		return null; 
	}
}
