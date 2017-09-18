/**
 * 
 */
package com.csw.utils.custometypes;

import java.util.Date;


/**
 *项目名称:CxfMyCsw 类描述：用于返回附件地址和附件说明 创建人:Administrator 创建时间: 2013-9-9 上午10:24:15
 */
public class SensorAttachmentMarkerType {
	private String sensorid;// 传感器的标识符
	private String sensorattachementpath;// 传感器附件web路径
	private String sensorattachementmarker;// 传感器类型
	private SensorAttachmentType img;// 传感器附件
	private Date sensormlsaveDate;// 传感器附件保存时间

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public String getSensorattachementpath() {
		return sensorattachementpath;
	}

	public void setSensorattachementpath(String sensorattachementpath) {
		this.sensorattachementpath = sensorattachementpath;
	}

	public String getSensorattachementmarker() {
		return sensorattachementmarker;
	}

	public void setSensorattachementmarker(String sensorattachementmarker) {
		this.sensorattachementmarker = sensorattachementmarker;
	}

	public SensorAttachmentType getImg() {
		return img;
	}

	public void setImg(SensorAttachmentType img) {
		this.img = img;
	}

	public Date getSensormlsaveDate() {
		return sensormlsaveDate;
	}

	public void setSensormlsaveDate(Date sensormlsaveDate) {
		this.sensormlsaveDate = sensormlsaveDate;
	}

}
