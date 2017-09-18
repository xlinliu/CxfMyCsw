package com.datametedia.commonutil;

import java.util.List;
import com.datametedia.customTypes.DataSetInfoConfig;
import com.datametedia.customTypes.MetadataInfo;
import com.csw.exceptions.DirectoryNotExistException;

public class MetadataInfoWriter {
	// public static void main(String[] args) throws DirectoryNotExistException
	// {
	// MetadataInfoWriter miw = new MetadataInfoWriter();
	// miw.write2ModelBasicInfo();
	// for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
	// System.out.println(mi.getMbi()
	// .getMetaReference_administrativeArea());
	// System.out.println(mi.getDbi().getDistributionInfo_city());
	// }
	// }

	/**
	 * ����Ϣȫ��д�뵽ModelBasicInfo��
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ModelBasicInfo() throws DirectoryNotExistException {
		MetedataXMLDBUtil mxb = MetedataXMLDBUtil.getInstance();
		for (String docname : mxb.getAllDocumentName()) {
			write2MetadataBasicInfo(docname);
		}
	}

	/**
	 * ���ĵ���ȫ������Ϣд�뵽modelinfo��
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ModelName() throws DirectoryNotExistException {
		MetedataXMLDBUtil mxb = MetedataXMLDBUtil.getInstance();
		DataSetInfoConfig.getDocNameList().addAll(mxb.getAllDocumentName());
	}

	/**
	 * ���������е�metadata��Ԫ������ע��ʱ��doc��ȡ ��
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2MetadataName() throws DirectoryNotExistException {
		MetedataXMLDBUtil mxb = MetedataXMLDBUtil.getInstance();
		DataSetInfoConfig.getDocNameList().addAll(mxb.getAllDocumentName());
	}

	/**
	 * ɾ����MetadataConfig�еĽڵ�����
	 * 
	 * @param docname
	 */
	public static void deleteForMetadataConfig(String docname) {
		// ɾ������
		for (MetadataInfo mbi : DataSetInfoConfig.getMgis()) {
			if (mbi.getDocnameStr().equals(docname)) {
				DataSetInfoConfig.getDocNameList().remove(docname);
				DataSetInfoConfig.getMgis().remove(mbi);
				break;
			}
		}
	}

	/**
	 * ���������е�metadata��ԭʼ���ݱ���
	 */
	@SuppressWarnings("unchecked")
	public static void write2MetadataBasicInfo(String docname) {
		MetadataInfo mbi;
		MetedataXMLDBUtil mxb = MetedataXMLDBUtil.getInstance();
		mbi = new MetadataInfo();
		// ��������
		mbi.setDocnameStr(docname);
		// ��ȡ��������
		List<String> strings = mxb.queryDocumentOfMetadata(
				"dataQualityInfo_statement", docname);
		if (strings != null && strings.size() > 0) {
			mbi.setDataQualityInfo_statement(strings.get(0));
		}
		// �������ݼ���־��Ϣ
		writeIdenInfo2MetatadaBasicInfo(mxb, mbi, docname);
		// �������ݼ�������Ϣ
		writeContentInfo2MetadataBasicInfo(mxb, mbi, docname);
		// �������ݼ��ַ���Ϣ
		writeDistributeInfo2MetadataBasicInfo(mxb, mbi, docname);
		// �������ݼ�Ԫ������Ϣ
		writeMetadataInfo2MetedataBasicInfo(mxb, mbi, docname);
		DataSetInfoConfig.getMgis().add(mbi);
		DataSetInfoConfig.getDocNameList().add(docname);
	}

	@SuppressWarnings("unchecked")
	private static void writeMetadataInfo2MetedataBasicInfo(
			MetedataXMLDBUtil mxb, MetadataInfo mbi, String docname) {
		// Ԫ���ݱ�־��
		List<String> list = mxb.queryDocumentOfMetadata(
				"metaReference_metadataIdentifier", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_metadataIdentifier(list.get(0));
		}
		// Ԫ��������
		list = mxb.queryDocumentOfMetadata("metaReference_metadataLanguage",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_metadataLanguage(list.get(0));
		}
		// Ԫ�����ַ���
		list = mxb.queryDocumentOfMetadata(
				"metaReference_metadataCharacterSet", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_metadataCharacterSet(list.get(0));
		}
		// // Ԫ�����ַ���
		// list = mxb.queryDocumentOfMetadata(
		// "metaReference_metadataCharacterSet", docname);
		// if (list != null && list.size() != 0) {
		// mbi.getMbi().setMetaReference_metadataCharacterSet(list.get(0));
		// }
		// ��ϵ��
		list = mxb.queryDocumentOfMetadata("metaReference_individualName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_individualName(list.get(0));
		}
		// ��ϵ��λ
		list = mxb.queryDocumentOfMetadata("metaReference_organizationName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_organizationName(list.get(0));
		}
		// ��ϵ�绰metaReference_voice
		list = mxb.queryDocumentOfMetadata("metaReference_voice", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_voice(list.get(0));
		}
		// metaReference_facsimile
		list = mxb.queryDocumentOfMetadata("metaReference_facsimile", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_facsimile(list.get(0));
		}
		// metaReference_deliveryPoint
		list = mxb.queryDocumentOfMetadata("metaReference_deliveryPoint",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_deliveryPoint(list.get(0));
		}
		// metaReference_city
		list = mxb.queryDocumentOfMetadata("metaReference_city", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_city(list.get(0));
		}
		// metaReference_administrativeArea
		list = mxb.queryDocumentOfMetadata("metaReference_administrativeArea",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_administrativeArea(list.get(0));
		}
		// metaReference_postalCode
		list = mxb.queryDocumentOfMetadata("metaReference_postalCode", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_postalCode(list.get(0));
		}
		// metaReference_country
		list = mxb.queryDocumentOfMetadata("metaReference_country", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_country(list.get(0));
		}
		// metaReference_electronicMailAddress
		list = mxb.queryDocumentOfMetadata(
				"metaReference_electronicMailAddress", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_electronicMailAddress(list.get(0));
		}
		// metaReference_metadataDateStamp
		list = mxb.queryDocumentOfMetadata("metaReference_metadataDateStamp",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_metadataDateStamp(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private static void writeDistributeInfo2MetadataBasicInfo(
			MetedataXMLDBUtil mxb, MetadataInfo mbi, String docname) {
		// �ַ���ʽ����
		List<String> list = mxb.queryDocumentOfMetadata(
				"distributionInfo_formatName", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_formatName(list.get(0));
		}
		// �ַ���������
		list = mxb.queryDocumentOfMetadata("distributionInfo_individualName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_individualName(list.get(0));
		}
		// �ַ���λ
		list = mxb.queryDocumentOfMetadata("distributionInfo_organizationName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_organizationName(list.get(0));
		}
		// �ַ�����ϵ�绰
		list = mxb.queryDocumentOfMetadata("distributionInfo_voice", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_voice(list.get(0));
		}
		// �ַ�������
		list = mxb.queryDocumentOfMetadata("distributionInfo_facsimile",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_facsimile(list.get(0));
		}
		// �ַ�����ϵ��ַ
		list = mxb.queryDocumentOfMetadata("distributionInfo_deliveryPoint",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_deliveryPoint(list.get(0));
		}
		// �ַ������ڳ���
		list = mxb.queryDocumentOfMetadata("distributionInfo_city", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_city(list.get(0));
		}
		// �ַ���������������
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_administrativeArea", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_administrativeArea(list.get(0));
		}
		// �ַ���������������
		list = mxb.queryDocumentOfMetadata("distributionInfo_postalCode",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_postalCode(list.get(0));
		}
		// �ַ������ڹ���
		list = mxb.queryDocumentOfMetadata("distributionInfo_country", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_country(list.get(0));
		}
		// �ַ��������ʼ�
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_electronicMailAddress", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_electronicMailAddress(list.get(0));
		}
		// �ַ�����������
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_linkage", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_linkage(
					list.get(0));
		}
		// �ַ�������Э��
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_protocol", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_protocol(
					list.get(0));
		}
		// �ַ�����������
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_name", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_name(list.get(0));
		}
		// �ַ�����������
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_description", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_description(
					list.get(0));
		}
		// �ַ���������
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_function", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_function(
					list.get(0));
		}
	}

	/**
	 * ���ݼ�������Ϣ
	 * 
	 * @param mxb
	 * @param mbi
	 * @param docname
	 */
	@SuppressWarnings("unchecked")
	private static void writeContentInfo2MetadataBasicInfo(
			MetedataXMLDBUtil mxb, MetadataInfo mbi, String docname) {
		// ���ݼ���Դ��
		List<String> list = mxb.queryDocumentOfMetadata(
				"contentInfo_resourceDomain", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().setContentInfo_resourceDomain(list.get(0));
		}
		// ���ݼ�����
		list = mxb
				.queryDocumentOfMetadata("contentInfo_topicCategory", docname);
		mbi.getDsci().getContentInfo_topicCategory().addAll(list);
		// ����ռ���Ϣ�б�γ��ϢcontentInfo_spatialExtension
		// ����
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_westLongitude", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_westLongitude(list.get(0));
		}
		// ����
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_eastLongitude", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_eastLongitude(list.get(0));
		}
		// ��γ
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_southLatitude", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_southLatitude(list.get(0));
		}
		// ��γ
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_northLatitude", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_northLatitude(list.get(0));
		}
		// ����ϵͳ
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_coordinateSystem", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci()
					.getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_coordinateSystem(
							list.get(0));
		}
		// ͶӰ��ʽ
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_projection", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_projection(list.get(0));
		}
		// �ȷֱ����߷�ĸ
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_equivalentScale", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci()
					.getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_equivalentScale(
							list.get(0));
		}
		// �������
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_distance", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_distance(list.get(0));
		}
		// ��ֱ���ֵ
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_topVertical", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_topVertical(list.get(0));
		}
		// ��ֱ��Сֵ
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_lowVertical", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_lowVertical(list.get(0));
		}
		// �߳�ϵͳ
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_altitudeSystem", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci()
					.getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_altitudeSystem(list.get(0));
		}
		// ��ʼʱ��
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_temporalExtension_beginDate", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_temporalExtension()
					.setContentInfo_temporalExtension_beginDate(list.get(0));
		}
		// ����ʱ��
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_temporalExtension_endDate", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_temporalExtension()
					.setContentInfo_temporalExtension_endDate(list.get(0));
		}
	}

	/**
	 * ��ȡ�����Ϣ
	 * 
	 * @param mbi
	 * @param docname
	 */
	@SuppressWarnings("unchecked")
	private static void writeIdenInfo2MetatadaBasicInfo(MetedataXMLDBUtil mxb,
			MetadataInfo mbi, String docname) {
		// ��ȡԪ��������
		List<String> list = mxb.queryDocumentOfMetadata(
				"identificationInfo_title", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_title(list.get(0));
		}
		// ��ȡ����ԴժҪ
		list = mxb.queryDocumentOfMetadata("identificationInfo_Abstract",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_Abstract(list.get(0));
		}
		// ��ȡ����ժҪʱ��
		list = mxb.queryDocumentOfMetadata("identificationInfo_date", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_date(list.get(0));
		}
		// ��ȡ����ժҪʱ������
		list = mxb.queryDocumentOfMetadata("identificationInfo_dateType",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_dateType(list.get(0));
		}
		// ���ݼ���ϵ��
		list = mxb.queryDocumentOfMetadata("identificationInfo_individualName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_individualName(list.get(0));
		}
		// ���ݼ���ϵ��λ
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_organizationName", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_organizationName(list.get(0));
		}
		// ���ݼ���λ��ϵ�绰
		list = mxb.queryDocumentOfMetadata("identificationInfo_voice", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_voice(list.get(0));
		}
		// ���ݼ���λ��ϵ����
		list = mxb.queryDocumentOfMetadata("identificationInfo_facsimile",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_facsimile(list.get(0));
		}
		// ���ݼ���λ��ϵ��ַ
		list = mxb.queryDocumentOfMetadata("identificationInfo_deliveryPoint",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_deliveryPoint(list.get(0));
		}
		// ���ݲɼ���λ��ϵ�����ڳ���
		list = mxb.queryDocumentOfMetadata("identificationInfo_city", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_city(list.get(0));
		}
		// ���ݲɼ���λ��ϵ����������
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_administrativeArea", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_administrativeArea(list.get(0));
		}
		// ��������
		list = mxb.queryDocumentOfMetadata("identificationInfo_postalCode",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_postalCode(list.get(0));
		}
		// ���ڹ���
		list = mxb.queryDocumentOfMetadata("identificationInfo_country",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_country(list.get(0));
		}
		// �����ʼ���ַ
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_electronicMailAddress", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_electronicMailAddress(
					list.get(0));
		}
		// ���ݸ�ʽ����
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_datasetFormatName", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_datasetFormatName(list.get(0));
		}
		// ���ݸ�ʽ�汾
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_datasetFormatVersion", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi()
					.setIdentificationInfo_datasetFormatVersion(list.get(0));
		}
		// ���ݼ�����
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_datasetLanguage", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_datasetLanguage(list.get(0));
		}
		// ���ݼ��ַ�����
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_datasetCharacterSet", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_datasetCharacterSet(list.get(0));
		}
		// ���ݼ�Ŀ��
		list = mxb.queryDocumentOfMetadata("identificationInfo_purpose",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_purpose(list.get(0));
		}
		// �ؼ���
		list = mxb.queryDocumentOfMetadata("identificationInfo_keyword",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().getIdentificationInfo_keyword().addAll(list);
		}
	}
}
