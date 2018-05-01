package com.diit.web.portal.controller;

import com.diit.common.utils.CookieUtils;
import com.diit.common.utils.JedisClient;
import com.diit.common.wrapper.BaseController;
import com.diit.core.user.pojo.User;
import com.diit.service.portal.LoginService;
import com.diit.common.constant.Const;
import com.diit.common.pojo.DiitResult;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

/**
 * 用户登录注册 Controller
 *
 * @author lisw
 * @create 2017-01-18
 */
@Api(value = "API - LoginController", description = "portal控制器")
@Controller
@RefreshScope
@Configuration
@MapperScan(basePackages = "com.diit.service.portal.impl")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JedisClient jedisClient;

    @Value("${redisKey.expire_time}")
    private Integer EXPIRE_TIME;

    @Value("${proxy_name}")
    private String PROXY_NAME;
    
    @Value("${home_name}")
    private String HOME_NAME;

    @Value("${portal_path}")
    private String PORTAL_PATH;
    
    private void setCtx(Model model){
    	if(StringUtils.isEmpty(PROXY_NAME)){
    		model.addAttribute("ctx", "");
    	}else{
    		model.addAttribute("ctx", "/"+PROXY_NAME);
    	}
    }
    
    private void setHome(Model model){
    	if(StringUtils.isEmpty(HOME_NAME)){
    		model.addAttribute("home", "");
    	}else{
    		model.addAttribute("home", "/"+HOME_NAME);
    	}
    }

    @RequestMapping(value = "/to_login",method = RequestMethod.GET)
    public String to_login(Model model){
    	setCtx(model);
    	
        return "login";
    }
    
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){
    	//String name = CookieUtils.getCookieValue(request, Const.USER_NAME);
        String name = jedisClient.get(Const.USER_NAME);
    	model.addAttribute("name", name);
    	setCtx(model);
    	setHome(model);
    	
        return "index";
    }
    
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response){
    	//CookieUtils.deleteCookie(request, response, Const.TOKEN_LOGIN);
        jedisClient.del(Const.TOKEN_LOGIN);
    	setCtx(model);
    	
        return "login";
    }
    
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegister(Model model, String returnUrl) {
        model.addAttribute("uid", UUID.randomUUID().toString());
        setCtx(model);
        
        return "register";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLogin(Model model, String returnUrl) {
        model.addAttribute("returnUrl", returnUrl);
        setCtx(model);
        
        return "login";
    }

    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String showSuccess(String username, Model model) {
        model.addAttribute("username", username);
        setCtx(model);
        
        return "success";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public @ResponseBody String login(User user, String returnUrl, HttpServletRequest request, HttpServletResponse response) {
    	DiitResult result = null;
    	try{
    		result = loginService.login(user);
    	}catch(Exception e){
    		logger.info("异常：" + e.getMessage());
    	}
        if (result!=null&&result.isSuccess()) {
        	//CookieUtils.setCookie(request, response, Const.TOKEN_LOGIN, result.getMessage());
        	//CookieUtils.setCookie(request, response, Const.USER_NAME, result.getData().toString());
        	//request.getSession().setAttribute("name",result.getData().toString());
            jedisClient.set(Const.TOKEN_LOGIN, result.getMessage());
            jedisClient.expire(Const.TOKEN_LOGIN, EXPIRE_TIME);
            jedisClient.set(Const.USER_NAME, result.getData().toString());
            jedisClient.expire(Const.USER_NAME, EXPIRE_TIME);
        	
            //有返回URL 跳转
            if (StringUtils.isNotBlank(returnUrl)) {
                return "({'success':'" + returnUrl + "'})";
            }
            return PORTAL_PATH;
        }
        String err = result==null?"error":result.getMessage();
        return "({'msg':'" + err + "'})";

    }

    @RequestMapping(value = "/loginservice")
    public @ResponseBody String valida(String callback, String method, Integer uid) {
        return callback + "({\"Identity\":{\"Unick\":\"\",\"Name\":\"\",\"IsAuthenticated\":false}})";
    }

    /**
     * 验证用户名、邮箱、电话是否重复
     * @param isEngaged 检测的名称
     * @param regName 用户名
     * @param email 邮箱
     * @param phone 电话
     * @return
     */
    @RequestMapping("/validateuser/{isEngaged}")
    public @ResponseBody String validateUser(
            @PathVariable("isEngaged")          String isEngaged,
            @RequestParam(defaultValue = "")    String regName,
            @RequestParam(defaultValue = "")    String email,
            @RequestParam(defaultValue = "")    String phone) {

        return loginService.validateUser(isEngaged, regName, email, phone);
    }

    /**
     * 验证码判断
     * @param authCode 判断验证码是否正确
     * @param uuid
     * @return
     */
    @RequestMapping("/validate/validateAuthCode")
    public @ResponseBody String validateUser(String authCode, String uuid) {
        return loginService.validateAuthCode(authCode, uuid);
    }

    /**
     * 请求格式 POST
     * 注册 不使用邮箱注册
     *
     * @param regName       注册名
     * @param pwd           第一次密码
     * @param pwdRepeat     第二次密码
     * @param phone         电话
     * @param mobileCode    手机验证码
     * @param authCode      输入的验证码
     * @param uuid          Redis验证码uuid
     * @return
     */
    @RequestMapping("/register/regService")
    public @ResponseBody String regService(String regName, String pwd, String pwdRepeat, String phone, String mobileCode, String authCode, String uuid) {
        return loginService.register(regName, pwd, pwdRepeat, phone, mobileCode, uuid,authCode, "");
    }
    /**
     * 请求格式 POST
     * 注册 使用邮箱注册
     *
     * @param regName       注册名
     * @param pwd           第一次密码
     * @param pwdRepeat     第二次密码
     * @param phone         电话
     * @param mobileCode    手机验证码
     * @param email         邮箱
     * @param authCode      输入的验证码
     * @param uuid          Redis验证码uuid
     * @return
     */
    @RequestMapping("/register/sendRegEmail")
    public @ResponseBody String sendRegEmail(String regName, String pwdRepeat, String pwd, String phone, String mobileCode, String uuid, String authCode, String email) {
        return loginService.register(regName, pwd, pwdRepeat, phone, mobileCode, uuid, authCode, email);
    }

}
