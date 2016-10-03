package cn.stq.learn.annotation.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Description: <br/>
 * 水果颜色注解
 * <br/><br/>Author: tianquan.shi<tianquan.shi@msxf.com> 
 * <br/>Create Time: 2016年10月3日 上午11:53:28
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
	/*颜色枚举*/
	public enum Color{BLUE,RED,GREEN};
	
	/*颜色属性*/
	Color fruitColor() default Color.GREEN; 

}
