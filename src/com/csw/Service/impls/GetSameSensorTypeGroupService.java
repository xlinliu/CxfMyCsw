package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.GetSameSensorTypeGroupInterface;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.SensorBasciForOracleType;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.PlatformandSensors;
import com.csw.utils.custometypes.SensorInfo;
import com.csw.utils.custometypes.SensorTypes;

/**
 *��Ŀ����:CxfMyCsw ����������ȡ�������ƶ����͵�ȫ���Ĵ����� ������:Administrator ����ʱ��: 2013-8-20
 * ����02:56:28
 */
public class GetSameSensorTypeGroupService implements
		GetSameSensorTypeGroupInterface {
	public static void main(String[] args) throws ServiceException {
		long pre = System.currentTimeMillis();
		GetSameSensorTypeGroupService gsstgs = new GetSameSensorTypeGroupService();
		List<String> strs = gsstgs.getSameSensorTypeSensorGroup("admin",
				"cswadmin", SensorTypes.InsituSensor);
		System.out.println(strs.size());
		for (String str : strs) {
			System.out.println(str);
		}
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "����");
	}

	public List<String> getSameSensorTypeSensorGroup(String username,
			String password, SensorTypes sensortype) throws ServiceException {
		// ��ʵ�û���Ϣ
		long pre = System.currentTimeMillis();
		UserInfoUtil.CheckUserLogin(username, password);
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "haomiao");
		List<String> result = new ArrayList<String>();
		// ��ȡ���д�����������
		List<SensorBasciForOracleType> sbfot = OperateSensornewUtil
				.GetAllSensorBasicInfo(false, false);
		if (sbfot.isEmpty()) {
			return null;
		}
		// ��ȡ�������еĴ������ı�ʶ��
		List<String> existingSensors = new ArrayList<String>();
		for (SensorBasciForOracleType sb : sbfot) {
			existingSensors.add(sb.getSENSORID());
		}
		List<PlatformandSensors> ps = OperateSensornewUtil
				.GetAllPlatformsandSensorIds("all");
		if (ps.isEmpty()) {
			return null;
		}
		String queryPlatformType = "";
		// ���ݴ����������ͻ�ȡ��Ӧ��ƽ̨������
		if (sensortype.equals(SensorTypes.InsituSensor)) {
			// ԭλ�ģ����ǲ�ѯԭλ������ƽ̨��ԭλ��Ƶ������ƽ̨
			queryPlatformType = "InsituSensorPlatform-Video,InsituSensorPlatform-Weather,InsituSensorPlatform-Composite";
		} else if (sensortype.equals(SensorTypes.InsituSensor_Mobile)) {
			// ��ѯ�ƶ�������ƽ̨
			queryPlatformType = "InsituSensorPlatform-Mobile";
		} else if (sensortype.equals(SensorTypes.RemoteSensorScanner)) {
			// ��ѯȫ����ң��ƽ̨
			queryPlatformType = "RemoteSensorPlatform";
		} else if (sensortype.equals(SensorTypes.InsituSensor_Video)) {
			// ��ѯ��Ƶƽ̨
			queryPlatformType = "InsituSensorPlatform-Video";
		}
		// System.out.println(queryPlatformType);
		// ��ȡ�����ȫ���Ĵ�������ƽ̨
		List<String> tempList = new ArrayList<String>();
		for (PlatformandSensors p : ps) {
			for (SensorBasciForOracleType sb : sbfot) {
				// System.out.println(sb.getSENSORTYPE());
				if (sb.getSENSORID().equals(p.getPlatform().getSensor())
						&& queryPlatformType.contains(sb.getSENSORTYPE())) {
					tempList.add(sb.getSENSORID());
				}
			}
		}
		if (!tempList.isEmpty()) {
			for (String str : tempList) {
				for (PlatformandSensors p : ps) {
					if (p.getPlatform().getSensor().equals(str)) {
						for (SensorInfo si : p.getSensors()) {
							result.add(si.getSensor());
						}
					}
				}
			}
		}
		result.retainAll(existingSensors);
		// for (SensorBasciForOracleType sb : sbfot) {
		// String str = sb.getSENSORID();
		// String senStr = sb.getSENSORTYPE();
		// if (sensortype.equals(SensorTypes.InsituSensor)) {
		// if (senStr.contains("InsituSensor")
		// && !senStr.contains("-Mobile")) {
		// result.add(str);
		// }
		// } else if (sensortype.equals(SensorTypes.InsituSensor_Mobile)) {
		// if (senStr.equals("-Mobile")) {
		// result.add(str);
		// }
		// } else if (sensortype.equals(SensorTypes.RemoteSensorScanner)) {
		// if (senStr.equals("RemoteSensorScanner")) {
		// result.add(str);
		// }
		// } else if (sensortype.equals(SensorTypes.InsituSensor_Video)) {
		// if (senStr.contains("-Video")) {
		// result.add(str);
		// }
		// }
		// }
		return result;
	}
}
