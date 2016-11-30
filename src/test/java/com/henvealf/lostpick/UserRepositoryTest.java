package com.henvealf.lostpick;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.henvealf.lostpick.config.JdbcConfig;
import com.henvealf.lostpick.data.JdbcUserRepository;
import com.henvealf.lostpick.web.beans.LoginForm;
import com.henvealf.lostpick.web.beans.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JdbcConfig.class)
public class UserRepositoryTest {
	  @Autowired
	  JdbcUserRepository userRepository;
	  
	  @Test
	  @Transactional
	  public void save_newUser() {
	    //assertEquals(4, spitterRepository.count());
	    User user = new User("温斯顿", "123456", "11", "11",
	        "1", "12131231","123213","123");
	    userRepository.add(user);
	  }
	  
	  @Test
	  @Transactional
	  public void find_userById(){
		  User user = userRepository.findByUserId("0021111");
		  System.out.println("user.getEmail" + user.getEmail());
		  System.out.println("user.getUserName" + user.getUserName());
	  }
	  
	  @Test
	  @Transactional
	  public void update_userInfo(){
		    User user = new User("温斯顿", "改一改", "11", "11",
			        "1", "12131231","123213","123");
		  userRepository.updateInfo(user);
	  }
	  
	  @Test
	  @Transactional
	  public void update_userPassword(){
		    User user = new User("温斯顿", "又改了密码", "11", "11",
			        "1", "12131231","123213","123");
		   //userRepository.updatePassword(user);
	  }
	  @Test
	  @Transactional
	  public void checkPassword(){
		  LoginForm loginForm = new LoginForm();
		  loginForm.setUserId("001");
		  loginForm.setPassword("1234");
		  System.out.println(userRepository.checkLogin(loginForm));
	  }
}
