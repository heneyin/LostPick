package com.henvealf.lostpick.myvalidations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * 只能为数字无字幕的组合
 * @author Henvealf
 *
 */
public class NumberAndAbcValidaor implements ConstraintValidator<NumberAndAbc,String>{

	private String numberAndAbcRex = "^[A-Za-z0-9]+$"; 
	@Override
	public void initialize(NumberAndAbc constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if( value == null || value == ""){
			return false;
		}
		Matcher matcher = Pattern.compile(numberAndAbcRex).matcher(value);
		return matcher.matches();
	}

}
