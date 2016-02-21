package com.cn.wolf.util;

import java.io.*;

/**
 * Created by wolf on 16/2/20.
 */
public class ObjectSerializeUtil {

    public static Object getObjFromStr(String serStr)throws  IOException,ClassNotFoundException {
        if(null==serStr){
            return null;
        }
        String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        Object result = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();

        return result;
    }


    public static String getStrFromObj(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }
}
