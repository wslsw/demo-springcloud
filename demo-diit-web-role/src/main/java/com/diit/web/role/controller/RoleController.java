package com.diit.web.role.controller;

import io.swagger.annotations.Api;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diit.common.pojo.DiitResult;
import com.diit.common.wrapper.BaseController;
import com.diit.core.role.pojo.Role;
import com.diit.service.role.RoleService;

import java.util.Map;


/**
 * Role 首页Controller
 *
 * @author lishw
 * @create 2017-01-18
 */
@Api(value = "API - RoleController", description = "角色操作控制器")
@Controller
@RefreshScope
@Configuration
@MapperScan(basePackages = "com.diit.service.role.impl")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    
    @Value("${proxy_name}")
    private String PROXY_NAME;

    private void setCtx(Model model){
    	if(StringUtils.isEmpty(PROXY_NAME)){
    		model.addAttribute("ctx", "");
    	}else{
    		model.addAttribute("ctx", "/"+PROXY_NAME);
    	}
    }
    
    @RequestMapping("/index")
    public String showIndex(Model model) {
    	setCtx(model);
    	
        return "index";
    }
    
    @RequestMapping("/role_add")
    public String role_add(Model model) {
    	setCtx(model);
    	
        return "role_add";
    }
    
    @RequestMapping("/role_edit")
    public String role_edit(Model model, @RequestParam("id") Long id) {
    	Role role = roleService.getRole(id);
    	model.addAttribute("role", role);
    	setCtx(model);
    	
    	return "role_edit";
    }
    
    @RequestMapping("/user_assign")
    public String user_assign(Model model, @RequestParam("id") Long id) {
    	setCtx(model);
    	
    	return "user_assign";
    }
    
    @RequestMapping("/role/listUsers")
    public @ResponseBody Map<String, Object> listUsersWithPage(Model model, Integer page, Integer limit) {
    	Map<String, Object> lists = null;
    	try{
    		lists = roleService.listUsersWithPage(page, limit);
    	}catch(Exception e){
    		logger.info("异常：" + e.getMessage());
    	}

        return lists;
    }
    
    /**
	 * 用户查询操作
	 **/
    @RequestMapping("/role/listRolesWithPage")
    public @ResponseBody Map<String, Object> listRolesWithPage(Model model, Integer page, Integer limit) {
    	Map<String, Object> lists = null;
    	try{
    		lists = roleService.listRolesWithPage(page, limit);
    	}catch(Exception e){
    		logger.info("异常：" + e.getMessage());
    	}

        return lists;
    }
    
    /**
	 * 用户添加操作
	 **/
	@RequestMapping(value="/role/insertRole",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public DiitResult insertRole(@RequestBody Role role) throws Exception{
		DiitResult result = new DiitResult();
		
		try{
			result = roleService.validateRole(role.getName());
			if(result.isSuccess()){
				result = roleService.saveRole(role);
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return result;
	}
	
	/**
	 * 用户更新操作
	 **/
	@RequestMapping(value="/role/updateRole",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public DiitResult updateRole(@RequestBody Role role) throws Exception{
		DiitResult result = new DiitResult();
		
		try{
			result = roleService.saveRole(role);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 用户删除操作
	 **/
	@RequestMapping(value="/role/deleteRole",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public DiitResult deleteRole(String ids) throws Exception{
		DiitResult result = new DiitResult();
		
		try{
			result = roleService.deleteRole(ids);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


}
