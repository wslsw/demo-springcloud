package com.diit.service.user;

import java.util.HashMap;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.diit.common.pojo.DiitResult;
import com.diit.core.user.pojo.User;
import com.diit.service.user.hystrix.UserServiceHystrix;

/**
 * 用户操作相关服务
 *
 * @author lishw
 * @create 2017-01-18
 */
@FeignClient(value = "service-user", fallback = UserServiceHystrix.class)
public interface UserService {
	
	@RequestMapping(value = "/listUsersWithPage",method = RequestMethod.GET)
	HashMap<String, Object> listUsersWithPage(@RequestParam("start")Integer start, @RequestParam("length")Integer length);
	
	@RequestMapping(value = "/getUserByAccount",method = RequestMethod.GET)
	public User getUserByAccount(@RequestParam("account")String account);
	
	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
	User getUser(@RequestParam("id")Long id);
	
	@RequestMapping(value = "/validateUser",method = RequestMethod.GET)
	DiitResult validateUser(@RequestParam("account")String account);
	
	@RequestMapping(value = "/saveUser",method = RequestMethod.POST)
	DiitResult saveUser(@RequestBody User user);
	
	@RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
	DiitResult deleteUser(@RequestBody Long[] ids);
}
