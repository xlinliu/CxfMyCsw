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
import com.serviceresult.customTypes.ServiceResultInfo;
import com.yxl.csw.Services.ServiceResultService;

public class ServiceResultServiceOperationTest {
	private String username = "admin";
	private String password = "cswadmin";
	String filepath = "F:\\资料\\移交材料\\注册中心各种资源数据格式范例\\服务结果\\service_link_gasleakage_response_2_result.xml";
	ServiceResultService mso=null;
	@Before
	public void setUp() throws Exception {
		JaxWsProxyFactoryBean jwpfBean = new JaxWsProxyFactoryBean();
		jwpfBean.setAddress("http://localhost:8010/CxfMyCsw/services/ServiceResultServiceOperation");
		jwpfBean.setServiceClass(ServiceResultService.class);
		mso = (ServiceResultService) jwpfBean.create();
	}

	@Test
	public void testServiceResultServiceOperation() {
	}

	@Test
	public void testGetDocument() throws ServiceException, NullZeroException, XMLDocumentException, DocumentnotExistException {
	System.out.println(	mso.getDocument(username, password, "serviceresult"));
	}

	@Test
	public void testUploadDocument() throws ServiceException, NullZeroException, XMLDocumentNotExistException, XMLDocumentException, DocumentException {
		String xmlcontent=FileOperationUtil.ReadFileContent(filepath, "UTF-8");
		mso.uploadDocument(username, password, xmlcontent, "serviceresult");
	}

	@Test
	public void testDeleteDocument() throws ServiceException, NullZeroException, XMLDocumentNotExistException, DocumentnotExistException {
		mso.deleteDocument(username, password, "serviceresult");
	}

	@Test
	public void testUpdateDocument() throws ServiceException, NullZeroException, XMLDocumentNotExistException, XMLDocumentException, DocumentException, DocumentnotExistException {
		String xmlcontent=FileOperationUtil.ReadFileContent(filepath, "UTF-8");
		mso.updateDocument(username, password, xmlcontent, "serviceresult");

	}

	@Test
	public void testGetAllServiceResultInfo() {
		List<ServiceResultInfo> sris=mso.getAllServiceResultInfo();
		for(ServiceResultInfo sri:sris){
			System.out.println(sri.getDocname());
		}
	}

	@Test
	public void testGetServiceResultIds() throws ServiceException, NullZeroException {
		System.out.println(mso.getServiceResultIds(username, password));
	}

	@Test
	public void testGetServiceResultInfoByFileName() {
		System.out.println(mso.getServiceResultInfoByFileName("serviceresult").getServiceResult_Iden_Service_Keywords());
	}

}
