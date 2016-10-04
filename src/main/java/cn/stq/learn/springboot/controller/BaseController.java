package cn.stq.learn.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class BaseController {
	
	@Value("${greeting}")
	String greeting;
	
	@ResponseBody
	@RequestMapping("/greeting")
	public String test(){
		return greeting;
	}

}
