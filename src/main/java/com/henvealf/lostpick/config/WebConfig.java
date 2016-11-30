package com.henvealf.lostpick.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.henvealf.lostpick")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	/**
	 * 一个Bean-视图解析器,告诉request下一步发送到哪一个View。
	 * @return
	 */
	@Bean
	public ViewResolver viewRewolver(){
		InternalResourceViewResolver resolver = 
				new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
/*	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;             
	}*/
	
	/**
	 * 配置消息资源
	 * @return
	 */
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath://messages");
		messageSource.setCacheSeconds(10);
		return messageSource;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer tiles = new TilesConfigurer();
		/*This property takes an array of Strings where each entry specifies the
		location of tile-definition XML files,表示你的tiles配置文件应该是/WEB-INF/layout/的tiles.xml*/
		tiles.setDefinitions(new String[]{
			"/WEB-INF/layout/tiles.xml"	
		});
		//在运行并且刷新时否检查tiles定义文件。
		tiles.setCheckRefresh(true);
		return tiles;
	}
	
	@Bean
	public ViewResolver viewResolver(){
		return new TilesViewResolver();
	}

	/**
	 * 使用CommonsMultipartResolved定义并配置处理复合请求的Bean
	 * @return
	 * @throws IOException
	 */
	@Bean
	public MultipartResolver multipartResolver() throws IOException{
		CommonsMultipartResolver resolver
			= new CommonsMultipartResolver();
		//临时路径
		resolver.setUploadTempDir(new FileSystemResource("/tmp/notice/uploads"));
		//文件最大容量
		resolver.setMaxUploadSize(2097152);
		//最大缓存容量 5MB
		resolver.setMaxInMemorySize(1024 * 1024 * 5);
		
		//there’s no way to specify the maximum multipart
		//request size.
		return resolver;
	}



}
