package com.datametedia.commonutil;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.XMLDocumentException;

public class MetedataXMLDBUtilTest {
	MetedataXMLDBUtil mxdbUtil;

	@Before
	public void setUp() throws Exception {
		mxdbUtil = MetedataXMLDBUtil.getInstance();
	}

	@Test
	public void testSaveModelXML2BerkeleyDB() throws XMLDocumentException, ServiceException {
		mxdbUtil.SaveMetadataXMLToBerkeley("metedata673", FileOperationUtil
				.ReadFileContent("D:\\太原市公交站点元模型 .xml", "UTF-8"));
	}

	@Test
	public void testGetAllDocuments() throws DirectoryNotExistException {
		List<String> docnamesList = mxdbUtil.getAllDocumentName();
		for (String str : docnamesList) {
			System.out.println(str);
		}
	}

	@Test
	public void testGetDocument() throws XMLDocumentException,
			DocumentnotExistException {
		System.out.println(mxdbUtil.getDocument("metedata673"));
	}

	@Test
	public void testGetQuery() {
		System.out.println(MetedataXMLDBUtil
				.getQueryPath("identificationInfo_datasetFormatName"));
	}

}
