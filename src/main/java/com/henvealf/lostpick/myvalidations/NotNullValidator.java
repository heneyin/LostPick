package com.henvealf.lostpick.myvalidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 验证不能为空的验证器
 * @author Henvealf
 *
 */
public class NotNullValidator implements ConstraintValidator<MyNotNull,String>{

	@Override
	public void initialize(MyNotNull constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.equals("") || value.contains(" ")){
			return false;
		}
		return true;
	}

}
