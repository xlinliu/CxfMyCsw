package com.csw.Service.impls;

import com.csw.Service.interfaces.ParseAndSaveAllTypesProcessInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;

public class ParseAndSaveAllTypesProcessService implements
		ParseAndSaveAllTypesProcessInterface {

	public boolean ParseAndSaveAllTypesProcessMethod(String username,
			String password, String xmlcontent) throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);

		String basepath = new GetRealPathUtil().getWebInfPath();
		String ebrimfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "ebrim");
		String sensormlfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "sensorml");
		String xslfilepath = basepath + "transformxsls\\SensorMLToEbRIM.xsl";
		String processmodelxsltpath = basepath + "transformxsls\\Process.xsl";
		String result = TransactionOperation.ParseAndSaveAllTypesProcessMethod(
				xmlcontent, sensormlfilepath, processmodelxsltpath,
				xslfilepath, ebrimfilepath, null, null, null, true, username);
		FileOperationUtil.DeleteFile(sensormlfilepath);
		FileOperationUtil.DeleteFile(ebrimfilepath);
		if (result != null && result.length() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
