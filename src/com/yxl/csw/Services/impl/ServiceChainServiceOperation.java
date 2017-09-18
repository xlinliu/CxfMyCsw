package com.yxl.csw.Services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.DocumentException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.model.commonutil.ModelXMLDBUtil;
import com.service.commonutil.ServiceChainInfoConfig;
import com.service.commonutil.ServiceChainXMLDBUtil;
import com.service.commonutil.ServiceChainlInfoWriter;
import com.service.customTypes.ServiceChainInfo;
import com.yxl.csw.Services.ServiceChainService;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class ServiceChainServiceOperation implements ServiceChainService {
	ServiceChainXMLDBUtil scxd = null;
	public ServiceChainServiceOperation() {
		scxd = ServiceChainXMLDBUtil.getInstance();
		File files=new File(ServiceChainXMLDBUtil.getXMLSTORE_LOCATION());
		for(File file:files.listFiles()){
			try {
				ServiceChainlInfoWriter.writeDocument2ServiceChainInfo(file.getName().replace(".xml", ""));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	public List<ServiceChainInfo> getAllServiceChainInfo() {
		return ServiceChainInfoConfig.getScis();
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
		scxd.SaveServiceChainXMLToBerkeley(docname, xmlcontent);
		return true;
	}

	public boolean deleteDocument(String username, String password,
			String docname) throws ServiceException, NullZeroException,
			DocumentnotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		scxd.deleteServiceChainXMLFromBerkeley(docname);
		return true;
	}

	public boolean updateDocument(String username, String password,
			String xmlcontent, String docname) throws ServiceException,
			NullZeroException, DocumentnotExistException, DocumentException,
			XMLDocumentException, XMLDocumentNotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		scxd.updateServiceChainXMLToBerkeley(docname, xmlcontent);
		return true;
	}

	public List<String> getServiceChainIds(String username, String password)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (ServiceChainInfo sci : ServiceChainInfoConfig.getScis()) {
			results.add(sci.getServiceChain_ServiceLinkID());
		}
		return results;
	}

	public ServiceChainInfo getServiceChainInfoById(String username,
			String password, String servicechainId) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		for (ServiceChainInfo sci : ServiceChainInfoConfig.getScis()) {
			if (sci.getServiceChain_ServiceLinkID().equals(servicechainId)) {
				return sci;
			}
		}
		throw new ServiceException("无所提供的[" + servicechainId + "]服务链");
	}

	public List<ServiceChainInfo> getServiceChainInfosByEvent(String username,
			String password, String enventtype, String eventphase)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<ServiceChainInfo> results = new ArrayList<ServiceChainInfo>();
		for (ServiceChainInfo sci : ServiceChainInfoConfig.getScis()) {
			if (sci.getServiceChain_eventType() != null
					&& sci.getServiceChain_eventStage() != null) {
				if (sci.getServiceChain_eventType().equals(enventtype)
						&& sci.getServiceChain_eventStage().equals(eventphase)) {
					results.add(sci);
				}
			}
		}
		return results;
	}

	public List<String> getAllEventTypes() {
		List<String> eventtypes = new ArrayList<String>();
		for (ServiceChainInfo sci : ServiceChainInfoConfig.getScis()) {
			if (!eventtypes.contains(sci.getServiceChain_eventType())) {
				eventtypes.add(sci.getServiceChain_eventType());
			}
		}
		return eventtypes;
	}

	public List<String> getAllEventPhase() {
		List<String> eventstages = new ArrayList<String>();
		for (ServiceChainInfo sci : ServiceChainInfoConfig.getScis()) {
			if (!eventstages.contains(sci.getServiceChain_eventStage())) {
				eventstages.add(sci.getServiceChain_eventStage());
			}
		}
		return eventstages;
	}

}
