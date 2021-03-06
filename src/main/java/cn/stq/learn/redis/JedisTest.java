package cn.stq.learn.redis;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.stq.learn.utils.TestUtil;
import redis.clients.jedis.Jedis;

/**
 * 学习redis基本知识.
 * 
 * @update
 * @create 2016年10月19日 下午2:33:38
 * @author tianquan.shi<tianquan.shi@msxf.com>
 */
public class JedisTest {

	private static Jedis jedis = null;
	
	
	@Before
	public void before() {
		//DO 连接Redis,默认端口是6379
		jedis = new Jedis("127.0.0.1");
		assertEquals("PONG", jedis.ping());
	}

	@Test
	public void  testConcurrency() {
		// TODO Auto-generated method stub
		Counter counter = new Counter();
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int t = counter.getCount();
//				if(t >= 0)
				{
					System.out.println(t);
				};
			}
		};
		for(int i=0; i<100;i++){
			new Thread(r).start();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static class Counter{
		int c=0;
		Jedis jedis = new Jedis("127.0.0.1");
		
		public int getCount() {
			/*int tmp = c++;
			return tmp;*/
			
			final String key = "counter";
			String setnxResult = null;
			try {
				setnxResult = jedis.set(key, "1", "NX", "EX", 3L);
			} catch (Exception e) {
				//System.out.println(e.getMessage());
				return -101;
			}
			//1 if the key was set; 0 if the key was not set
			if(StringUtils.equals(setnxResult, "OK")){
				//System.out.println(TestUtil.getFunName()+"  "+ Thread.currentThread().getName()+"--0");
				int tmp = c;
				c++;
//				jedis.expire(key, 1);
				return tmp;
			}
			if(StringUtils.isEmpty(setnxResult)){
				//System.out.println(TestUtil.getFunName()+"  "+ Thread.currentThread().getName()+"--1");
//				jedis.expire(key, 1);
				return 1000;
			}
			return -102;
		}
	}
	
	@Test
	public void tt() throws InterruptedException{
		final String key = "key";
		System.out.println(jedis.set("key", "1", "NX", "EX", 5L));
		System.out.println(jedis.set("key", "1", "NX", "EX", 5L));

		for(int i=0;i<20;++i){
			if(!jedis.exists(key)){
				System.out.println(key+" is expired,and it is not existed any more.");
				break;
			}
			System.out.println(jedis.ttl(key));
			Thread.sleep(1000);
		}
	}
	/**
	 * 测试设置键值过期时间.
	 * @throws InterruptedException
	 */
	@Test
	public void testExpire() throws InterruptedException {
		final String key = "expiredKey";
		if(jedis.exists(key)){
			jedis.del(key);
		}
		jedis.set(key,"val");		
		
		jedis.expire(key, 5);
		for(int i=0;i<20;++i){
			if(!jedis.exists(key)){
				System.out.println(key+" is expired,and it is not existed any more.");
				break;
			}
			System.out.println(jedis.ttl(key));
			Thread.sleep(1000);
		}
	}
	
	@Test 
	public void testHash1(){
		final String key = "hashKey1";
		if(!jedis.exists(key)){
			jedis.hset(key, "name", "aa");
			jedis.hset(key, "age", "11");
		}
		Map<String, String> map = new HashMap<>();
		map.put("name", "newName");
		jedis.hmset(key, map);
		System.out.println(JSON.toJSONString(jedis.hgetAll(key)));
	}
	
	
	
	/**
	 * 测试HashMap
	 */
	@Test
	public void tesHash0(){
		final String key = "hashKey";
		final String name = "sky";
		final String age = "25";
		if(jedis.exists(key)){
			jedis.del(key);
		}
		jedis.hset(key, "name", name);
		jedis.hset(key, "age", age);
		
		System.out.println(JSON.toJSONString(jedis.hgetAll(key)));
		System.out.println("name->"+jedis.hget(key, "name"));
		System.out.println("age->"+jedis.hget(key, "age"));
	}
	
	/**
	 * 测试列表
	 */
	@Test
	public void tesList(){
		final String key = "tutorial-list";
		if(jedis.exists(key)){
			jedis.del(key);
		}
		//存储数据到列表中
	      jedis.lpush(key, "Redis");
	      jedis.lpush(key, "Mongodb");
	      jedis.lpush(key, "Mysql");
	     // 获取存储的数据并输出
	     List<String> list = jedis.lrange(key, 0 ,5);
	     System.out.println(jedis.type(key));
	     for(int i=0; i<list.size(); i++) {
	       System.out.println("Stored string in redis:: "+list.get(i));
	     }
	}
	
	
	/**
	 * 测试简单string键值对
	 */
	@Test
	public void testStringPush() {
		final String key = "key";
		final String val = "val";
		// 设置 redis 字符串数据
		jedis.set(key, val);
		// 获取存储的数据并输出
		System.out.println(jedis.get(key));
		assertEquals(val, jedis.get(key));
	}

	/**
	 * 测试redis连接.
	 */
	@Test
	public void testPing() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("127.0.0.1");
		// 查看服务是否运行
		System.out.println("Server is running: " + jedis.ping());
		assertEquals("PONG", jedis.ping());
	}

}
