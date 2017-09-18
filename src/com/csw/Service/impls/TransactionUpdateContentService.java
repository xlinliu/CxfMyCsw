package com.csw.Service.impls;

import com.csw.Service.interfaces.TransactionUpdateContentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;

public class TransactionUpdateContentService implements
		TransactionUpdateContentServiceInterface {

	public int TransactionUpdateContentMethod(String username, String password,
			String transactioncontent) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(transactioncontent) == null) {
			throw new ServiceException("参数transactioncontent不能为空!");
		}
		TransactionOperation.ParseTransactionUpdateXmlDocument(
				transactioncontent, username);
		return 1;
	}
}
