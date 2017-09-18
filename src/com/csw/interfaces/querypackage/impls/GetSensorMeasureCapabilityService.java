package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorMeasureCapabilityInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorMeasureCapabilityService implements
		GetSensorMeasureCapabilityInterface {
	/**
	 * 获取传感器的特定测量方面的属性的值
	 * 
	 * @param sensorid
	 * @param capability
	 * @return
	 */
	public String GetSensorMeasureCapabilityMethod(String username,
			String password, String sensorid, String capability)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorMeasurePropertyInfo(sensorid,
				capability);
	}

	/**
	 * 获取传感器的某一方面的测量能力
	 */
	public List<SensorBasicInfoType> GetSensorMeasureCapabilitiesForMultiSensorsMethod(
			String username, String password, List<String> sensorids,
			String capability) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setOthersensorinfo(OperateSensorUtil
						.GetSensorMeasurePropertyInfo(sensorid, capability));
				sbits.add(sbit);
			}
		}
		return sbits;
	}
}
