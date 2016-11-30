package com.henvealf.lostpick.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.henvealf.lostpick.config.filter.MyCharacterEncodingFilter;
import com.henvealf.lostpick.config.filter.MyEncodingFilter;
/**
 * 这是一个DispatcherServlet,用户的所有请求都会先经过这里
 * @author Henvealf
 *
 */
public class LostpickWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	/**
	 * 配置ContextLoaderListener.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class ,MyCharacterEncodingFilter.class};
	}

	/**
	 * 定义的DispatcharServlet的配置
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		 return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		return new Filter[]{
				new CharacterEncodingFilter("utf-8", true)
		};
	}

}
