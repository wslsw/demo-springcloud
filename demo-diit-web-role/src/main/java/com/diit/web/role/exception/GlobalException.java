package com.diit.web.role.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @author lishw
 * @create 2017-01-18
 */

@Component
public class GlobalException implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        logger.info("进入User 全局异常处理器！");

        //控制台打印异常
        e.printStackTrace();

        logger.error("发生异常！",e);

        // 发短信 发邮件 ...

        // 跳转错误页面
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("msg", "服务器开小差，请稍后重试！");
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
