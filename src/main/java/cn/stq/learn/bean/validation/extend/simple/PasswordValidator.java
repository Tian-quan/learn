/**
 * 
 */
package cn.stq.learn.bean.validation.extend.simple;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @update 
 * @create 2016年10月3日 下午4:09:01
 * @author tianquan.shi<tianquan.shi@msxf.com> 
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {
	private String pattern;
	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(Password constraintAnnotation) {
		pattern = constraintAnnotation.pattern();
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return null != value && value.matches(pattern);
	}

}
