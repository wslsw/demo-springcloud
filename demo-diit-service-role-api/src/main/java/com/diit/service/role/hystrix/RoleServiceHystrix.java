package com.diit.service.role.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.diit.common.pojo.DiitResult;
import com.diit.core.role.pojo.Role;
import com.diit.service.role.RoleService;

/**
 * 角色操作熔断处理
 *
 * @author lishw
 * @create 2017-01-18
 */
@Component
public class RoleServiceHystrix implements RoleService{
	
	@Override
	public Map<String, Object> listUsersWithPage(Integer start, Integer length){
		return null;
	}
	
	@Override
	public HashMap<String, Object> listRolesWithPage(Integer start, Integer length){
		return null;
	}
	
	@Override
	public Role getRole(Long id) {
		return null;
	}
	
	@Override
	public DiitResult validateRole(String name) {
		return DiitResult.build(500, "服务调用异常!");
	}

	@Override
	public DiitResult saveRole(Role role) {
		return DiitResult.build(500, "服务调用异常!");
	}

	@Override
	public DiitResult deleteRole(String ids) {
		return DiitResult.build(500, "服务调用异常!");
	}
	
}
