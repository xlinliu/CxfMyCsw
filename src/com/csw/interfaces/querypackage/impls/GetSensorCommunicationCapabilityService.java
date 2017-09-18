package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorCommunicationCapabilityInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorCommunicationCapabilityService implements
		GetSensorCommunicationCapabilityInterface {

	public String GetSensorCommunicationCapabilityMethod(String username,
			String password, String sensorid, String capability)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetCommunicationPropertyInfo(sensorid,
				capability);
	}

	public List<SensorBasicInfoType> GetSensorCommunicationCapabilityForMultiSensorsMethod(
			String username, String password, List<String> sensorids,
			String capability) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setOthersensorinfo(OperateSensorUtil
						.GetCommunicationPropertyInfo(sensorid, capability));
				sbits.add(sbit);
			}
		}
		return sbits;
	}
}
