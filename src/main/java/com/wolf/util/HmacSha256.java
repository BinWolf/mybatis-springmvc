package com.wolf.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by wolf on 15/11/23.
 */
public class HmacSha256 {

    /**
     * 解密
     * @param key
     * @param data
     * @return
     */
    public static  String HMAC_SHA256(String key,String data ) {
        try {
            String macKey = key;
            String macData =data;

            Mac mac = Mac.getInstance("HmacSHA256");

            byte[] secretByte = macKey.getBytes("UTF-8");
            byte[] dataBytes = macData.getBytes("UTF-8");
            SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");

            mac.init(secret);
            byte[] doFinal = mac.doFinal(dataBytes);

            byte[] hexB = new Hex().encode(doFinal);
            String checksum = new String(hexB);
            return checksum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
