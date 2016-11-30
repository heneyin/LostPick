package com.henvealf.lostpick.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
/**
 * 对一些操作进行安全担保
 * @author Henvealf
 *
 */
public class OperationSecurity {
	/**
	 * 判断访问用户是不是该页面的作者
	 * @param userId 
	 * @param model
	 * @return
	 */
	public static boolean isLoginUser(String userId,Model model){
		String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//System.out.println(loginUserId);
		if(!userId.equals(loginUserId)){
			model.addAttribute("errorMessage","你没有权限!");
			//return "error/error";
			return false;
		}
		return true;
	}
}
