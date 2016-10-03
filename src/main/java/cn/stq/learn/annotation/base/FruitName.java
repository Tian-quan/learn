package cn.stq.learn.annotation.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Description: 水果名称注解<br/>
 * 
 * <br/><br/>Author: tianquan.shi<tianquan.shi@msxf.com> 
 * <br/>Create Time: 2016年10月3日 上午11:43:09
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
	String value()  default "";
}
