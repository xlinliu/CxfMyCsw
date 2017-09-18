/**
 * 
 */
package com.csw.utils.custometypes;

import java.util.List;

/**
 *项目名称:CxfMyCsw 类描述：用于webservices中的传感器的基本信息 创建人:Administrator 创建时间: 2013-9-10
 * 下午03:03:58
 */
public class SensorBasicInfoType {
	/**
	 * 传感器标识符
	 */
	private String sensorid;
	/**
	 * 传感器全称
	 */
	private String sensorlongname;
	/**
	 * 传感器简称
	 */
	private String sensorshortname;
	/**
	 * 传感器类型
	 */
	private String sensortype;
	/**
	 * 传感器的sos服务地址
	 */
	private String sensorsosurl;
	/**
	 * 传感器的sos的offering机构号
	 */
	private String sensoroffering;
	/**
	 * 传感器预期应用
	 */
	private String sensorintendapp;
	/**
	 * 传感器关键字
	 */
	private String sensorkeyword;
	/**
	 * 传感器观测范围
	 */
	private String sensorbbox;
	/**
	 * 传感器位置
	 */
	private String sensorpos;
	/**
	 * 传感器开始工作时间
	 */
	private String sensorbegintime;
	/**
	 * 传感器结束工作时间
	 */
	private String senosrendtime;
	/**
	 * 传感器搭载平台
	 */
	private SensorBasicInfoType sensorplatform;
	/**
	 * 传感器的组件（子传感器）
	 */
	private List<SensorBasicInfoType> sensorcomponents;
	/**
	 * 传感器的预留的字段，【已被使用为sos的访问地址】
	 */
	private String othersensorinfo;
	/**
	 * 传感器的组织信息
	 */
	private String sensororgan;

	/**
	 * 传感器观测数据的访问地址
	 */
	// private String sosurl;

	/**
	 * @return the sensorid
	 */
	public String getSensorid() {
		return sensorid;
	}

	/**
	 * @param sensorid
	 *            the sensorid to set
	 */
	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	/**
	 * @return the sensorlongname
	 */
	public String getSensorlongname() {
		return sensorlongname;
	}

	/**
	 * @param sensorlongname
	 *            the sensorlongname to set
	 */
	public void setSensorlongname(String sensorlongname) {
		this.sensorlongname = sensorlongname;
	}

	/**
	 * @return the sensorshortname
	 */
	public String getSensorshortname() {
		return sensorshortname;
	}

	/**
	 * @param sensorshortname
	 *            the sensorshortname to set
	 */
	public void setSensorshortname(String sensorshortname) {
		this.sensorshortname = sensorshortname;
	}

	/**
	 * @return the sensortype
	 */
	public String getSensortype() {
		return sensortype;
	}

	/**
	 * @param sensortype
	 *            the sensortype to set
	 */
	public void setSensortype(String sensortype) {
		this.sensortype = sensortype;
	}

	/**
	 * @return the sensorintendapp
	 */
	public String getSensorintendapp() {
		return sensorintendapp;
	}

	/**
	 * @param sensorintendapp
	 *            the sensorintendapp to set
	 */
	public void setSensorintendapp(String sensorintendapp) {
		this.sensorintendapp = sensorintendapp;
	}

	/**
	 * @return the sensorkeyword
	 */
	public String getSensorkeyword() {
		return sensorkeyword;
	}

	/**
	 * @param sensorkeyword
	 *            the sensorkeyword to set
	 */
	public void setSensorkeyword(String sensorkeyword) {
		this.sensorkeyword = sensorkeyword;
	}

	/**
	 * @return the sensorbbox
	 */
	public String getSensorbbox() {
		return sensorbbox;
	}

	/**
	 * @param sensorbbox
	 *            the sensorbbox to set
	 */
	public void setSensorbbox(String sensorbbox) {
		this.sensorbbox = sensorbbox;
	}

	/**
	 * @return the sensorpos
	 */
	public String getSensorpos() {
		return sensorpos;
	}

	/**
	 * @param sensorpos
	 *            the sensorpos to set
	 */
	public void setSensorpos(String sensorpos) {
		this.sensorpos = sensorpos;
	}

	/**
	 * @return the sensorbegintime
	 */
	public String getSensorbegintime() {
		return sensorbegintime;
	}

	/**
	 * @param sensorbegintime
	 *            the sensorbegintime to set
	 */
	public void setSensorbegintime(String sensorbegintime) {
		this.sensorbegintime = sensorbegintime;
	}

	/**
	 * @return the senosrendtime
	 */
	public String getSenosrendtime() {
		return senosrendtime;
	}

	/**
	 * @param senosrendtime
	 *            the senosrendtime to set
	 */
	public void setSenosrendtime(String senosrendtime) {
		this.senosrendtime = senosrendtime;
	}

	/**
	 * @return the sensorplatform
	 */
	public SensorBasicInfoType getSensorplatform() {
		return sensorplatform;
	}

	/**
	 * @param sensorplatform
	 *            the sensorplatform to set
	 */
	public void setSensorplatform(SensorBasicInfoType sensorplatform) {
		this.sensorplatform = sensorplatform;
	}

	/**
	 * @return the sensorcomponents
	 */
	public List<SensorBasicInfoType> getSensorcomponents() {
		return sensorcomponents;
	}

	/**
	 * @param sensorcomponents
	 *            the sensorcomponents to set
	 */
	public void setSensorcomponents(List<SensorBasicInfoType> sensorcomponents) {
		this.sensorcomponents = sensorcomponents;
	}

	/**
	 * @return the othersensorinfo
	 */
	public String getOthersensorinfo() {
		return othersensorinfo;
	}

	/**
	 * @param othersensorinfo
	 *            the othersensorinfo to set
	 */
	public void setOthersensorinfo(String othersensorinfo) {
		this.othersensorinfo = othersensorinfo;
	}

	/**
	 * @return the sensororgan
	 */
	public String getSensororgan() {
		return sensororgan;
	}

	/**
	 * @param sensororgan
	 *            the sensororgan to set
	 */
	public void setSensororgan(String sensororgan) {
		this.sensororgan = sensororgan;
	}

	public String getSensorsosurl() {
		return sensorsosurl;
	}

	public void setSensorsosurl(String sensorsosurl) {
		this.sensorsosurl = sensorsosurl;
	}

	public String getSensoroffering() {
		return sensoroffering;
	}

	public void setSensoroffering(String sensoroffering) {
		this.sensoroffering = sensoroffering;
	}
}
