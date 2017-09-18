package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorConnectionEmailInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorConnectionEmailService implements
		GetSensorConnectionEmailInterface {

	public String GetSensorConnectionEmailMethod(String username,
			String password, String sensorid) throws ServiceException {

		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名或密码!");
		}
		// 检验用户名与 密码在数据库中是否真正存在
		int result = UserInfoUtil.CheckUserInfo(username, password);
		if (result == 1) {
			return OperateSensorUtil.GetConnectionEmailInfo(sensorid);
		} else {
			throw new ServiceException("用户名与密码错误!");
		}

	}

	/**
	 * 获取指定的传感器系列的通信邮件
	 */
	public List<SensorBasicInfoType> GetSensorConnectionEmailForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名或密码!");
		}
		// 检验用户名与 密码在数据库中是否真正存在
		int result = UserInfoUtil.CheckUserInfo(username, password);
		if (result == 1) {
			List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
			if (sensorids != null && sensorids.size() != 0) {
				for (String sensorid : sensorids) {
					SensorBasicInfoType sbit = new SensorBasicInfoType();
					sbit.setSensorid(sensorid);
					sbit.setOthersensorinfo(OperateSensorUtil
							.GetConnectionEmailInfo(sensorid));
					sbits.add(sbit);
				}
			}
			return sbits;
		} else {
			throw new ServiceException("用户名与密码错误!");
		}
	}

}
