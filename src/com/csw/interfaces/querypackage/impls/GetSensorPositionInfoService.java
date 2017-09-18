package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorPositionInfoInterface;
import com.csw.model.ebXMLModel.SensorBasciForOracleType;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public class GetSensorPositionInfoService implements
		GetSensorPositionInfoInterface {
	public static void main(String[] args) {
		GetSensorPositionInfoService gspis = new GetSensorPositionInfoService();
		List<String> sensorids = new ArrayList<String>();
		sensorids
				.add("urn:liesmars:insitusensor:platform:BLXBHY2QVideoStation");
		try {
			List<SensorBasicInfoType> sbits = gspis
					.GetSensorPositionInfoForMultiSensorsMethod("admin",
							"cswadmin", sensorids);
			if (sbits != null) {
				for (SensorBasicInfoType sbit : sbits) {
					System.out.println(sbit.getSensorpos());
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String GetSensorPositionInfoMethod(String username, String password,
			String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorPosInfo(sensorid);
	}

	public List<SensorBasicInfoType> GetSensorPositionInfoForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasicInfoType> results = new ArrayList<SensorBasicInfoType>();
		List<SensorBasciForOracleType> sbits = OperateSensornewUtil
				.getAllBasicInfoOfTable();
		SensorBasicInfoType sbit = null;
		for (SensorBasciForOracleType temp : sbits) {
			sbit = new SensorBasicInfoType();
			if (sensorids.contains(temp.getSENSORID())) {
				sbit.setSensorid(temp.getSENSORID());
				sbit.setSensorpos(temp.getSENSORPOS());
				results.add(sbit);
			}
		}
		return results;
	}
}
