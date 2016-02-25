package com.cn.wolf.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    private JsonUtil() {
    }

    /**
     * json转成对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 对象转成json
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }
}
