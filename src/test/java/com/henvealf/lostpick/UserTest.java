package com.henvealf.lostpick;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import com.henvealf.lostpick.web.beans.User;


public class UserTest {
	private SessionFactory sessionFactory;
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
	
	@Test
	public void add(){
		try {
			setUp();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		User user = new User();
		System.out.println("hah");
		user.setUserId("444444444");
		user.setUserName("我是谁111");
		user.setAcademy("天津");
		user.setEmail("11@11.com");
		user.setPassword("12345611");
		user.setPhonecode("1111");
		user.setQQNumber("1111");
		user.setSex("1");
		

		try {
			session.save(user);
			tx.commit();
		} catch (Exception e) {
			System.out.println("hahaa11111111111");
			e.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
	
	}
}
