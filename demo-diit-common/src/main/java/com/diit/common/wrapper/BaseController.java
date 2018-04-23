package com.diit.common.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义基础Controller
 *
 * @author lishw
 * @date 2017-01-18
 */
public abstract class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HttpServletRequest request;
	
	protected String getContextPath(){
		return request.getContextPath();
	}
	
	protected String getRequestUrl(){
		return request.getRequestURL().toString();
	}

}
