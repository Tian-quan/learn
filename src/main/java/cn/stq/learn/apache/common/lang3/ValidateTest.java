package cn.stq.learn.apache.common.lang3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import cn.stq.learn.utils.LogUtil;

public class ValidateTest {
	private final Logger log = LogUtil.getAppLogger();
	@Test
	public void testParttern1(){
			log.info(StringEscapeUtils.escapeJson("{'a\':\'b\'}"));
	}
	@Test
	public void testParttern(){
		try {
			Validate.matchesPattern("123a", "\\d+","只能是纯数字");
			//fail("should throw exception");
		} catch (Exception e) {
			log.info(e.getMessage());
			assertEquals(e.getClass(), IllegalArgumentException.class);
		}
	}
	
	@Test
	public void testNotNull() {
		try {
			String a= null;
			Validate.notNull(a,"不能为null.");
			fail("should throw exception");
		} catch (Exception e) {
			assertEquals(e.getClass(), NullPointerException.class);
		}
	}
	

}
