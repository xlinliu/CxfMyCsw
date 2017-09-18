package com.event.commonutils;

import static org.junit.Assert.*;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.EventBeginException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.exceptions.XMLnotFormatException;

public class EventXMLDBUtilTest {
	EventXMLDBUtil exdb;

	@Before
	public void setUp() throws Exception {
		exdb = EventXMLDBUtil.getInstance();
	}

	@Test
	public void testSaveEventXMLToBerkeley() throws XMLDocumentException,
			XMLnotFormatException, DocumentException, EventBeginException,
			DocumentnotExistException, ServiceException {
		// exdb.SaveEventXMLToBerkeley("doc5",
		// FileOperationUtil.ReadFileContent(
		// "D:\\事件文档\\第1阶段.xml", "UTF-8"));
		// exdb.SaveEventXMLToBerkeley("doc5",
		// FileOperationUtil.ReadFileContent(
		// "D:\\事件文档\\第2阶段.xml", "UTF-8"));
		exdb.SaveEventXMLToBerkeley("doc5", FileOperationUtil.ReadFileContent(
				"D:\\事件文档\\第3阶段.xml", "UTF-8"));
		exdb.SaveEventXMLToBerkeley("doc5", FileOperationUtil.ReadFileContent(
				"D:\\事件文档\\第4阶段.xml", "UTF-8"));
	}

	@Test
	public void testDeleteEventXMLFromBerkeley()
			throws DocumentnotExistException, XMLDocumentNotExistException {
		exdb.deleteEventXMLFromBerkeley("doc4");
	}

	@Test
	public void testUpdateEventXMLToBerkeley() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryEventXMLFromBerkeley() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}

	// @Test
	// public void testGetQueryPath() {
	// System.out.println(EventXMLDBUtil
	// .getQueryPath("four_event_casualty_Death_unit"));
	// }

	@Test
	public void testQueryEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryDocumentOfEvent() {
		System.out.println(exdb.queryDocumentOfEvent(
				"second_event_prediction_possibileSeverity", "doc4"));
	}

	@Test
	public void testQueryDocumentOfEventStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllDocumentName() throws XMLDocumentException,
			DirectoryNotExistException, DocumentnotExistException {
		for (String eat : exdb.getAllDocumentName()) {
			System.out.println(eat);
			System.out.println(exdb.getDocument(eat));
		}
	}

	@Test
	public void testGetDocument() throws XMLDocumentException,
			DocumentnotExistException {
		System.out.println(exdb.getDocument("doc"));

	}

}
