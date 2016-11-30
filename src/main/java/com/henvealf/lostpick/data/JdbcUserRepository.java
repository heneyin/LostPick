package com.henvealf.lostpick.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.henvealf.lostpick.utils.EncryptUtil;
import com.henvealf.lostpick.web.beans.LoginForm;
import com.henvealf.lostpick.web.beans.User;

@Repository
public class JdbcUserRepository implements UserRepository{
	
	private static final String SELECT_USER = " select * from users ";
	private static final String SELECT_USER_BY_ID = SELECT_USER + "where userId = ?";
	private static final String INSERT_USER = "insert into users "
					+ "	( userId, password, userName, academy, sex, phonecode, QQNumber, email)"
					+"  values ( ?, ?, ? , ?, ?, ?, ?, ?)";
	private static final String UPDATE_USER_INFO = "update users set userName= ? , "
			+ "academy = ? ,sex = ? , phonecode = ?, QQNumber = ?, email = ? where userId = ?";
	private static final String UPDATE_USER_PASSWORD = "update users set password = ? where userId = ? ";
	
	private static final String SELECT_PASSWORD = "select password from users where userId = ? ";
	
	private static final String CHECK_LOGIN = "select count(*) as count from users where userId = ? and password = ? ";
	
	private static final String CHECK_USERID = "select count(*) as count from users where userId = ?";
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcUserRepository(){
	}
	
	public JdbcUserRepository(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 添加一个新的User
	 */
	@Override
	public void add(User user) {
		jdbcTemplate.update(INSERT_USER,
				user.getUserId(),
				user.getPassword(),	
				user.getUserName(),
				user.getAcademy(),
				user.getSex(),
				user.getPhonecode(),
				user.getQQNumber(),
				user.getEmail());
	}

	@Override
	public User findByUserId(String userId) {
		return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new UserRowMapper(),userId);
	}
	
	private static final class UserRowMapper implements RowMapper<User>{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(
					rs.getString("userId"),
					rs.getString("password"),
					rs.getString("userName"),
					rs.getString("academy"),
					rs.getString("sex"),
					rs.getString("phonecode"),
					rs.getString("QQNumber"),
					rs.getString("email"));
		}
	}
	
	@Transactional
	public void updateInfo(User user){
		jdbcTemplate.update(UPDATE_USER_INFO, 
				user.getUserName(),
				user.getAcademy(),
				user.getSex(),
				user.getPhonecode(),
				user.getQQNumber(),
				user.getEmail(),
				user.getUserId()
		);
	}

	@Override
	public void updatePassword(LoginForm loginForm) {
		jdbcTemplate.update(UPDATE_USER_PASSWORD,
				loginForm.getPassword(),
				loginForm.getUserId());
	}

	@Override
	public void updatePassword(String userId, String newPassword) {
		jdbcTemplate.update(UPDATE_USER_PASSWORD,
				userId, newPassword);
	}

	@Override
	public String getPassword(String userId) {
		String queryForObject = jdbcTemplate.queryForObject(
				SELECT_PASSWORD, 
				new RowMapper<String>(){
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("password");
					}
				},
				userId);
		return queryForObject;
	}
	
	public boolean checkLogin(LoginForm loginForm){
		String userId = loginForm.getUserId();
		String realPassword = this.getPassword(userId);
		if(realPassword != null){
			return EncryptUtil.match(loginForm.getPassword(), realPassword);
		}
		return false;
	}
	
	
	/***
	 * 检查用户名是否可用
	 */
	public boolean checkUserIdCanUse(String userId){
		int count = jdbcTemplate.queryForObject(
				CHECK_USERID, 
				new RowMapper<Integer>(){
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getInt("count");
					}
				},
				userId);
		if(count == 1){	//不可用
			return false;
		}
		return true;
	}

	

}
