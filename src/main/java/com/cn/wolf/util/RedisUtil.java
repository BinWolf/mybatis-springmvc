package com.cn.wolf.util;

import org.apache.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by wolf on 16/2/20.
 */
public class RedisUtil {
    private static JedisPool pool = null;
    private static Logger log = Logger.getLogger(RedisUtil.class);

    public RedisUtil() {
    }

    private static JedisPool getPool() {
        if(pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            ResourceBundle bundle = ResourceBundle.getBundle("redispool");
            if(bundle == null) {
                throw new IllegalArgumentException("[redispool.properties] is not found!");
            }
            config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")).intValue());
            config.setTestOnBorrow(bundle.getString("redis.pool.testOnBorrow").equals("true"));
            config.setTestOnReturn(bundle.getString("redis.pool.testOnReturn").equals("true"));
            String host = bundle.getString("redis.ip");
            int port = Integer.valueOf(bundle.getString("redis.port")).intValue();
            pool = new JedisPool(config, host, port, 100000);
        }
        return pool;
    }

    private static void returnResource(JedisPool pool, Jedis redis) {
        if(redis != null) {
            pool.returnResource(redis);
//            pool.close();
        }

    }

    public static void setExpireTime(String key, int seconds) {
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.expire(key, seconds);
        } catch (Exception var8) {
            pool.returnBrokenResource(jedis);
            var8.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

    }

    public static void del(String key) {
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.del(new String[]{key});
        } catch (Exception var7) {
            pool.returnBrokenResource(jedis);
            var7.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

    }

    public static String get(String key) {
        String value = null;
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception var8) {
            pool.returnBrokenResource(jedis);
            var8.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

        return value;
    }

    public static void set(String key, String value) {
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.set(key, value);
        } catch (Exception var8) {
            pool.returnBrokenResource(jedis);
            var8.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

    }

    public static void set(String key, String value, int timeLimit) {
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.set(key, value);
            jedis.expire(key, timeLimit);
        } catch (Exception var9) {
            pool.returnBrokenResource(jedis);
            var9.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

    }

    public static String hget(String key, String field) {
        String value = null;
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception var9) {
            pool.close();
            var9.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

        return value;
    }

    public static Map<String, String> hgetAll(String key) {
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            Map e = jedis.hgetAll(key);
            return e;
        } catch (Exception var7) {
            pool.returnBrokenResource(jedis);
            var7.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

        return null;
    }

    public static void hset(String key, String field, String value) {
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.hset(key, field, value);
        } catch (Exception var9) {
            pool.returnBrokenResource(jedis);
            var9.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

    }

    public static void hdel(String key, String... fields) {
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.hdel(key, fields);
        } catch (Exception var8) {
            pool.returnBrokenResource(jedis);
            var8.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

    }

    public static String lpop(String key) {
        String value = null;
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.lpop(key);
        } catch (Exception var8) {
            pool.returnBrokenResource(jedis);
            var8.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

        return value;
    }

    public static void lpush(String key, String value) {
        JedisPool pool = null;
        Jedis jedis = null;

        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.lpush(key, new String[]{value});
        } catch (Exception var8) {
            pool.returnBrokenResource(jedis);
            var8.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }

    }

    public static void setObject(String key, Object value) throws IOException {
        try {
            String e = ObjectSerializeUtil.getStrFromObj(value);
            set(key, e);
        } catch (IllegalArgumentException var3) {
            log.error("参数不合法！", var3);
        } catch (UnsupportedEncodingException var4) {
            log.error("不支持的编码！", var4);
        }

    }

    public static void setObject(String key, Object value, int timeLimit) throws IOException {
        try {
            String e = ObjectSerializeUtil.getStrFromObj(value);
            set(key, e, timeLimit);
        } catch (IllegalArgumentException var4) {
            log.error("参数不合法！", var4);
        } catch (UnsupportedEncodingException var5) {
            log.error("不支持的编码！", var5);
        }

    }

    public static Object getObject(String key) {
        Object o = null;

        try {
            o = ObjectSerializeUtil.getObjFromStr(get(key));
        } catch (Exception var3) {
            log.error("获取缓存对象出错getObject", var3);
        }

        return o;
    }

    public static Object getObjectBySqlAndParams(String sql, String method, Object... params) {
        if(null == params) {
            return getObject(MD5(sql) + method);
        } else {
            StringBuffer sb = new StringBuffer(sql);

            for(int i = 0; i < params.length; ++i) {
                sb.append(params[i]);
            }

            return getObject(MD5(sb.toString()) + method);
        }
    }

    public static String getKeyBySqlAndParams(String sql, String method, Object... params) {
        if(null == params) {
            return MD5(sql) + method;
        } else {
            StringBuffer sb = new StringBuffer(sql);

            for(int i = 0; i < params.length; ++i) {
                sb.append(params[i]);
            }

            return MD5(sb.toString()) + method;
        }
    }

    public static final String MD5(String s) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] e = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(e);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static boolean setValidKey(String key, long resetLimit, String value) {
        JedisPool pool = null;
        Jedis jedis = null;
        boolean temp = true;

        try {
            pool = getPool();
            jedis = pool.getResource();
            if(jedis.exists(key).booleanValue()) {
                if(resetLimit < 1200L - jedis.ttl(key).longValue()) {
                    jedis.del(key);
                    jedis.setex(key, 1200, value);
                } else {
                    temp = false;
                }
            } else {
                jedis.setex(key, 1200, value);
            }
        } catch (Exception var11) {
            pool.returnBrokenResource(jedis);
            var11.printStackTrace();
            temp = false;
        } finally {
            returnResource(pool, jedis);
        }

        return temp;
    }
}
