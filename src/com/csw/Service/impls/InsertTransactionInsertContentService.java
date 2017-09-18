package com.csw.Service.impls;

import com.csw.Service.interfaces.InsertTransactionInsertContentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.StringUtil;

public class InsertTransactionInsertContentService implements
		InsertTransactionInsertContentServiceInterface {
	public int InsertTransactionInsertCotentMethod(String username,
			String password, String content, String processType,
			String SerivceType, String intendedApplication)
			throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		// ��֤�����Ƿ����
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod("content")) {
			throw new ServiceException("����content����������ʵ!");
		}
		String basepath = new GetRealPathUtil().getWebInfPath();
		String filepath = FileOperationUtil.CreateFilePathOperation(basepath,
				"transactioninsert");
		FileOperationUtil.WriteToFileContent(content, filepath, "UTF-8");
		String processId = TransactionOperation.InsertTransaction(filepath,
				username);
		FileOperationUtil.DeleteFile(filepath);
		GetRegistryRegistryInfoUtils.SaveProcessBasicInfo(processId,
				processType, SerivceType, intendedApplication);
		return 1;
	}
}
