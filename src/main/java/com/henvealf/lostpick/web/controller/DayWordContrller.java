package com.henvealf.lostpick.web.controller;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import com.henvealf.lostpick.data.DayWordRepository;
import com.henvealf.lostpick.data.UploadImage;
import com.henvealf.lostpick.web.beans.DayWord;

@Controller
@RequestMapping(value="/dayWord")
public class DayWordContrller {
	
	
	private DayWordRepository dayWordRepository;
	@Autowired
	public DayWordContrller(DayWordRepository dayWordRepository) {
		this.dayWordRepository = dayWordRepository;
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@RequestPart ("imageFile")Part imageFile,
						DayWord dayWord){
	
	String dbImgUrl = UploadImage.goCompressAndUploadDayImg(imageFile,dayWord);	
	
	dayWord.setImgUrl(dbImgUrl);
	dayWordRepository.add(dayWord);	
		
	return "redirct:/home";
	}
	
	/**
	 * 捕捉所有经过该控制器的DuplicateNoticeException
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public String handleDuplicateNotice(Model model){
		model.addAttribute("errorMessage","未知错误 ");
		return "error/error";
	}
}
