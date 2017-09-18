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
				"E:\\实验原位传感器SML130928\\武大视频站点\\WudaVideoStation.xml", "UTF-8");
		// String sensormlcontent = FileOperationUtil
		// .ReadFileContent(
		// "E:\\实验原位传感器SML130928\\131226 三峡野猫面滑坡监测站\\YemaomianLandslideStation-BPM085.xml",
		// "UTF-8");
		// String sensormlcontent = FileOperationUtil
		// .ReadFileContent(
		// "E:\\实验原位传感器SML130928\\131226 三峡野猫面滑坡监测站\\YemaomianLandslideStation-BPM085.xml",
		// "UTF-8");
		// String sensormlcontent = FileOperationUtil
		// .ReadFileContent(
		// "E:\\实验原位传感器SML130928\\131226 三峡野猫面滑坡监测站\\YemaomianLandslideStation-BPM085.xml",
		// "UTF-8");
		csow.saveSensorML(username, password, sensormlcontent);
		System.out.println("over");
	}

	@Test
	public void testSaveSensorML() throws ServiceException {
		String sensormlcontent = FileOperationUtil
				.ReadFileContent(
						"F:\\资料\\移交材料\\注册中心各种资源数据格式范例\\传感器\\131226 三峡野猫面滑坡监测站\\YemaomianLandslideStation-BPM0856.xml",
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
						"E:\\实验原位传感器SML130928\\131226 三峡野猫面滑坡监测站\\YemaomianLandslideStation.xml",
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
				"urn:liesmars:insitusensor:BeiDouBusTAIYUAN-BDS-晋A93154"));
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
		 * 類似上個測試 testIsSensorBelongToPlatform(***)
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
												"E:\\实验原位传感器SML130928\\131226 三峡野猫面滑坡监测站\\Yemao3mianLandslideStation.xml",
												"UTF-8")));
	}

	@Test
	public void testUpdateUserInfo() {
		// 成功
	}

	@Test
	public void testUpdateUserInfoMethod() {
		// 成功
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
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorBandCategoryMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorBandNumberMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorChineseOutputInfo() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorChineseOutputInfoList() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorCommunicationCapabilityForMultiSensorsMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorCommunicationCapabilityMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorComputeCapabilityForMultiSensorsMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorComputeCapabilityMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorConnectionEarthPosInfosMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorConnectionEarthPosInfoMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorConnectionEmailMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorConnectionEmailsMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorGeoInfoMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorGeoInfosMethod() {
		/**
		 * 已测试
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
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorMeasureCapabilityMethod() {
		/**
		 * 已测试
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
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorPhysicalPropertyMethod() {
		/**
		 * 已测试
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
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorSensorTypeYXLMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorSensorTypeMethod() {
		/**
		 * 已测试
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
		 * 已测试
		 */
	}

	@Test
	public void testGetSensorWorkValidTimeBeginsMethod() {
		/**
		 * 已测试
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
		 * 已测试
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
		 * 已測試
		 */
	}

	@Test
	public void testDeleteSensorMLAttchmentByWebFilePathMehtod() {
		/**
		 * 已測試
		 */
	}

	@Test
	public void testDeleteSomeSensorsAttachmentsWithMarkers() {
		/**
		 * 已測試
		 */
	}

	@Test
	public void testGetAllSensorIdsWithSensorImage() {
		/**
		 * 已測試
		 */
	}

	@Test
	public void testGetSomeSensorAttachementPathsForSomeSensors() {
		/**
		 * 已測試
		 */
	}

	@Test
	public void testIsExistSensorAttchmentBySensorWebFilePathMethod() {
		/**
		 * 已測試
		 */
	}

	@Test
	public void testIsExistSensorAttchmentMethod() {
		/**
		 * 已測試
		 */
	}

	@Test
	public void testReadSensorAttchmentPathFile() {
		/**
		 * 已測試
		 */
	}

	@Test
	public void testSaveSensorMLAttachmentMethod() {
		/**
		 * 已測試
		 */
	}

	@Test
	public void testSaveSomeSensorAttachementsWithMarkers() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testDeleteSensorByTransactionDeleteMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testQuerySensorsByConditionsMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetRecordsContent() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testTransactionUpdateContentMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testUpdateSomeSensorInfoMethod() {
		/**
		 * 已测试
		 */
	}

	@Test
	public void testGetServiceParam() {
		/**
		 * 已测试
		 */
	}
}
