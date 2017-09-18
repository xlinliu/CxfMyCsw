/**
 * 
 */
package com.csw.utils.custometypes;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-6 下午02:07:57
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SensorAttachmentType {
	private String typename;
	@XmlMimeType("application/octet-stream")
	private DataHandler imageData;

	public DataHandler getImageData() {
		return imageData;
	}

	public void setImageData(DataHandler imageData) {
		this.imageData = imageData;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}
