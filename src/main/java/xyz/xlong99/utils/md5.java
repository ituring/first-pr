package xyz.xlong99.utils;



import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 使用md5加密密码。数据库不明文保存密码
 * @author xlong
 * @date 2019-03-28 21:48
 */
public class md5 {
    /**
     * 利用MD5进行加密
     */
    private static String encoderByMd5(String str) {
        String newstr = null;
        try {
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newstr;
    }

    /**
     * 加盐的md5加密，调用这个
     * @param str
     * @return
     */
    public static String encoderAddSalt(String str) {
        String salt = "xlong";
        str = salt + str;
        return encoderByMd5(str);
    }
}
