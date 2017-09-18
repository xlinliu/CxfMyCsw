package com.csw.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * �������������ʵ��
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

	// ��ȡ������ͼƬ�������ַ
	public static String Getsensorimgweburl() {
		return getKeyValue("sensorimgwebpath");
	}

	// ��ȡ������ͼƬ�ı����ļ�·��
	public static String Getsensorimglocalurl() {
		// cswurl
		return getKeyValue("sensorrealpath");
	}

	/**
	 * ��ȡ�����ļ�����Ӧ����ֵ
	 * 
	 * @param key
	 *            ����
	 * @return String
	 */
	public static String getKeyValue(String key) {
		return props.getProperty(key);
	}
}
