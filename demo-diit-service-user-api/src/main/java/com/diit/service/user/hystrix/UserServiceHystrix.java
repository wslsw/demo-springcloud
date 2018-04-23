package com.diit.service.user.hystrix;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.diit.common.pojo.DiitResult;
import com.diit.core.user.pojo.User;
import com.diit.service.user.UserService;

/**
 * 用户操作熔断处理
 *
 * @author lishw
 * @create 2017-01-18
 */
@Component
public class UserServiceHystrix implements UserService{
	
	@Override
	public HashMap<String, Object> listUsersWithPage(Integer start, Integer length){
		return null;
	}
	
	@Override
	public User getUserByAccount(String account){
		return null;
	}
	
	@Override
	public User getUser(Long id) {
		return null;
	}
	
	@Override
	public DiitResult validateUser(String account) {
		return DiitResult.build(500, "服务调用异常!");
	}

	@Override
	public DiitResult saveUser(User user) {
		return DiitResult.build(500, "服务调用异常!");
	}

	@Override
	public DiitResult deleteUser(Long[] ids) {
		return DiitResult.build(500, "服务调用异常!");
	}
	
}
