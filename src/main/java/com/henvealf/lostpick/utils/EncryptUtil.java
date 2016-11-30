package com.henvealf.lostpick.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密密码
 * @author Henvealf
 *
 */
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * @author XUYI
 * Spring Security 3.1 PasswordEncoder
 */
public class EncryptUtil {
    //从配置文件中获得
    private static final String SITE_WIDE_SECRET = "if-you";
    private static final PasswordEncoder encoder = new StandardPasswordEncoder(
       SITE_WIDE_SECRET);
 
    public static String encrypt(String rawPassword) {
         return encoder.encode(rawPassword);
    }
 
    public static boolean match(String rawPassword, String password) {
         return encoder.matches(rawPassword, password);
    }
/*    
    public static void main(String[] args) {
		System.out.println(EncryptUtil.encrypt("每次结果都不一样伐?").length());
		System.out.println(EncryptUtil.encrypt("123456"));
        System.out.println(EncryptUtil.encrypt("123456"));
		System.out.println(EncryptUtil.encrypt("每次结果都不一样伐?"));
        System.out.println(EncryptUtil.encrypt("每次结果都不一样伐?"));  
        boolean result = match("每次结果都不一样伐?", "4e960675ccf01d1e8089a16c6a900bf101680b6bdc927365958b4feadb7cd5339cd447af66994115");
        boolean result1 = match("123456", "b4ac9173184a30ce4e36c1f4b65381b9e3a2d32eaece8262dfb789f78ede7b484b744102f9e070d2");
        boolean result2 = match("123456", "93ff0aa82a4baf78d0bc90970acba3f806aa2e14058d7c329ca0ebf48f693a59df9938da493c002a"); 
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        //但是把每次结果拿出来进行match，你会发现可以得到true。
	}*/
    
    
    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public EncryptUtil() {
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    @SuppressWarnings("unused")
	private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

 }