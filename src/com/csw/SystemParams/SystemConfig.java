package com.csw.SystemParams;

import com.csw.utils.GetRealPathUtil;
import com.csw.utils.PropertiesUtil;
import com.csw.utils.FileUtil.FileOperationUtil;

/**
 *项目名称:CxfMyCsw 类描述：系统参数配置 创建人:Administrator 创建时间: 2013-9-6 上午10:48:43
 */
public class SystemConfig {

	private static String username = "";// 用户名
	private static String password = "";// 密码

	// 进行sensorml转换为ebrim时的xslt文件的路径
	// public static String XSLFilePATH = "F:\\SensorMLToEbRIM.xsl";
	private static String filepath = new GetRealPathUtil().getWebInfPath()
			+ "systemconfig.properties";
	// private static String filepath = new GetRealPathUtil().getWebInfPath()
	// + "systemconfig.properties";
	public static String XSLFilePATH = FileOperationUtil.readPropertyFile(
			filepath, "XSLFilePATH");
	// public static String sENSOR_BBOX_DEF =
	// "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::BoundedBy";// 传感器观测范围信息
	public static String SENSOR_BBOX_DEF = FileOperationUtil.readPropertyFile(
			filepath, "SENSOR_BBOX_DEF");// 传感器观测范围信息
	// public static String sensorimagesaveurl = "";// 传感器图片保存的地址
	public static String sensorimagesaveurl = FileOperationUtil
			.readPropertyFile(filepath, "SENSORIMAGESAVEURL");// 传感器图片保存的地址

	public static String sensorimgweburl = FileOperationUtil.readPropertyFile(
			filepath, "sensorimgwebpath");// 传感器图片url访问的地址

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
	 * 这是部署在新的服务器等其他地方需要修改的
	 */
	// public static String sensorimagesaveurl =
	// "http:\\\\202.114.118.60:9002\\CxfMyCsw\\files\\";// 上传的传感器的图片保存路径
	// public static String sensorsavebasepath =
	// "D:\\Program Files\\apache-tomcat-7.0.42-9002\\webapps\\CxfMyCsw\\files\\";
	// public static String sensorimagesaveurl
	// =PropertiesOperation.Getsensorimgweburl();// 上传的传感器的图片保存路径
	// public static String sensorsavebasepath =
	// "C:\\apache-tomcat-7.0.47-9002\\webapps\\CxfMyCsw\\files\\";
	public static String sensorsavebasepath = PropertiesUtil
			.Getsensorimglocalurl();
	/* 图片地址管理系统参数设置 end */
}
