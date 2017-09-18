package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;

import com.csw.Service.interfaces.CheckSensorMLIdByQueryDBServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorExistBoolean;

public class CheckSensorMLIdByQueryDBService implements
		CheckSensorMLIdByQueryDBServiceInterface {

	public boolean CheckSensorMLIdByQueryDBMethod(String username,
			String password, String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数sensomrl不能为空");
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		// 这里使用的是检查sensorML是不是在整个全局中都是唯一的
		SensorExistBoolean seb = OperateSensorUtil
				.CheckSensorMLExistMethod(sensorid);
		return seb.getIsExist();
	}

	public List<SensorExistBoolean> CheckSensorMLIdsByQueryDBMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (sensorids != null) {
			List<SensorExistBoolean> sebs = new ArrayList<SensorExistBoolean>();
			for (String sensorid : sensorids) {
				sensorid = StringUtil.DeletePackageStr(sensorid);
				sebs.add(OperateSensorUtil.CheckSensorMLExistMethod(sensorid));
			}
			return sebs;
		} else {
			return null;
		}

	}
}
