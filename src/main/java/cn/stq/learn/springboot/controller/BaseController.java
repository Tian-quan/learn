package cn.stq.learn.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class BaseController {
	
	@ResponseBody
	@RequestMapping("/test")
	public String test(){
		return "hello wwww";
	}

}
