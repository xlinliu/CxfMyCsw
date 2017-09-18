package com.csw.Service.impls;

import java.io.File;
import java.io.IOException;
import com.csw.Service.interfaces.InsertSomeInfoTransactionServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.ParseEbRIMAndSaveInfo;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.GetRealPathUtil;
import com.ebrim.model.rim.RegistryPackageDocument;

public class InsertSomeInfoTransactionService implements
		InsertSomeInfoTransactionServiceInterface {

	public void CreateRegistryPackageFile(RegistryPackageDocument rpdDocument,
			String filepath) throws IOException {
		rpdDocument.save(new File(filepath));
	}

	public int InsertSomeInfoTransactionMethod(String username,
			String password, String id, String keywords, String inputs,
			String outputs, String northenv, String westenv, String eastenv,
			String southenv, String pointx, String pointy,
			String intendedApplication, String processType, String serviceType)
			throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		ParseEbRIMAndSaveInfo pe = new ParseEbRIMAndSaveInfo();
		String basepath = new GetRealPathUtil().getWebInfPath();
		if (!OperateSensorUtil.CheckSensorIdIsExistMethod(id + ":package")) {
			if (!id.contains("urn:ogc:object:feature:Sensor:")
					&& processType.toLowerCase().contains("system")) {
				id = "urn:ogc:object:feature:Sensor:" + id;
			}
			if (!id.contains("urn:ogc:object:feature:Sensor:")
					&& processType.toLowerCase().contains("component")) {
				id = "urn:ogc:object:feature:Sensor:" + id;
			}
			if (!id.contains("urn:ogc:def:identifier:OGC::")
					&& processType.toLowerCase().contains("processmodel")) {
				id = "urn:ogc:def:identifier:OGC::" + id;
			}
			if (!id.contains("urn:ogc:def:identifier:OGC::")
					&& processType.toLowerCase().contains("processchain")) {
				id = "urn:ogc:def:identifier:OGC::" + id;
			}
			RegistryPackageDocument rpd = GetRegistryRegistryInfoUtils
					.CreateSimpleRegistryPackage(id, keywords, outputs, inputs,
							northenv, westenv, southenv, eastenv, pointx,
							pointy);
			String filepath = FileOperationUtil.CreateFilePathOperation(
					basepath, "simplegetreocrds");
			FileOperationUtil.WriteToFileContent(rpd.xmlText(), filepath,
					"UTF-8");
			pe.ParseXMLDocument(filepath, username);
			FileOperationUtil.DeleteFile(filepath);
			GetRegistryRegistryInfoUtils.SaveProcessBasicInfo(id,
					intendedApplication, processType, serviceType);
		}
		return 1;
	}
}
