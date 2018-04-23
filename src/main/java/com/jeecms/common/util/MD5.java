package com.jeecms.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import com.sun.crypto.provider.SunJCE;

/**
 * md5工具
 * @author swc 2018-04-16
 *
 */
public class MD5 {

    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    static {
        Security.addProvider(new SunJCE());
    }

    public static String MD5Encode(String origin) {
        String resultString = origin;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            buf.append(byteToHexString(b[i]));
        }
        return buf.toString();
    }

    private static String byteToHexString(byte b) {
        return hexDigits[((b & 0xF0) >> 4)] + hexDigits[(b & 0xF)];
    }
}
