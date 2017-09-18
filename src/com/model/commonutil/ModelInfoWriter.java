package com.model.commonutil;

import java.util.List;

import com.csw.exceptions.DirectoryNotExistException;
import com.model.customTypes.ModelBasicInfo;

/**
 * 将系统中的模型信息完全写入到配置的类【ModelInfoConfig】中
 * 
 * @author yxliang
 * 
 */
public class ModelInfoWriter {
	public static void main(String[] args) throws DirectoryNotExistException {
		ModelInfoWriter.write2ModelBasicInfo();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			System.out.println(mbi.getModelID());
			System.out.println(mbi.getModelType());
			System.out.println(mbi.getModelSubType());
			System.out.println(mbi.getKeywords());
			System.out.println(mbi.getShortName());
			System.out.println(mbi.getFullName());
		}
		for (String m : ModelInfoConfig.getDocnames()) {
			System.out.println(m);
		}
	}

	/**
	 * 删除指定berkeley中的docname对应的modelbasicinfo信息
	 * 
	 * @param docname
	 */
	public static void deleteDocumentFromModelBasicInfo(String docname) {
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getDocname().equals(docname)) {
				ModelInfoConfig.getMbInfos().remove(mbi);
				ModelInfoConfig.getDocnames().remove(docname);
				break;
			}
		}
	}

	/**
	 * 将新增的文档的基本信息加入到ModelBasicInfo中
	 * 
	 * @param docname
	 */
	@SuppressWarnings("unchecked")
	public static void writeDocument2ModelBasicInfo(String docname) {
		ModelBasicInfo mbi;
		ModelXMLDBUtil mxb = ModelXMLDBUtil.getInstance();
		mbi = new ModelBasicInfo();
		mbi.setDocname(docname);
		// 获取关键字
		List<String> keywords = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_keywords_keyword", docname);
		mbi.getKeywords().addAll(keywords);
		// 获取模型标识符
		List<String> ModelID = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelID", docname);
		if (ModelID != null && ModelID.size() != 0) {
			mbi.setModelID(ModelID.get(0));
		}
		// 获取模型全称
		List<String> Modelfullname = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelName_fullName", docname);
		if (Modelfullname != null && Modelfullname.size() != 0) {
			mbi.setFullName(Modelfullname.get(0));
		}
		// 获取模型简称
		List<String> Modelshortname = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelName_shortName", docname);
		if (Modelshortname != null && Modelshortname.size() != 0) {
			mbi.setShortName(Modelshortname.get(0));
		}
		// 获取模型类型
		List<String> ModelType = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelType", docname);
		if (ModelType != null && ModelType.size() != 0) {
			mbi.setModelType(ModelType.get(0));
		}
		// 获取子类型
		List<String> ModelsubType = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelSubType", docname);
		System.out.println(ModelsubType + " : ModelsubType");
		if (ModelType != null && ModelType.size() != 0) {
			mbi.setModelSubType(ModelsubType.get(0));
		}
		// 获取模型应用领域
		List<String> ModelDomain = (List<String>) mxb.queryDocumentOfModel(
				"model_characteristics_domains_domain", docname);
		mbi.setModelDomain(ModelDomain);
		// 获取级别
		List<String> modellevel = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelLevel", docname);
		if (modellevel != null && modellevel.size() != 0) {
			mbi.setModelLevel(modellevel.get(0));
		}
		// 获取功能
		List<String> modelfun = (List<String>) mxb.queryDocumentOfModel(
				"model_characteristics_function", docname);
		if (modelfun != null && modelfun.size() != 0) {
			mbi.setFunction(modelfun.get(0));
		}
		// 获取联系人
		List<String> modelperson = (List<String>) mxb
				.queryDocumentOfModel(
						"model_administration_contacters_contact_ResponsibleParty_individualName",
						docname);
		if (modelperson != null && modelperson.size() != 0) {
			mbi.setModelperson(modelperson.get(0));
		}
		// 获取工作单位
		List<String> modelorganname = (List<String>) mxb
				.queryDocumentOfModel(
						"model_administration_contacters_contact_ResponsibleParty_OrganizationName",
						docname);
		if (modelorganname != null && modelorganname.size() != 0) {
			mbi.setModelorganname(modelorganname.get(0));
		}
		// 获取模型的空间范围中的东经，西经，北纬，南纬
		List<String> northvalues = mxb.queryDocumentOfModel(
				"model_spatiality_spatialScope_scope_north", docname);
		if (northvalues != null && northvalues.size() != 0) {
			try {
				mbi.setNorthvalue(Double.parseDouble(northvalues.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		List<String> southvalues = mxb.queryDocumentOfModel(
				"model_spatiality_spatialScope_scope_south", docname);
		if (southvalues != null && southvalues.size() != 0) {
			try {
				mbi.setSouthvalue(Double.parseDouble(southvalues.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		List<String> westvalues = mxb.queryDocumentOfModel(
				"model_spatiality_spatialScope_scope_west", docname);
		if (westvalues != null && westvalues.size() != 0) {
			try {
				mbi.setWestvalue(Double.parseDouble(westvalues.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		List<String> eastvalues = mxb.queryDocumentOfModel(
				"model_spatiality_spatialScope_scope_east", docname);
		if (eastvalues != null && eastvalues.size() != 0) {
			try {
				mbi.setEastvalue(Double.parseDouble(eastvalues.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// 获取模型的性能类型
		List<String> performanceType = mxb.queryDocumentOfModel(
				"model_performance_performanceGoal_performanceType", docname);
		if (performanceType != null && performanceType.size() != 0) {
			try {
				mbi.setPerformanceType(performanceType.get(0));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// 获取模型的精度单位
		List<String> precisionunit = mxb.queryDocumentOfModel(
				"model_performance_performanceGoal_precision_unit", docname);
		if (precisionunit != null && precisionunit.size() != 0) {
			try {
				mbi.setPreciseunit(precisionunit.get(0));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// 获取模型的精度值
		List<String> precisionvalue = mxb.queryDocumentOfModel(
				"model_performance_performanceGoal_precision_value", docname);
		if (precisionvalue != null && precisionvalue.size() != 0) {
			try {
				mbi.setPrecisevalue(precisionvalue.get(0));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		ModelInfoConfig.getMbInfos().add(mbi);
		ModelInfoConfig.getDocnames().add(docname);
	}

	/**
	 * 将信息全部写入到ModelBasicInfo中
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ModelBasicInfo() throws DirectoryNotExistException {
		ModelXMLDBUtil mxb = ModelXMLDBUtil.getInstance();
		for (String docname : mxb.getAllDocumentName()) {
			writeDocument2ModelBasicInfo(docname);
		}
	}

	/**
	 * 将文档中全部的信息写入到modelinfo中
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ModelName() throws DirectoryNotExistException {
		ModelXMLDBUtil mxb = ModelXMLDBUtil.getInstance();
		ModelInfoConfig.getDocnames().addAll(mxb.getAllDocumentName());
	}
}
