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
	@RequestMapping(value = "/test2")
	public User test2(@RequestParam String str, @RequestBody User user, @RequestBody User use2r) {
		return user;
	}

	@ResponseBody
	@ApiOperation(value = "测试3方法value", notes = "测试3方法notes")
	@RequestMapping(value = "/test3")
	public String test3(@RequestParam int ok) {
		return null;
	}

	@ResponseBody
	@ApiOperation(value = "测试4方法value", notes = "测试4方法notes")
	@RequestMapping(value = "/test4")
	public void test4() {
	}

}
