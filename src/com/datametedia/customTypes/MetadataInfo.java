package com.datametedia.customTypes;

/**
 * ���ڱ���Ԫ����ģ�͵Ļ�����Ϣ
 * 
 * @author yxliang
 * 
 */
public class MetadataInfo {
	private String docnameStr;// �ĵ�����
	private String dataQualityInfo_statement;// ����˵��
	private Metadata_metadataInfo mbi = new Metadata_metadataInfo();// Ԫ������Ϣ
	private DistributionInfo dbi = new DistributionInfo();// ���ݼ��ַ���Ϣ
	private IdentificationInfo ifi = new IdentificationInfo();// ���ݼ���־��Ϣ
	private DataSetContentInfo dsci = new DataSetContentInfo();// ���ݼ�������Ϣ

	public String getDataQualityInfo_statement() {
		return dataQualityInfo_statement;
	}

	public void setDataQualityInfo_statement(String dataQualityInfo_statement) {
		this.dataQualityInfo_statement = dataQualityInfo_statement;
	}

	public Metadata_metadataInfo getMbi() {
		return mbi;
	}

	public void setMbi(Metadata_metadataInfo mbi) {
		this.mbi = mbi;
	}

	public DistributionInfo getDbi() {
		return dbi;
	}

	public void setDbi(DistributionInfo dbi) {
		this.dbi = dbi;
	}

	public IdentificationInfo getIfi() {
		return ifi;
	}

	public void setIfi(IdentificationInfo ifi) {
		this.ifi = ifi;
	}

	public DataSetContentInfo getDsci() {
		return dsci;
	}

	public void setDsci(DataSetContentInfo dsci) {
		this.dsci = dsci;
	}

	public String getDocnameStr() {
		return docnameStr;
	}

	public void setDocnameStr(String docnameStr) {
		this.docnameStr = docnameStr;
	}
}
