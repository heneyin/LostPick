package com.henvealf.lostpick.web.controller;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.henvealf.lostpick.utils.CodeUtils;


/**
 * 生成验证码的控制器
 * @author Henvealf
 *
 */
@Controller
public class CodeController {
	
	@RequestMapping(value="/codePicture")
	public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置不缓存图片  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "No-cache");  
        response.setDateHeader("Expires", 0) ;  
        //指定生成的相应图片  
        response.setContentType("image/jpeg") ;  
        CodeUtils idCode = new CodeUtils();  
        BufferedImage image =new BufferedImage(idCode.getWidth() , idCode.getHeight() , BufferedImage.TYPE_INT_BGR) ;  
        Graphics2D g = image.createGraphics() ;  
        //定义字体样式  
        Font myFont = new Font("黑体" , Font.BOLD , 16) ;  
        //设置字体  
        g.setFont(myFont) ;  
          
        g.setColor(idCode.getRandomColor(200 , 250)) ;  
        //绘制背景  
        g.fillRect(0, 0, idCode.getWidth() , idCode.getHeight()) ;  
          
        g.setColor(idCode.getRandomColor(180, 200)) ;  
        idCode.drawRandomLines(g, 160) ;  
        String pictureCode =  idCode.drawRandomString(4, g);
        
        //保存到session中，用来验证
        HttpSession codeSession = request.getSession();
        codeSession.setAttribute("codePicture", pictureCode);
        codeSession.setMaxInactiveInterval(60*5);
        
        g.dispose();  
        ImageIO.write(image, "JPEG", response.getOutputStream()) ; 
	}
	
	/**
	 * 捕捉所有经过该控制器的DuplicateNoticeException
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public String handleDuplicateNotice(Model model){
		model.addAttribute("errorMessage","未知错误");
		return "error/error";
	}
}
