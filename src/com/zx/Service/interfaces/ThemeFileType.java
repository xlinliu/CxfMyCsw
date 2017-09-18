/**
 * 
 */
package com.zx.Service.interfaces;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-22 下午07:41:37
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ThemeFileType {
	private String filewebpath;// 文件存储的web路径
	private String filelocalpath;// 文件存储的本地路径
	private String filename;// 文件的名称
	private String typename;// 文件后缀名
	@XmlMimeType("application/octet-stream")
	private DataHandler imageData;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public DataHandler getImageData() {
		return imageData;
	}

	public void setImageData(DataHandler imageData) {
		this.imageData = imageData;
	}

	public String getFilewebpath() {
		return filewebpath;
	}

	public void setFilewebpath(String filewebpath) {
		this.filewebpath = filewebpath;
	}

	public String getFilelocalpath() {
		return filelocalpath;
	}

	public void setFilelocalpath(String filelocalpath) {
		this.filelocalpath = filelocalpath;
	}

}
