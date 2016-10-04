package cn.stq.learn.springboot.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	public static void doGetRequest(final String url) throws Exception{

	    // 创建HttpClient对象
	    HttpClient client = HttpClients.createDefault();

	    // 创建GET请求（在构造器中传入URL字符串即可）
	    HttpGet get = new HttpGet(url);

	    // 调用HttpClient对象的execute方法获得响应
	    HttpResponse response = client.execute(get);

	    // 调用HttpResponse对象的getEntity方法得到响应实体
	    HttpEntity httpEntity = response.getEntity();

	    // 使用EntityUtils工具类得到响应的字符串表示
	    String result = EntityUtils.toString(httpEntity,"utf-8");
	    System.out.println(result);
	}
	
	public static void doPostRequest(final String url) throws Exception{

	    // 创建HttpClient对象
	    HttpClient client = HttpClients.createDefault();

	    // 创建POST请求
	    HttpPost post = new HttpPost(url);

	    // 创建一个List容器，用于存放基本键值对（基本键值对即：参数名-参数值）
	    List<BasicNameValuePair> parameters = new ArrayList<>();
	    parameters.add(new BasicNameValuePair("name", "张三"));
	    parameters.add(new BasicNameValuePair("age", "25"));

	    // 向POST请求中添加消息实体
	    post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));

	    // 得到响应并转化成字符串
	    HttpResponse response = client.execute(post);
	    HttpEntity httpEntity = response.getEntity();
	    String result = EntityUtils.toString(httpEntity,"utf-8");
	    System.out.println(result);
	}
}
