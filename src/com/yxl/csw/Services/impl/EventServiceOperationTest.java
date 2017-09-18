package com.yxl.csw.Services.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.EventBeginException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.exceptions.XMLnotFormatException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.event.customTypes.EventBasicInfo;
import com.yxl.csw.Services.DataMetadataService;
import com.yxl.csw.Services.EventService;

public class EventServiceOperationTest {
	private String username = "admin";
	private String password = "cswadmin";
	String filepath = "F:\\资料\\移交材料\\注册中心各种资源数据格式范例\\事件\\第1阶段.xml";
	EventService mso=null;
	@Before
	public void setUp() throws Exception {
		JaxWsProxyFactoryBean jwpfBean = new JaxWsProxyFactoryBean();
		jwpfBean.setAddress("http://localhost:8010/CxfMyCsw/services/EventOpearationService");
		jwpfBean.setServiceClass(EventService.class);
		mso = (EventService) jwpfBean.create();
	}

	@Test
	public void testGetEventIdByCondition() {
	}

	@Test
	public void testGetAllEventBasicInfo() {
		List<EventBasicInfo> ebis=mso.getAllEventBasicInfo();
		for(EventBasicInfo ebi:ebis){
			System.out.println(ebi.getDocname() );
			System.out.println(ebi.getEventBeginPosition_First());
			System.out.println(ebi.getEventEndPosition_First() );
			System.out.println(ebi.getEcontactinfo_First().getCountry() );
		}
	}

	@Test
	public void testGetDocument() throws ServiceException, NullZeroException, DocumentnotExistException {
		System.out.println(mso.getDocument(username, password, "event__010"));
	}

	@Test
	public void testUploadDocument() throws ServiceException, NullZeroException, DocumentnotExistException, XMLnotFormatException, XMLDocumentException, DocumentException, EventBeginException {
		String xmlcontent=FileOperationUtil.ReadFileContent(filepath, "UTF-8");
		mso.uploadDocument(username, password, xmlcontent, "event__010");
	}

	@Test
	public void testDeleteDocument() throws ServiceException, NullZeroException, DocumentnotExistException, XMLDocumentNotExistException {
		mso.deleteDocument(username, password, "event__010");
	}

	@Test
	public void testUpdateDocument() throws ServiceException, NullZeroException, DocumentnotExistException, XMLnotFormatException, XMLDocumentException, DocumentException, EventBeginException, XMLDocumentNotExistException {
		String xmlcontent=FileOperationUtil.ReadFileContent("F:\\资料\\移交材料\\注册中心各种资源数据格式范例\\事件\\整个.xml", "UTF-8");
		mso.updateDocument(username, password, xmlcontent, "event__010");
	}

	@Test
	public void testGetEventIds() throws ServiceException, NullZeroException {
		System.out.println(mso.getEventIds(username, password));
	}

	@Test
	public void testGetAllEventProcessStatus() throws ServiceException, NullZeroException {
		System.out.println(mso.getAllEventProcessStatus(username, password).get(0).getEventid());
	}

	@Test
	public void testGetEventProcessStatusByEventId() {
	}

	@Test
	public void testUpdateEventProcessStatus() {
	}

	@Test
	public void testGetEventIdByMohuQuery() {
	}

	@Test
	public void testGetEventIdsByTime() {
	}

	@Test
	public void testGetEventIdsByBBOX() {
	}

	@Test
	public void testGetEventIdByCategory() {
	}

	@Test
	public void testGetEventIdByPattern() {
	}

	@Test
	public void testGetEventIdByUrgency() {
	}

	@Test
	public void testGetEventIdBySeverity() {
	}

	@Test
	public void testGetEventIdByCertainty() {
	}

	@Test
	public void testGetEventIdByInheritance() {
	}

	@Test
	public void testGetEventIdByDeath() {
	}

	@Test
	public void testGetEventIdByInjury() {
	}

	@Test
	public void testGetEventIdByMisssing() {
	}

	@Test
	public void testGetEventIdByEconomicLossing() {
	}

	@Test
	public void testGetEventIdBydirectLossesNumber() {
	}

	@Test
	public void testGetEventIdByIndirectLoss() {
	}

}
