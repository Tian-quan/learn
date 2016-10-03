/**
 * 
 */
package cn.stq.learn.annotation.base;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Description: <br/>
 * 
 * <br/><br/>Author: tianquan.shi<tianquan.shi@msxf.com> 
 * <br/>Create Time: 2016年10月3日 下午12:03:53
 */
public class AppleTest {

	/**
	 * Test method for {@link cn.stq.learn.annotation.base.Apple#toString()}.
	 */
	@Test
	public void testToString() {
		Apple apple = new Apple();
		System.out.println(apple);
		FruitAnnotationProcessor.processAnnotation(apple);
		System.out.println(apple);
	}

}
