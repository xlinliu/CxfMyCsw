package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorKeyWordsInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-8-2 ����06:30:44
 */
public class GetSensorKeyWordsService implements GetSensorKeyWordsInterface {
	/**
	 * ���Գɹ�
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
	 * ��ȡ�������Ĺؼ���
	 */
	public String GetSensorKeyWordsMethod(String username, String password,
			String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorKeyWordInfo(sensorid);
	}

	/**
	 * ��ȡ���д������Ĺؼ���
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
