package cn.stq.learn.apache.common.lang3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import cn.stq.learn.utils.LogUtil;

public class StringUtilsTest {
	private final Logger log = LogUtil.getAppLogger();

	@Test
	public void testIsBank() {
		assertTrue(StringUtils.isBlank(null));
		assertTrue(StringUtils.isBlank(""));
		assertTrue(StringUtils.isBlank("  "));
		assertEquals(StringUtils.isEmpty("  "),false);
	}

	@Test
	public void testIsNotBlan() {
		assertFalse(StringUtils.isNoneBlank(null));
		assertFalse(StringUtils.isNoneBlank(""));
		assertFalse(StringUtils.isNoneBlank("   "));
		assertTrue(StringUtils.isNoneBlank("a"));

	}

	@Test
	/* 测试缩写 */
	public void testAbbreviate() {
		log.info(StringUtils.abbreviate("aa", 4));
		log.info(StringUtils.abbreviate("aaaaaaaa", 4));
	}

	@Test
	// 测试首字母大写
	public void testCapitalize() {
		// StringUtils.capitalize(null) = null
		// StringUtils.capitalize("") = ""
		// StringUtils.capitalize("cat") = "Cat"
		// StringUtils.capitalize("cAt") = "CAt"
		assertNull(StringUtils.capitalize(null));
		assertEquals(StringUtils.capitalize(""), "");
		assertEquals(StringUtils.capitalize("cat"), "Cat");
	}

	@Test
	// 移除末尾间隔符
	public void testChomp() {
		assertEquals(StringUtils.chomp("aa\r\n"), "aa");
		assertEquals(StringUtils.removeEnd("aabb", "b"), "aab");
		assertEquals(StringUtils.removeEnd("aabb", "bb"), "aa");
	}

	@Test
	public void testContains() {
		assertEquals(StringUtils.contains(null, ""), false);
		assertEquals(StringUtils.contains("", null), false);
		assertEquals(StringUtils.contains("", ""), true);
		assertEquals(StringUtils.contains("abc", ""), true);
		assertEquals(StringUtils.contains("abc", "a"), true);
		assertEquals(StringUtils.contains("abc", "z"), false);
		assertEquals(StringUtils.containsAny("abcd", "ab", "cd"), true);
		assertEquals(StringUtils.containsAny("abc", "d", "abc"), true);
	}

	@Test
	public void testCountMatches() {
		// StringUtils.countMatches(null, *) = 0
		// StringUtils.countMatches("", *) = 0
		// StringUtils.countMatches("abba", 0) = 0
		// StringUtils.countMatches("abba", 'a') = 2
		// StringUtils.countMatches("abba", 'b') = 2
		// StringUtils.countMatches("abba", 'x') = 0

		assertEquals(StringUtils.countMatches(null, "*"), 0);
		assertEquals(StringUtils.countMatches("", "*"), 0);
		assertEquals(StringUtils.countMatches("abba", (char) 0), 0);
		assertEquals(StringUtils.countMatches("abba", 'a'), 2);
		assertEquals(StringUtils.countMatches("abba", 'b'), 2);
		assertEquals(StringUtils.countMatches("abba", 'x'), 0);
		assertEquals(StringUtils.countMatches("abba", "ab"), 1);

	}

	@Test
	public void testDeleteWhitespace() {
		// StringUtils.deleteWhitespace(null) = null
		// StringUtils.deleteWhitespace("") = ""
		// StringUtils.deleteWhitespace("abc") = "abc"
		// StringUtils.deleteWhitespace(" ab c ") = "abc"

		assertEquals(StringUtils.deleteWhitespace(null), null);
		assertEquals(StringUtils.deleteWhitespace(""), "");
		assertEquals(StringUtils.deleteWhitespace("abc"), "abc");
		assertEquals(StringUtils.deleteWhitespace(" ab c "), "abc");
	}

	@Test
	public void testDefaultString() {
		// StringUtils.defaultString(null) = ""
		// StringUtils.defaultString("") = ""
		// StringUtils.defaultString("bat") = "bat"
		// StringUtils.defaultString(null, "NULL") = "NULL"
		// StringUtils.defaultString("", "NULL") = ""
		// StringUtils.defaultString("bat", "NULL") = "bat"

		assertEquals(StringUtils.defaultString(null), "");
		assertEquals(StringUtils.defaultString(""), "");
		assertEquals(StringUtils.defaultString("bat"), "bat");
		assertEquals(StringUtils.defaultString(null, "NULL"), "NULL");
		assertEquals(StringUtils.defaultString("", "NULL"), "");
		assertEquals(StringUtils.defaultString("bat", "NULL"), "bat");

	}

	@Test
	public void testEquals() {
		// StringUtils.equals(null, null) = true
		// StringUtils.equals(null, "abc") = false
		// StringUtils.equals("abc", null) = false
		// StringUtils.equals("abc", "abc") = true
		// StringUtils.equals("abc", "ABC") = false

		assertEquals(StringUtils.equals(null, null), true);
		assertEquals(StringUtils.equals(null, ""), false);
		assertEquals(StringUtils.equals(null, "abc"), false);
		assertEquals(StringUtils.equals("abc", null), false);
		assertEquals(StringUtils.equals("abc", "abc"), true);
		assertEquals(StringUtils.equals("abc", "ABC"), false);
		assertEquals(StringUtils.equalsIgnoreCase("abc", "ABC"), true);
	}
	
	 @Test
	 public void testIndexOfDifference() {
		// StringUtils.indexOfDifference(null) = -1
		// StringUtils.indexOfDifference(new String[] {}) = -1
		// StringUtils.indexOfDifference(new String[] {"abc"}) = -1
		// StringUtils.indexOfDifference(new String[] {null, null}) = -1
		// StringUtils.indexOfDifference(new String[] {"", ""}) = -1
		// StringUtils.indexOfDifference(new String[] {"", null}) = 0
		// StringUtils.indexOfDifference(new String[] {"abc", null, null}) = 0
		// StringUtils.indexOfDifference(new String[] {null, null, "abc"}) = 0
		// StringUtils.indexOfDifference(new String[] {"", "abc"}) = 0
		// StringUtils.indexOfDifference(new String[] {"abc", ""}) = 0
		// StringUtils.indexOfDifference(new String[] {"abc", "abc"}) = -1
		// StringUtils.indexOfDifference(new String[] {"abc", "a"}) = 1
		// StringUtils.indexOfDifference(new String[] {"ab", "abxyz"}) = 2
		// StringUtils.indexOfDifference(new String[] {"abcde", "abxyz"}) = 2
		// StringUtils.indexOfDifference(new String[] {"abcde", "xyz"}) = 0
		// StringUtils.indexOfDifference(new String[] {"xyz", "abcde"}) = 0
		// StringUtils.indexOfDifference(new String[] {"i am a machine", "i am a robot"}) = 7

		 
		 assertEquals(StringUtils.indexOfDifference(null) , -1);
		 assertEquals(StringUtils.indexOfDifference(new String[] {}) , -1);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"abc"}) , -1);
		 assertEquals(StringUtils.indexOfDifference(new String[] {null, null}) , -1);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"", ""}) , -1);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"", null}) , 0);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"abc", null, null}) , 0);
		 assertEquals(StringUtils.indexOfDifference(new String[] {null, null, "abc"}) , 0);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"", "abc"}) , 0);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"abc", ""}) , 0);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"abc", "abc"}) , -1);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"abc", "a"}) , 1);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"ab", "abxyz"}) , 2);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"abcde", "abxyz"}) , 2);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"abcde", "xyz"}) , 0);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"xyz", "abcde"}) , 0);
		 assertEquals(StringUtils.indexOfDifference(new String[] {"i am a machine", "i am a robot"}) , 7);
	 }

	  @Test
	  public void testJoin() {
		  assertEquals(StringUtils.join(1,2), "12");
		  int[] intArr = new int[]{1,2,3};
		  assertEquals(StringUtils.join(intArr,'$'), "1$2$3");
	  }
	  
}
