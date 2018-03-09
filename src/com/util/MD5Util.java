package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * MD5加密工具类
 */
public class MD5Util {
    public final static String calc(String ss){//MD5加密算法
        String s = ss ==null?"":ss;
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try{
            byte[] strTemp = s.getBytes();//获得二进制
            MessageDigest mdTemp = MessageDigest.getInstance( "MD5" );//加密器
            mdTemp.update( strTemp );//执行加密
            byte[] md = mdTemp.digest();//加密结果
            int j = md.length;
            char str[] = new char[j*2];
            int k = 0;
            for (int i = 0;i<j;i++){
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0>>>4&0xf];
                str[k++] = hexDigits[byte0&0xf];
            }
            return new String( str );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
