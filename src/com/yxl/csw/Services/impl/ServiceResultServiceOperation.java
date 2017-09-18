package com.yxl.csw.Services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.DocumentException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.service.commonutil.ServiceChainXMLDBUtil;
import com.serviceresult.commonutil.ServiceResultInfoConfig;
import com.serviceresult.commonutil.ServiceResultInfoWriter;
import com.serviceresult.commonutil.ServiceResultXMLDBUtil;
import com.serviceresult.customTypes.ServiceResultInfo;
import com.yxl.csw.Services.ServiceResultService;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class ServiceResultServiceOperation implements ServiceResultService {
	ServiceResultXMLDBUtil scxd = null;
	public ServiceResultServiceOperation() {
		scxd = ServiceResultXMLDBUtil.getInstance();
		File files=new File(ServiceResultXMLDBUtil.getXMLSTORE_LOCATION());
		for(File file:files.listFiles()){
			try {
				ServiceResultInfoWriter.writeDocument2ServiceResultInfo(file.getName().replace(".xml",""));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

	public String getDocument(String username, String password, String docname)
			throws ServiceException, NullZeroException,
			DocumentnotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		return scxd.getDocument(docname);
	}

	public boolean uploadDocument(String username, String password,
			String xmlcontent, String docname) throws ServiceException,
			NullZeroException, XMLDocumentNotExistException,
			XMLDocumentException, DocumentException {
		UserInfoUtil.CheckUserLogin(username, password);
		scxd.SaveServiceResultXMLToBerkeley(docname, xmlcontent);
		return true;
	}

	public boolean deleteDocument(String username, String password,
			String docname) throws ServiceException, NullZeroException,
			DocumentnotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		scxd.deleteServiceResultXMLFromBerkeley(docname);
		return true;
	}

	public boolean updateDocument(String username, String password,
			String xmlcontent, String docname) throws ServiceException,
			NullZeroException, DocumentnotExistException, XMLDocumentException,
			XMLDocumentNotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		scxd.updateServiceResultXMLToBerkeley(docname, xmlcontent);
		return true;
	}

	public List<ServiceResultInfo> getAllServiceResultInfo() {
		return ServiceResultInfoConfig.getScis();
	}

	public List<String> getServiceResultIds(String username, String password)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> ids = new ArrayList<String>();
		for (ServiceResultInfo sri : ServiceResultInfoConfig.getScis()) {
			ids.add(sri.getServiceResult_Rela_serviceID());
		}
		return ids;
	}

	public ServiceResultInfo getServiceResultInfoByFileName(String docname) {
		for(ServiceResultInfo sri:ServiceResultInfoConfig.getScis()){
			if(sri.getDocname().equals(docname)){
				return sri;
			}
		}
		return null;
	}

}
