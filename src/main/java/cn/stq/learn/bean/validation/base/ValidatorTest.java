/**
 * 
 */
package cn.stq.learn.bean.validation.base;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import cn.stq.learn.utils.LogUtil;

/**
 * Description: <br/>
 * 
 * <br/>
 * <br/>
 * Author: tianquan.shi<tianquan.shi@msxf.com> <br/>
 * Create Time: 2016年10月3日 下午1:44:35
 */
public class ValidatorTest {
	private Map<String, String> valiaMap = null;
	static Logger logger = LogUtil.getAppLogger();
	@Before
	public void setup(){
		valiaMap = null;
	}
	
	//基本验证
	@Test
	public void testValidateToMap() {
		User user = new User();
		user.setUsername("");
		user.setAge(0);
		user.setPassword("1234567aaaa");
		valiaMap = ValidatorUtil.validateToMap(user);
		logger.error(JSON.toJSONString(valiaMap));
	}
	//正则表达式验证
	@Test
	public void testPatternValidateToMap() {
		User user = new User();
		user.setUsername("name");
		user.setAge(0);
		user.setPassword("1");
		valiaMap = ValidatorUtil.validateToMap(user);
		logger.error(JSON.toJSONString(valiaMap));
	}
	
	//带group方式的验证.
	@Test
	public void testValidateGroupToMap() {
		User user = new User();
		user.setUsername("");
		user.setAge(0);
		user.setPassword("");
		valiaMap = ValidatorUtil.validateGroupToMap(user, AgeValid.class);
		System.out.println(JSON.toJSONString(valiaMap));
		logger.error(JSON.toJSONString(valiaMap));
	}
	
	//级联验证
	@Test
	public void testCascadeValidate(){
		UserGoup group = new UserGoup();
		group.setGroupName("1");
		User user = new User();
		user.setUsername("");
		user.setAge(0);
		user.setPassword("");
		group.setUserGroup(Arrays.asList(user));
		valiaMap = ValidatorUtil.validateToMap(group);
		logger.error(JSON.toJSONString(valiaMap));
	}
}
