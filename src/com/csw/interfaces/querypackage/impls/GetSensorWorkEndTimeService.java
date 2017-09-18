package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorWorkEndTimeInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public class GetSensorWorkEndTimeService implements
		GetSensorWorkEndTimeInterface {

	public String GetSensorWorkValidTimeEndMethod(String username,
			String password, String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorWorkEndTime(sensorid);
	}

	public List<SensorBasicInfoType> GetSensorWorkValidTimeEndForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setSenosrendtime(OperateSensorUtil
						.GetSensorWorkEndTime(sensorid));
				sbits.add(sbit);
			}
		}
		return sbits;
	}
}
