package com.csw.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 该类用于设计与实现
 * 
 * @author Wangke
 * 
 */
public class PropertiesUtil {
	static String profilepath = new GetRealPathUtil().getWebInfPath()
			+ "/systemconfig.properties";
	private static Properties props = new Properties();
	static {
		try {
			props.load(new FileInputStream(profilepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.exit(-1);
		}
	}

	// 获取传感器图片的网络地址
	public static String Getsensorimgweburl() {
		return getKeyValue("sensorimgwebpath");
	}

	// 获取传感器图片的本地文件路径
	public static String Getsensorimglocalurl() {
		// cswurl
		return getKeyValue("sensorrealpath");
	}

	/**
	 * 读取属性文件中相应键的值
	 * 
	 * @param key
	 *            主键
	 * @return String
	 */
	public static String getKeyValue(String key) {
		return props.getProperty(key);
	}
}
