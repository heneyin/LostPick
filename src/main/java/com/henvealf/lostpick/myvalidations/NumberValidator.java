package com.henvealf.lostpick.myvalidations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 判断String是否为纯数字
 * @author Henvealf
 *
 */
public class NumberValidator implements ConstraintValidator<Number,String>{

	private String numberReg = "^[1-9]\\d*$";
	
	@Override
	public void initialize(Number constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if( value == null || value == ""){
			return false;
		}
		Matcher matcher = Pattern.compile(numberReg).matcher(value);
		return matcher.matches();
	}
	

}
