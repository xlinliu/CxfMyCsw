package com.csw.Service.impls;

import com.csw.Service.interfaces.TransformSensorMLToebRIMUpdateServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;

public class TransformSensorMLToebRIMUpdateService implements
		TransformSensorMLToebRIMUpdateServiceInterface {

	public String TransformSnesorMLToebRIMUpdateMethod(String username,
			String password, String sensorml) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorml) == null) {
			throw new ServiceException("参数sensorml不能为空!");
		}
		String basepath = new GetRealPathUtil().getWebInfPath();
		String ebrimfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "ebrim");
		String sensormlfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "sensorml");
		String xslfilepath = basepath + "transformxsls\\SensorMLToEbRIM.xsl";
		String processmodelxsltpath = basepath + "transformxsls\\Process.xsl";
		FileOperationUtil.WriteToFileContent(sensorml, sensormlfilepath,
				"UTF-8");
		String ebrim = TransactionOperation.ParseAndSaveAllTypesProcessMethod(
				sensorml, sensormlfilepath, processmodelxsltpath, xslfilepath,
				ebrimfilepath, null, null, null, false, username);
		if (ebrim.equals("")) {
			throw new ServiceException("参数sensorml内容错误，请输入规范文档内容");
		}
		FileOperationUtil.DeleteFile(sensormlfilepath);
		FileOperationUtil.DeleteFile(ebrimfilepath);
		return ebrim;
	}
}
