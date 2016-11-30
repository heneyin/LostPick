package com.henvealf.lostpick.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.henvealf.lostpick.data.DayWordRepository;

@Controller
public class HomeController {
	
	private DayWordRepository dayWordRepository;
	@Autowired
	public HomeController(DayWordRepository dayWordRepository) {
		this.dayWordRepository = dayWordRepository;
	}
	
	@RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
	public String home( Model  model){
		model.addAttribute("dayWord",dayWordRepository.findMaxIdDayWord());
		return "home";
	}
	
	/**
	 * 捕捉所有经过该控制器的DuplicateNoticeException
	 * @return
	 
	@ExceptionHandler(Exception.class)
	public String handleDuplicateNotice(Model model){
		model.addAttribute("errorMessage","未知错误 ");
		return "error/error";
	}*/
}
