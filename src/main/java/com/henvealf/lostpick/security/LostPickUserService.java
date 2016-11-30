package com.henvealf.lostpick.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.henvealf.lostpick.data.UserRepository;
import com.henvealf.lostpick.utils.EncryptUtil;


/**
 * 从数据库中得到用户，   添加到Spring Security用户仓库
 * @author Henvealf
 *
 */
public class LostPickUserService implements UserDetailsService{

	private final UserRepository userRepository;
	
	public LostPickUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
		
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		com.henvealf.lostpick.web.beans.User 
					lopUser = userRepository.findByUserId(userId);
		
		if(lopUser != null){
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			UserDetails userDetails = new HasRealNameUser(lopUser.getUserId(),  //在jsp中为username
							lopUser.getPassword(),
							lopUser.getUserName(),
							authorities);
			return userDetails;
		}
		throw new UsernameNotFoundException(
				"用户 '" + userId + "' 没找到.");
	}

}
