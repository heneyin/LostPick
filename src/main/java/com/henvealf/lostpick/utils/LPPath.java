package com.henvealf.lostpick.utils;

import java.io.File;

/**
 * 存放用到的路径
 * Created by Henvealf on 2016/11/23.
 */
public class LPPath {
    public static final char SEQ_CHAR = File.separatorChar;
    public static final String ROOT = System.getProperty("web.root");
    // 用户图片路径
    public static final String USER_IMAGE = ROOT + "user" + SEQ_CHAR + "image";
    public static final String TMP = ROOT + "tmp" + SEQ_CHAR + "image";
    public static final String ADMIN_IMAGE = ROOT + "admin" + SEQ_CHAR + "image";

}
