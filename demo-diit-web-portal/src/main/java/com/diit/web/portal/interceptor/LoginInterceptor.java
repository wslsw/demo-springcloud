package com.diit.web.portal.interceptor;

import com.diit.common.utils.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.diit.common.constant.Const;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器
 *
 * @author lishw
 * @date 2018-01-09
 */
@Configuration
@RefreshScope
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisClient jedisClient;

	@Value("${login_path}")
    private String LOGIN_PATH;
	
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("startTime");  
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag =true;
        String ip = request.getRemoteAddr();
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        System.out.println("用户:"+ip+",访问目标:"+method.getDeclaringClass().getName() + "." + method.getName());
        
        //执行handler之前执行此方法 true 放行 false 拦截
        //String cookieValue = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);
        String cookieValue = jedisClient.get(Const.TOKEN_LOGIN);
        //获取访问URL
        String url = request.getRequestURL().toString();
        
        if (StringUtils.isBlank(cookieValue)) {
            //跳转登录页面
        	response.sendRedirect(LOGIN_PATH);
            //拦截
            return false;
        }
        
        /*User user=(User)request.getSession().getAttribute("user");
        if(null==user){
            response.sendRedirect("to_login");
            flag = false;
        }else{
            flag = true;
        }*/
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        long startTime = startTimeThreadLocal.get();
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        // 打印方法执行时间
        if (executeTime > 1000) {
            System.out.println("[" + method.getDeclaringClass().getName() + "." + method.getName() + "] 执行耗时 : " + executeTime + "ms");
        } else {
            System.out.println("[" + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "] 执行耗时 : " + executeTime + "ms");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }	
}
