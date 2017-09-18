package com.serviceresult.commonutil;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.XMLDocumentException;

public class ServiceResultXMLDBUtilTest {
	ServiceResultXMLDBUtil srb = ServiceResultXMLDBUtil.getInstance();

	@Before
	public void setUp() throws Exception {
		srb = ServiceResultXMLDBUtil.getInstance();
	}

	@Test
	public void testSaveServiceChainXMLToBerkeley()
			throws XMLDocumentException, ServiceException {
		srb.SaveServiceResultXMLToBerkeley("sv5", FileOperationUtil
				.ReadFileContent(
						"D:\\service_link_gasleakage_response_2_result.xml",
						"UTF-8"));
	}

	@Test
	public void testDeleteServiceChainXMLFromBerkeley() {
	}

	@Test
	public void testUpdateServiceChainXMLToBerkeley() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryServiceChainXMLFromBerkeley() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQueryPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryServiceChain() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryDocumentOfServiceChainXmlManagerStringListOfString() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryDocumentOfServiceChain() {
		System.out.println(srb.queryDocumentOfServiceResult(
				"Identification_FullName",
				"service_link_gasleakage_response_2_result").get(0));
	}

	@Test
	public void testGetAllDocumentName() throws DirectoryNotExistException {
		for (String str : srb.getAllDocumentName()) {
			System.out.println(str);
		}
	}

	@Test
	public void testGetDocument() throws XMLDocumentException,
			DocumentnotExistException {
		System.out.println(srb
				.getDocument("service_link_gasleakage_response_2_result"));
	}

}
