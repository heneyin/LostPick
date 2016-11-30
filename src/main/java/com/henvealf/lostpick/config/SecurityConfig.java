package com.henvealf.lostpick.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.henvealf.lostpick.data.UserRepository;
import com.henvealf.lostpick.security.LostPickUserService;

/**
 * 激活Web安全
 * @author Henvealf
 *
 */
@Configuration  		
@EnableWebSecurity		//和 WebSecurityConfigurer一起，为Web提供最基础的保障
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserRepository userRepository;
	private static final String SITE_WIDE_SECRET = "if-you";
	
	/***
	 * 首先配置验证管理器
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(new LostPickUserService(userRepository))
			.passwordEncoder(new StandardPasswordEncoder(SITE_WIDE_SECRET));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.formLogin()	//登录
			.loginPage("/user/login")
			.usernameParameter("userId")//将默认的username改为userId
		.and()			//登出
			.logout()
			.logoutSuccessUrl("/home")
		.and()
			.authorizeRequests()
			.antMatchers("/notice/addNotice").access("hasRole('ROLE_USER')")
			.antMatchers("/user/cpf/*").access("hasRole('ROLE_USER')")
			.antMatchers("/user/cp/*").access("hasRole('ROLE_USER')");
	}
	

}
