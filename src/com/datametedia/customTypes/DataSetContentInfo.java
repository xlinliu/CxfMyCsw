package com.datametedia.customTypes;

import java.util.ArrayList;
import java.util.List;

public class DataSetContentInfo {
	private String contentInfo_resourceDomain;// ���ݼ���Դ��
	private List<String> contentInfo_topicCategory = new ArrayList<String>();// ���ݼ�����
	private MetadataGeoInfo contentInfo_spatialExtension = new MetadataGeoInfo();
	// ���ݼ��ĵ���ռ���Ϣ
	private MetadataTemporalInfo contentInfo_temporalExtension = new MetadataTemporalInfo();

	// ���ݼ�ʱ����Ϣ

	public String getContentInfo_resourceDomain() {
		return contentInfo_resourceDomain;
	}

	public List<String> getContentInfo_topicCategory() {
		return contentInfo_topicCategory;
	}

	public void setContentInfo_topicCategory(
			List<String> contentInfo_topicCategory) {
		this.contentInfo_topicCategory = contentInfo_topicCategory;
	}

	public void setContentInfo_resourceDomain(String contentInfo_resourceDomain) {
		this.contentInfo_resourceDomain = contentInfo_resourceDomain;
	}

	public MetadataGeoInfo getContentInfo_spatialExtension() {
		return contentInfo_spatialExtension;
	}

	public void setContentInfo_spatialExtension(
			MetadataGeoInfo contentInfo_spatialExtension) {
		this.contentInfo_spatialExtension = contentInfo_spatialExtension;
	}

	public MetadataTemporalInfo getContentInfo_temporalExtension() {
		return contentInfo_temporalExtension;
	}

	public void setContentInfo_temporalExtension(
			MetadataTemporalInfo contentInfo_temporalExtension) {
		this.contentInfo_temporalExtension = contentInfo_temporalExtension;
	}

}
