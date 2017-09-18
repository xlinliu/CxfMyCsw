package com.csw.Service.impls;

import java.io.File;
import java.util.List;
import org.apache.xmlbeans.XmlException;
import com.csw.Service.interfaces.SaveEbRIMServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorMLType;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.StringUtil;
import com.ebrim.model.rim.RegistryPackageDocument;

public class SaveEbRimService implements SaveEbRIMServiceInterface {
	public static void main(String[] args) throws Exception {

		// List<String> sensors = new ArrayList<String>();
		// sensors.add("urn:liesmars:remotesensor:platform:AQUA");
		// sensors
		// .add("urn:Liesmars:insitusensor:platform:CUMTNorthGateAirQualityMonitorStation");
		// sensors
		// .add("urn:Liesmars:insitusensor:platform:CUMTEnvironmentSurveyingInstituteAirQualityMonitorStation");
		// deleteSensors(sensors);
		// System.out.println("over delete");
		// File file = new
		// File("F:\\小组资料\\各种数据注册实例\\传感器注册实例\\20141226 gnss-r sml");
		// insertSensorMLToCsw(sesif, file);

		// List<String> sensorids = FileOperationUtil.ReadOneLineOfFile(new
		// File(
		// "D:\\tt.txt"));
		// deleteSensors(sensorids);
		// System.out.println("delete over");

		SaveEbRimService sesif = new SaveEbRimService();
		File file = new File("E:\\xuzhoukuodasml");
		insertSensorMLToCsw(sesif, file);
		System.out.println("over insert");

		// updateSensorContents(
		// new File("E:\\RS-sml0109new"),
		// "<swe:field name=\"是否可控\">",
		// "<swe:DataRecord definition=\"urn:ogc:def:property:Liesmars:physicalProperties\">",
		// "<swe:field name=\"是否可控\"> <swe:Boolean definition=\"urn:ogc:def:property:isOperable\"> <swe:value>false</swe:value></swe:Boolean></swe:field>");
		// System.out.println("over");
	}

	public static void deleteSensors(List<String> sensorids)
			throws ServiceException {
		DeleteSimpleSensorService dsss = new DeleteSimpleSensorService();
		int k = 1;
		for (String sensorid : sensorids) {
			dsss.DeleteSimpleSensorMethod("admin", "cswadmin", sensorid.trim());
			System.out.println("No." + (k++) + " : " + sensorid);
		}
	}

	public int DeleteSimpleSensorMethod(String username, String password,
			String sensorid) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		// 参数验证
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("参数sensorid不能为空!");
		}
		SensorMLType smType = OperateSensorUtil.GetSensorMLBasicInfo(sensorid);

		// 核实的RegistryPacakge是否是该用户所拥有
		if (smType != null) {
			OperateSensorUtil.DeleteSensorML(username, password, sensorid);
			// 如果是超级用户就可以直接删除或者就是本人
			if (smType.getOwner().equals(username)
					|| UserInfoUtil.GetLevelOfUser(username, password) == 1) {
				String result = TransactionOperation
						.DeleteRegistryPackageById(sensorid);
				if (result.equals("success")) {
					try {
						OperateSensornewUtil.deleteSensorBasicInfo(sensorid);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return 1;
				} else {
					throw new ServiceException("用户删除档失败");
				}
			} else {
				throw new ServiceException("用户[" + username + "]无权对文档["
						+ sensorid + "]进行操作，请核实后重新操作!");
			}
		} else {

			String result = TransactionOperation
					.DeleteRegistryPackageById(sensorid);
			if (result.equals("success")) {
				OperateSensornewUtil.deleteSensorBasicInfo(sensorid);
				return 1;
			} else {
				throw new ServiceException("用户删除档失败");
			}
		}
	}

	public static String insertSensorMLToCsw(SaveEbRimService sesif, File file) {
		if (file.isFile()) {
			String content = FileOperationUtil.ReadFileContentByFile(file,
					"UTF-8");
			try {
				int resultnum = sesif.SaveEbRIMMethod("admin", "cswadmin", "",
						"", content, "");
				if (resultnum == 1) {
					System.out.println("成功!");
				}
				System.out.println(resultnum);
			} catch (ServiceException e) {
				e.printStackTrace();
				return file.getName();
			}
		} else {
			for (File temps : file.listFiles()) {
				insertSensorMLToCsw(sesif, temps);
			}
		}
		return "sucecess";
	}

	public int SaveEbRIMMethod(String username, String password,
			String intendedApplication, String processType, String sensorml,
			String serviceType) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		String basepath = new GetRealPathUtil().getWebInfPath();
		String ebrimfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "ebrim");
		String sensormlfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "sensorml");
		String xslfilepath = basepath + "transformxsls\\SensorMLToEbRIM.xsl";
		String processmodelxsltpath = basepath + "transformxsls\\Process.xsl";

		String ebrimcontent = TransactionOperation
				.ParseAndSaveAllTypesProcessMethod(sensorml, sensormlfilepath,
						processmodelxsltpath, xslfilepath, ebrimfilepath, null,
						null, null, false, username);
		// 获取传感器的id，ebrim结束的
		RegistryPackageDocument rpd;
		String rpid = "";
		try {
			rpd = RegistryPackageDocument.Factory.parse(ebrimcontent);
			rpid = rpd.getRegistryPackage().getId().trim();
			boolean bol = OperateSensorUtil.CheckSensorIdIsExistMethod(rpid);
			if (bol) {
				String sensorid = StringUtil.DeletePackageStr(rpid);
				TransactionOperation.DeleteRegistryPackageById(sensorid);
				throw new ServiceException("传感器标识符:[" + sensorid + "]已经被使用");
			} else {
				// 如果存在
				boolean sensormlbol = OperateSensorUtil.IsExistsSensorML(
						username, rpid);
				if (sensormlbol) {
					OperateSensorUtil.DeleteSensorML(username, password, rpid);
				}
			}
		} catch (XmlException e) {
			throw new ServiceException("参数sensorml内容错误，请输入规范文档内容");
		}
		TransactionOperation.ParseAndSaveAllTypesProcessMethod(sensorml,
				sensormlfilepath, processmodelxsltpath, xslfilepath,
				ebrimfilepath, processType, intendedApplication, serviceType,
				true, username);
		OperateSensorUtil.SaveSensorML(username, password, rpid, sensorml
				.trim());
		FileOperationUtil.DeleteFile(sensormlfilepath);
		FileOperationUtil.DeleteFile(ebrimfilepath);
		/**
		 * 保存传感器基本信息表中
		 */
		OperateSensornewUtil.saveSensorBasicInfo(StringUtil
				.DeletePackageStr(rpid));
		return 1;
	}
}
