package com.henvealf.lostpick.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;


import com.henvealf.lostpick.utils.ImageUtils;
import com.henvealf.lostpick.utils.LPPath;
import com.henvealf.lostpick.web.beans.CutImageLocation;
import com.henvealf.lostpick.web.beans.DayWord;
import com.henvealf.lostpick.web.beans.Notice;

/**
 * 上传操作的工具类
 * @author Henvealf
 *
 */
public class UploadImage {
	
	private static String realPath = System.getProperty("web.root") + "user" + File.separatorChar + "image";
	//Ubuntu
	//private static String realPath1 = "/home/tomcat/tomcat8/webapps/lost-pick-spring/user/image";
	//private static String realPathDayWordCom = "/home/tomcat/tomcat8/webapps/lost-pick-spring/admin";
	/**
	 * 上传图片
	 * @param cutLocation
	 * @param noticePicture
	 * @param newNotice
	 * @param
	 * @return 数据库中要保存的路径
	 */
	 public static String goCutAndUpload(CutImageLocation cutLocation,Part noticePicture,Notice newNotice){
			
			String dbImgUrl = null;
			try {
				int x1 =  (int) cutLocation.getX1();
				int y1 =  (int) cutLocation.getX1();
				int width =  (int) (cutLocation.getW());  //
				int height = (int) (cutLocation.getH()); 
				
				InputStream imgStream = noticePicture.getInputStream(); //获取图片流数据
				
				//String realPath = request.getServletContext().getRealPath("/user");
				//realPath = realPath.replace("/", "\\");
				String imgUrl = 
						ImageUtils.makePathWithFileName(
								noticePicture.getSubmittedFileName(),	// 得到文件名
								realPath,
								newNotice.getUserId());//保存路径
				//System.out.println("最终图片保存路径为: " + imgUrl);
				ImageUtils.cutFromStream(imgStream,imgUrl, x1, y1, width, height);
				imgUrl = imgUrl.replace("\\", "/");
				dbImgUrl = imgUrl.substring(imgUrl.indexOf("image")).replace("\\", "/");
				//System.out.println("保存在数据库中的 imgUrl：" + dbImgUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("上传图片成功");
			return dbImgUrl;
	 }
	 
	 /**
	  * 压缩图片并上传，最后得到数据库中保存路径
	  * @param noticePicture
	  * @param newNotice
	  * @return
	  */
	 public static String goCompressAndUpload(Part noticePicture, Notice newNotice){
		 String dbImgUrl = null;
		 try {
			InputStream imgStream = noticePicture.getInputStream(); //获取图片流数据
			
			String imgSaveUrl = ImageUtils.makePathWithFileName(
							noticePicture.getSubmittedFileName(),
							LPPath.USER_IMAGE,
							newNotice.getUserId());//保存图片的路径
			 
			System.out.println("最终图片保存路径为: " + imgSaveUrl);
			ImageUtils.compressPicAndUpload(imgStream, imgSaveUrl);

			dbImgUrl = imgSaveUrl.substring(imgSaveUrl.indexOf("image"));
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return dbImgUrl;
	 }
	 
	 /**
	  * 压缩图片并上传，最后得到数据库中保存路径
	  * @param noticePicture
	  * @param
	  * @return
	  */
	 public static String goCompressAndUploadDayImg(Part noticePicture, DayWord dayWord){
		 String dbImgUrl = null;
		 try {
			InputStream imgStream = noticePicture.getInputStream(); //获取图片流数据
			
			String imgSaveUrl = 
					ImageUtils.makePathWithFileName(
							noticePicture.getSubmittedFileName(), 
							LPPath.ADMIN_IMAGE
							);//保存图片的路径
			
			System.out.println("最终图片保存路径为: " + imgSaveUrl);
			ImageUtils.compressPicAndUpload(imgStream, imgSaveUrl);

			dbImgUrl = imgSaveUrl.substring(imgSaveUrl.indexOf("admin"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		} 
		 
		return dbImgUrl;
	 }
}
