package com.henvealf.lostpick.data;

import com.henvealf.lostpick.web.beans.LoginForm;
import com.henvealf.lostpick.web.beans.User;

public interface UserRepository {
	/**
	 * 保存新用户的信息
	 * @return
	 */
	public void add(User user);

	public User findByUserId(String userId);
	
	/**
	 * 修改用户基本信息
	 * @param user
	 */
	public void updateInfo(User user);
	
	/**
	 * 修改用户密码
	 * @param user
	 */
	public void updatePassword(LoginForm loginForm);
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param newPassword
	 */
	public void updatePassword(String userId, String newPassword);
	
	/**
	 * 获取密码 根据Id
	 * @param userId
	 * @return
	 */
	public String getPassword(String userId);
	
	public boolean checkLogin(LoginForm loginForm);
	
	public boolean checkUserIdCanUse(String userId);
}
