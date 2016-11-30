package com.henvealf.lostpick.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.henvealf.lostpick.web.beans.Admin;
import com.henvealf.lostpick.web.beans.DayWord;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginForm(Model model){
		model.addAttribute(new Admin());
		return "adminLogin";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String processLogin(Admin admin,Model model){
		if(admin.getName().equals("henvealf") && admin.getPassword().equals("lostpick123!")){
				model.addAttribute("key","yes");
				model.addAttribute(new DayWord());
				return "addDayWord";
		}else{
			return "redirect:/admin/login";
		}
	}
	

	/**
	 * 捕捉所有经过该控制器的DuplicateNoticeException
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public String handleDuplicateNotice(Model model){
		model.addAttribute("errorMessage","未知错误 ，可能你访问的表单已经不存在");
		return "error/error";
	}
}
