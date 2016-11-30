package com.henvealf.lostpick;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.henvealf.lostpick.config.JdbcConfig;
import com.henvealf.lostpick.data.NoticeRepository;
import com.henvealf.lostpick.web.beans.Notice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JdbcConfig.class)
public class NoticeRepositoryTest {
	
	@Autowired
	NoticeRepository noticeRepository;
	
	@Test
	@Transactional
	public void findAllNotice(){
		List<Notice> list = noticeRepository.findAllNotice(true,1);
		for(int i = 0 ; i <list.size(); i++){
			System.out.println("getUserId()"+list.get(i).getUserId());
			System.out.println(list.get(i).getId());
			System.out.println(list.get(i).getGoodsType());
		}
	}
	
	@Test
	@Transactional
	public void addNotice(){

	}
}
