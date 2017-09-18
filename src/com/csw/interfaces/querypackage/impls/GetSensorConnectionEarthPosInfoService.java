package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorConnectionEarthPosInfoInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public class GetSensorConnectionEarthPosInfoService implements
		GetSensorConnectionEarthPosInfoInterface {

	public String GetSensorConnectionEarthPosInfoMethod(String username,
			String password, String sensorid) throws ServiceException {

		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetConnectionEarthPosInfo(sensorid);
	}

	/**
	 * 获取传感器的组织单位的具体地址的信息的方法
	 */
	public List<SensorBasicInfoType> GetSensorConnectionEarthPosInfoFroMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setOthersensorinfo(OperateSensorUtil
						.GetConnectionEarthPosInfo(sensorid));
				sbits.add(sbit);
			}
		}
		return sbits;
	}
}
