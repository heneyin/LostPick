package com.henvealf.lostpick;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.Test;

import com.henvealf.lostpick.utils.ImageUtils;

public class OtherTest {
	@Test
	public void filePathNameTest(){
		String path = ImageUtils.makePathWithFileName("我和你.jsp", "1","lande","hah");
		System.out.println(path);

	}
	
	@Test
	public void cutcutString(){
		String upInfo = "123_456#/41111";
		String noticeIdStr = upInfo.substring(0, upInfo.indexOf('_'));
		String userId = upInfo.substring(upInfo.indexOf('_')+1, upInfo.indexOf('#'));
		
		String backUri = upInfo.substring(upInfo.indexOf('#')+1,upInfo.length());
		System.out.println("backUri"+backUri);
		System.out.println( upInfo.indexOf('_'));
		System.out.println(noticeIdStr);
		System.out.println("#"+upInfo.indexOf('#'));
		System.out.println("userId" + userId);
		
		System.out.println(userId);
	}
	
	private String numberReg = "^[1-9]\\d*$";
	@Test
	public void numberValifation() {
		
		Matcher matcher = Pattern.compile(numberReg).matcher("12345_");
		
		System.out.println(matcher.matches()); 
	}
	
	
}
