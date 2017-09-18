package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorPhysicalPropertyInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorPhysicalPropertyService implements
		GetSensorPhysicalPropertyInterface {

	public String GetSensorPhysicalPropertyMethod(String username,
			String password, String sensorid, String physicalproperty)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorPhysicalPropertyMethod(sensorid,
				physicalproperty);
	}

	public List<SensorBasicInfoType> GetSensorPhysicalPropertyForMultiSensorsMethod(
			String username, String password, List<String> sensorids,
			String physicalproperty) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setOthersensorinfo(OperateSensorUtil
						.GetSensorPhysicalPropertyMethod(sensorid,
								physicalproperty));
				sbits.add(sbit);
			}
		}
		return sbits;
	}

}
