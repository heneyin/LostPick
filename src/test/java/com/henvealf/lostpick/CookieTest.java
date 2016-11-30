package com.henvealf.lostpick;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.henvealf.lostpick.utils.CookieUtils;

public class CookieTest {
	@Test
	public void cookie( ) {
		
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse resp = new MockHttpServletResponse();
		Cookie cc = new Cookie("test", "123");

		
		cc.setMaxAge(60);
		resp.addCookie(cc);
		
		Cookie[] cookies  = req.getCookies();
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++){
				Cookie cookie = cookies[i];
				System.out.println("cookieName: " + cookie.getName());
				if("test".equals(cookie.getName())){
					System.out.println("找到：" + cookie.getValue());
				}
			}
		}
		
		String qwe = CookieUtils.getCookieValue(req, "test", "333");
		System.out.println(qwe);
	}
}
