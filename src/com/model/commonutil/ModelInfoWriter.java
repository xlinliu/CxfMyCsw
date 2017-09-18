package com.model.commonutil;

import java.util.List;

import com.csw.exceptions.DirectoryNotExistException;
import com.model.customTypes.ModelBasicInfo;

/**
 * ��ϵͳ�е�ģ����Ϣ��ȫд�뵽���õ��ࡾModelInfoConfig����
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
	 * ɾ��ָ��berkeley�е�docname��Ӧ��modelbasicinfo��Ϣ
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
	 * ���������ĵ��Ļ�����Ϣ���뵽ModelBasicInfo��
	 * 
	 * @param docname
	 */
	@SuppressWarnings("unchecked")
	public static void writeDocument2ModelBasicInfo(String docname) {
		ModelBasicInfo mbi;
		ModelXMLDBUtil mxb = ModelXMLDBUtil.getInstance();
		mbi = new ModelBasicInfo();
		mbi.setDocname(docname);
		// ��ȡ�ؼ���
		List<String> keywords = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_keywords_keyword", docname);
		mbi.getKeywords().addAll(keywords);
		// ��ȡģ�ͱ�ʶ��
		List<String> ModelID = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelID", docname);
		if (ModelID != null && ModelID.size() != 0) {
			mbi.setModelID(ModelID.get(0));
		}
		// ��ȡģ��ȫ��
		List<String> Modelfullname = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelName_fullName", docname);
		if (Modelfullname != null && Modelfullname.size() != 0) {
			mbi.setFullName(Modelfullname.get(0));
		}
		// ��ȡģ�ͼ��
		List<String> Modelshortname = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelName_shortName", docname);
		if (Modelshortname != null && Modelshortname.size() != 0) {
			mbi.setShortName(Modelshortname.get(0));
		}
		// ��ȡģ������
		List<String> ModelType = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelType", docname);
		if (ModelType != null && ModelType.size() != 0) {
			mbi.setModelType(ModelType.get(0));
		}
		// ��ȡ������
		List<String> ModelsubType = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelSubType", docname);
		System.out.println(ModelsubType + " : ModelsubType");
		if (ModelType != null && ModelType.size() != 0) {
			mbi.setModelSubType(ModelsubType.get(0));
		}
		// ��ȡģ��Ӧ������
		List<String> ModelDomain = (List<String>) mxb.queryDocumentOfModel(
				"model_characteristics_domains_domain", docname);
		mbi.setModelDomain(ModelDomain);
		// ��ȡ����
		List<String> modellevel = (List<String>) mxb.queryDocumentOfModel(
				"model_identification_modelLevel", docname);
		if (modellevel != null && modellevel.size() != 0) {
			mbi.setModelLevel(modellevel.get(0));
		}
		// ��ȡ����
		List<String> modelfun = (List<String>) mxb.queryDocumentOfModel(
				"model_characteristics_function", docname);
		if (modelfun != null && modelfun.size() != 0) {
			mbi.setFunction(modelfun.get(0));
		}
		// ��ȡ��ϵ��
		List<String> modelperson = (List<String>) mxb
				.queryDocumentOfModel(
						"model_administration_contacters_contact_ResponsibleParty_individualName",
						docname);
		if (modelperson != null && modelperson.size() != 0) {
			mbi.setModelperson(modelperson.get(0));
		}
		// ��ȡ������λ
		List<String> modelorganname = (List<String>) mxb
				.queryDocumentOfModel(
						"model_administration_contacters_contact_ResponsibleParty_OrganizationName",
						docname);
		if (modelorganname != null && modelorganname.size() != 0) {
			mbi.setModelorganname(modelorganname.get(0));
		}
		// ��ȡģ�͵Ŀռ䷶Χ�еĶ�������������γ����γ
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
		// ��ȡģ�͵���������
		List<String> performanceType = mxb.queryDocumentOfModel(
				"model_performance_performanceGoal_performanceType", docname);
		if (performanceType != null && performanceType.size() != 0) {
			try {
				mbi.setPerformanceType(performanceType.get(0));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// ��ȡģ�͵ľ��ȵ�λ
		List<String> precisionunit = mxb.queryDocumentOfModel(
				"model_performance_performanceGoal_precision_unit", docname);
		if (precisionunit != null && precisionunit.size() != 0) {
			try {
				mbi.setPreciseunit(precisionunit.get(0));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// ��ȡģ�͵ľ���ֵ
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
	 * ����Ϣȫ��д�뵽ModelBasicInfo��
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
	 * ���ĵ���ȫ������Ϣд�뵽modelinfo��
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ModelName() throws DirectoryNotExistException {
		ModelXMLDBUtil mxb = ModelXMLDBUtil.getInstance();
		ModelInfoConfig.getDocnames().addAll(mxb.getAllDocumentName());
	}
}
