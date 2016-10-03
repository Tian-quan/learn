package cn.stq.learn.bean.validation.extend.map;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.AssertFalse.List;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@interface MapExist {
	MapExistEntry[] value() default {};
	String message() default "";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	@Documented
	@Retention(RUNTIME)
	@Target({ FIELD, METHOD, ANNOTATION_TYPE })
	@Constraint(validatedBy = MapExistValidator.class)
	public @interface MapExistEntry {
		String key();

		String message();

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}
}
