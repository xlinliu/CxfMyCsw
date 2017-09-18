package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorIntendedApplicationInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorIntendedApplicationService implements
		GetSensorIntendedApplicationInterface {
	/**
	 * ����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GetSensorIntendedApplicationService gsi = new GetSensorIntendedApplicationService();
		List<String> sensorids = new ArrayList<String>();
		sensorids.add("urn:ogc:feature:insitesensor:CarAXA568-GPS");
		try {
			List<SensorBasicInfoType> sbits = gsi
					.GetSensorIntendedApplicationForMultiSensorsMethod("admin",
							"cswadmin", sensorids);
			for (SensorBasicInfoType sbit : sbits) {
				System.out.println(sbit.getSensorid() + " :  "
						+ sbit.getSensorintendapp());
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��ȡ��������Ԥ��Ӧ��
	 */
	public String GetSensorIntendedApplicationMethod(String username,
			String password, String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorIntendAppInfo(sensorid);
	}

	/**
	 * ��ȡ������������Ԥ��Ӧ�ü���
	 */
	public List<SensorBasicInfoType> GetSensorIntendedApplicationForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				if (sensorid != null && !sensorid.isEmpty()) {
					SensorBasicInfoType sbit = new SensorBasicInfoType();
					sbit.setSensorid(sensorid);
					sbit.setSensorintendapp(OperateSensorUtil
							.GetSensorIntendAppInfo(sensorid));
					sbits.add(sbit);
				}
			}
		}
		return sbits;
	}
}
