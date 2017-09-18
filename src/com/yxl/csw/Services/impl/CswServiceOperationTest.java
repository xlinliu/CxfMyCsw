package com.yxl.csw.Services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.XmlException;
import org.junit.Before;
import org.junit.Test;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.EbrimNotFormatException;
import com.csw.exceptions.FileExistException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.SensorExistException;
import com.csw.exceptions.SensorIdNotEqualException;
import com.csw.exceptions.SensorNotExistException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.TransactionProcessException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;
import com.csw.utils.custometypes.SensorMLType;
import com.csw.utils.custometypes.SensorTypes;

public class CswServiceOperationTest {
	CswServiceOperation csow = null;
	String username = "admin";
	String password = "cswadmin";

	@Before
	public void setUp() throws Exception {
		csow = new CswServiceOperation();
	}

	@Test
	public void testSaveSensorMLs() throws UnsupportedEncodingException,
			ServiceException, NullZeroException, XmlException,
			EbrimNotFormatException, DBObjectSaveException, IOException,
			SensorExistException, FileExistException,
			TransactionProcessException, SensorIdNotEqualException {
		String sensormlcontent = FileOperationUtil.ReadFileContent(
				"E:\\ʵ��ԭλ������SML130928\\�����Ƶվ��\\WudaVideoStation.xml", "UTF-8");
		// String sensormlcontent = FileOperationUtil
		// .ReadFileContent(
		// "E:\\ʵ��ԭλ������SML130928\\131226 ��ϿҰè�滬�¼��վ\\YemaomianLandslideStation-BPM085.xml",
		// "UTF-8");
		// String sensormlcontent = FileOperationUtil
		// .ReadFileContent(
		// "E:\\ʵ��ԭλ������SML130928\\131226 ��ϿҰè�滬�¼��վ\\YemaomianLandslideStation-BPM085.xml",
		// "UTF-8");
		// String sensormlcontent = FileOperationUtil
		// .ReadFileContent(
		// "E:\\ʵ��ԭλ������SML130928\\131226 ��ϿҰè�滬�¼��վ\\YemaomianLandslideStation-BPM085.xml",
		// "UTF-8");
		csow.saveSensorML(username, password, sensormlcontent);
		System.out.println("over");
	}

	@Test
	public void testSaveSensorML() throws ServiceException {
		String sensormlcontent = FileOperationUtil
				.ReadFileContent(
						"F:\\����\\�ƽ�����\\ע�����ĸ�����Դ���ݸ�ʽ����\\������\\131226 ��ϿҰè�滬�¼��վ\\YemaomianLandslideStation-BPM0856.xml",
						"UTF-8");
		csow.saveSensorML(username, password, sensormlcontent);
	}

	@Test
	public void testDeleteSensorML() throws ServiceException, NullZeroException {
		csow.deleteSensorML(username, password,
				"urn:liesmars:insitusensor:platform:YemaomianLandslideStation");
	}

	@Test
	public void testDeleteSensorMLs() throws ServiceException,
			NullZeroException {
		List<String> sensorids = new ArrayList<String>();
		sensorids
				.add("urn:liesmars:insitusensor:platform:YemaomianLandslideStation");
		csow.deleteSensorMLs(username, password, sensorids);
	}

	@Test
	public void testReadSensorContent() throws FileNotFoundException,
			ServiceException, NullZeroException, SensorNotExistException {
		String sensorcontent = csow.readSensorContent(username, password,
				"urn:liesmars:insitusensor:platform:YemaomianLandslideStation");
		System.out.println(sensorcontent);
	}

	@Test
	public void testIsExistsSensorML() throws ServiceException,
			NullZeroException {

		System.out
				.println(csow
						.isExistsSensorML(username, password,
								"urn:liesmars:insitusensor:platform:YemaomianLandslideStation"));
	}

	@Test
	public void testGetSensorMLSaveTime() throws ServiceException,
			NullZeroException, SensorNotExistException {
		// fail("Not yet implemented");
		System.out
				.println(csow
						.getSensorMLSaveTime(username, password,
								"urn:liesmars:insitusensor:platform:YemaomianLandslideStation"));
	}

	@Test
	public void testGetSensorMLIdsOfUser() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSensorMLIdsOfUser(username, password));
	}

	@Test
	public void testUpdateSensorMLs() throws UnsupportedEncodingException,
			ServiceException, NullZeroException, XmlException,
			EbrimNotFormatException, DBObjectSaveException, IOException,
			SensorExistException, FileExistException,
			TransactionProcessException, SensorIdNotEqualException,
			SensorNotExistException {
		// fail("Not yet implemented");
		List<String> sensorids = new ArrayList<String>();
		List<String> sensormlcontents = new ArrayList<String>();
		String sensormlcontent = FileOperationUtil
				.ReadFileContent(
						"E:\\ʵ��ԭλ������SML130928\\131226 ��ϿҰè�滬�¼��վ\\YemaomianLandslideStation.xml",
						"UTF-8");
		sensorids
				.add("urn:liesmars:insitusensor:platform:YemaomianLandslideStation");
		sensormlcontents.add(sensormlcontent);
		csow.updateSensorMLs(username, password, sensorids, sensormlcontents);
	}

	@Test
	public void testUpdateSensorML() {
	}

	@Test
	public void testCheckUserName() throws ServiceException, NullZeroException {
		csow.checkUserName("admin");
	}

	@Test
	public void testCheckUserLogin() throws ServiceException, NullZeroException {
		System.out.println(csow.checkUserLogin(username, password));
	}

	@Test
	public void testCheckSensorMLIdExistMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.checkSensorMLIdExistMethod("admin", "cswadmin",
				"xxx"));
	}

	@Test
	public void testCreateGetRecordByIdDocumentMethod()
			throws ServiceException, NullZeroException {
		System.out.println(csow.createGetRecordByIdDocumentMethod("admin",
				"cswadmin",
				"urn:liesmars:insitusensor:BeiDouBusTAIYUAN-BDS-��A93154"));
	}

	@Test
	public void testDeleteUserInfo() throws ServiceException, NullZeroException {
		// fail("Not yet implemented");
		csow.deleteUserInfo("visitor", "visitor");
	}

	@Test
	public void testGetAllPlatfromType() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getAllPlatfromType(username, password));
	}

	@Test
	public void testGetSensorTypes() throws ServiceException, NullZeroException {
		List<String> sensors = new ArrayList<String>();
		sensors
				.add("urn:liesmars:insitusensor:platform:YemaomianLandslideStation");
		System.out.println(csow.getSensorTypes(username, password, sensors));
	}

	@Test
	public void testGetSensorType() throws NullZeroException, ServiceException {
		System.out
				.println(csow
						.getSensorType(username, password,
								"urn:liesmars:insitusensor:platform:YemaomianLandslideStation"));
	}

	@Test
	public void testGetAllPlatfromId() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getAllPlatfromId(username, password));
	}

	@Test
	public void testGetSingleSensorBasicInfo() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSingleSensorBasicInfo(username, password,
				"urn:liesmars:insitusensor:platform:YemaomianLandslideStation")
				.getSENSORTYPE());
	}

	@Test
	public void testGetAllSensorMLDocumentByIdsMethod()
			throws ServiceException, NullZeroException {
		List<String> ids = new ArrayList<String>();
		ids.add("urn:liesmars:insitusensor:platform:YemaomianLandslideStation");
		System.out.println(csow.getAllSensorMLDocumentByIdsMethod(username,
				password, ids, "", true).size());
	}

	@Test
	public void testGetAllSensorTypes() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getAllSensorTypes(username, password));
	}

	@Test
	public void testGetRecordsDocumentMethod() {
		// csow.getRecordsDocumentMethod(username, password, queryStr,
		// allownull)
	}

	@Test
	public void testGetAllSensorBasicInfo() throws ServiceException,
			NullZeroException {
		List<SensorBasicInfoType> sbfts = csow.getAllSensorBasicInfo(username,
				password, true);
		for (SensorBasicInfoType sbft : sbfts) {
			System.out.println(sbft.getSensorid() + "--"
					+ sbft.getSensorintendapp());
		}
	}

	@Test
	public void testGetOwnerAllSensorMLDocumentMethod()
			throws ServiceException, NullZeroException {
		System.out.println(csow.getOwnerAllSensorMLDocumentMethod(username,
				password).size());
	}

	@Test
	public void testGetRegistrySensorWithOwnerOptionMethod()
			throws ServiceException, NullZeroException {
		System.out.println(csow.getRegistrySensorWithOwnerOptionMethod(
				username, password, true));
	}

	@Test
	public void testGetSameSensorTypeSensorGroup() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSameSensorTypeSensorGroup(username,
				password, SensorTypes.InsituSensor));
	}

	@Test
	public void testGetSensorOperableMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSensorOperableMethod(username, password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085")
				.getIsOperable());
	}

	@Test
	public void testGetSensorsOperableListMethod() throws ServiceException,
			NullZeroException {
		List<String> sensors = new ArrayList<String>();
		sensors
				.add("urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085");
		System.out.println(csow.getSensorsOperableListMethod(username,
				password, sensors));
	}

	@Test
	public void testGetSensorsBelongPlatform() throws ServiceException,
			NullZeroException {
		List<String> sensors = new ArrayList<String>();
		sensors
				.add("urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085");
		System.out.println(csow.getSensorsBelongPlatform(username, password,
				sensors));
	}

	@Test
	public void testGetSensorShareLevelMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSensorShareLevelMethod(username, password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085"));
	}

	@Test
	public void testGetSensorShareLevelsMethod() throws ServiceException,
			NullZeroException {
		List<String> lists = new ArrayList<String>();
		lists.add("urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085");
		System.out.println(csow.getSensorShareLevelsMethod(username, password,
				lists));

	}

	@Test
	public void testGetSepcialPlatfromSensors() throws ServiceException,
			NullZeroException {
		List<String> platforms = new ArrayList<String>();
		platforms
				.add("urn:liesmars:insitusensor:platform:YemaomianLandslideStation");
		System.out.println(csow.getSepcialPlatfromSensors(username, password,
				platforms));
	}

	@Test
	public void testGetSepcialPlatfromSensor() throws ServiceException,
			NullZeroException {
		System.out
				.println(csow
						.getSepcialPlatfromSensor(username, password,
								"urn:liesmars:insitusensor:platform:YemaomianLandslideStation"));
	}

	@Test
	public void testLoginUserMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.loginUserMethod(username, password));
	}

	@Test
	public void testParseQueryStrAndConditionsMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.parseQueryStrAndConditionsMethod(username,
				password, ""));
	}

	@Test
	public void testQuerySensorsBBOXMethod() throws ServiceException,
			NullZeroException {
		List<String> sensors = new ArrayList<String>();
		System.out.println(csow.querySensorsBBOXMethod(username, password,
				sensors));
	}

	@Test
	public void testQueryAllUserInfosMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.queryAllUserInfosMethod(username, password));
	}

	@Test
	public void testGetPlatFormandSensors() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getPlatFormandSensors(username, password,
				"InsituSensorPlatform-Composite"));
	}

	@Test
	public void testRegistryMethod() {
		// /System.out.println(csow.registryMethod(address, age, emailAddress,
		// gender, level, password, telephone, username, zhiye)(username,
		// password, true));
	}

	@Test
	public void testSearchGetRecordByIdDocumentMethod() {
		// csow.searchGetRecordByIdDocumentMethod(username, password, "");
	}

	@Test
	public void testIsSensorBelongToPlatform() throws ServiceException,
			NullZeroException {
		System.out
				.println(csow
						.isSensorBelongToPlatform(
								username,
								password,
								"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085",
								"urn:liesmars:insitusensor:platform:YemaomianLandslideStation"));
	}

	@Test
	public void testIsSensorsBelongToPlatforms() {
		/**
		 * ����ς��yԇ testIsSensorBelongToPlatform(***)
		 */
	}

	@Test
	public void testShowCapabilitiesResponseMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.showCapabilitiesResponseMethod(username,
				password));
	}

	@Test
	public void testShowSensorInfoForUpdateMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.showSensorInfoForUpdateMethod(username,
				password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085"));
	}

	@Test
	public void testTransactionSensorMLToeEbRIM() throws ServiceException,
			NullZeroException {
		System.out
				.println(csow
						.transactionSensorMLToeEbRIM(
								username,
								password,
								FileOperationUtil
										.ReadFileContent(
												"E:\\ʵ��ԭλ������SML130928\\131226 ��ϿҰè�滬�¼��վ\\Yemao3mianLandslideStation.xml",
												"UTF-8")));
	}

	@Test
	public void testUpdateUserInfo() {
		// �ɹ�
	}

	@Test
	public void testUpdateUserInfoMethod() {
		// �ɹ�
	}

	@Test
	public void testCreateGetReocdsDetailMethod() throws NullZeroException,
			ServiceException {
		String queryStr = "";
		csow.createGetReocdsDetailMethod(username, password, queryStr);
	}

	@Test
	public void testGetMutiSensorTypeOfSensors() throws ServiceException,
			NullZeroException {
		List<String> sensors = new ArrayList<String>();
		sensors
				.add("urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085");
		System.out.println(csow.getMutiSensorTypeOfSensors(username, password,
				sensors).size());
	}

	@Test
	public void testGetChineseInputsInfo() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getChineseInputsInfo(username, password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085"));
	}

	@Test
	public void testGetChineseInputsInfoList() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorBandCategoryMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorBandNumberMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorChineseOutputInfo() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorChineseOutputInfoList() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorCommunicationCapabilityForMultiSensorsMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorCommunicationCapabilityMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorComputeCapabilityForMultiSensorsMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorComputeCapabilityMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorConnectionEarthPosInfosMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorConnectionEarthPosInfoMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorConnectionEmailMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorConnectionEmailsMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorGeoInfoMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorGeoInfosMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorIntendedApplicationsMethod()
			throws ServiceException, NullZeroException {
		System.out.println(csow.getSensorIntendedApplicationMethod(username,
				password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085")
				.get(0).getSENSORINTEND());
	}

	@Test
	public void testGetSensorIntendedApplicationMethod()
			throws ServiceException, NullZeroException {
		List<String> sensors = new ArrayList<String>();
		sensors
				.add("urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085");
		System.out.println(csow.getSensorIntendedApplicationsMethod(username,
				password, sensors).get(0).getSENSORINTEND());
	}

	@Test
	public void testGetSensorKeyWordsMethod() throws ServiceException,
			NullZeroException {
		List<String> sensorids = new ArrayList<String>();
		sensorids
				.add("urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085");
		System.out.println(csow.getSensorKeyWordsMethod(username, password,
				sensorids).get(0).getKeywords());
	}

	@Test
	public void testGetSensorKeyWordMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSensorKeyWordMethod(username, password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085"));
	}

	@Test
	public void testGetSensorLongNameMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSensorLongNameMethod(username, password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085"));
	}

	@Test
	public void testGetSensorLongNamesMethod() throws ServiceException,
			NullZeroException {
		List<String> sensorids = new ArrayList<String>();
		sensorids
				.add("urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085");
		System.out.println(csow.getSensorLongNamesMethod(username, password,
				sensorids).get(0).getLongname());
	}

	@Test
	public void testGetSensorMeasureCapabilitysMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorMeasureCapabilityMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorOrganizationInfosMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSensorOrganizationInfoMethod(username,
				password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085"));
	}

	@Test
	public void testGetSensorOrganizationInfoMethod() throws ServiceException,
			NullZeroException {
		List<String> sensorids = new ArrayList<String>();
		sensorids
				.add("urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085");
		System.out.println(csow.getSensorOrganizationInfosMethod(username,
				password, sensorids));
	}

	@Test
	public void testGetSensorPhysicalPropertysMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorPhysicalPropertyMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorPositionInfoMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSensorPositionInfoMethod(username, password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085"));

	}

	@Test
	public void testGetSensorPositionInfosMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorSensorTypeYXLMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorSensorTypeMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorShortNameMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSensorShortNameMethod(username, password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085"));
	}

	@Test
	public void testGetSensorShortNamesMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorWorkValidTimeBeginsMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorWorkValidTimeBeginMethod()
			throws ServiceException, NullZeroException {
		String sensorid = "urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085";
		System.out.println(csow.getSensorWorkValidTimeBeginMethod(username,
				password, sensorid));
	}

	@Test
	public void testGetSensorWorkValidTimeEndsMethod() {
		/*
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetSensorWorkValidTimeEndMethod() throws ServiceException,
			NullZeroException {
		System.out.println(csow.getSensorWorkValidTimeBeginMethod(username,
				password,
				"urn:liesmars:insitusensor:YemaomianLandslideStation-BPM085"));
	}

	@Test
	public void testCheckIsConnectMethod() throws ServiceException {
		System.out.println(csow.checkIsConnectMethod("admin", "cswadmin"));
	}

	@Test
	public void testCreateGetRecordsDocumentMethod() {
		// csow.createGetRecordsDocumentMethod(username, password, startRecord,
		// maximumRecord, west, east, south, north, startTime, endTime,
		// requestType, profileType, title, keyword)
	}

	@Test
	public void testDeleteAllSensorsMethod() throws ServiceException,
			NullZeroException {
		csow.deleteAllSensorsMethod(username, password);
	}

	@Test
	public void testGetAllBasicInfoOfSensorMLMethod() throws ServiceException,
			NullZeroException {
		List<SensorMLType> SMTS = csow.getAllBasicInfoOfSensorMLMethod("admin",
				"cswadmin", true);
		System.out.println(SMTS.size());
	}

	@Test
	public void testCleanAllSensorAttachment() throws ServiceException,
			NullZeroException {
		csow.cleanAllSensorAttachment("admin", "cswadmin", "admin");
	}

	@Test
	public void testDeleteSensorMLAttachmentMethod() {
		/**
		 * �ќyԇ
		 */
	}

	@Test
	public void testDeleteSensorMLAttchmentByWebFilePathMehtod() {
		/**
		 * �ќyԇ
		 */
	}

	@Test
	public void testDeleteSomeSensorsAttachmentsWithMarkers() {
		/**
		 * �ќyԇ
		 */
	}

	@Test
	public void testGetAllSensorIdsWithSensorImage() {
		/**
		 * �ќyԇ
		 */
	}

	@Test
	public void testGetSomeSensorAttachementPathsForSomeSensors() {
		/**
		 * �ќyԇ
		 */
	}

	@Test
	public void testIsExistSensorAttchmentBySensorWebFilePathMethod() {
		/**
		 * �ќyԇ
		 */
	}

	@Test
	public void testIsExistSensorAttchmentMethod() {
		/**
		 * �ќyԇ
		 */
	}

	@Test
	public void testReadSensorAttchmentPathFile() {
		/**
		 * �ќyԇ
		 */
	}

	@Test
	public void testSaveSensorMLAttachmentMethod() {
		/**
		 * �ќyԇ
		 */
	}

	@Test
	public void testSaveSomeSensorAttachementsWithMarkers() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testDeleteSensorByTransactionDeleteMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testQuerySensorsByConditionsMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetRecordsContent() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testTransactionUpdateContentMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testUpdateSomeSensorInfoMethod() {
		/**
		 * �Ѳ���
		 */
	}

	@Test
	public void testGetServiceParam() {
		/**
		 * �Ѳ���
		 */
	}
}
