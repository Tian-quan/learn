/**
 * 
 */
package cn.stq.learn.annotation.base;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Description: <br/>
 * 水果供应商
 * <br/><br/>Author: tianquan.shi<tianquan.shi@msxf.com> 
 * <br/>Create Time: 2016年10月3日 上午11:57:57
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface FruitProvider {
	
	public int id() default -1;
	
	public String name() default "";
	
	public String address() default "";
}
