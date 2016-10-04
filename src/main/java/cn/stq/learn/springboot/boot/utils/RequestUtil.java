package cn.stq.learn.springboot.boot.utils;

import java.lang.reflect.Field;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class RequestUtil {
	/**
	 * HttpServletRequest参数值转为JavaBean
	 * 
	 * @see 该方法目前只能处理所有属性均为String的JavaBean
	 * @see 且只能处理当前类,暂不能处理父类和子类
	 * @see 且类属性只能是String
	 * @create Dec 17, 2015 4:44:47 PM
	 * @author 玄玉<http://blog.csdn.net/jadyer>
	 */
	public static <T> T requestToBean(HttpServletRequest request, Class<T> beanClass) {
		try {
			T bean = beanClass.newInstance();
			// getFields()能获取到父类和子类中所有public的属性
			for (Field field : beanClass.getDeclaredFields()) {
				// 构造setter方法
				String methodName = "set" + StringUtils.capitalize(field.getName());
				try {
					// 执行setter方法
					beanClass.getMethod(methodName, String.class).invoke(bean,
							URLDecoder.decode(request.getParameter(field.getName()), "UTF-8"));
				} catch (Exception e) {
					// ignore exception
					continue;
				}
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
