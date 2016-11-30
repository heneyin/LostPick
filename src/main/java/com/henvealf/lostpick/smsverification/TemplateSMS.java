package com.henvealf.lostpick.smsverification;

import com.henvealf.lostpick.smsverification.common.Config;
import com.henvealf.lostpick.smsverification.common.HttpUtil;

/**
 * 邮件短信接口调用示例
 */
public class TemplateSMS
{
	// url中20150822之后的部分
	private static String operation = "/SMS/templateSMS";

	// 参数详述请参考http://www.qingmayun.com/document.html
	private static String accountSid = Config.ACCOUNT_SID;
	private static String appId = Config.APP_ID;
	private static String templateId = "21730004";
	private static String to = "15264756971";
	private static String param = "LostPick,112311,3";

	/**
	 * 邮件短信
	 */
	public static void execute()
	{
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&appId=" + appId + "&templateId=" + templateId + "&to="
				+ to + "&param=" + param + HttpUtil.createCommonParam();

		// 提交请求
		String result = HttpUtil.post(url, body);
		System.out.println("result:" + System.lineSeparator() + result);
	}
}
