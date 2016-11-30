package com.henvealf.lostpick.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 一个过滤器，修改编码
 * 同时设置servlet编码
 * @author Henvealf
 *
 */
public class MyEncodingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		//System.out.println("自定义的编码过滤器开始工作：");
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		filterChain.doFilter(req, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("啊啊啊啊！我这过滤器诞生楼！！！");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("啊啊啊啊！我这过滤器不能被销毁啊");
	}
	
}
