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
 *项目名称:CxfMyCsw 类描述：获取传感器制定类型的全部的传感器 创建人:Administrator 创建时间: 2013-8-20
 * 下午02:56:28
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
		System.out.println(now - pre + "毫秒");
	}

	public List<String> getSameSensorTypeSensorGroup(String username,
			String password, SensorTypes sensortype) throws ServiceException {
		// 核实用户信息
		long pre = System.currentTimeMillis();
		UserInfoUtil.CheckUserLogin(username, password);
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "haomiao");
		List<String> result = new ArrayList<String>();
		// 获取所有传感器的名称
		List<SensorBasciForOracleType> sbfot = OperateSensornewUtil
				.GetAllSensorBasicInfo(false, false);
		if (sbfot.isEmpty()) {
			return null;
		}
		// 获取其中所有的传感器的标识符
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
		// 根据传感器的类型获取对应的平台的类型
		if (sensortype.equals(SensorTypes.InsituSensor)) {
			// 原位的，则是查询原位传感器平台和原位视频传感器平台
			queryPlatformType = "InsituSensorPlatform-Video,InsituSensorPlatform-Weather,InsituSensorPlatform-Composite";
		} else if (sensortype.equals(SensorTypes.InsituSensor_Mobile)) {
			// 查询移动传感器平台
			queryPlatformType = "InsituSensorPlatform-Mobile";
		} else if (sensortype.equals(SensorTypes.RemoteSensorScanner)) {
			// 查询全部的遥感平台
			queryPlatformType = "RemoteSensorPlatform";
		} else if (sensortype.equals(SensorTypes.InsituSensor_Video)) {
			// 查询视频平台
			queryPlatformType = "InsituSensorPlatform-Video";
		}
		// System.out.println(queryPlatformType);
		// 获取满足的全部的传感器的平台
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
