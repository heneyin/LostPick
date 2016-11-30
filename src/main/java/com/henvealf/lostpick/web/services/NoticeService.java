package com.henvealf.lostpick.web.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.henvealf.lostpick.data.NoticeRepository;

public class NoticeService {
	
	private NoticeRepository noticeRepository;
	
	public NoticeService(NoticeRepository noticeRepository){
		this.noticeRepository = noticeRepository;
	}
	
	public static String getNowTime(){
		/**订单的时间**/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date =  new Date();  
		return sdf.format(date);
	}
	
	public static List<String> getGoodsTypeOptions(){
		List<String> list = new ArrayList<String>();
		list.add("钥匙");
		list.add("手机");
		list.add("钱包");
		list.add("校园卡");
		list.add("身份证");
		list.add("银行卡");
		list.add("包");
		list.add("其他");
		return list;
	}


}
