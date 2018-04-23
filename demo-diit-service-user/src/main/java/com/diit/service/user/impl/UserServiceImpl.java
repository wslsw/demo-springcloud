package com.diit.service.user.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.diit.common.pojo.DiitResult;
import com.diit.core.user.mapper.UserMapper;
import com.diit.core.user.pojo.User;
import com.diit.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.*;

/**
 * 用户操作相关服务 Service 实现
 *
 * @author lishw
 * @create 2017-01-18
 */
@Api(value = "API - UserServiceImpl", description = "用户相关服务")
@RestController
@EnableHystrix
@RefreshScope
@Configuration
@MapperScan(basePackages = "com.diit.core.user.mapper")
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    private UserMapper userMapper;
	
    @Autowired
    private DiscoveryClient client;
	
	@Override
    @ApiOperation("获取用户列表")
	@ApiImplicitParams(
        {
            @ApiImplicitParam(name = "start", value = "", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "length", value = "", required = true, dataType = "Integer")
        }
    )
	@ApiResponses(
        {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；资源不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")
        }
    )
	public HashMap<String, Object> listUsersWithPage(Integer start, Integer length){
		HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(start, length);

        List<User> list = userMapper.listAll();
        PageInfo<User> pageInfo = new PageInfo<>(list);

        map.put("count", pageInfo.getTotal());//数据总条数
        map.put("data", list);//数据集合
        map.put("code", 0);
        map.put("msg", "");

        return map;
	}
	
	@Override
	@ApiOperation("获取单个用户")
	@ApiImplicitParams(
        {
            @ApiImplicitParam(name = "id", value = "", required = true, dataType = "Long"),
        }
    )
	@ApiResponses(
        {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；资源不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")
        }
    )
	public User getUser(Long id) {
		return userMapper.getUserById(id);
	}
	
	@Override
	@ApiOperation("获取单个用户")
	@ApiImplicitParams(
        {
            @ApiImplicitParam(name = "account", value = "", required = true, dataType = "String"),
        }
    )
	@ApiResponses(
        {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；资源不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")
        }
    )
	public User getUserByAccount(String account){
		/*ServiceInstance instance = client.getLocalServiceInstance();
		User u = new User();
		u.setAccount("admin");
		u.setName("管理员");
		u.setPassword("c4ca4238a0b923820dcc509a6f75849b");
		Short status = 1;
		u.setStatus(status);
		int sleep = new Random().nextInt(2000);
		logger.info("sleepTime: "+sleep);
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("/getUserByAccount, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        
		return u;*/
		return userMapper.getUserByAccount(account);
	}
	
	@Override
	@ApiOperation("验证用户")
	@ApiImplicitParams(
        {
            @ApiImplicitParam(name = "account", value = "", required = true, dataType = "String"),
        }
    )
	@ApiResponses(
        {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；资源不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")
        }
    )
	public DiitResult validateUser(String account) {
		User user = userMapper.getUserByAccount(account);
		if(user == null){
			return DiitResult.ok();
		}else{
			return DiitResult.build(304, "用户已存在!");
		}
	}

	@Override
	@ApiOperation("新增/更新用户")
	@ApiImplicitParams(
        {
            @ApiImplicitParam(name = "user", value = "", required = true, dataType = "User"),
        }
    )
	@ApiResponses(
        {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；资源不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")
        }
    )
	@Transactional
	public DiitResult saveUser(@RequestBody User user) {
		if (user == null) {
            return DiitResult.build(400, "数据为空!");
        }
		
		if(user.getId()==null){
			user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			userMapper.insertUser(user);
		}else{
			User u = userMapper.getUserById(user.getId());
			if(!u.getPassword().equals(user.getPassword())){
				user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			}
			userMapper.updateUser(user);
		}
		
		return DiitResult.ok();
	}
	
	@Override
	@ApiOperation("删除用户")
	@ApiImplicitParams(
        {
            @ApiImplicitParam(name = "ids", value = "", required = true, dataType = "Long[]"),
        }
    )
	@ApiResponses(
        {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；资源不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")
        }
    )
	public DiitResult deleteUser(@RequestBody Long[] ids) {
		userMapper.deleteBatch(ids);
		
		return DiitResult.ok();
	}
}
