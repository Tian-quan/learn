package cn.stq.learn.springboot.test;

import java.util.Date;

import org.junit.Test;

import cn.stq.learn.springboot.httpclient.HttpUtil;

public class HttpUtilTest {

	@Test
	public void testGet() {
		String url = "http://127.0.0.1:8888/api/test";
		try {
			HttpUtil.doGetRequest(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	@Test
	public void testPost() {
		String url = "http://127.0.0.1:8888/api/test";
		try {
			HttpUtil.doPostRequest(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void tt(){
		System.out.println(String.format("%tF %<tT", new Date()));
	}
}
