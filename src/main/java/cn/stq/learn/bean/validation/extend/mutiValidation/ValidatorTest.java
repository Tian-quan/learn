/**
 * 
 */
package cn.stq.learn.bean.validation.extend.mutiValidation;

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

import cn.stq.learn.bean.validation.base.ValidatorUtil;
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
	private Map<String, String> validMap = null;
	static Logger logger = LogUtil.getAppLogger();
	@Before
	public void setup(){
		validMap = null;
	}
	
	//基本验证
	@Test
	public void testValidateToMap() {
		Address address = new Address();
		address.setZipcodes("1231456");
		validMap = ValidatorUtil.validateToMap(address);
		logger.error(JSON.toJSONString(validMap));
	}
	

}
