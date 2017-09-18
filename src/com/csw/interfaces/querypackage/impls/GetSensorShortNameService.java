package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorShortNameInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorShortNameService implements GetSensorShortNameInterface {

	public String GetSensorShortNameMethod(String username, String password,
			String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorShortNameMethod(sensorid);
	}

	public List<SensorBasicInfoType> GetSensorShortNameforMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String str : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(str);
				sbit.setSensorshortname(OperateSensorUtil
						.GetSensorShortNameMethod(str));
				sbits.add(sbit);
			}
			return sbits;
		} else {
			return null;
		}
	}
}
