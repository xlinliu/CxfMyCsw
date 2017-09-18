/**
 * 
 */
package com.csw.model.ebXMLModel;

import java.util.Date;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-6 上午10:29:40
 */
public class SensorMLImage {
	private int id;
	private String sensorid;// 传感器的id
	private String sensorimgurl;// 传感器图片存储地址（可选）
	private Date savetime;// 保存时间
	private String attchmentmarker;// 附件标识说明，这个是用户自身定义标准，自身根据提取
	private String owner;// 附件提供者

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public String getSensorimgurl() {
		return sensorimgurl;
	}

	public void setSensorimgurl(String sensorimgurl) {
		this.sensorimgurl = sensorimgurl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSavetime() {
		return savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

	public String getAttchmentmarker() {
		return attchmentmarker;
	}

	public void setAttchmentmarker(String attchmentmarker) {
		this.attchmentmarker = attchmentmarker;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
