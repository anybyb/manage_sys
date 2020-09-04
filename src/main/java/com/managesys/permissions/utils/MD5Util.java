package com.managesys.permissions.utils;

import java.security.MessageDigest;
/**
 * 
 * <p>Title: MD5Util.java</p>
 * <p>Description: </p>
 * <p>Copyright: Niehui Copyright(c)2014</p>
 * <p>Company: </p>
 * <p>CreateTime:2014年7月4日 下午1:22:41</p>
 * @author wcc
 * @version 1.0
 *
 */
public class MD5Util {

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    public static String byteArrayToString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));

        }
        return resultSb.toString();
    }

    private static String byteToNumString(byte b) {

        int _b = b;
        if ( _b < 0 ) {
            _b = 256 + _b;
        }
        return String.valueOf(_b);
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if ( n < 0 ) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String encode(String origin) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex) {

        }
        return resultString;
    }

}
