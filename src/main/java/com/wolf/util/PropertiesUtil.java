package com.wolf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties文件读写操作工具类
 *
 */
public class PropertiesUtil {

	private static Properties prop ;
	
	public static String getValue(String key) {
		if(null==prop){
			try {
				prop = new Properties();
				InputStream in = PropertiesUtil.class
						.getResourceAsStream("/properties/conf.properties");
				prop.load(in);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (String) prop.get(key);
	}

	/**
	 *
	 * @param path 相对于class目录
	 * @return
	 */
	public static Properties loadPropertyFile(String path){
		InputStream in = PropertiesUtil.class.getResourceAsStream(path);
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	

}
