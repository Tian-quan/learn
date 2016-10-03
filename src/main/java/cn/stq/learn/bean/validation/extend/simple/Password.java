/**
 * 
 */
package cn.stq.learn.bean.validation.extend.simple;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
/**
 * @update 
 * @create 2016年10月3日 下午4:04:56
 * @author tianquan.shi<tianquan.shi@msxf.com> 
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {
	
	public String pattern() default "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}";
	
	String message() default "密码不合法.";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
}
