package cn.stq.learn.bean.validation.extend.mutiValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {
	
	private String countryCode;
	
	@Override
	public void initialize(ZipCode constraintAnnotation) {
		// TODO Auto-generated method stub
		countryCode = constraintAnnotation.countryCode();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return null!=value && value.startsWith(countryCode);
	}

}
