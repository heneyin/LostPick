package com.henvealf.lostpick.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.henvealf.lostpick.data.DayWordRepository;
import com.henvealf.lostpick.data.JdbcDayWordRepository;
import com.henvealf.lostpick.data.JdbcNoticeRepository;
import com.henvealf.lostpick.data.JdbcUserRepository;
import com.henvealf.lostpick.data.NoticeRepository;
import com.henvealf.lostpick.data.UserRepository;

@Configuration
public class JdbcConfig {
	
	/**
	 * 数据源
	 * @return
	 */
	@Bean(name="dataSource")
	public DataSource dataSource(){
		//C3P0ConnectionProvider ds = new C3P0ConnectionProvider();

		BasicDataSource ds = new BasicDataSource();
		
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/lost_pick_spring"
							+ "?useUnicode=true&characterEncoding=UTF-8");
		
		ds.setUsername("root");
		ds.setPassword("heneSQL!");
		ds.setInitialSize(5);
		ds.setMaxActive(10);
		
		return ds;
	}
	
	/**
	 * jdbc模板
	 * @param dataSource
	 * @return
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	
	/**
	 * User的仓库
	 * @param jdbcTemplate
	 * @return
	 */
	@Bean
	public UserRepository userRepository(JdbcTemplate jdbcTemplate){
		return new JdbcUserRepository(jdbcTemplate);
	}
	
	/**
	 * Notice的仓库
	 * @param jdbcTemplate
	 * @return
	 */
	@Bean
	public NoticeRepository noticeRepository(JdbcTemplate jdbcTemplate){
		return new JdbcNoticeRepository(jdbcTemplate);
	}
	
	/**
	 * DayWord的仓库
	 * @param jdbcTemplate
	 * @return
	 */
	@Bean
	public DayWordRepository dayWordRepository(JdbcTemplate jdbcTemplate){
		return new JdbcDayWordRepository(jdbcTemplate);
	}
}
