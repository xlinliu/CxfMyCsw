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
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-22 ����07:41:37
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ThemeFileType {
	private String filewebpath;// �ļ��洢��web·��
	private String filelocalpath;// �ļ��洢�ı���·��
	private String filename;// �ļ�������
	private String typename;// �ļ���׺��
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
