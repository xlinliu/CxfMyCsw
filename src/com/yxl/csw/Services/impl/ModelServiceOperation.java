package com.yxl.csw.Services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.model.commonutil.ModelInfoConfig;
import com.model.commonutil.ModelInfoWriter;
import com.model.commonutil.ModelXMLDBUtil;
import com.model.customTypes.ModelBasicInfo;
import com.model.customTypes.ModelOperationTypes;
import com.model.customTypes.ModelParamNotFormException;
import com.model.customTypes.ModelQueryParam;
import com.model.customTypes.ModelQueryParamList;
import com.yxl.csw.Services.ModelService;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.ModelNotExistException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.utils.Userutils.UserInfoUtil;

public class ModelServiceOperation implements ModelService {
	private ModelXMLDBUtil mxb;
	public ModelServiceOperation() {
		mxb = ModelXMLDBUtil.getInstance();
		File files=new File(ModelXMLDBUtil.getXMLSTORE_LOCATION());
		for(File file:files.listFiles()){
			try {
				ModelInfoWriter.writeDocument2ModelBasicInfo(file.getName().replace(".xml",""));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	/**
	 * 根据条件查询模型
	 * @param username
	 * @param password
	 * @param mpl
	 * @return
	 * @throws ModelParamNotFormException 
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	public List<ModelBasicInfo> getModelByCondition(String username,String password,ModelQueryParamList mpl) throws ModelParamNotFormException, ServiceException, NullZeroException{
		UserInfoUtil.CheckUserLogin(username, password);
		List<ModelBasicInfo> resultList = getAllModelBasicInfo();
		List<ModelBasicInfo> result=new ArrayList<ModelBasicInfo>();
		for(ModelBasicInfo mbi:resultList){
			result.add(mbi);
		}
		if(result==null|| result.size()==0){
			return result;
		}
		if (mpl == null || mpl.getMqp() == null
				&& mpl.getMqp().size() == 0) {
			throw new ModelParamNotFormException("请求参数列表不能为空!");
		}
		List<ModelQueryParam> mqparams = mpl.getMqp();
		if (mqparams != null) {
			for (ModelQueryParam mqp : mqparams) {
				if (mqp.getEot() == null) {
					throw new ModelParamNotFormException(
							"输入参数中必须包含ModelOperationTypes类型的值");
				}
				if (mqp.getEot().equals(
						ModelOperationTypes.MODEL_APPLICATION_TEXT_ONE_CONTAIN)) {
					// 查找模型的预期应用
					if (mqp.getValue() != null && mqp.getValue() != null
							&& mqp.getValue().size() == 1) {
						result.retainAll(getModelBasicInfoByApplication(username, password, mqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new ModelParamNotFormException(
								"MODEL_APPLICATION_TEXT_N_CONTAIN 输入参数不正确！");
					}
				}else if(mqp.getEot().equals(ModelOperationTypes.MODEL_FUNCTION_TEXT_ONE_CONTAIN)){
					// 查找模型的函数
					if (mqp.getValue() != null && mqp.getValue() != null
							&& mqp.getValue().size() == 1) {
						result.retainAll(getModelBasicInfoByFunction(username, password, mqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new ModelParamNotFormException(
								"MODEL_FUNCTION_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				}else if(mqp.getEot().equals(ModelOperationTypes.MODEL_ID_TEXT_ONE_CONTAIN)){
					// 查找模型id
					if (mqp.getValue() != null && mqp.getValue() != null
							&& mqp.getValue().size() == 1) {
						try {
							ModelBasicInfo mbi=	getModelBasicInfoById(username, password, mqp.getValue().get(0));
							if(mbi==null){
								return null;
							}else {
								if(result.contains(mbi)){
									result=new ArrayList<ModelBasicInfo>();
									result.add(mbi);
								}
							}
						} catch (ModelNotExistException e) {
							e.printStackTrace();
						}
					} else {
						throw new ModelParamNotFormException(
								"MODEL_ID_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				}else if(mqp.getEot().equals(ModelOperationTypes.MODEL_KEYWORDS_TEXT_ONE_CONTAIN)){
					// 查找模型关键字
					if (mqp.getValue() != null && mqp.getValue() != null
							&& mqp.getValue().size() == 1) {
						result.retainAll(getModelBasicInfoByKeyword(username, password, mqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new ModelParamNotFormException(
								"MODEL_KEYWORDS_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				}else if(mqp.getEot().equals(ModelOperationTypes.MODEL_LONGNAME_TEXT_ONE_CONTAIN)){
					// 查找模型全称
					if (mqp.getValue() != null && mqp.getValue() != null
							&& mqp.getValue().size() == 1) {
						result.retainAll(getModelBasicInfoByLongName(username, password, mqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new ModelParamNotFormException(
								"MODEL_LONGNAME_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				}else if(mqp.getEot().equals(ModelOperationTypes.MODEL_PERSONNAME_TEXT_ONE_CONTAIN)){
					// 查找模型所有者名称
					if (mqp.getValue() != null && mqp.getValue() != null
							&& mqp.getValue().size() == 1) {
						result.retainAll(getModelBasicInfoByPersonName(username, password, mqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new ModelParamNotFormException(
								"MODEL_PERSONNAME_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				}else if(mqp.getEot().equals(ModelOperationTypes.MODEL_SHORTNAME_TEXT_ONE_CONTAIN)){
					// 查找模型简称
					if (mqp.getValue() != null && mqp.getValue() != null
							&& mqp.getValue().size() == 1) {
						result.retainAll(getModelBasicInfoByShortName(username, password, mqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new ModelParamNotFormException(
								"MODEL_SHORTNAME_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				}else if(mqp.getEot().equals(ModelOperationTypes.MODEL_WORKSPACE_TEXT_ONE_CONTAIN)){
					// 查找模型工作空间
					if (mqp.getValue() != null && mqp.getValue() != null
							&& mqp.getValue().size() == 1) {
						result.retainAll(getModelBasicInfoByWorkSpace(username, password, mqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new ModelParamNotFormException(
								"MODEL_WORKSPACE_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				}
				
			}
		}
		return result;
	}
	public boolean uploadDocument(String username, String password,
			String xmlcontent, String modelid) throws ServiceException,
			NullZeroException, XMLDocumentNotExistException,
			XMLDocumentException {
		UserInfoUtil.CheckUserLogin(username, password);
		return mxb.SaveModelXMLToBerkeley(modelid, xmlcontent);
	}

	public boolean deleteDocument(String username, String password,
			String docname) throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, DocumentnotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		mxb.deleteModelXMLFromBerkeley(docname);
		return true;
	}

	public boolean updateDocument(String username, String password,
			String xmlcontent, String modelid) throws ServiceException,
			NullZeroException, XMLDocumentNotExistException,
			XMLDocumentException, DocumentnotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		mxb.updateModelXMLToBerkeley(modelid, xmlcontent);
		return true;
	}

	public List<String> getAllDocumentForKeywords(String username,
			String password, List<String> keywords, String type)
			throws FileNotFoundException {
		List<String> results = new ArrayList<String>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			for (String keyword : keywords) {
				if (mbi.getKeywords().contains(keyword)) {
					results.add(mbi.getDocname());
					break;
				}
			}
		}
		return results;
	}

	public List<String> getAllDocumentForfullName(String username,
			String password, String fullNameStr) {
		List<String> results = new ArrayList<String>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getFullName().contains(fullNameStr)) {
				results.add(mbi.getDocname());
			}
		}
		return results;
	}

	public List<String> getAllDocumentForshortName(String username,
			String password, String shortNameStr) {
		List<String> results = new ArrayList<String>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getShortName().contains(shortNameStr)) {
				results.add(mbi.getDocname());
			}
		}
		return results;
	}

	public List<String> getAllDocumentFormodelType(String username,
			String password, String modelTypeStr) {
		List<String> results = new ArrayList<String>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getModelType().contains(modelTypeStr)) {
				results.add(mbi.getDocname());
			}
		}
		return results;
	}

	public List<String> getAllDocumentFormodelSubType(String username,
			String password, String modelSubTypeStr) {
		List<String> results = new ArrayList<String>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getModelSubType().contains(modelSubTypeStr)) {
				results.add(mbi.getDocname());
			}
		}
		return results;
	}

	public List<String> getAllDocumentForDomain(String username,
			String password, String domain) {
		List<String> results = new ArrayList<String>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getModelDomain().contains(domain)) {
				results.add(mbi.getDocname());
			}
		}
		return results;
	}

	public List<ModelBasicInfo> getAllModelBasicInfo() {
		return ModelInfoConfig.getMbInfos();
	}

	public String getDocument(String username, String password, String docname)
			throws ServiceException, NullZeroException, XMLDocumentException,
			DocumentnotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		return mxb.getDocument(docname);
	}

	public List<String> getModelIds(String username, String password)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> mbis = new ArrayList<String>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			mbis.add(mbi.getModelID());
		}
		return mbis;
	}

	public List<ModelBasicInfo> getModelBasicInfoByWorkSpace(String username,
			String password, String workspace) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<ModelBasicInfo> mbis = new ArrayList<ModelBasicInfo>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getModelorganname().contains(workspace)) {
				mbis.add(mbi);
			}
		}
		return mbis;
	}

	public List<ModelBasicInfo> getModelBasicInfoByPersonName(String username,
			String password, String personname) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<ModelBasicInfo> mbis = new ArrayList<ModelBasicInfo>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getModelperson().contains(personname)) {
				mbis.add(mbi);
			}
		}
		return mbis;
	}

	public List<ModelBasicInfo> getModelBasicInfoByFunction(String username,
			String password, String function) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<ModelBasicInfo> mbis = new ArrayList<ModelBasicInfo>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getFunction().contains(function)) {
				mbis.add(mbi);
			}
		}
		return mbis;
	}

	public List<ModelBasicInfo> getModelBasicInfoByApplication(String username,
			String password, String application) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<ModelBasicInfo> mbis = new ArrayList<ModelBasicInfo>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getModelDomain().contains(application)) {
				mbis.add(mbi);
			}
		}
		return mbis;
	}

	public List<ModelBasicInfo> getModelBasicInfoByLongName(String username,
			String password, String longName) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<ModelBasicInfo> mbis = new ArrayList<ModelBasicInfo>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getFullName().contains(longName)) {
				mbis.add(mbi);
			}
		}
		return mbis;
	}

	public List<ModelBasicInfo> getModelBasicInfoByShortName(String username,
			String password, String shortName) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<ModelBasicInfo> mbis = new ArrayList<ModelBasicInfo>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getShortName().contains(shortName)) {
				mbis.add(mbi);
			}
		}
		return mbis;
	}

	public ModelBasicInfo getModelBasicInfoById(String username,
			String password, String modelid) throws ServiceException,
			NullZeroException, ModelNotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getModelID().contains(modelid)) {
				return mbi;
			}
		}
		throw new ModelNotExistException("无指定标识符["+modelid+"]的模型信息");
	}

	public List<ModelBasicInfo> getModelBasicInfoByKeyword(String username,
			String password, String keyword) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<ModelBasicInfo> mbis = new ArrayList<ModelBasicInfo>();
		for (ModelBasicInfo mbi : ModelInfoConfig.getMbInfos()) {
			if (mbi.getKeywords().contains(keyword)) {
				mbis.add(mbi);
			}
		}
		return mbis;
	}
}
