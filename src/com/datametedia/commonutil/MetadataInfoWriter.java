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
	 * 将信息全部写入到ModelBasicInfo中
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
	 * 将文档中全部的信息写入到modelinfo中
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ModelName() throws DirectoryNotExistException {
		MetedataXMLDBUtil mxb = MetedataXMLDBUtil.getInstance();
		DataSetInfoConfig.getDocNameList().addAll(mxb.getAllDocumentName());
	}

	/**
	 * 将其中所有的metadata的元数据在注册时的doc获取 ；
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2MetadataName() throws DirectoryNotExistException {
		MetedataXMLDBUtil mxb = MetedataXMLDBUtil.getInstance();
		DataSetInfoConfig.getDocNameList().addAll(mxb.getAllDocumentName());
	}

	/**
	 * 删除在MetadataConfig中的节点数据
	 * 
	 * @param docname
	 */
	public static void deleteForMetadataConfig(String docname) {
		// 删除内容
		for (MetadataInfo mbi : DataSetInfoConfig.getMgis()) {
			if (mbi.getDocnameStr().equals(docname)) {
				DataSetInfoConfig.getDocNameList().remove(docname);
				DataSetInfoConfig.getMgis().remove(mbi);
				break;
			}
		}
	}

	/**
	 * 将其中所有的metadata的原始数据保存
	 */
	@SuppressWarnings("unchecked")
	public static void write2MetadataBasicInfo(String docname) {
		MetadataInfo mbi;
		MetedataXMLDBUtil mxb = MetedataXMLDBUtil.getInstance();
		mbi = new MetadataInfo();
		// 设置名称
		mbi.setDocnameStr(docname);
		// 获取数据质量
		List<String> strings = mxb.queryDocumentOfMetadata(
				"dataQualityInfo_statement", docname);
		if (strings != null && strings.size() > 0) {
			mbi.setDataQualityInfo_statement(strings.get(0));
		}
		// 设置数据集标志信息
		writeIdenInfo2MetatadaBasicInfo(mxb, mbi, docname);
		// 设置数据集内容信息
		writeContentInfo2MetadataBasicInfo(mxb, mbi, docname);
		// 设置数据集分发信息
		writeDistributeInfo2MetadataBasicInfo(mxb, mbi, docname);
		// 设置数据集元数据信息
		writeMetadataInfo2MetedataBasicInfo(mxb, mbi, docname);
		DataSetInfoConfig.getMgis().add(mbi);
		DataSetInfoConfig.getDocNameList().add(docname);
	}

	@SuppressWarnings("unchecked")
	private static void writeMetadataInfo2MetedataBasicInfo(
			MetedataXMLDBUtil mxb, MetadataInfo mbi, String docname) {
		// 元数据标志符
		List<String> list = mxb.queryDocumentOfMetadata(
				"metaReference_metadataIdentifier", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_metadataIdentifier(list.get(0));
		}
		// 元数据语种
		list = mxb.queryDocumentOfMetadata("metaReference_metadataLanguage",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_metadataLanguage(list.get(0));
		}
		// 元数据字符集
		list = mxb.queryDocumentOfMetadata(
				"metaReference_metadataCharacterSet", docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_metadataCharacterSet(list.get(0));
		}
		// // 元数据字符集
		// list = mxb.queryDocumentOfMetadata(
		// "metaReference_metadataCharacterSet", docname);
		// if (list != null && list.size() != 0) {
		// mbi.getMbi().setMetaReference_metadataCharacterSet(list.get(0));
		// }
		// 联系人
		list = mxb.queryDocumentOfMetadata("metaReference_individualName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_individualName(list.get(0));
		}
		// 联系单位
		list = mxb.queryDocumentOfMetadata("metaReference_organizationName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getMbi().setMetaReference_organizationName(list.get(0));
		}
		// 联系电话metaReference_voice
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
		// 分发格式名称
		List<String> list = mxb.queryDocumentOfMetadata(
				"distributionInfo_formatName", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_formatName(list.get(0));
		}
		// 分发个人名称
		list = mxb.queryDocumentOfMetadata("distributionInfo_individualName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_individualName(list.get(0));
		}
		// 分发单位
		list = mxb.queryDocumentOfMetadata("distributionInfo_organizationName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_organizationName(list.get(0));
		}
		// 分发方联系电话
		list = mxb.queryDocumentOfMetadata("distributionInfo_voice", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_voice(list.get(0));
		}
		// 分发方传真
		list = mxb.queryDocumentOfMetadata("distributionInfo_facsimile",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_facsimile(list.get(0));
		}
		// 分发方联系地址
		list = mxb.queryDocumentOfMetadata("distributionInfo_deliveryPoint",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_deliveryPoint(list.get(0));
		}
		// 分发方所在城市
		list = mxb.queryDocumentOfMetadata("distributionInfo_city", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_city(list.get(0));
		}
		// 分发方所在行政区域
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_administrativeArea", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_administrativeArea(list.get(0));
		}
		// 分发方所在邮政编码
		list = mxb.queryDocumentOfMetadata("distributionInfo_postalCode",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_postalCode(list.get(0));
		}
		// 分发方所在国家
		list = mxb.queryDocumentOfMetadata("distributionInfo_country", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_country(list.get(0));
		}
		// 分发方电子邮件
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_electronicMailAddress", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_electronicMailAddress(list.get(0));
		}
		// 分发方传输链接
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_linkage", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_linkage(
					list.get(0));
		}
		// 分发方传输协议
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_protocol", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_protocol(
					list.get(0));
		}
		// 分发方传输名称
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_name", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_name(list.get(0));
		}
		// 分发方传输描述
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_description", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_description(
					list.get(0));
		}
		// 分发传输类型
		list = mxb.queryDocumentOfMetadata(
				"distributionInfo_transferOptions_function", docname);
		if (list != null && list.size() != 0) {
			mbi.getDbi().setDistributionInfo_transferOptions_function(
					list.get(0));
		}
	}

	/**
	 * 数据集内容信息
	 * 
	 * @param mxb
	 * @param mbi
	 * @param docname
	 */
	@SuppressWarnings("unchecked")
	private static void writeContentInfo2MetadataBasicInfo(
			MetedataXMLDBUtil mxb, MetadataInfo mbi, String docname) {
		// 数据集资源域
		List<String> list = mxb.queryDocumentOfMetadata(
				"contentInfo_resourceDomain", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().setContentInfo_resourceDomain(list.get(0));
		}
		// 数据集分类
		list = mxb
				.queryDocumentOfMetadata("contentInfo_topicCategory", docname);
		mbi.getDsci().getContentInfo_topicCategory().addAll(list);
		// 地理空间信息中北纬信息contentInfo_spatialExtension
		// 西经
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_westLongitude", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_westLongitude(list.get(0));
		}
		// 东经
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_eastLongitude", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_eastLongitude(list.get(0));
		}
		// 南纬
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_southLatitude", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_southLatitude(list.get(0));
		}
		// 北纬
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_northLatitude", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_northLatitude(list.get(0));
		}
		// 坐标系统
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_coordinateSystem", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci()
					.getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_coordinateSystem(
							list.get(0));
		}
		// 投影方式
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_projection", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_projection(list.get(0));
		}
		// 等分比例尺分母
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_equivalentScale", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci()
					.getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_equivalentScale(
							list.get(0));
		}
		// 采样间距
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_distance", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_distance(list.get(0));
		}
		// 垂直最大值
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_topVertical", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_topVertical(list.get(0));
		}
		// 垂直最小值
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_lowVertical", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_lowVertical(list.get(0));
		}
		// 高程系统
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_spatialExtension_altitudeSystem", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci()
					.getContentInfo_spatialExtension()
					.setContentInfo_spatialExtension_altitudeSystem(list.get(0));
		}
		// 开始时间
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_temporalExtension_beginDate", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_temporalExtension()
					.setContentInfo_temporalExtension_beginDate(list.get(0));
		}
		// 结束时间
		list = mxb.queryDocumentOfMetadata(
				"contentInfo_temporalExtension_endDate", docname);
		if (list != null && list.size() != 0) {
			mbi.getDsci().getContentInfo_temporalExtension()
					.setContentInfo_temporalExtension_endDate(list.get(0));
		}
	}

	/**
	 * 获取相关信息
	 * 
	 * @param mbi
	 * @param docname
	 */
	@SuppressWarnings("unchecked")
	private static void writeIdenInfo2MetatadaBasicInfo(MetedataXMLDBUtil mxb,
			MetadataInfo mbi, String docname) {
		// 获取元数据名称
		List<String> list = mxb.queryDocumentOfMetadata(
				"identificationInfo_title", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_title(list.get(0));
		}
		// 获取数据源摘要
		list = mxb.queryDocumentOfMetadata("identificationInfo_Abstract",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_Abstract(list.get(0));
		}
		// 获取数据摘要时间
		list = mxb.queryDocumentOfMetadata("identificationInfo_date", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_date(list.get(0));
		}
		// 获取数据摘要时间类型
		list = mxb.queryDocumentOfMetadata("identificationInfo_dateType",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_dateType(list.get(0));
		}
		// 数据集联系人
		list = mxb.queryDocumentOfMetadata("identificationInfo_individualName",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_individualName(list.get(0));
		}
		// 数据集联系单位
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_organizationName", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_organizationName(list.get(0));
		}
		// 数据集单位联系电话
		list = mxb.queryDocumentOfMetadata("identificationInfo_voice", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_voice(list.get(0));
		}
		// 数据集单位联系传真
		list = mxb.queryDocumentOfMetadata("identificationInfo_facsimile",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_facsimile(list.get(0));
		}
		// 数据集单位联系地址
		list = mxb.queryDocumentOfMetadata("identificationInfo_deliveryPoint",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_deliveryPoint(list.get(0));
		}
		// 数据采集单位联系人所在城市
		list = mxb.queryDocumentOfMetadata("identificationInfo_city", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_city(list.get(0));
		}
		// 数据采集单位联系人所在区域
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_administrativeArea", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_administrativeArea(list.get(0));
		}
		// 邮政编码
		list = mxb.queryDocumentOfMetadata("identificationInfo_postalCode",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_postalCode(list.get(0));
		}
		// 所在国家
		list = mxb.queryDocumentOfMetadata("identificationInfo_country",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_country(list.get(0));
		}
		// 电子邮件地址
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_electronicMailAddress", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_electronicMailAddress(
					list.get(0));
		}
		// 数据格式名称
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_datasetFormatName", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_datasetFormatName(list.get(0));
		}
		// 数据格式版本
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_datasetFormatVersion", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi()
					.setIdentificationInfo_datasetFormatVersion(list.get(0));
		}
		// 数据集语言
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_datasetLanguage", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_datasetLanguage(list.get(0));
		}
		// 数据集字符编码
		list = mxb.queryDocumentOfMetadata(
				"identificationInfo_datasetCharacterSet", docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_datasetCharacterSet(list.get(0));
		}
		// 数据集目的
		list = mxb.queryDocumentOfMetadata("identificationInfo_purpose",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().setIdentificationInfo_purpose(list.get(0));
		}
		// 关键字
		list = mxb.queryDocumentOfMetadata("identificationInfo_keyword",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getIfi().getIdentificationInfo_keyword().addAll(list);
		}
	}
}
