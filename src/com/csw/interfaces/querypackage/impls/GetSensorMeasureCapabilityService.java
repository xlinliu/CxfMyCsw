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
	 * ��ȡ���������ض�������������Ե�ֵ
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
	 * ��ȡ��������ĳһ����Ĳ�������
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
