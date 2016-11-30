package com.henvealf.lostpick.utils;

import java.io.File;

/**
 * 11233
 * @author Henvealf
 *
 */
public class FileUtils {

	private final static char SEQ = File.separatorChar;
	//windows中
	private final static String absolutePathLeft
			= "E:"+ SEQ +"My Progarm"+ SEQ +"Java Web" + SEQ + "lost-pick-spring\\src\\main\\webapp\\user\\";
	//Ubuntu
	//private final static String absolutePathLeft = "/home/tomcat/tomcat8/webapps/lost-pick-spring/user/"; 
	
	
	/***
	 * 111
	 * @param path 删除的路径
	 * @return 
	 */
	public static void deleteSingleFile(String path){
		path = absolutePathLeft + path;
		//windows中，Ubutun就注释掉
		//path = absolutePathLeft + path.replace("/", "\\");
		System.out.println(path);
		File file = new File(path);

		file.delete();

	}
	
}
