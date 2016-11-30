package com.henvealf.lostpick.config;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.henvealf.lostpick.config.filter.MyCharacterEncodingFilter;
/**
 * 配置CharacterEncodingFilter，解决乱码问题
 * @author Henvealf
 *
 */
public class EncodingFilterConfig implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		Dynamic filter = servletContext.addFilter("CharacterEncodingFilter", 
				encodingFilter(servletContext));
		filter.addMappingForUrlPatterns(null, false, "/*");
		filter.setInitParameter("encoding", "utf-8");
		filter.setInitParameter("forceEncoding", "true");
		filter.getUrlPatternMappings();
	}
	private MyCharacterEncodingFilter encodingFilter(ServletContext servletContext){
		System.out.println("加载： CharacterEncodingFilter");
		MyCharacterEncodingFilter cef = new MyCharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);
		cef.setServletContext(servletContext);
		return cef;
	}
}
