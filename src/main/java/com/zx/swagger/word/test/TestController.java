package com.zx.swagger.word.test;


import com.zx.swagger.word.test.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(description = "swagger 测试控制器", tags = {"阿萨德"})
public class TestController {

	@ResponseBody
	@ApiOperation(value = "测试1方法value", notes = "测试1方法notes")
	@RequestMapping(value = "/test1", method = RequestMethod.POST)
	public User test1(@RequestBody User user) {
		System.out.println("user:::" + user.toString());
		return user;
	}

	@ResponseBody
	@ApiOperation(value = "测试2方法value", notes = "测试2方法notes")
	@RequestMapping(value = "/test2", method = RequestMethod.POST)
	public String test2(@RequestParam String str, @RequestParam String str2) {
		System.out.printf(str);
		return str;
	}

}
