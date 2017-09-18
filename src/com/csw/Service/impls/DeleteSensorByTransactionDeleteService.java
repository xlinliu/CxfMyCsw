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
 * ɾ�������� �� webservice�ṩ
 * 
 * @author Administrator
 * 
 */
public class DeleteSensorByTransactionDeleteService implements
		DeleteSensorByTransactionDeleteServiceInterface {

	public int DeleteSensorByTransactionDeleteMethod(String username,
			String password, String transactioncontent) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil
				.checkStringIsNotNULLAndEmptyMethod(transactioncontent)) {
			throw new ServiceException("����transactioncontent���ݲ���ȷ");
		}
		try {
			String rpid = "";
			// �ڶ�������������Ҫɾ���ĸ���process��id
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
			throw new ServiceException("����Transaction-delete��ʽɾ���ĳ������⣬ԭ�����£�["
					+ e.getLocalizedMessage() + "]");
		}
	}
}
