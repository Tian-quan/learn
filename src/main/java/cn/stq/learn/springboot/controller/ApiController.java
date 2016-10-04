package cn.stq.learn.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	
	@ResponseBody
	@RequestMapping(value="/test",method={RequestMethod.POST,RequestMethod.GET})
	public String test(){
		return "1231231";
	}

}
