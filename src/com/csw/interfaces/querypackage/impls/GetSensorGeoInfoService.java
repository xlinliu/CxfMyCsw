package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorGeoInfoInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorGeoInfoService implements GetSensorGeoInfoInterface {
	public static void main(String[] args) {
		GetSensorGeoInfoService gsgi = new GetSensorGeoInfoService();
		List<String> sensorids = new ArrayList<String>();
		sensorids.add("urn:ogc:feature:insitesensor:Node002-airTempHum");
		sensorids.add("urn:ogc:feature:insitesensor:CarAXB499-GPS");
		sensorids.add("urn:ogc:feature:insitesensor:Node002-rainfall");
		sensorids.add("urn:ogc:feature:insitusensor:platform:CarAXB4919");
		try {
			List<SensorBasicInfoType> sbits = gsgi
					.GetSensorGetoInfoForMultiSensorsMethod("admin",
							"cswadmin", sensorids);
			for (SensorBasicInfoType sbit : sbits) {
				System.out.println(sbit.getSensorbbox());
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public String GetSensorGeoInfoMethod(String username, String password,
			String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorGeoInfo(sensorid);
	}

	public List<SensorBasicInfoType> GetSensorGetoInfoForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit
						.setSensorbbox(OperateSensorUtil
								.GetSensorGeoInfo(sensorid));
				sbits.add(sbit);
			}
		}
		return sbits;
	}
}
