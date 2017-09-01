package com.lcj.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 获取/设置*.properties文件属性
 */
public class PropertiesManager {
	private Properties propertie;
	private FileInputStream inputFile;

	public void saveProperties(FileOutputStream fos,String cfgPath)
	{
		try {
			propertie.store(fos, cfgPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 构造函数
	 * @param filePath
	 */
	public PropertiesManager(String filePath) {
		propertie = new Properties();
		try {
			inputFile = new FileInputStream(filePath);
			propertie.load(inputFile);
			inputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取指定key的value
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);
			return value;
		} else
			return null;
	}


	public boolean setValue(String key,String value) {
		if (propertie.containsKey(key)) {
			propertie.setProperty(key, value);
			return true;
		} else
			return false;
	}	
	
	
	/**
	 * 获取指定文件的指定key对应的value
	 * @param fileName
	 * @param key
	 * @return
	 */
	public String getValue(String fileName, String key) {
		try {
			String value = "";
			inputFile = new FileInputStream(fileName);
			propertie.load(inputFile);
			inputFile.close();
			if (propertie.contains(key)) {
				value = propertie.getProperty(key);
				return value;
			} else
				return value;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}

