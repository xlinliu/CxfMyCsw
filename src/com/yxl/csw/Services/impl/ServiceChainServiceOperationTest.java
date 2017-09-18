package com.yxl.csw.Services.impl;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.service.customTypes.ServiceChainInfo;
import com.yxl.csw.Services.ServiceChainService;

public class ServiceChainServiceOperationTest {
	private String username = "admin";
	private String password = "cswadmin";
	String filepath = "F:\\资料\\移交材料\\注册中心各种资源数据格式范例\\服务链\\service_link_gasLeakage_reponse_1.xml";
	ServiceChainService mso=null;
	@Before
	public void setUp() throws Exception {
		JaxWsProxyFactoryBean jwpfBean = new JaxWsProxyFactoryBean();
		jwpfBean.setAddress("http://localhost:8010/CxfMyCsw/services/ServiceChainServiceOperation");
		jwpfBean.setServiceClass(ServiceChainService.class);
		mso = (ServiceChainService) jwpfBean.create();
	}


	@Test
	public void testGetAllServiceChainInfo() {
		List<ServiceChainInfo> scis=mso.getAllServiceChainInfo();
		for(ServiceChainInfo sci:scis){
			System.out.println(sci.getDocname());
		}
	}

	@Test
	public void testGetDocument() throws ServiceException, NullZeroException, XMLDocumentException, DocumentnotExistException {
		System.out.println(mso.getDocument(username, password, "dod"));
	}

	@Test
	public void testUploadDocument() throws ServiceException, NullZeroException, XMLDocumentNotExistException, XMLDocumentException, DocumentException {
		String xmlcontent=FileOperationUtil.ReadFileContent(filepath, "UTF-8");
		mso.uploadDocument(username, password, xmlcontent, "dod");
	}

	@Test
	public void testDeleteDocument() throws ServiceException, NullZeroException, XMLDocumentNotExistException, DocumentnotExistException {
		mso.deleteDocument(username, password, "dod");
	}

	@Test
	public void testUpdateDocument() throws ServiceException, NullZeroException, XMLDocumentNotExistException, XMLDocumentException, DocumentException, DocumentnotExistException {
		String xmlcontent=FileOperationUtil.ReadFileContent(filepath, "UTF-8");
		mso.updateDocument(username, password, xmlcontent, "dod");
	}

	@Test
	public void testGetServiceChainIds() throws ServiceException, NullZeroException {
		System.out.println(mso.getServiceChainIds(username, password));
	}

	@Test
	public void testGetServiceChainInfoById() throws ServiceException, NullZeroException {
		System.out.println(mso.getServiceChainInfoById(username, password, "urn:ogc:def:service:link:gasleakage:response:gasDiffusionAnalysis").getServiceChain_eventType());
		System.out.println(mso.getServiceChainInfoById(username, password, "urn:ogc:def:service:link:gasleakage:response:gasDiffusionAnalysis").getServiceChain_eventStage());
	}

	@Test
	public void testGetServiceChainInfosByEvent() throws ServiceException, NullZeroException {
		System.out.println(mso.getServiceChainInfosByEvent(username, password, "燃气泄漏", "应急响应"));
	}

	@Test
	public void testGetAllEventTypes() {
		System.out.println(mso.getAllEventPhase());
	}

	@Test
	public void testGetAllEventPhase() {
		testGetAllEventTypes();
		System.out.println(mso.getAllEventTypes());
	}

}
