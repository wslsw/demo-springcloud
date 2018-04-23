package com.diit.service.portal;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.diit.common.pojo.DiitResult;
import com.diit.core.user.pojo.User;
import com.diit.service.portal.hystrix.LoginServiceHystrix;


/**
 * 用户登录相关服务
 *
 * @author lishw
 * @create 2017-01-18
 */
@FeignClient(value = "service-portal", fallback = LoginServiceHystrix.class)
public interface LoginService {

    /**
     * 请求格式 POST
     * 用户登录
     *
     * @param user User POJO Json
     * @return {
     *           status: 200 //200 成功 400 登录失败 500 系统异常
     *           msg: "OK" //错误 用户名或密码错误,请检查后重试.
     *           data: "fe5cb546aeb3ce1bf37abcb08a40493e" //登录成功，返回token
     *         }
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    DiitResult login(@RequestBody User user);

    /**
     * 请求格式 GET
     * 根据token值获取用户信息
     *
     * @param token token值
     * @param callback 可选参数 有参表示jsonp调用
     * @return {
     *           status: 200 // 200 成功 400 没有此token 500 系统异常
     *           msg: "OK" // ERROR 没有此token.
     *           data: {"username":"admin"} //返回用户名
     *         }
     */
    @RequestMapping(value = "/token",method = RequestMethod.GET)
    DiitResult token(
            @RequestParam("token")      String token,
            @RequestParam("callback")   String callback
    );

    /**
     * 请求格式 GET
     * 根据token值 退出登录
     *
     * @param token token值
     * @param callback 可选参数 有参表示jsonp调用
     * @return {
     *           status: 200 //200 成功 400 没有此token 500 系统异常
     *           msg: "OK" //错误 没有此token.
     *           data: null
     *         }
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    DiitResult logout(
            @RequestParam("token")      String token,
            @RequestParam("callback")   String callback
    );

    /**
     * 请求格式 POST
     * 注册检查是否可用
     *
     * @param isEngaged 需要检查是否使用的名称
     * @return {
     *           "success": 0 可用 1 不可用
     *           "morePin":["sssss740","sssss5601","sssss76676"] //isEngaged = isPinEngaged时返回推荐
     *         }
     */
    @RequestMapping(value = "/validateUser",method = RequestMethod.POST)
    String validateUser(
            @RequestParam("isEngaged")  String isEngaged,
            @RequestParam("regName")    String regName,
            @RequestParam("email")      String email,
            @RequestParam("phone")      String phone
    );

    /**
     * 请求格式 POST
     * 验证验证码
     *
     * @param authCode 输入的验证码
     * @param uuid Redis验证码uuid
     * @return {
     *           "success": 0 可用 1 不可用
     *         }
     */
    @RequestMapping(value = "/validateAuthCode",method = RequestMethod.POST)
    String validateAuthCode(
            @RequestParam("authCode")   String authCode,
            @RequestParam("uuid")       String uuid);

    /**
     * 请求格式 POST
     * 注册
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
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    String register(
            @RequestParam("regName")    String regName,
            @RequestParam("pwd")        String pwd,
            @RequestParam("pwdRepeat")  String pwdRepeat,
            @RequestParam("phone")      String phone,
            @RequestParam("mobileCode") String mobileCode,
            @RequestParam("uuid")       String uuid,
            @RequestParam("authCode")   String authCode,
            @RequestParam("email")      String email
    );
}
