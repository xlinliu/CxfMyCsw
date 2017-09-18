package com.csw.SystemParams;

import com.csw.utils.GetRealPathUtil;
import com.csw.utils.PropertiesUtil;
import com.csw.utils.FileUtil.FileOperationUtil;

/**
 *��Ŀ����:CxfMyCsw ��������ϵͳ�������� ������:Administrator ����ʱ��: 2013-9-6 ����10:48:43
 */
public class SystemConfig {

	private static String username = "";// �û���
	private static String password = "";// ����

	// ����sensormlת��Ϊebrimʱ��xslt�ļ���·��
	// public static String XSLFilePATH = "F:\\SensorMLToEbRIM.xsl";
	private static String filepath = new GetRealPathUtil().getWebInfPath()
			+ "systemconfig.properties";
	// private static String filepath = new GetRealPathUtil().getWebInfPath()
	// + "systemconfig.properties";
	public static String XSLFilePATH = FileOperationUtil.readPropertyFile(
			filepath, "XSLFilePATH");
	// public static String sENSOR_BBOX_DEF =
	// "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::BoundedBy";// �������۲ⷶΧ��Ϣ
	public static String SENSOR_BBOX_DEF = FileOperationUtil.readPropertyFile(
			filepath, "SENSOR_BBOX_DEF");// �������۲ⷶΧ��Ϣ
	// public static String sensorimagesaveurl = "";// ������ͼƬ����ĵ�ַ
	public static String sensorimagesaveurl = FileOperationUtil
			.readPropertyFile(filepath, "SENSORIMAGESAVEURL");// ������ͼƬ����ĵ�ַ

	public static String sensorimgweburl = FileOperationUtil.readPropertyFile(
			filepath, "sensorimgwebpath");// ������ͼƬurl���ʵĵ�ַ

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		SystemConfig.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		SystemConfig.password = password;
	}

	/**
	 * ���ǲ������µķ������������ط���Ҫ�޸ĵ�
	 */
	// public static String sensorimagesaveurl =
	// "http:\\\\202.114.118.60:9002\\CxfMyCsw\\files\\";// �ϴ��Ĵ�������ͼƬ����·��
	// public static String sensorsavebasepath =
	// "D:\\Program Files\\apache-tomcat-7.0.42-9002\\webapps\\CxfMyCsw\\files\\";
	// public static String sensorimagesaveurl
	// =PropertiesOperation.Getsensorimgweburl();// �ϴ��Ĵ�������ͼƬ����·��
	// public static String sensorsavebasepath =
	// "C:\\apache-tomcat-7.0.47-9002\\webapps\\CxfMyCsw\\files\\";
	public static String sensorsavebasepath = PropertiesUtil
			.Getsensorimglocalurl();
	/* ͼƬ��ַ����ϵͳ�������� end */
}
