package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorLongNameInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorLongNameService implements GetSensorLongNameInterface {
	/**
	 * 测试成功
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		GetSensorLongNameService gsls = new GetSensorLongNameService();
		String sensorlongname = gsls.GetSensorLongNameMethod("admin",
				"cswadmin", "urn:ogc:feature:remotesensor:AVHRR3_NOAA-19");
		System.out.println(sensorlongname);
	}

	public String GetSensorLongNameMethod(String username, String password,
			String sensorid) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名或密码!");
		}
		// 检验用户名与 密码在数据库中是否真正存在
		int result = UserInfoUtil.CheckUserInfo(username, password);
		if (result == 1) {
			return OperateSensorUtil.GetSensorLongName(sensorid);
		} else {
			throw new ServiceException("用户名与密码错误!");
		}
	}

	public List<SensorBasicInfoType> GetSensorLongNamesMethod(String username,
			String password, List<String> sensorids) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setSensorlongname(OperateSensorUtil
						.GetSensorLongName(sensorid));
				sbits.add(sbit);
			}
		}
		return sbits;
	}
}
