package com.csw.Service.impls;

import com.csw.Service.interfaces.DeleteSimpleSensorServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorMLType;

public class DeleteSimpleSensorService implements
		DeleteSimpleSensorServiceInterface {
	public static void main(String[] args) throws ServiceException {
		DeleteSimpleSensorService dsss = new DeleteSimpleSensorService();
		dsss.DeleteSimpleSensorMethod("admin", "cswadmin",
				"urn:liesmars:insitusensor:BusTAIYUAN-A81757-BDS");
		dsss.DeleteSimpleSensorMethod("admin", "cswadmin",
		"urn:liesmars:insitusensor:platform:BusTAIYUAN-A81757");
		System.out.println("her");
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
}
