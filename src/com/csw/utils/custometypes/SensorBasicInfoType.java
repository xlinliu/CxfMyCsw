/**
 * 
 */
package com.csw.utils.custometypes;

import java.util.List;

/**
 *��Ŀ����:CxfMyCsw ������������webservices�еĴ������Ļ�����Ϣ ������:Administrator ����ʱ��: 2013-9-10
 * ����03:03:58
 */
public class SensorBasicInfoType {
	/**
	 * ��������ʶ��
	 */
	private String sensorid;
	/**
	 * ������ȫ��
	 */
	private String sensorlongname;
	/**
	 * ���������
	 */
	private String sensorshortname;
	/**
	 * ����������
	 */
	private String sensortype;
	/**
	 * ��������sos�����ַ
	 */
	private String sensorsosurl;
	/**
	 * ��������sos��offering������
	 */
	private String sensoroffering;
	/**
	 * ������Ԥ��Ӧ��
	 */
	private String sensorintendapp;
	/**
	 * �������ؼ���
	 */
	private String sensorkeyword;
	/**
	 * �������۲ⷶΧ
	 */
	private String sensorbbox;
	/**
	 * ������λ��
	 */
	private String sensorpos;
	/**
	 * ��������ʼ����ʱ��
	 */
	private String sensorbegintime;
	/**
	 * ��������������ʱ��
	 */
	private String senosrendtime;
	/**
	 * ����������ƽ̨
	 */
	private SensorBasicInfoType sensorplatform;
	/**
	 * ��������������Ӵ�������
	 */
	private List<SensorBasicInfoType> sensorcomponents;
	/**
	 * ��������Ԥ�����ֶΣ����ѱ�ʹ��Ϊsos�ķ��ʵ�ַ��
	 */
	private String othersensorinfo;
	/**
	 * ����������֯��Ϣ
	 */
	private String sensororgan;

	/**
	 * �������۲����ݵķ��ʵ�ַ
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
