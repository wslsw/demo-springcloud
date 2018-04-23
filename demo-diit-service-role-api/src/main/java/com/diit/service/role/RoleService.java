package com.diit.service.role;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.diit.common.pojo.DiitResult;
import com.diit.core.role.pojo.Role;
import com.diit.service.role.hystrix.RoleServiceHystrix;

/**
 * 角色操作相关服务
 *
 * @author lishw
 * @create 2017-01-18
 */
@FeignClient(value = "service-role",fallback = RoleServiceHystrix.class)
public interface RoleService {
	
	@RequestMapping(value = "/listUsersWithPage",method = RequestMethod.GET)
	Map<String, Object> listUsersWithPage(@RequestParam("start")Integer start, @RequestParam("length")Integer length);
	
	@RequestMapping(value = "/listRolesWithPage",method = RequestMethod.GET)
	HashMap<String, Object> listRolesWithPage(@RequestParam("start")Integer start, @RequestParam("length")Integer length);
	
	@RequestMapping(value = "/getRole",method = RequestMethod.GET)
	Role getRole(@RequestParam("id")Long id);
	
	@RequestMapping(value = "/validateRole",method = RequestMethod.GET)
	DiitResult validateRole(@RequestParam("name")String name);
	
	@RequestMapping(value = "/saveRole",method = RequestMethod.POST)
	DiitResult saveRole(@RequestBody Role role);
	
	@RequestMapping(value = "/deleteRole",method = RequestMethod.POST)
	DiitResult deleteRole(@RequestBody String ids);
}
