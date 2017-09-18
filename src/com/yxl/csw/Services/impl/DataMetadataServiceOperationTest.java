package com.yxl.csw.Services.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.MetadataInfoException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.datametedia.customTypes.MetadataInfo;
import com.yxl.csw.Services.DataMetadataService;
public class DataMetadataServiceOperationTest {
	private String username = "admin";
	private String password = "cswadmin";
	String filepath = "F:\\资料\\移交材料\\注册中心各种资源数据格式范例\\数据元数据\\太原市1_5000地形数据元模型.xml";
	DataMetadataService mso=null;
	@Before
	public void setUp() throws Exception {
		JaxWsProxyFactoryBean jwpfBean = new JaxWsProxyFactoryBean();
		jwpfBean.setAddress("http://localhost:8010/CxfMyCsw/services/MetedataServiceOpeartion");
		jwpfBean.setServiceClass(DataMetadataService.class);
		mso = (DataMetadataService) jwpfBean.create();
	}

	@Test
	public void testGetDocument() {
		fail("Not yet implemented");
	}

	@Test
	public void testUploadDocument() throws ServiceException, NullZeroException, XMLDocumentNotExistException, XMLDocumentException {
		String xmlcontent=FileOperationUtil.ReadFileContent(filepath, "UTF-8")	;
		mso.uploadDocument(username, password, xmlcontent, "太原市1_5000地形数据元模型");
	}

	@Test
	public void testDeleteDocument() throws ServiceException, NullZeroException, XMLDocumentNotExistException, DocumentnotExistException {
		mso.deleteDocument(username, password, "太原市1_5000地形数据元模型");
	}

	@Test
	public void testUpdateDocument() throws ServiceException, NullZeroException, XMLDocumentNotExistException, XMLDocumentException, DocumentnotExistException {
		testUploadDocument();
		String xmlcontent=FileOperationUtil.ReadFileContent(filepath, "UTF-8")	;
		mso.updateDocument(username, password, xmlcontent, "太原市1_5000地形数据元模型");
	}

	@Test
	public void testGetAllMetadataInfo() {
		List<MetadataInfo> mis=mso.getAllMetadataInfo();
		for(MetadataInfo mi:mis){
			System.out.println(mi.getDocnameStr());
			System.out.println(mi.getDsci().getContentInfo_resourceDomain());
		}
	}

	@Test
	public void testGetAllMetadataIds() throws ServiceException, NullZeroException {
		System.out.println(mso.getAllMetadataIds(username, password));
		
	}

	@Test
	public void testGetMetadataInfo() throws ServiceException, NullZeroException, MetadataInfoException {
	System.out.println(	mso.getMetadataInfo(username, password,"urn:tysjcdlsjzx:data:taiyuan:DEM_1:5000").getDbi().getDistributionInfo_city());
	}

	@Test
	public void testGetMetadataInfoByCreatedTime() {
	}

	@Test
	public void testGetMetadataInfoByconnOrganization() throws ServiceException, NullZeroException {
		System.out.println(mso.getMetadataInfoByconnOrganization(username, password, "太原市基础地理数据中心").get(0).getDocnameStr());
	}

	@Test
	public void testGetMetadataInfoBydatequality() {
	}

	@Test
	public void testGetMetadataInfoBylinkage() {
	}

	@Test
	public void testGetMetadataInfoByprotocol() {
	}

	@Test
	public void testGetMetadataInfoByDistributionOrganName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByDistributionFormatName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByTimePeroid() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByExtension_Distance() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByEquivalentScale() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByCoordinateSystem() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByBoundary() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByKeywords() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByPurpose() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByFormatName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByOrganName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByAbstract() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadataInfoByTitle() {
		fail("Not yet implemented");
	}

}
