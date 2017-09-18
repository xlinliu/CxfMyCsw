package com.datametedia.customTypes;

import java.util.ArrayList;
import java.util.List;

public class IdentificationInfo {
	private String identificationInfo_title;// 数据集名称
	private String identificationInfo_Abstract;// 数据集摘要
	private String identificationInfo_date;// 数据集修订日期
	private String identificationInfo_dateType;// 数据集日期类型creation\publication\revision
	private String identificationInfo_individualName;// 数据集联系人
	private String identificationInfo_organizationName;// 数据集联系单位
	private String identificationInfo_voice;// 数据集负责人联系人电话
	private String identificationInfo_facsimile;// 数据集负责人联系传真
	private String identificationInfo_deliveryPoint;// 数据负责人联系地址
	private String identificationInfo_city;// 负责人所在城市
	private String identificationInfo_administrativeArea;// 负责方所在行政区域
	private String identificationInfo_postalCode;// 负责方所在地邮编
	private String identificationInfo_country;// 负责人所在国家
	private String identificationInfo_electronicMailAddress;// 负责方电子邮件地址
	private String identificationInfo_datasetFormatName;// 负责方数据集的格式名称
	private String identificationInfo_datasetFormatVersion;// 负责方数据集的格式版本
	private String identificationInfo_datasetLanguage;// 负责方数据集的语种
	private String identificationInfo_datasetCharacterSet;// 负责方数据集的字符编码
	private String identificationInfo_purpose;// 数据集的目的
	private List<String> identificationInfo_keyword = new ArrayList<String>();// 数据集关键字

	public String getIdentificationInfo_title() {
		return identificationInfo_title;
	}

	public void setIdentificationInfo_title(String identificationInfo_title) {
		this.identificationInfo_title = identificationInfo_title;
	}

	public String getIdentificationInfo_Abstract() {
		return identificationInfo_Abstract;
	}

	public void setIdentificationInfo_Abstract(
			String identificationInfo_Abstract) {
		this.identificationInfo_Abstract = identificationInfo_Abstract;
	}

	public String getIdentificationInfo_date() {
		return identificationInfo_date;
	}

	public void setIdentificationInfo_date(String identificationInfo_date) {
		this.identificationInfo_date = identificationInfo_date;
	}

	public String getIdentificationInfo_dateType() {
		return identificationInfo_dateType;
	}

	public void setIdentificationInfo_dateType(
			String identificationInfo_dateType) {
		this.identificationInfo_dateType = identificationInfo_dateType;
	}

	public String getIdentificationInfo_individualName() {
		return identificationInfo_individualName;
	}

	public void setIdentificationInfo_individualName(
			String identificationInfo_individualName) {
		this.identificationInfo_individualName = identificationInfo_individualName;
	}

	public String getIdentificationInfo_organizationName() {
		return identificationInfo_organizationName;
	}

	public void setIdentificationInfo_organizationName(
			String identificationInfo_organizationName) {
		this.identificationInfo_organizationName = identificationInfo_organizationName;
	}

	public String getIdentificationInfo_voice() {
		return identificationInfo_voice;
	}

	public void setIdentificationInfo_voice(String identificationInfo_voice) {
		this.identificationInfo_voice = identificationInfo_voice;
	}

	public String getIdentificationInfo_facsimile() {
		return identificationInfo_facsimile;
	}

	public void setIdentificationInfo_facsimile(
			String identificationInfo_facsimile) {
		this.identificationInfo_facsimile = identificationInfo_facsimile;
	}

	public String getIdentificationInfo_deliveryPoint() {
		return identificationInfo_deliveryPoint;
	}

	public void setIdentificationInfo_deliveryPoint(
			String identificationInfo_deliveryPoint) {
		this.identificationInfo_deliveryPoint = identificationInfo_deliveryPoint;
	}

	public String getIdentificationInfo_city() {
		return identificationInfo_city;
	}

	public void setIdentificationInfo_city(String identificationInfo_city) {
		this.identificationInfo_city = identificationInfo_city;
	}

	public String getIdentificationInfo_administrativeArea() {
		return identificationInfo_administrativeArea;
	}

	public void setIdentificationInfo_administrativeArea(
			String identificationInfo_administrativeArea) {
		this.identificationInfo_administrativeArea = identificationInfo_administrativeArea;
	}

	public String getIdentificationInfo_postalCode() {
		return identificationInfo_postalCode;
	}

	public void setIdentificationInfo_postalCode(
			String identificationInfo_postalCode) {
		this.identificationInfo_postalCode = identificationInfo_postalCode;
	}

	public String getIdentificationInfo_country() {
		return identificationInfo_country;
	}

	public void setIdentificationInfo_country(String identificationInfo_country) {
		this.identificationInfo_country = identificationInfo_country;
	}

	public String getIdentificationInfo_electronicMailAddress() {
		return identificationInfo_electronicMailAddress;
	}

	public void setIdentificationInfo_electronicMailAddress(
			String identificationInfo_electronicMailAddress) {
		this.identificationInfo_electronicMailAddress = identificationInfo_electronicMailAddress;
	}

	public String getIdentificationInfo_datasetFormatName() {
		return identificationInfo_datasetFormatName;
	}

	public void setIdentificationInfo_datasetFormatName(
			String identificationInfo_datasetFormatName) {
		this.identificationInfo_datasetFormatName = identificationInfo_datasetFormatName;
	}

	public String getIdentificationInfo_datasetFormatVersion() {
		return identificationInfo_datasetFormatVersion;
	}

	public void setIdentificationInfo_datasetFormatVersion(
			String identificationInfo_datasetFormatVersion) {
		this.identificationInfo_datasetFormatVersion = identificationInfo_datasetFormatVersion;
	}

	public String getIdentificationInfo_datasetLanguage() {
		return identificationInfo_datasetLanguage;
	}

	public void setIdentificationInfo_datasetLanguage(
			String identificationInfo_datasetLanguage) {
		this.identificationInfo_datasetLanguage = identificationInfo_datasetLanguage;
	}

	public String getIdentificationInfo_datasetCharacterSet() {
		return identificationInfo_datasetCharacterSet;
	}

	public void setIdentificationInfo_datasetCharacterSet(
			String identificationInfo_datasetCharacterSet) {
		this.identificationInfo_datasetCharacterSet = identificationInfo_datasetCharacterSet;
	}

	public String getIdentificationInfo_purpose() {
		return identificationInfo_purpose;
	}

	public void setIdentificationInfo_purpose(String identificationInfo_purpose) {
		this.identificationInfo_purpose = identificationInfo_purpose;
	}

	public List<String> getIdentificationInfo_keyword() {
		return identificationInfo_keyword;
	}

	public void setIdentificationInfo_keyword(
			List<String> identificationInfo_keyword) {
		this.identificationInfo_keyword = identificationInfo_keyword;
	}

}
