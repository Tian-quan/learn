package cn.stq.learn.apache.common.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import cn.stq.learn.utils.TestUtil;

public class BeanUtilsTest {


	@Test
	public void testMap2Bean() {
		Person wife = new Person();
		wife.setName("wife");
		wife.setAge(24);

		Person husband = new Person();
		husband.setName("husband");
		husband.setAge(24);
		husband.setWife(wife);

		Map<String, String> map = null;
		try {
			map = BeanUtils.describe(husband);
			TestUtil.outAsJSON(map);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		Person husbandFromMap = new Person();
		try {
			BeanUtils.populate(husbandFromMap, map);
			TestUtil.outAsJSON(husbandFromMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testBean2Map() {
		Person wife = new Person();
		wife.setName("wife");
		wife.setAge(24);

		Person husband = new Person();
		husband.setName("husband");
		husband.setAge(24);
		husband.setWife(wife);

		try {
			Map<String, String> map = BeanUtils.describe(husband);
			System.out.println(TestUtil.formatJSON(map));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static class Person {
		String name;
		int age;
		Person wife;

		public Person getWife() {
			return wife;
		}

		public void setWife(Person wife) {
			this.wife = wife;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}

	}
}
