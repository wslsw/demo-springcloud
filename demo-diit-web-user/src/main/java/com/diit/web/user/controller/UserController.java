package com.diit.web.user.controller;

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
import com.diit.core.user.pojo.User;
import com.diit.service.user.UserService;
import com.diit.web.user.vo.ManageUserVO;

import java.util.Date;
import java.util.Map;


/**
 * User 首页Controller
 *
 * @author lishw
 * @create 2017-01-18
 */
@Api(value = "API - UserController", description = "用户操作控制器")
@Controller
@RefreshScope
@Configuration
@MapperScan(basePackages = "com.diit.service.user.impl")
public class UserController {

    @Autowired
    private UserService userService;

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
        ManageUserVO userVO = new ManageUserVO();
        userVO.setCreatetime(new Date());
        userVO.setName("张三");
        userVO.setRemark("CEO");

        model.addAttribute("user", userVO);
        setCtx(model);
        
        return "index";
    }
    
    @RequestMapping("/user_add")
    public String user_add(Model model) {
    	setCtx(model);
    	
        return "user_add";
    }
    
    @RequestMapping("/user_edit")
    public String user_edit(Model model, @RequestParam("id") Long id) {
    	User user = userService.getUser(id);
    	model.addAttribute("user", user);
    	setCtx(model);
    	
    	return "user_edit";
    }
    
    /**
	 * 用户查询操作
	 **/
    @RequestMapping("/user/listUsersWithPage")
    public @ResponseBody Map<String, Object> listUsersWithPage(Model model, Integer page, Integer limit) {

        Map<String, Object> lists = userService.listUsersWithPage(page, limit);

        return lists;
    }
    
    /**
	 * 用户添加操作
	 **/
	@RequestMapping(value="/user/insertUser",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public DiitResult insertUser(@RequestBody User user) throws Exception{
		DiitResult result = new DiitResult();
		
		try{
			result = userService.validateUser(user.getAccount());
			if(result.isSuccess()){
				result = userService.saveUser(user);
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return result;
	}
	
	/**
	 * 用户更新操作
	 **/
	@RequestMapping(value="/user/updateUser",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public DiitResult updateUser(@RequestBody User user) throws Exception{
		DiitResult result = new DiitResult();
		
		try{
			result = userService.saveUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 用户删除操作
	 **/
	@RequestMapping(value="/user/deleteUser",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public DiitResult deleteUser(@RequestBody Long[] ids) throws Exception{
		DiitResult result = new DiitResult();
		
		try{
			result = userService.deleteUser(ids);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


}
