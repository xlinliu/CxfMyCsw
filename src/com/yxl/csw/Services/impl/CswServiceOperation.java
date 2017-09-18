package com.yxl.csw.Services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.xmlbeans.XmlException;
import org.hibernate.Session;
import com.csw.Service.impls.CreateGetRecordByIdDocumentService;
import com.csw.Service.impls.CreateGetRecordsDocumentService;
import com.csw.Service.impls.DeleteSensorByTransactionDeleteService;
import com.csw.Service.impls.DeleteSimpleSensorService;
import com.csw.Service.impls.GetAllBasicInfoOfSensorMLService;
import com.csw.Service.impls.GetAllPlatfromTypesService;
import com.csw.Service.impls.GetAllSensorBasicInfoService;
import com.csw.Service.impls.GetRecordsService;
import com.csw.Service.impls.GetRegistrySensorWithOwnerOptionService;
import com.csw.Service.impls.GetSameSensorTypeGroupService;
import com.csw.Service.impls.GetSensorIsOperableService;
import com.csw.Service.impls.GetSensorShareableService;
import com.csw.Service.impls.GetSensorsBelongPlatfromService;
import com.csw.Service.impls.GetSpecialPlatformSensorsService;
import com.csw.Service.impls.ParseQueryStrAndGetConditionsService;
import com.csw.Service.impls.QueryAllSensorsBBOXService;
import com.csw.Service.impls.QueryPlatFormandSensorsService;
import com.csw.Service.impls.QuerySensorsByConditionsService;
import com.csw.Service.impls.RegistryService;
import com.csw.Service.impls.SaveEbRimService;
import com.csw.Service.impls.SaveSensorMLService;
import com.csw.Service.impls.SearchGetRecordByIdDocumentService;
import com.csw.Service.impls.SensorIsBelongPlatfromService;
import com.csw.Service.impls.ShowCapabilitiesResponseService;
import com.csw.Service.impls.ShowGetResponseDocumentService;
import com.csw.Service.impls.TransactionUpdateContentService;
import com.csw.Service.impls.TransformSensorMLToebRIMService;
import com.csw.Service.impls.UpdateSomeSensorInfoService;
import com.csw.Service.impls.UpdateUserInfoService;
import com.csw.Service.interfaces.SensorIsBelongPlatfromInterface;
import com.csw.beans.LoginUserBean;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.EbrimNotFormatException;
import com.csw.exceptions.FileExistException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.SensorExistException;
import com.csw.exceptions.SensorIdNotEqualException;
import com.csw.exceptions.SensorNotExistException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.TransactionProcessException;
import com.csw.interfaces.querypackage.impls.CreateGetRecordsDetailService;
import com.csw.interfaces.querypackage.impls.GetSensorBandCategoryService;
import com.csw.interfaces.querypackage.impls.GetSensorBandNumberService;
import com.csw.interfaces.querypackage.impls.GetSensorChineseInputsService;
import com.csw.interfaces.querypackage.impls.GetSensorChineseOutputsService;
import com.csw.interfaces.querypackage.impls.GetSensorCommunicationCapabilityService;
import com.csw.interfaces.querypackage.impls.GetSensorComputeCapabilityService;
import com.csw.interfaces.querypackage.impls.GetSensorConnectionEarthPosInfoService;
import com.csw.interfaces.querypackage.impls.GetSensorConnectionEmailService;
import com.csw.interfaces.querypackage.impls.GetSensorGeoInfoService;
import com.csw.interfaces.querypackage.impls.GetSensorIntendedApplicationService;
import com.csw.interfaces.querypackage.impls.GetSensorKeyWordsService;
import com.csw.interfaces.querypackage.impls.GetSensorLongNameService;
import com.csw.interfaces.querypackage.impls.GetSensorMeasureCapabilityService;
import com.csw.interfaces.querypackage.impls.GetSensorOrganizationInfoService;
import com.csw.interfaces.querypackage.impls.GetSensorPhysicalPropertyService;
import com.csw.interfaces.querypackage.impls.GetSensorPositionInfoService;
import com.csw.interfaces.querypackage.impls.GetSensorSensorTypeService;
import com.csw.interfaces.querypackage.impls.GetSensorShortNameService;
import com.csw.interfaces.querypackage.impls.GetSensorWorkBeginTimeService;
import com.csw.interfaces.querypackage.impls.GetSensorWorkEndTimeService;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.FileUtil.AllFileNameOfDirUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.SensorInfoUtil.SensorMLAttacheMentInfoOperation;
import com.csw.utils.custometypes.SensorAttachmentMarkerType;
import com.csw.utils.custometypes.SensorAttachmentType;
import com.csw.utils.custometypes.SensorBBox;
import com.csw.model.ebXMLModel.SensorBasciForOracleType;
import com.csw.utils.custometypes.PlatformandSensors;
import com.csw.utils.custometypes.SensorBasicInfoType;
import com.csw.utils.custometypes.SensorCommunicationProValueType;
import com.csw.utils.custometypes.SensorComputeProValueType;
import com.csw.utils.custometypes.SensorConnPosType;
import com.csw.utils.custometypes.SensorEmailType;
import com.csw.utils.custometypes.SensorGeoType;
import com.csw.utils.custometypes.SensorInputInfoType;
import com.csw.utils.custometypes.SensorIntendAppForOracleType;
import com.csw.utils.custometypes.SensorKeywordType;
import com.csw.utils.custometypes.SensorLongNameType;
import com.csw.utils.custometypes.SensorMLDocumentType;
import com.csw.utils.custometypes.SensorMLType;
import com.csw.utils.custometypes.SensorMeasureProValueType;
import com.csw.utils.custometypes.SensorOperable;
import com.csw.utils.custometypes.SensorOrganType;
import com.csw.utils.custometypes.SensorOutputType;
import com.csw.utils.custometypes.SensorPhysicProType;
import com.csw.utils.custometypes.SensorPlatformPair;
import com.csw.utils.custometypes.SensorPosType;
import com.csw.utils.custometypes.SensorShareLevel;
import com.csw.utils.custometypes.SensorShortName;
import com.csw.utils.custometypes.SensorTimeType;
import com.csw.utils.custometypes.SensorTypeYXL;
import com.csw.utils.custometypes.SensorTypes;
import com.csw.utils.custometypes.SensorandBelongPlatform;
import com.ebrim.model.rim.RegistryPackageDocument;
import com.csw.utils.Userutils.UserInfoUtil;
import com.yxl.csw.Services.CswService;
import com.csw.SystemParams.SystemConfig;

public class CswServiceOperation implements CswService {

	public boolean saveSensorMLs(String username, String password,
			List<String> sensormlcontents) throws UnsupportedEncodingException,
			ServiceException, NullZeroException, XmlException,
			EbrimNotFormatException, DBObjectSaveException, IOException,
			SensorExistException, FileExistException,
			TransactionProcessException, SensorIdNotEqualException {
		if (sensormlcontents == null || sensormlcontents.size() == 0) {
			throw new ServiceException("参数sensormlcontents不规范");
		}
		UserInfoUtil.CheckUserLogin(username, password);
		for (String sensormlcontent : sensormlcontents) {
			StringUtil.checkStringIsNotNULLAndEmptyMethod(sensormlcontent,
					"sensormlcontent");
			String ebrimcontent = com.csw.utils.SensorInfoUtil.OperateSensorUtil
					.TransformSensorMLToEbRIMMethod(sensormlcontent);
			System.out.println(FormatXmlUtil.formatXml(ebrimcontent));
			// 获取传感器的标识符
			String sensorid = StringUtil
					.DeletePackageStr(RegistryPackageDocument.Factory.parse(
							ebrimcontent).getRegistryPackage().getId().trim());
			// 是否存在
			if (com.csw.utils.SensorInfoUtil.OperateSensorUtil
					.IsExistsSensorML(sensorid)) {
				throw new SensorExistException("编号为" + sensorid + "的传感器并已经存在!");
			}
			// EbRimOperationUtil.ParseAndSaveEbrimContent(ebrimcontent);
			SaveEbRimService service = new SaveEbRimService();
			service.SaveEbRIMMethod(username, password, "", "",
					sensormlcontent, "");
			// oUtil.SaveSensorML(username, password, sensorid,
			// sensormlcontent);
			// 保存文档
			boolean bol = com.csw.utils.SensorInfoUtil.OperateSensorUtil
					.SaveSensorML(username, password, sensorid, sensormlcontent);
			if (!bol) {
				throw new ServiceException("无法sensorml问题 !");
			} else {
				return bol;
			}
		}
		return true;
	}

	public boolean saveSensorML(String username, String password,
			String sensormlcontent) throws ServiceException {
		SaveEbRimService sebs = new SaveEbRimService();
		if (1 == sebs.SaveEbRIMMethod(username, password, "", "",
				sensormlcontent, "")) {
			return true;
		}
		return false;
	}

	public boolean deleteSensorML(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		if (1 == new DeleteSimpleSensorService().DeleteSimpleSensorMethod(
				username, password, sensorid)) {
			return true;
		}
		return false;
	}

	public boolean deleteSensorMLs(String username, String password,
			List<String> sensorids) throws ServiceException, NullZeroException {
		if (sensorids == null || sensorids.size() == 0) {
			throw new ServiceException("参数sensorids要求");
		}
		UserInfoUtil.CheckUserLogin(username, password);
		DeleteSimpleSensorService dssi = new DeleteSimpleSensorService();
		for (String sensorid : sensorids) {
			dssi.DeleteSimpleSensorMethod(username, password, sensorid);
			// StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid,
			// "sensorid");
			// // 首先删除用户保存的文件
			// if (OperateSensorUtil.IsExistsSensorML(username, sensorid)) {
			// OperateSensorUtil.DeleteSensorML(username, sensorid);
			// // 删除指定的传感器的的编号
			// DeleteCswElementUtil.deleteCswSensorInfo(username, sensorid);
			// }
		}
		return true;
	}

	public String readSensorContent(String username, String password,
			String sensorid) throws ServiceException, NullZeroException,
			SensorNotExistException, FileNotFoundException {
		SaveSensorMLService sm = new SaveSensorMLService();
		return sm.ReadSensorContent(username, password, sensorid);
	}

	public boolean isExistsSensorML(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid, "sensorid");
		return com.csw.utils.SensorInfoUtil.OperateSensorUtil
				.IsExistsSensorML(sensorid);
	}

	public Date getSensorMLSaveTime(String username, String password,
			String sensorid) throws ServiceException, NullZeroException,
			SensorNotExistException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid, "sensorid");
		return com.csw.utils.SensorInfoUtil.OperateSensorUtil
				.GetSensorMLSavedTime(sensorid);
	}

	public List<String> getSensorMLIdsOfUser(String username, String passowrd)
			throws ServiceException, NullZeroException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, passowrd);
		List<String> list = com.csw.utils.SensorInfoUtil.OperateSensorUtil
				.getSensorMLIdsOfUser(username);
		List<String> sensorids = new ArrayList<String>();
		if (list != null) {
			for (String str : list) {
				sensorids.add(StringUtil.DeletePackageStr(str));
			}
		}
		return sensorids;
	}

	public boolean updateSensorMLs(String username, String password,
			List<String> sensorids, List<String> sensormlcontents)
			throws ServiceException, NullZeroException,
			UnsupportedEncodingException, XmlException,
			EbrimNotFormatException, DBObjectSaveException, IOException,
			SensorExistException, FileExistException,
			TransactionProcessException, SensorIdNotEqualException,
			SensorNotExistException {
		SaveSensorMLService sms = new SaveSensorMLService();
		for (int i = 0; i < sensorids.size(); i++) {
			sms.DeleteSensorML(username, password, sensorids.get(i));
			if (sms.SaveSensorML(username, password, sensorids.get(i),
					sensormlcontents.get(i))) {
				return false;
			}
		}
		return true;

	}

	public boolean updateSensorML(String username, String password,
			String sensorid, String sensormlcontent) throws ServiceException,
			NullZeroException, UnsupportedEncodingException, XmlException,
			EbrimNotFormatException, DBObjectSaveException, IOException,
			SensorExistException, FileExistException,
			TransactionProcessException, SensorIdNotEqualException,
			SensorNotExistException {
		SaveSensorMLService sms = new SaveSensorMLService();
		sms.DeleteSensorML(username, password, sensorid);
		return sms.SaveSensorML(username, password, sensorid, sensormlcontent);
	}

	public boolean checkUserName(String username) throws ServiceException,
			NullZeroException {
		StringUtil.checkStringIsNotNULLAndEmptyMethod(username, "username");
		return UserInfoUtil.CheckUserNameIsExist(username);
	}

	/**
	 * 核实用户信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws NullZeroException
	 */
	@SuppressWarnings("unchecked")
	public boolean checkUserLogin(String username, String password)
			throws ServiceException, NullZeroException {
		StringUtil.checkStringIsNotNULLAndEmptyMethod(username, "username");
		StringUtil.checkStringIsNotNULLAndEmptyMethod(password, "password");
		boolean bol = false;
		List list = (List) com.csw.utils.SensorInfoUtil.OperateSensorUtil
				.queryObject("from LoginUserBean where username='"
						+ username.trim() + "' and password='"
						+ password.trim() + "'");
		if (list != null && list.size() != 0) {
			bol = true;
		}
		return bol;
	}

	public boolean checkSensorMLIdExistMethod(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid, "sensorid");
		return com.csw.utils.SensorInfoUtil.OperateSensorUtil
				.CheckSensorMLExistFromXMLMethod(StringUtil
						.DeletePackageStr(sensorid));
	}

	public String createGetRecordByIdDocumentMethod(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		CreateGetRecordByIdDocumentService cgrbis = new CreateGetRecordByIdDocumentService();
		return cgrbis.CreateGetRecordByIdDocumentMethod(username, password,
				sensorid);
	}

	public int deleteUserInfo(String username, String password)
			throws ServiceException, NullZeroException {// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (UserInfoUtil.DeleteUserByUserName(username)) {
			return 1;
		} else {
			throw new ServiceException("不存在该用户!");
		}
	}

	public List<String> getAllPlatfromType(String username, String password)
			throws ServiceException, NullZeroException {
		GetAllPlatfromTypesService gaps = new GetAllPlatfromTypesService();
		return gaps.getAllPlatfromType(username, password);
	}

	public List<String> getSensorTypes(String username, String password,
			List<String> sensorids) throws ServiceException, NullZeroException {
		List<String> results = new ArrayList<String>();
		GetAllSensorBasicInfoService gs = new GetAllSensorBasicInfoService();
		for (String sensorid : sensorids) {
			results.add(gs.GetSingleSensorBasicInfo(username, password,
					sensorid).getSensortype());
		}
		return results;
	}

	public String getSensorType(String username, String password,
			String sensorid) throws NullZeroException, ServiceException {
		GetAllSensorBasicInfoService gs = new GetAllSensorBasicInfoService();
		return gs.GetSingleSensorBasicInfo(username, password, sensorid)
				.getSensortype();
	}

	public List<String> getAllPlatfromId(String username, String password)
			throws ServiceException, NullZeroException {
		QueryPlatFormandSensorsService qpfs = new QueryPlatFormandSensorsService();
		List<PlatformandSensors> pss = qpfs.GetPlatFormandSensors(username,
				password, "all");
		List<String> results = new ArrayList<String>();
		for (PlatformandSensors ps : pss) {
			results.add(ps.getPlatform().getSensor());
		}
		return results;
	}

	/**
	 * 获取单个传感器信息
	 */
	public SensorBasciForOracleType getSingleSensorBasicInfo(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		SensorBasciForOracleType sb = OperateSensornewUtil
				.getSingleBasicInfoOfTable(sensorid);
		return sb;
	}

	public List<String> getAllSensorTypes(String username, String password)
			throws ServiceException, NullZeroException {
		List<String> types = new ArrayList<String>();
		List<SensorBasciForOracleType> sbits = OperateSensornewUtil
				.getAllSensorBasicInfo();
		for (SensorBasciForOracleType sbot : sbits) {
			if (!types.contains(sbot.getSENSORTYPE())) {
				types.add(sbot.getSENSORTYPE());
			}
		}
		return types;
	}

	public String getRecordsDocumentMethod(String username, String password,
			String queryStr, boolean allownull) throws ServiceException,
			NullZeroException {
		GetRecordsService grs = new GetRecordsService();
		return grs.GetRecordsDocumentMethod(username, password, queryStr,
				allownull);
	}

	public List<SensorBasicInfoType> getAllSensorBasicInfo(String username,
			String password, boolean all) throws ServiceException,
			NullZeroException {
		GetAllSensorBasicInfoService gs = new GetAllSensorBasicInfoService();
		return gs.GetAllSensorBasicInfo(username, password, all);
	}

	public List<String> getOwnerAllSensorMLDocumentMethod(String username,
			String password) throws ServiceException, NullZeroException {
		List<String> sensorids = com.csw.utils.SensorInfoUtil.OperateSensorUtil
				.getSensorMLIdsOfUser(username);
		List<String> resultStrings = new ArrayList<String>();
		for (String sensorid : sensorids) {
			resultStrings.add(com.csw.utils.SensorInfoUtil.OperateSensorUtil
					.GetSensorMLBySensorid(sensorid));
		}
		return resultStrings;
		// 下面的主要是存在，从getAllSensorMLInfoWithOwner获取到用户包含的传感器信息，但是后面，smt.getSensorid为null，暂时没弄清楚，后期再解决
		// List<SensorMLType>
		// smts=OperateSensorUtil.GetAllSensorMLInfoWithOwner(username);
		// for(SensorMLType smt:smts){
		// System.out.println(smt.getSensorid()+"  "+smt.getOwner()+"  "+smt.getSavetime());
		// }
		// List<String> resultStrings=new ArrayList<String>();
		// for(SensorMLType smt: smts){
		// resultStrings.add(OperateSensorUtil.GetSensorMLBySensorid(smt.getSensorid()));
		// }
		// return resultStrings;
	}

	public List<String> getRegistrySensorWithOwnerOptionMethod(String username,
			String password, boolean all) throws ServiceException,
			NullZeroException {
		GetRegistrySensorWithOwnerOptionService grs = new GetRegistrySensorWithOwnerOptionService();
		return grs.GetRegistrySensorWithOwnerOptionMethod(username, password,
				all);
	}

	public List<String> getSameSensorTypeSensorGroup(String username,
			String password, SensorTypes sensortype) throws ServiceException,
			NullZeroException {
		GetSameSensorTypeGroupService gstsv = new GetSameSensorTypeGroupService();
		return gstsv.getSameSensorTypeSensorGroup(username, password,
				sensortype);
	}

	public SensorOperable getSensorOperableMethod(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		GetSensorIsOperableService gsos = new GetSensorIsOperableService();
		return gsos.GetSensorOperableMethod(username, password, sensorid);
	}

	public List<SensorOperable> getSensorsOperableListMethod(String username,
			String password, List<String> sensorids) throws ServiceException,
			NullZeroException {
		GetSensorIsOperableService gsos = new GetSensorIsOperableService();
		return gsos.GetSensorsOperableListMethod(username, password, sensorids);
	}

	public List<SensorandBelongPlatform> getSensorsBelongPlatform(
			String username, String password, List<String> sensors)
			throws ServiceException, NullZeroException {
		GetSensorsBelongPlatfromService gsbp = new GetSensorsBelongPlatfromService();
		return gsbp.GetSensorsBelongPlatform(username, password, sensors);
	}

	public SensorShareLevel getSensorShareLevelMethod(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		GetSensorShareableService GSS = new GetSensorShareableService();
		return GSS.GetSensorShareLevelMethod(username, password, sensorid);
	}

	public List<SensorShareLevel> getSensorShareLevelsMethod(String username,
			String password, List<String> sensorids) throws ServiceException,
			NullZeroException {
		GetSensorShareableService GSS = new GetSensorShareableService();
		return GSS.GetSensorShareLevelsMethod(username, password, sensorids);
	}

	public List<PlatformandSensors> getSepcialPlatfromSensors(String username,
			String password, List<String> platforms) throws ServiceException,
			NullZeroException {
		GetSpecialPlatformSensorsService gsps = new GetSpecialPlatformSensorsService();
		return gsps.GetSepcialPlatfromSensors(username, password, platforms);
	}

	public PlatformandSensors getSepcialPlatfromSensor(String username,
			String password, String platform) throws ServiceException,
			NullZeroException {
		GetSpecialPlatformSensorsService gsps = new GetSpecialPlatformSensorsService();
		List<String> platforms = new ArrayList<String>();
		platforms.add(platform);
		return gsps.GetSepcialPlatfromSensors(username, password, platforms)
				.get(0);
	}

	public LoginUserBean loginUserMethod(String username, String password)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		return UserInfoUtil.UserLoginMethod(username, password);
	}

	public Map<String, String> parseQueryStrAndConditionsMethod(
			String username, String password, String queryStr)
			throws ServiceException, NullZeroException {
		ParseQueryStrAndGetConditionsService pService = new ParseQueryStrAndGetConditionsService();
		return pService.ParseQueryStrAndConditionsMethod(username, password,
				queryStr);
	}

	public List<SensorBBox> querySensorsBBOXMethod(String username,
			String password, List<String> sensors) throws ServiceException,
			NullZeroException {
		QueryAllSensorsBBOXService qa = new QueryAllSensorsBBOXService();
		return qa.QuerySensorsBBOXMethod(username, password, sensors);
	}

	public List<LoginUserBean> queryAllUserInfosMethod(String username,
			String password) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		return UserInfoUtil.GetAllUserLoginBeans();
	}

	public List<PlatformandSensors> getPlatFormandSensors(String username,
			String password, String platfromtype) throws ServiceException,
			NullZeroException {
		QueryPlatFormandSensorsService qp = new QueryPlatFormandSensorsService();
		return qp.GetPlatFormandSensors(username, password, platfromtype);
	}

	public int registryMethod(String address, int age, String emailAddress,
			int gender, int level, String password, String telephone,
			String username, String zhiye) throws ServiceException {
		RegistryService rService = new RegistryService();
		return rService.RegistryMethod(address, age, emailAddress, gender,
				level, password, telephone, username, zhiye);
	}

	public String searchGetRecordByIdDocumentMethod(String username,
			String password, String getrecordByIdContent)
			throws ServiceException, NullZeroException {
		SearchGetRecordByIdDocumentService tk = new SearchGetRecordByIdDocumentService();
		return tk.SearchGetRecordByIdDocumentMethod(username, password,
				getrecordByIdContent);
	}

	public boolean isSensorBelongToPlatform(String username, String password,
			String sensor, String platform) throws ServiceException,
			NullZeroException {
		SensorIsBelongPlatfromInterface sInterface = new SensorIsBelongPlatfromService();
		return sInterface.IsSensorBelongToPlatform(username, password, sensor,
				platform);
	}

	public List<SensorPlatformPair> isSensorsBelongToPlatforms(String username,
			String password, List<SensorPlatformPair> spfs)
			throws ServiceException, NullZeroException {
		SensorIsBelongPlatfromInterface sInterface = new SensorIsBelongPlatfromService();
		return sInterface.IsSensorsBelongToPlatforms(username, password, spfs);
	}

	public String showCapabilitiesResponseMethod(String username,
			String password) throws ServiceException, NullZeroException {
		ShowCapabilitiesResponseService sss = new ShowCapabilitiesResponseService();
		return sss.ShowCapabilitiesResponseMethod(username, password);
	}

	/**
	 * 获取传感器信息用于更新
	 */
	public SensorBasciForOracleType showSensorInfoForUpdateMethod(
			String username, String password, String sensorid)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensornewUtil.GetSensorBasicInfo(sensorid);

	}

	public String transactionSensorMLToeEbRIM(String username, String password,
			String sensorml) throws ServiceException, NullZeroException {
		TransformSensorMLToebRIMService tf = new TransformSensorMLToebRIMService();
		return tf.TransactionSensorMLToeEbRIMMethod(username, password,
				sensorml);
	}

	public int updateUserInfo(String adminusername, String adminpassword,
			String username, String password, String newpassword, int level)
			throws ServiceException, NullZeroException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		String[] s = { adminusername, adminpassword };
		StringUtil.checkStrsIsNotNULLAndEmptyMethod(s, "username or password");
		StringUtil.checkStringIsNotNULLAndEmptyMethod(newpassword,
				"newpassword");
		if (UserInfoUtil.GetLevelOfUser(adminusername, adminpassword) == 1) {
			boolean bol = UserInfoUtil
					.UpdateUserInfo("update LoginUserBean set password='"
							+ newpassword + "',level='" + level
							+ "' where username='" + username + "'");
			if (bol) {
				return 1;
			} else {
				throw new ServiceException("用户[" + username + "]的级别更新失败!");
			}
		}
		return 0;
	}

	public int updateUserInfoMethod(String username, String password,
			String address, String telephone) throws ServiceException,
			NullZeroException {
		UpdateUserInfoService uuis = new UpdateUserInfoService();
		return uuis
				.UpdateUserInfoMethod(username, password, address, telephone);
	}

	public String createGetReocdsDetailMethod(String username, String password,
			String queryStr) throws NullZeroException, ServiceException {
		CreateGetRecordsDetailService cgds = new CreateGetRecordsDetailService();
		return cgds.CreateGetReocdsDetailMethod(username, password, queryStr);
	}

	public List<SensorTypeYXL> getMutiSensorTypeOfSensors(String username,
			String password, List<String> sensors) throws ServiceException,
			NullZeroException {
		List<SensorTypeYXL> types = new ArrayList<SensorTypeYXL>();
		List<SensorBasciForOracleType> sbits = OperateSensornewUtil
				.getAllSensorBasicInfo();
		SensorTypeYXL sTypeYXL = null;
		for (String sensor : sensors) {
			for (SensorBasciForOracleType sb : sbits) {
				if (sb.getSENSORID().equals(sensor)) {
					sTypeYXL = new SensorTypeYXL();
					sTypeYXL.setSensorid(sensor);
					sTypeYXL.setType(sb.getSENSORTYPE());
					types.add(sTypeYXL);
				}
			}
		}
		return types;

	}

	public SensorInputInfoType getChineseInputsInfo(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		GetSensorChineseInputsService gsis = new GetSensorChineseInputsService();
		return gsis.GetChineseInputsInfo(username, password, sensorid);
	}

	public List<SensorInputInfoType> getChineseInputsInfoList(String username,
			String password, List<String> sensorids) throws ServiceException,
			NullZeroException {
		GetSensorChineseInputsService gsis = new GetSensorChineseInputsService();
		return gsis.GetChineseInputsInfoList(username, password, sensorids);
	}

	public String getSensorBandCategoryMethod(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		GetSensorBandCategoryService gsbs = new GetSensorBandCategoryService();
		return gsbs.GetSensorBandCategoryMethod(username, password, sensorid);
	}

	public String getSensorBandNumberMethod(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		GetSensorBandNumberService gsbns = new GetSensorBandNumberService();
		return gsbns.GetSensorBandNumberMethod(username, password, sensorid);
	}

	public SensorOutputType getSensorChineseOutputInfo(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		GetSensorChineseOutputsService gscos = new GetSensorChineseOutputsService();
		return gscos.GetSensorChineseOutputInfo(username, password, sensorid);
	}

	public List<SensorOutputType> getSensorChineseOutputInfoList(
			String username, String password, List<String> sensorids)
			throws ServiceException, NullZeroException {
		GetSensorChineseOutputsService gscos = new GetSensorChineseOutputsService();
		return gscos.GetSensorChineseOutputInfoList(username, password,
				sensorids);
	}

	public List<SensorCommunicationProValueType> getSensorCommunicationCapabilityForMultiSensorsMethod(
			String username, String password, List<String> sensorids,
			String capability) throws ServiceException, NullZeroException {
		GetSensorCommunicationCapabilityService gc = new GetSensorCommunicationCapabilityService();
		List<SensorBasicInfoType> temps = gc
				.GetSensorCommunicationCapabilityForMultiSensorsMethod(
						username, password, sensorids, capability);
		List<SensorCommunicationProValueType> tn = new ArrayList<SensorCommunicationProValueType>();
		SensorCommunicationProValueType sValueType = null;
		for (SensorBasicInfoType sb : temps) {
			sValueType = new SensorCommunicationProValueType();
			sValueType.setProname(capability);
			sValueType.setProvalue(sb.getOthersensorinfo());
			sValueType.setSensorid(sb.getSensorid());
			tn.add(sValueType);
		}
		return tn;
	}

	public Object getSensorCommunicationCapabilityMethod(String username,
			String password, String sensorid, String capability)
			throws ServiceException, NullZeroException {
		GetSensorCommunicationCapabilityService gc = new GetSensorCommunicationCapabilityService();
		return gc.GetSensorCommunicationCapabilityMethod(username, password,
				sensorid, capability);
	}

	public List<SensorComputeProValueType> getSensorComputeCapabilityForMultiSensorsMethod(
			String username, String password, List<String> sensorids,
			String capability) throws ServiceException, NullZeroException {
		GetSensorComputeCapabilityService gcs = new GetSensorComputeCapabilityService();
		List<SensorBasicInfoType> temps = gcs
				.GetSensorComputeCapabilityForMultiSensorsMethod(username,
						password, sensorids, capability);
		List<SensorComputeProValueType> tns = new ArrayList<SensorComputeProValueType>();
		SensorComputeProValueType tsc = null;
		for (SensorBasicInfoType sb : temps) {
			tsc = new SensorComputeProValueType();
			tsc.setProname(capability);
			tsc.setProvalue(sb.getOthersensorinfo());
			tsc.setSensorid(sb.getSensorid());
			tns.add(tsc);
		}
		return tns;
	}

	public Object getSensorComputeCapabilityMethod(String username,
			String password, String sensorid, String capability)
			throws ServiceException, NullZeroException {
		GetSensorComputeCapabilityService gcs = new GetSensorComputeCapabilityService();
		return gcs.GetSensorComputeCapabilityMethod(username, password,
				sensorid, capability);
	}

	public List<SensorConnPosType> getSensorConnectionEarthPosInfosMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException, NullZeroException {
		GetSensorConnectionEarthPosInfoService gs = new GetSensorConnectionEarthPosInfoService();
		List<SensorBasicInfoType> temps = gs
				.GetSensorConnectionEarthPosInfoFroMultiSensorsMethod(username,
						password, sensorids);
		List<SensorConnPosType> tns = new ArrayList<SensorConnPosType>();
		SensorConnPosType type = null;
		for (SensorBasicInfoType sb : temps) {
			type = new SensorConnPosType();
			type.setSensorid(sb.getSensorid());
			type.setPosinfo(sb.getOthersensorinfo());
			tns.add(type);
		}
		return tns;
	}

	public String getSensorConnectionEarthPosInfoMethod(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		GetSensorConnectionEarthPosInfoService gs = new GetSensorConnectionEarthPosInfoService();
		return gs.GetSensorConnectionEarthPosInfoMethod(username, password,
				sensorid);
	}

	public String getSensorConnectionEmailMethod(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		GetSensorConnectionEmailService gs = new GetSensorConnectionEmailService();
		return gs.GetSensorConnectionEmailMethod(username, password, sensorid);
	}

	public List<SensorEmailType> getSensorConnectionEmailsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException, NullZeroException {
		GetSensorConnectionEmailService gs = new GetSensorConnectionEmailService();
		List<SensorBasicInfoType> temps = gs
				.GetSensorConnectionEmailForMultiSensorsMethod(username,
						password, sensorids);
		List<SensorEmailType> sets = new ArrayList<SensorEmailType>();
		SensorEmailType sen = null;
		for (SensorBasicInfoType sb : temps) {
			sen = new SensorEmailType();
			sen.setEmailStr(sb.getOthersensorinfo());
			sen.setSensorid(sb.getSensorid());
			sets.add(sen);
		}
		return sets;
	}

	public String getSensorGeoInfoMethod(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		GetSensorGeoInfoService gss = new GetSensorGeoInfoService();
		return gss.GetSensorGeoInfoMethod(username, password, sensorid);
	}

	public List<SensorGeoType> getSensorGeoInfosMethod(String username,
			String password, List<String> sensorids) throws ServiceException,
			NullZeroException {
		GetSensorGeoInfoService gss = new GetSensorGeoInfoService();
		List<SensorBasicInfoType> sbis = gss
				.GetSensorGetoInfoForMultiSensorsMethod(username, password,
						sensorids);
		List<SensorGeoType> sgt = new ArrayList<SensorGeoType>();
		SensorGeoType tem = null;
		for (SensorBasicInfoType sb : sbis) {
			tem = new SensorGeoType();
			tem.setGeoStr(sb.getSensorbbox());
			tem.setSensorid(sb.getSensorid());
			sgt.add(tem);
		}
		return sgt;
	}

	public List<SensorIntendAppForOracleType> getSensorIntendedApplicationsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException, NullZeroException {
		GetSensorIntendedApplicationService gsia = new GetSensorIntendedApplicationService();
		List<SensorBasicInfoType> sbits = gsia
				.GetSensorIntendedApplicationForMultiSensorsMethod(username,
						password, sensorids);
		List<SensorIntendAppForOracleType> results = new ArrayList<SensorIntendAppForOracleType>();
		SensorIntendAppForOracleType te = null;
		for (SensorBasicInfoType sb : sbits) {
			te = new SensorIntendAppForOracleType();
			te.setSENSORID(sb.getSensorid());
			te.setSENSORINTEND(sb.getSensorintendapp());
			te.setSENSOROWNER(username);
			results.add(te);
		}
		return results;
	}

	public List<SensorIntendAppForOracleType> getSensorIntendedApplicationMethod(
			String username, String password, String sensorid)
			throws ServiceException, NullZeroException {
		GetSensorIntendedApplicationService gsia = new GetSensorIntendedApplicationService();
		String intend = gsia.GetSensorIntendedApplicationMethod(username,
				password, sensorid);
		List<SensorIntendAppForOracleType> sis = new ArrayList<SensorIntendAppForOracleType>();
		SensorIntendAppForOracleType si = new SensorIntendAppForOracleType();
		si.setSENSORID(sensorid);
		si.setSENSORINTEND(intend);
		si.setSENSOROWNER(username);
		sis.add(si);
		return sis;
	}

	public List<SensorKeywordType> getSensorKeyWordsMethod(String username,
			String password, List<String> sensorids) throws ServiceException,
			NullZeroException {
		GetSensorKeyWordsService gsks = new GetSensorKeyWordsService();
		List<SensorBasicInfoType> temps = gsks
				.GetSensorKeyWordsForMultiSensorsMethod(username, password,
						sensorids);
		List<SensorKeywordType> skts = new ArrayList<SensorKeywordType>();
		SensorKeywordType skt = null;
		List<String> keys = null;
		String[] strs = null;
		String kystr = null;
		for (SensorBasicInfoType sb : temps) {
			skt = new SensorKeywordType();
			skt.setSensorid(sb.getSensorid());
			keys = new ArrayList<String>();
			kystr = sb.getSensorkeyword();
			System.out.println(kystr);
			strs = kystr.split(",");
			for (int i = 0; i < strs.length; i++) {
				keys.add(strs[i].toString());
			}
			skt.setKeywords(keys);
			skts.add(skt);
		}
		return skts;
	}

	public String getSensorKeyWordMethod(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		GetSensorKeyWordsService gsks = new GetSensorKeyWordsService();
		return gsks.GetSensorKeyWordsMethod(username, password, sensorid);
	}

	public String getSensorLongNameMethod(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		GetSensorLongNameService gsls = new GetSensorLongNameService();
		return gsls.GetSensorLongNameMethod(username, password, sensorid);
	}

	public List<SensorLongNameType> getSensorLongNamesMethod(String username,
			String password, List<String> sensorids) throws ServiceException,
			NullZeroException {
		GetSensorLongNameService gsls = new GetSensorLongNameService();
		List<SensorBasicInfoType> sbits = gsls.GetSensorLongNamesMethod(
				username, password, sensorids);
		List<SensorLongNameType> slnts = new ArrayList<SensorLongNameType>();
		SensorLongNameType slnt = null;
		for (SensorBasicInfoType sbt : sbits) {
			slnt = new SensorLongNameType();
			slnt.setLongname(sbt.getSensorlongname());
			slnt.setSensorid(sbt.getSensorid());
			slnts.add(slnt);
		}
		return slnts;
	}

	public List<SensorMeasureProValueType> getSensorMeasureCapabilitysMethod(
			String username, String password, List<String> sensorids,
			String capability) throws ServiceException, NullZeroException {
		GetSensorMeasureCapabilityService gsmc = new GetSensorMeasureCapabilityService();
		List<SensorBasicInfoType> sbits = gsmc
				.GetSensorMeasureCapabilitiesForMultiSensorsMethod(username,
						password, sensorids, capability);
		List<SensorMeasureProValueType> results = new ArrayList<SensorMeasureProValueType>();
		SensorMeasureProValueType smMea = null;
		for (SensorBasicInfoType sbt : sbits) {
			smMea = new SensorMeasureProValueType();
			smMea.setProname(capability);
			smMea.setProvalue(sbt.getOthersensorinfo());
			smMea.setSensorid(sbt.getSensorid());
			results.add(smMea);
		}
		return results;
	}

	public String getSensorMeasureCapabilityMethod(String username,
			String password, String sensorid, String capability)
			throws ServiceException, NullZeroException {
		GetSensorMeasureCapabilityService gsmc = new GetSensorMeasureCapabilityService();
		return gsmc.GetSensorMeasureCapabilityMethod(username, password,
				sensorid, capability);
	}

	public List<SensorOrganType> getSensorOrganizationInfosMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException, NullZeroException {
		GetSensorOrganizationInfoService gsos = new GetSensorOrganizationInfoService();
		List<SensorBasicInfoType> sbits = gsos
				.GetSensorOrganizationInfoForMultiSensorsMethod(username,
						password, sensorids);
		List<SensorOrganType> sots = new ArrayList<SensorOrganType>();
		SensorOrganType sot = null;
		for (SensorBasicInfoType sbot : sbits) {
			sot = new SensorOrganType();
			sot.setSensorid(sbot.getSensorid());
			sot.setSensororgan(sbot.getSensororgan());
			sots.add(sot);
		}
		return sots;
	}

	public String getSensorOrganizationInfoMethod(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		return new GetSensorOrganizationInfoService()
				.GetSensorOrganizationInfoMethod(username, password, sensorid);
	}

	public List<SensorPhysicProType> getSensorPhysicalPropertysMethod(
			String username, String password, List<String> sensorids,
			String physicalproperty) throws ServiceException, NullZeroException {
		GetSensorPhysicalPropertyService gsp = new GetSensorPhysicalPropertyService();
		List<SensorBasicInfoType> sbits = gsp
				.GetSensorPhysicalPropertyForMultiSensorsMethod(username,
						password, sensorids, physicalproperty);
		List<SensorPhysicProType> sppt = new ArrayList<SensorPhysicProType>();
		SensorPhysicProType sp = null;
		for (SensorBasicInfoType sb : sbits) {
			sp = new SensorPhysicProType();
			sp.setProname(physicalproperty);
			sp.setProvalue(sb.getOthersensorinfo());
			sp.setSensorid(sb.getSensorid());
			sppt.add(sp);
		}
		return sppt;
	}

	public String getSensorPhysicalPropertyMethod(String username,
			String password, String sensorid, String physicalproperty)
			throws ServiceException, NullZeroException {
		GetSensorPhysicalPropertyService gsp = new GetSensorPhysicalPropertyService();
		return gsp.GetSensorPhysicalPropertyMethod(username, password,
				sensorid, physicalproperty);
	}

	public String getSensorPositionInfoMethod(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		GetSensorPositionInfoService gsp = new GetSensorPositionInfoService();
		return gsp.GetSensorPositionInfoMethod(username, password, sensorid);
	}

	public List<SensorPosType> getSensorPositionInfosMethod(String username,
			String password, List<String> sensorids) throws ServiceException,
			NullZeroException {
		GetSensorPositionInfoService gsp = new GetSensorPositionInfoService();
		List<SensorBasicInfoType> sbts = gsp
				.GetSensorPositionInfoForMultiSensorsMethod(username, password,
						sensorids);
		List<SensorPosType> spts = new ArrayList<SensorPosType>();
		SensorPosType spt = null;
		for (SensorBasicInfoType sbit : sbts) {
			spt = new SensorPosType();
			spt.setPosStr(sbit.getSensorpos());
			spt.setSensorid(sbit.getSensorid());
			spts.add(spt);
		}
		return spts;
	}

	public List<SensorTypeYXL> getSensorSensorTypeYXLMethod(String username,
			String password, List<String> sensorids) throws ServiceException,
			NullZeroException {
		GetSensorSensorTypeService gssts = new GetSensorSensorTypeService();
		List<SensorBasicInfoType> sbits = gssts
				.GetSensorSensorTypeForMultiSensorMethod(username, password,
						sensorids);
		List<SensorTypeYXL> stys = new ArrayList<SensorTypeYXL>();
		SensorTypeYXL sty = null;
		for (SensorBasicInfoType sbit : sbits) {
			sty = new SensorTypeYXL();
			sty.setSensorid(sbit.getSensorid());
			sty.setType(sbit.getSensortype());
			stys.add(sty);
		}
		return stys;
	}

	public List<String> getSensorSensorTypeMethod(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		GetSensorSensorTypeService gsss = new GetSensorSensorTypeService();
		String string = gsss.GetSensorSensorTypeMethod(username, password,
				sensorid);
		List<String> strings = new ArrayList<String>();
		strings.add(string);
		return strings;
	}

	public String getSensorShortNameMethod(String username, String password,
			String sensorid) throws ServiceException, NullZeroException {
		GetSensorShortNameService gsss = new GetSensorShortNameService();
		return gsss.GetSensorShortNameMethod(username, password, sensorid);
	}

	public List<SensorShortName> getSensorShortNamesMethod(String username,
			String password, List<String> sensorids) throws ServiceException,
			NullZeroException {
		GetSensorShortNameService gsss = new GetSensorShortNameService();
		List<SensorBasicInfoType> sbits = gsss
				.GetSensorShortNameforMultiSensorsMethod(username, password,
						sensorids);
		List<SensorShortName> ssns = new ArrayList<SensorShortName>();
		SensorShortName ssn = null;
		for (SensorBasicInfoType sbt : sbits) {
			ssn = new SensorShortName();
			ssn.setSensorid(sbt.getSensorid());
			ssn.setShortname(sbt.getSensorshortname());
			ssns.add(ssn);
		}
		return ssns;
	}

	public List<SensorTimeType> getSensorWorkValidTimeBeginsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException, NullZeroException {
		GetSensorWorkBeginTimeService gskt = new GetSensorWorkBeginTimeService();
		List<SensorBasicInfoType> sbits = gskt
				.GetSensorWorkValidTimeBeginForMultiSensorsMethod(username,
						password, sensorids);
		List<SensorTimeType> stts = new ArrayList<SensorTimeType>();
		SensorTimeType stt = null;
		for (SensorBasicInfoType sbit : sbits) {
			stt = new SensorTimeType();
			stt.setSensorid(sbit.getSensorid());
			stt.setTimeStr(sbit.getSensorbegintime());
			stts.add(stt);
		}
		return stts;
	}

	public String getSensorWorkValidTimeBeginMethod(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		GetSensorWorkBeginTimeService gskt = new GetSensorWorkBeginTimeService();
		return gskt.GetSensorWorkValidTimeBeginMethod(username, password,
				sensorid);
	}

	public List<SensorTimeType> getSensorWorkValidTimeEndsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException, NullZeroException {
		GetSensorWorkEndTimeService gs = new GetSensorWorkEndTimeService();
		List<SensorBasicInfoType> sbits = gs
				.GetSensorWorkValidTimeEndForMultiSensorsMethod(username,
						password, sensorids);
		List<SensorTimeType> stts = new ArrayList<SensorTimeType>();
		SensorTimeType stt = null;
		for (SensorBasicInfoType sbit : sbits) {
			stt = new SensorTimeType();
			stt.setSensorid(sbit.getSensorid());
			stt.setTimeStr(sbit.getSenosrendtime());
			stts.add(stt);
		}
		return stts;
	}

	public String getSensorWorkValidTimeEndMethod(String username,
			String password, String sensorid) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		return com.csw.utils.SensorInfoUtil.OperateSensorUtil
				.GetSensorWorkEndTime(sensorid);
	}

	public boolean checkIsConnectMethod(String username, String password)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("用户名与密码不能为空!");
		}
		if (UserInfoUtil.CheckUserInfo(username, password) == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据用户填写的信息生成简单的getRecords的文档
	 * 
	 * @param username
	 *            用戶名
	 * @param password
	 *            密码
	 * @param startRecord
	 *            开始的查询记录
	 * @param maximumRecord
	 *            最大的返回的查询记录数
	 * @param west
	 *            西边的值
	 * @param east
	 *            东边的值
	 * @param south
	 *            南边的值
	 * @param north
	 *            北边的值
	 * @param startTime
	 *            开始的值
	 * @param endTime
	 *            结束的时间
	 * @param requestType
	 *            请求的类型
	 * @param profileType
	 *            profile的类型
	 * @param title
	 *            查询的关键 名称
	 * @param keyword
	 *            查询的关键子
	 * @return 返回的是生成getRecords的文档的内容,否则返回null;
	 * @throws NullZeroException
	 */
	public String createGetRecordsDocumentMethod(String username,
			String password, int startRecord, int maximumRecord, String west,
			String east, String south, String north, String startTime,
			String endTime, String requestType, String profileType,
			String title, String keyword) throws ServiceException,
			NullZeroException {
		return new CreateGetRecordsDocumentService()
				.CreateGetRecofdsDocumentMethod(username, password,
						startRecord, maximumRecord, west, east, south, north,
						startTime, endTime, requestType, profileType, title,
						keyword);
	}

	public boolean deleteAllSensorsMethod(String username, String password)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> sensorids = OperateSensorUtil
				.getAllSensorByOwner(username);
		DeleteSimpleSensorService dssi = new DeleteSimpleSensorService();
		for (String sensorid : sensorids) {
			// DeleteCswElementUtil.deleteCswSensorInfo(username, sensorid);
			dssi.DeleteSimpleSensorMethod(username, password, sensorid);
		}
		return true;
	}

	public List<SensorMLType> getAllBasicInfoOfSensorMLMethod(String username,
			String password, boolean type) throws ServiceException,
			NullZeroException {
		GetAllBasicInfoOfSensorMLService gabs = new GetAllBasicInfoOfSensorMLService();
		return gabs.GetAllBasicInfoOfSensorMLMethod(username, password, type);
	}

	public boolean cleanAllSensorAttachment(String username, String password,
			String owner) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorAttachmentMarkerType> samts = new ArrayList<SensorAttachmentMarkerType>();
		samts = SensorMLAttacheMentInfoOperation
				.getAllSensorAttachmentTypesByOwner(null, owner);
		// 获取全部的文件，并删除
		if (null != samts && samts.size() != 0) {
			for (SensorAttachmentMarkerType smat : samts) {
				System.out.println(smat.getSensorattachementpath());
				FileOperationUtil.DeleteFile(FileOperationUtil
						.TranslateWebFilepathToLocalFilePath(smat
								.getSensorattachementpath()));
			}
		}
		// 删除文件在数据库中的记录
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		if (owner != null && owner.trim().length() != 0) {
			session.createQuery(
					"delete from SensorMLImage where owner='" + owner + "'")
					.setCacheable(true).executeUpdate();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} else {
			// 如果在数据库中不存在信息，在将打开的session关闭
			// 删除信息
			GetSessionUtil.CloseSessionInstance(session);
			throw new ServiceException("输入的owner参数错误!");
		}
	}

	public int deleteSensorMLAttachmentMethod(String username, String password,
			String sensorid, String sensorattachmentmarker)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			try {
				// 获取所有的附件web路径
				List<String> imgurls = new ArrayList<String>();
				List<SensorAttachmentMarkerType> tempAttachmentMarkerTypes = SensorMLAttacheMentInfoOperation
						.ReadSensorAttachmentPathFile(sensorid,
								sensorattachmentmarker);
				if (tempAttachmentMarkerTypes != null
						&& tempAttachmentMarkerTypes.size() != 0) {
					for (SensorAttachmentMarkerType smat : tempAttachmentMarkerTypes) {
						imgurls.add(smat.getSensorattachementpath());
					}
				}
				if (imgurls != null && imgurls.size() != 0) {
					for (String imgurl : imgurls) {
						// 删除文件
						FileOperationUtil.DeleteFile(FileOperationUtil
								.TranslateWebFilepathToLocalFilePath(imgurl));
						// 在删除在系统中的文件路径记录
						SensorMLAttacheMentInfoOperation
								.DeleteSensorAttachementRecord(sensorid, imgurl);
					}
					return 1;
				} else {
					return 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}
		} else {
			throw new ServiceException("用户名或密码错误!");
		}
	}

	public int deleteSensorMLAttchmentByWebFilePathMehtod(String username,
			String password, String sensorid, String sensorwebfilepath)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			// 删除本地文件，如果没有，则不删除
			FileOperationUtil.DeleteFile(FileOperationUtil
					.TranslateWebFilepathToLocalFilePath(sensorwebfilepath));
			if (SensorMLAttacheMentInfoOperation.DeleteSensorAttachementRecord(
					sensorid, sensorwebfilepath)) {
				return 1;
			} else {
				return 2;
			}
		} else {
			throw new ServiceException("用户名或密码错误!");
		}
	}

	public List<SensorAttachmentMarkerType> deleteSomeSensorsAttachmentsWithMarkers(
			String username, String password,
			List<SensorAttachmentMarkerType> sensors) throws ServiceException,
			NullZeroException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			// 删除指定了附加说明和传感器标识符的传感器附件
			// 步骤：判定是否存在附件说明，如果不设置，则删除全部的附件（指定的传感器）
			if (sensors != null && sensors.size() != 0) {
				for (SensorAttachmentMarkerType samt : sensors) {
					if (samt.getSensorid() != null
							&& !samt.getSensorid().trim().equals("")) {
						// 这种情况下，进行删除指定的传感器的全部的附件
						deleteSensorMLAttachmentMethod(username, password, samt
								.getSensorid(), samt
								.getSensorattachementmarker());
					}
				}
			}

			if (sensors == null || sensors.size() == 0) {
				// 如果没有，则看作是删除成功
				return sensors;
			} else {
				List<SensorAttachmentMarkerType> results = new ArrayList<SensorAttachmentMarkerType>();
				for (SensorAttachmentMarkerType samt : sensors) {
					// 获取传感器的url地址samt.getSensorattachementpath();如果不存在，url地址肯定也不存在
					results = SensorMLAttacheMentInfoOperation
							.ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
									samt.getSensorid(), samt
											.getSensorattachementmarker(), true);
				}
				return results;
			}

		} else {
			throw new ServiceException("用户名或密码错误");
		}

	}

	@SuppressWarnings("unchecked")
	public List<String> getAllSensorIdsWithSensorImage(String attachmentmarker) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List<String> ssList = session.createQuery(
				" select sensorid from SensorMLImage where attchmentmarker='"
						+ attachmentmarker + "'").setCacheable(true).list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return ssList;
	}

	public List<SensorAttachmentMarkerType> getSomeSensorAttachementPathsForSomeSensors(
			String username, String password,
			List<SensorAttachmentMarkerType> sensors, boolean bol)
			throws ServiceException, NullZeroException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			List<SensorAttachmentMarkerType> results = new ArrayList<SensorAttachmentMarkerType>();
			if (sensors != null && sensors.size() != 0) {
				for (SensorAttachmentMarkerType samkt : sensors) {
					String sensorid = samkt.getSensorid();// 获取所需传感器标识
					String attchmentmarker = samkt.getSensorattachementmarker();// 获取所需传感器附件类型
					if (sensorid != null && sensorid.trim() != "") {
						// 输入的标识符正常
						if (bol) {
							results = SensorMLAttacheMentInfoOperation
									.ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
											sensorid, attchmentmarker, true);
						} else {
							results = SensorMLAttacheMentInfoOperation
									.ReadSensorAttachmentPathFile(sensorid,
											attchmentmarker);
						}
					}
				}
			}
			return results;
		} else {
			throw new ServiceException("用户名或密码错误");
		}
	}

	public boolean isExistSensorAttchmentBySensorWebFilePathMethod(
			String username, String password, String sensorid,
			String filewebpath, boolean bol) throws ServiceException,
			NullZeroException {
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		UserInfoUtil.CheckUserLogin(username, password);
		return SensorMLAttacheMentInfoOperation.IsSensorAttachementExist(
				sensorid, filewebpath, bol);
	}

	public boolean isExistSensorAttchmentMethod(String username,
			String password, String sensorid, String attchmentmarker,
			boolean bol) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		try {
			if (bol) {
				// 如果为true，则检测是否在文件系统中存在
				List<String> results = new ArrayList<String>();
				List<SensorAttachmentMarkerType> temp = SensorMLAttacheMentInfoOperation
						.ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
								sensorid, attchmentmarker, bol);
				if (temp != null && temp.size() != 0) {
					for (SensorAttachmentMarkerType samt : temp) {
						results.add(samt.getSensorattachementpath());
					}
				}
				if (results != null && results.size() != 0) {
					return true;
				} else {
					return false;
				}
			} else {
				// 只检测是否在数据文件系统中存在
				List<String> results = new ArrayList<String>();
				List<SensorAttachmentMarkerType> temp = SensorMLAttacheMentInfoOperation
						.ReadSensorAttachmentPathFile(sensorid, attchmentmarker);
				if (temp != null && temp.size() != 0) {
					for (SensorAttachmentMarkerType samt : temp) {
						results.add(samt.getSensorattachementpath());
					}
				}
				if (results != null && results.size() != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<String> readSensorAttchmentPathFile(String username,
			String password, String sensorid, String sensorattachmentmarker,
			boolean bol) throws ServiceException, NullZeroException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		try {
			StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid, "sensorid");
		} catch (NullZeroException e) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		List<SensorAttachmentMarkerType> samts = SensorMLAttacheMentInfoOperation
				.ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
						sensorid, sensorattachmentmarker, bol);
		if (samts != null && samts.size() != 0) {
			for (SensorAttachmentMarkerType samt : samts) {
				results.add(samt.getSensorattachementpath());
			}
		}
		return results;
	}

	public int saveSensorMLAttachmentMethod(String username, String password,
			String sensorid, SensorAttachmentType attchment,
			String attachemarker, String owner) throws ServiceException {
		if (owner == null || owner.trim().length() == 0) {
			throw new ServiceException("附件所属者owner参数不能为空，或设置错误");
		}
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			try {
				if (attchment.getTypename() == null
						&& attchment.getTypename().equals("")) {
					throw new ServiceException("附件后缀名必须指定");
				}
				// 生成文件的名称名称
				String onlyfilename = AllFileNameOfDirUtil
						.ReplaceFileNameWith_(sensorid)
						+ "." + attchment.getTypename();
				String webfilename = SystemConfig.sensorimagesaveurl
						+ onlyfilename;
				if (SensorMLAttacheMentInfoOperation.IsSensorAttachementExist(
						sensorid, webfilename, true)) {
					try {
						// 先删除相关文件
						deleteSensorMLAttachmentMethod(username, password,
								sensorid, attachemarker);
						// 再保存相关文件
						saveSensorMLAttachmentMethod(username, password,
								sensorid, attchment, attachemarker, owner);
						return 1;
					} catch (Exception e) {
						e.printStackTrace();
						return 2;
					}
				} else {
					// 保存传感器附件
					if (SensorMLAttacheMentInfoOperation.SaveSensorAttchment(
							sensorid, attachemarker, attchment, owner)) {
						return 1;
					} else {
						return 2;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}
		} else {
			throw new ServiceException("用户名或密码错误");
		}
	}

	public List<SensorAttachmentMarkerType> saveSomeSensorAttachementsWithMarkers(
			String username, String password,
			List<SensorAttachmentMarkerType> sensors, String owner)
			throws ServiceException {
		List<SensorAttachmentMarkerType> samkts = new ArrayList<SensorAttachmentMarkerType>();
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			if (sensors != null && sensors.size() != 0) {
				for (SensorAttachmentMarkerType smakt : sensors) {
					if (smakt.getSensorid() != null
							&& smakt.getImg().getTypename() != null
							&& smakt.getImg() != null) {
						if (SensorMLAttacheMentInfoOperation
								.SaveSensorAttchment(smakt.getSensorid(), smakt
										.getSensorattachementmarker(), smakt
										.getImg(), owner)) {
							SensorAttachmentMarkerType samkt = new SensorAttachmentMarkerType();
							samkt.setSensorid(smakt.getSensorid());
							samkt
									.setSensorattachementpath(SystemConfig.sensorimagesaveurl
											+ ""
											+ AllFileNameOfDirUtil
													.ReplaceFileNameWith_(smakt
															.getSensorid())
											+ "."
											+ smakt.getImg().getTypename());
							samkts.add(samkt);
						}
					}
				}
			}
			return samkts;
		} else {
			throw new ServiceException("用户名或密码错误");
		}
	}

	public int deleteSensorByTransactionDeleteMethod(String username,
			String password, String transactioncontent)
			throws ServiceException, NullZeroException {
		DeleteSensorByTransactionDeleteService dT = new DeleteSensorByTransactionDeleteService();
		return dT.DeleteSensorByTransactionDeleteMethod(username, password,
				transactioncontent);
	}

	public List<String> querySensorsByConditionsMethod(String username,
			String password, Map<String, String> conditions, boolean all,
			boolean allownull) throws ServiceException, NullZeroException {
		QuerySensorsByConditionsService qscs = new QuerySensorsByConditionsService();
		return qscs.QuerySensorsByConditionsMethod(username, password,
				conditions, all, allownull);
	}

	public String getRecordsContent(String username, String password,
			String getrerecords, String resultType) throws ServiceException,
			NullZeroException {
		ShowGetResponseDocumentService show = new ShowGetResponseDocumentService();
		return show.GetRecordsContent(username, password, getrerecords,
				resultType);
	}

	public int transactionUpdateContentMethod(String username, String password,
			String transactioncontent) throws ServiceException,
			NullZeroException {
		TransactionUpdateContentService t = new TransactionUpdateContentService();
		return t.TransactionUpdateContentMethod(username, password,
				transactioncontent);
	}

	public int updateSomeSensorInfoMethod(String username, String password,
			String sensorid, String keywords, String inputs, String outputs,
			String southenv, String westenv, String northenv, String eastenv,
			String positionx, String positiony) throws ServiceException,
			NullZeroException {
		UpdateSomeSensorInfoService usis = new UpdateSomeSensorInfoService();
		return usis.UpdateSomeSensorInfoMethod(username, password, sensorid,
				keywords, inputs, outputs, southenv, westenv, northenv,
				eastenv, positionx, positiony);
	}

	public String getServiceParam() {
		return SystemConfig.SENSOR_BBOX_DEF;
	}

	public List<SensorMLDocumentType> getAllSensorMLDocumentByIdsMethod(
			String username, String password, List<String> ids,
			String contenttype, boolean type) throws ServiceException,
			NullZeroException {
		List<SensorMLType> smtsList = com.csw.utils.SensorInfoUtil.OperateSensorUtil
				.GetAllSensorMLBasicInfo(ids);
		List<SensorMLDocumentType> smts = new ArrayList<SensorMLDocumentType>();
		SensorMLDocumentType st = null;
		for (SensorMLType s : smtsList) {
			st = new SensorMLDocumentType();
			st.setSensorid(s.getSensorid());
			st
					.setDocumentcontent(com.csw.utils.SensorInfoUtil.OperateSensorUtil
							.GetSensorMLBySensorid(s.getSensorid()));
			smts.add(st);
		}
		return smts;
	}
}
