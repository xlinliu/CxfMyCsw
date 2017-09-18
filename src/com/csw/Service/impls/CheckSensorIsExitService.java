package com.csw.Service.impls;

import javax.jws.WebService;
import com.csw.Service.interfaces.CheckSensorIsExitInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class CheckSensorIsExitService implements CheckSensorIsExitInterface {

	public boolean CheckSensorIsExitMehtod(String username, String password,
			String sensorid) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.CheckSensorIdIsExistMethod(sensorid);
	}
}
