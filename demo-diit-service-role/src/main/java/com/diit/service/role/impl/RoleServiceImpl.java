package com.diit.service.role.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.diit.common.pojo.DiitResult;
import com.diit.common.wrapper.BaseMapper;
import com.diit.core.role.mapper.RoleMapper;
import com.diit.core.role.mapper.SeqMapper;
import com.diit.core.role.pojo.Role;
import com.diit.service.role.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.*;

/**
 * 角色操作相关服务 Service 实现
 *
 * @author lishw
 * @create 2017-01-18
 */
@Api(value = "API - RoleServiceImpl", description = "角色相关服务")
@RestController
@EnableHystrix
@RefreshScope
@Configuration
@MapperScan(basePackages = "com.diit.core.role.mapper", markerInterface = BaseMapper.class)
public class RoleServiceImpl implements RoleService{
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
    private RoleMapper roleMapper;
	
	@Autowired
    private SeqMapper seqMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
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
	public Map<String, Object> listUsersWithPage(Integer start, Integer length){
		
		Object[] arr = new Object[]{start, length};
		
		Map<String, Object> map = restTemplate.getForObject("http://SERVICE-USER/listUsersWithPage?start={start}&length={length}",Map.class,arr);

        return map;
	}
	
	@Override
    @ApiOperation("获取角色列表")
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
	public HashMap<String, Object> listRolesWithPage(Integer start, Integer length){
		HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(start, length);

        List<Role> list = roleMapper.selectAll();
        PageInfo<Role> pageInfo = new PageInfo<>(list);

        map.put("count", pageInfo.getTotal());//数据总条数
        map.put("data", list);//数据集合
        map.put("code", 0);
        map.put("msg", "");

        return map;
	}
	
	@Override
	@ApiOperation("获取单个角色")
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
	public Role getRole(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	
	@Override	
	@ApiOperation("验证角色")
	@ApiImplicitParams(
        {
            @ApiImplicitParam(name = "name", value = "", required = true, dataType = "String"),
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
	public DiitResult validateRole(String name) {
		Role role = new Role();
		role.setName(name);
		Role r = roleMapper.selectOne(role);
		if(r == null){
			return DiitResult.ok();
		}else{
			return DiitResult.build(304, "角色已存在!");
		}
	}

	@Override
	@ApiOperation("新增/更新角色")
	@ApiImplicitParams(
        {
            @ApiImplicitParam(name = "role", value = "", required = true, dataType = "Role"),
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
	public DiitResult saveRole(@RequestBody Role role) {
		if (role == null) {
            return DiitResult.build(400, "数据为空!");
        }
		
		if(role.getId()==null){
			//role.setId(seqMapper.getBySeqName("SEQ_DIIT_ROLE_ID"));
			role.setCreatetime(new Date());
			roleMapper.insert(role);
		}else{
			Role r = new Role();
			r.setName(role.getName());
			r = roleMapper.selectOne(r);
			if(r == null){
				r = roleMapper.selectByPrimaryKey(role);
				role.setCreatetime(r.getCreatetime());
				roleMapper.updateByPrimaryKey(role);
			}else if(r.getId()==role.getId()){
				role.setCreatetime(r.getCreatetime());
				roleMapper.updateByPrimaryKey(role);
			}else{
				return DiitResult.build(304, "角色已存在!");
			}
		}
		
		return DiitResult.ok();
	}
	
	@Override
	@ApiOperation("删除角色")
	@ApiImplicitParams(
        {
            @ApiImplicitParam(name = "ids", value = "", required = true, dataType = "String"),
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
	public DiitResult deleteRole(@RequestBody String ids) {
		roleMapper.deleteByIds(ids);
		
		return DiitResult.ok();
	}
}
