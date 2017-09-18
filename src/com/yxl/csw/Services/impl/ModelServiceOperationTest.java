package com.yxl.csw.Services.impl;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.model.customTypes.ModelBasicInfo;
import com.yxl.csw.Services.ModelService;

public class ModelServiceOperationTest {
	private String username = "admin";
	private String password = "cswadmin";
	String filebasepath = "F:\\资料\\移交材料\\注册中心各种资源数据格式范例\\模型\\ShortestPathAnalysisModel.xml";
	ModelService mso=null;
	
	
	@Before
	public void setUp() throws Exception {
		JaxWsProxyFactoryBean jwpfBean = new JaxWsProxyFactoryBean();
		jwpfBean.setAddress("http://localhost:8010/CxfMyCsw/services/ModelServiceOpeartion");
		jwpfBean.setServiceClass(ModelService.class);
		mso = (ModelService) jwpfBean.create();
	}

	@Test
	public void testGetModelByCondition() {
		fail("Not yet implemented");
	}

	@Test
	public void testUploadDocument() throws ServiceException,
			NullZeroException, XMLDocumentNotExistException,
			XMLDocumentException, DocumentnotExistException {
		String xmlcontent = FileOperationUtil.ReadFileContent(filebasepath,
				"UTF-8");
		mso.uploadDocument(username, password, xmlcontent, "shortload");
	}

	@Test
	public void testDeleteDocument() throws ServiceException,
			NullZeroException, XMLDocumentNotExistException,
			DocumentnotExistException {
		mso.deleteDocument(username, password, "shortload");
	}

	@Test
	public void testUpdateDocument() throws ServiceException,
			NullZeroException, XMLDocumentNotExistException,
			XMLDocumentException, DocumentnotExistException {
		testUploadDocument();
		String xmlcontent = FileOperationUtil.ReadFileContent(filebasepath,
				"UTF-8");
		mso.updateDocument(username, password, xmlcontent, "shortload");
	}

	@Test
	public void testGetAllDocumentForKeywords() throws FileNotFoundException {
		List<String> keywords = new ArrayList<String>();
		keywords.add("geospatial");
		String type = null;
		mso.getAllDocumentForKeywords(username, password, keywords, type);
	}

	@Test
	public void testGetAllDocumentForfullName() {
	System.out.println(	mso.getAllDocumentForfullName(username, password, "geospatial shortest path analysis model"));
	}

	@Test
	public void testGetAllDocumentForshortName() {
		System.out.println(	mso.getAllDocumentForshortName(username, password, "shortest path analysis"));
	}

	@Test
	public void testGetAllDocumentFormodelType() {
		System.out.println(	mso.getAllDocumentFormodelType(username, password, "professional application model"));
	}

	@Test
	public void testGetAllDocumentFormodelSubType() {
		System.out.println(	mso.getAllDocumentFormodelSubType(username, password, "regional transportation planning"));
	}

	@Test
	public void testGetAllDocumentForDomain() {
		System.out.println(	mso.getAllDocumentForDomain(username, password, "transportation"));

	}

	@Test
	public void testGetAllModelBasicInfo() {
		List<ModelBasicInfo> mbis=mso.getAllModelBasicInfo();
		for(ModelBasicInfo mbi:mbis){
			System.out.println(mbi.getFullName());
		}
	}

	@Test
	public void testGetDocument() throws ServiceException, NullZeroException, XMLDocumentException, DocumentnotExistException {
	System.out.println(	mso.getDocument(username, password, "shortload"));
	}

	@Test
	public void testGetModelIds() throws ServiceException, NullZeroException {
		System.out.println(mso.getModelIds(username, password));
	}

	@Test
	public void testGetModelBasicInfoByWorkSpace() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelBasicInfoByPersonName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelBasicInfoByFunction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelBasicInfoByApplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelBasicInfoByLongName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelBasicInfoByShortName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelBasicInfoById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelBasicInfoByKeyword() {
		fail("Not yet implemented");
	}

}
