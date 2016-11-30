package com.henvealf.lostpick.myvalidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEquelsValidaor implements ConstraintValidator<NotEquels,String>{
	
	private String value;

	@Override
	public void initialize(NotEquels constraintAnnotation) {
		// TODO Auto-generated method stub
		this.value = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("传来的是"+value);
		if(value.equals(this.value)){
			return false;
		}
		return true;
	}

}
