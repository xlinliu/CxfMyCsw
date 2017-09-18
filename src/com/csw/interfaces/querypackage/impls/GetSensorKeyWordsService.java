package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorKeyWordsInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-8-2 下午06:30:44
 */
public class GetSensorKeyWordsService implements GetSensorKeyWordsInterface {
	/**
	 * 测试成功
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		GetSensorOrganizationInfoService gsois = new GetSensorOrganizationInfoService();
		System.out.println(gsois.GetSensorOrganizationInfoMethod("admin",
				"cswadmin", "urn:ogc:feature:remotesensor:AVHRR3_NOAA-16"));
		GetSensorKeyWordsService gsk = new GetSensorKeyWordsService();
		List<String> sensorids = new ArrayList<String>();
		sensorids.add("urn:ogc:feature:remotesensor:AVHRR3_NOAA-16");
		sensorids.add("urn:ogc:feature:remotesensor:AVHRR3_NOAA-19");
		sensorids.add("urn:ogc:feature:remotesensor:AVHRR3_NOAA-16");
		sensorids.add("urn:ogc:feature:insitesensor:CarAXB499-GPS");
		try {
			List<SensorBasicInfoType> sbits = gsk
					.GetSensorKeyWordsForMultiSensorsMethod("admin",
							"cswadmin", sensorids);
			if (sbits != null) {
				for (SensorBasicInfoType sbit : sbits) {
					System.out.println(sbit.getSensorkeyword());
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取传感器的关键字
	 */
	public String GetSensorKeyWordsMethod(String username, String password,
			String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorKeyWordInfo(sensorid);
	}

	/**
	 * 获取序列传感器的关键字
	 */
	public List<SensorBasicInfoType> GetSensorKeyWordsForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		List<SensorBasicInfoType> sbiTypes = new ArrayList<SensorBasicInfoType>();
		UserInfoUtil.CheckUserLogin(username, password);
		if (sensorids != null && sensorids.size() != 0) {
			for (String sensorid : sensorids) {
				SensorBasicInfoType sbit = new SensorBasicInfoType();
				sbit.setSensorid(sensorid);
				sbit.setSensorkeyword(OperateSensorUtil
						.GetSensorKeyWordInfo(sensorid));
				sbiTypes.add(sbit);
			}
		}
		return sbiTypes;
	}
}
