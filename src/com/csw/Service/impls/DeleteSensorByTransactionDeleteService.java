package com.csw.Service.impls;

import com.csw.Service.interfaces.DeleteSensorByTransactionDeleteServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 * 删除传感器 由 webservice提供
 * 
 * @author Administrator
 * 
 */
public class DeleteSensorByTransactionDeleteService implements
		DeleteSensorByTransactionDeleteServiceInterface {

	public int DeleteSensorByTransactionDeleteMethod(String username,
			String password, String transactioncontent) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil
				.checkStringIsNotNULLAndEmptyMethod(transactioncontent)) {
			throw new ServiceException("参数transactioncontent内容不正确");
		}
		try {
			String rpid = "";
			// 第二步，解析出需要删除的各个process的id
			for (String rpcontent : TransactionOperation
					.ParseTransactionDeleteContent(transactioncontent)) {
				rpid = GetRegistryRegistryInfoUtils
						.GetRegistryPackageIDByEbrimContent(rpcontent);
				if (OperateSensorUtil
						.CheckSensorIdIsBelongOwner(username, rpid)) {
					TransactionOperation.DeleteRegistryPackageById(rpid);
					OperateSensornewUtil.deleteSensorBasicInfo(StringUtil
							.DeletePackageStr(rpid));
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("进行Transaction-delete方式删除的出现问题，原因如下：["
					+ e.getLocalizedMessage() + "]");
		}
	}
}
