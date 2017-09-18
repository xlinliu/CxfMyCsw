package com.csw.utils.custometypes;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CxfFile")
@XmlAccessorType(XmlAccessType.FIELD)
public class CxfFile {
	private String candidateName;// ������
	private String resultFileType;// ��������
	private DataHandler resume;// ����

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getResultFileType() {
		return resultFileType;
	}

	public void setResultFileType(String resultFileType) {
		this.resultFileType = resultFileType;
	}

	public DataHandler getResume() {
		return resume;
	}

	public void setResume(DataHandler resume) {
		this.resume = resume;
	}

}
