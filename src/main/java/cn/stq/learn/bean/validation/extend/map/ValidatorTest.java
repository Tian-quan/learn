/**
 * 
 */
package cn.stq.learn.bean.validation.extend.map;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

import cn.stq.learn.base.utils.LogUtil;
import cn.stq.learn.bean.validation.base.ValidatorUtil;

/**
 * Description: <br/>
 * 
 * <br/>
 * <br/>
 * Author: tianquan.shi<tianquan.shi@msxf.com> <br/>
 * Create Time: 2016年10月3日 下午1:44:35
 */
public class ValidatorTest {
	private Map<String, String> validMap = null;
	static Logger logger = LogUtil.getAppLogger();
	@Before
	public void setup(){
		validMap = null;
	}
	
	//基本验证
	@Test
	public void testValidateToMap() {
		Map<String, String> map = new HashMap<>();
		User user = new User();
		user.setMap(map);
		map.put("key1", "123");
		validMap=ValidatorUtil.validateToMap(user);
		logger.error(JSON.toJSONString(validMap));
	}
	//基本验证
	@Test  
	public void testValidateToMap1() {
		Map<String, String> map = new HashMap<>();
		User user = new User();
		user.setMap(map);
		validMap=ValidatorUtil.validateToMap(user);
		logger.error(JSON.toJSONString(validMap));
	}

}
