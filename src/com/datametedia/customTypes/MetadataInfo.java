package com.datametedia.customTypes;

/**
 * 用于保存元数据模型的基本信息
 * 
 * @author yxliang
 * 
 */
public class MetadataInfo {
	private String docnameStr;// 文档名称
	private String dataQualityInfo_statement;// 质量说明
	private Metadata_metadataInfo mbi = new Metadata_metadataInfo();// 元数据信息
	private DistributionInfo dbi = new DistributionInfo();// 数据集分发信息
	private IdentificationInfo ifi = new IdentificationInfo();// 数据集标志信息
	private DataSetContentInfo dsci = new DataSetContentInfo();// 数据集内容信息

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
