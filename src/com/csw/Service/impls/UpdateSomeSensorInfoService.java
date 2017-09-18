package com.csw.Service.impls;

import com.csw.Service.interfaces.UpdateSomeSensorInfoServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;

public class UpdateSomeSensorInfoService implements
		UpdateSomeSensorInfoServiceInterface {
	public int UpdateSomeSensorInfoMethod(String username, String password,
			String sensorid, String keywords, String inputs, String outputs,
			String southenv, String westenv, String northenv, String eastenv,
			String positionx, String positiony) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid) == null) {
			throw new ServiceException("参数sensorid不能为空!");
		}
		TransactionOperation.UpdateSampleProcessInfoMethod(sensorid, keywords,
				inputs, outputs, southenv, westenv, northenv, eastenv,
				positionx, positiony);
		return 1;
	}
}
