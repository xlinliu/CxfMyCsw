package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorWorkBeginTimeInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public class GetSensorWorkBeginTimeService implements
		GetSensorWorkBeginTimeInterface {
	/**
	 * ≤‚ ‘
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GetSensorWorkBeginTimeService gswbts = new GetSensorWorkBeginTimeService();
		List<String> sensorids = new ArrayList<String>();
		sensorids.add("urn:ogc:feature:insitesensor:Node002-airTempHum");
		sensorids.add("urn:ogc:feature:insitesensor:CarAXB499-GPS");
		sensorids.add("urn:ogc:feature:insitusensor:platform:Node001");
		sensorids.add("urn:ogc:feature:insitesensor:CarHUBEI-AXA210-GPS");
		try {
			List<SensorBasicInfoType> sbits = gswbts
					.GetSensorWorkValidTimeBeginForMultiSensorsMethod("admin",
							"cswadmin", sensorids);
			for (SensorBasicInfoType sbit : sbits) {
				System.out.println(sbit.getSensorbegintime());
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

	public String GetSensorWorkValidTimeBeginMethod(String username,
			String password, String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorWorkBeginTime(sensorid);
	}

	public List<SensorBasicInfoType> GetSensorWorkValidTimeBeginForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setSensorbegintime(OperateSensorUtil
						.GetSensorWorkBeginTime(sensorid));
				sbits.add(sbit);
			}
		}
		return sbits;
	}
}
