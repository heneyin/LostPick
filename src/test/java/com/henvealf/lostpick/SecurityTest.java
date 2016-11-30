package com.henvealf.lostpick;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
public class SecurityTest {

	private static AuthenticationManager am = new SampleAuthenticationManager();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("Please enter your username:");
			String name = in.readLine();
			System.out.println("Please enter your password:");
			String password = in.readLine();
			try {
				
				//第一个参数是指代表用户本身的东西，例如用户名；而第二是指用户所提供的的凭证，例如密码。
				//这个request是还未进行验证的验证，也就是request验证。
				Authentication request = new UsernamePasswordAuthenticationToken(name, password);
				System.out.println("验证之前");
				//这个result是通过 验证管理器（AuthenticationManager）验证 之后的所得到的Authentication，里面携带了一些关于验证结果的信息。
				//她会依据一个正在使用的验证机制，被存储在一个被SecurityContextHolder所
				//管理的一个线程局部变量--SecurityContext中，而上面提到的验证机制，在这里是指SampleAuthenticationManager类
				//验证不成功就会抛出异常
				Authentication result = am.authenticate(request);
				//抛出异常后这里就不执行了
				System.out.println("验证之后");
				//验证成功，将验证结果保存在验证上下文所有器中
				SecurityContextHolder.getContext().setAuthentication(result);
				break;
			} catch(AuthenticationException e) {
				System.out.println("Authentication failed: " + e.getMessage());
			}
		}
		
		System.out.println("Successfully authenticated. Security context contains: " +
				SecurityContextHolder.getContext().getAuthentication());
	}
	
}

class SampleAuthenticationManager implements AuthenticationManager {

	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		if (auth.getName().equals(auth.getCredentials())) {
			return new UsernamePasswordAuthenticationToken(auth.getName(),
					auth.getCredentials(), AUTHORITIES);
		}
		
		throw new BadCredentialsException("Bad Credentials");
	}

}

