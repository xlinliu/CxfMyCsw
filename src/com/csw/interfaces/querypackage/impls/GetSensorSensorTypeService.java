package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorSensorTypeInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 * @author Administrator
 * 
 */
@WebService
public class GetSensorSensorTypeService implements GetSensorSensorTypeInterface {
	public static void main(String[] args) throws Exception {
		GetSensorSensorTypeService gsts = new GetSensorSensorTypeService();
		System.out.println(gsts.GetSensorSensorTypeMethod("admin", "cswadmin",
				"urn:liesmars:remotesensor:AURA-OMI"));
	}

	public String GetSensorSensorTypeMethod(String username, String password,
			String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorSensorTypeMethod(sensorid);
	}

	public List<SensorBasicInfoType> GetSensorSensorTypeForMultiSensorMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setSensortype(OperateSensorUtil
						.GetSensorSensorTypeMethod(sensorid));
				sbits.add(sbit);
			}
			return sbits;
		} else {
			return null;
		}
	}
}
