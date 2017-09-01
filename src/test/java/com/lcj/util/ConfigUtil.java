package com.lcj.util;

/**
 * 封装获取测试配置的方法。
 * @author Administrator
 *
 */
public class ConfigUtil {	
	public static String getConfig(String key){
		//读取环境变量
		String envPath = System.getProperty("user.dir") + "/config/envContact.properties";
		PropertiesManager pm = new PropertiesManager(envPath);

		return pm.getValue(key);
	}
}
