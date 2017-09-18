package com.csw.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * ��ͨJAVA��ȡ WEB��Ŀ�µ�WEB-INFĿ¼
 * 
 * @author wang
 * 
 * 
 */
public class GetRealPathUtil {
	public static void main(String[] args) {
		GetRealPathUtil pathUtil = new GetRealPathUtil();
		System.out.println("path==" + pathUtil.getWebInfPath());
	}

	/**
	 * ��ȡ���е�java��ͨ���web��Ŀ�ĸ�·�� ���� G:/Applications/mycsws/CxfMyCsw
	 * �@��ᘌ�javaweb�Ŀ��Ҳ�ǒ��õģ���������Ҳ�����
	 * 
	 * @return
	 */
	public String getWebInfPath() {
		URL url = getClass().getProtectionDomain().getCodeSource()
				.getLocation();
		String path = url.toString();
		int index = path.indexOf("WEB-INF");

		if (index == -1) {
			index = path.indexOf("classes");
		}

		if (index == -1) {
			index = path.indexOf("bin");
		}

		path = path.substring(0, index);

		if (path.startsWith("zip")) {// ��class�ļ���war��ʱ����ʱ����zip:D:/...������·��
			path = path.substring(4);
		} else if (path.startsWith("file")) {// ��class�ļ���class�ļ���ʱ����ʱ����file:/D:/...������·��
			path = path.substring(6);
		} else if (path.startsWith("jar")) {// ��class�ļ���jar�ļ�����ʱ����ʱ����jar:file:/D:/...������·��
			path = path.substring(10);
		}
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		path = path.substring(0, path.lastIndexOf("/"));
		path+="\\";
		return path;
	}
}
