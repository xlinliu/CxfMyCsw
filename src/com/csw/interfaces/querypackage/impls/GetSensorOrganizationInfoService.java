package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorOrganizationInfoInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorOrganizationInfoService implements
		GetSensorOrganizationInfoInterface {
	public static void main(String[] args) {
		GetSensorOrganizationInfoService gsois = new GetSensorOrganizationInfoService();
		List<String> sensorids = new ArrayList<String>();
		sensorids.add("urn:ogc:feature:insitesensor:Node002-airTempHum");
		sensorids.add("urn:ogc:feature:insitesensor:CarAXB499-GPS");
		sensorids.add("urn:ogc:feature:insitesensor:Node002-rainfall");
		sensorids.add("urn:ogc:feature:insitusensor:platform:CarAXB499");
		try {
			List<SensorBasicInfoType> sbits = gsois
					.GetSensorOrganizationInfoForMultiSensorsMethod("admin",
							"cswadmin", sensorids);
			for (SensorBasicInfoType sbit : sbits) {
				System.out.println(sbit.getSensororgan());
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public String GetSensorOrganizationInfoMethod(String username,
			String password, String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorOrganInfo(sensorid);
	}

	public List<SensorBasicInfoType> GetSensorOrganizationInfoForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setSensororgan(OperateSensorUtil
						.GetSensorOrganInfo(sensorid));
				sbits.add(sbit);
			}
		}
		return sbits;
	}
}
