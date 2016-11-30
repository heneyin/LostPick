package com.henvealf.lostpick.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/***
 * session 工具类
 * @author Henvealf
 *
 */
public class SessionUtils {
	
	/**
	 * 检查时候有指定的session
	 * @param
	 * @param req
	 * @return
	 */
	public static boolean hasSession(String name, HttpServletRequest req){
		HttpSession session = req.getSession();
		if(session.getAttribute(name) != null){
			//System.out.println("-------session:" + name );
			return true;
		}
		return false;
	}

	public static String getValue(String name, HttpServletRequest req){
		HttpSession session = req.getSession();
		if(session.getAttribute(name) != null){

			String value =  (String) session.getAttribute(name);
			//System.out.println("--session:" + name +" " + value);
			return value;
		}
		return null; 
	}
	
}
