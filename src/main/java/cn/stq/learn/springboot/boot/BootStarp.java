package cn.stq.learn.springboot.boot;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import cn.stq.learn.springboot.boot.filter.RequestFilter;

@SpringBootApplication(scanBasePackages={"cn.stq.learn.springboot"})
public class BootStarp  {


	@Bean
	@Order(2)
	public Filter characterEncodingFilter(){
		return new CharacterEncodingFilter("UTF-8", true);
	}

	@Bean
	@Order(3)
	public Filter apiFilter(){
		//过滤调用API的请求
		return new RequestFilter("/api");
	}

}
