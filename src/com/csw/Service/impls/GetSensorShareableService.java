/**
 * 
 */
package com.csw.Service.impls;

import java.util.List;
import com.csw.Service.interfaces.GetSensorShareableInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorShareLevel;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-15 ����04:50:05
 */
public class GetSensorShareableService implements GetSensorShareableInterface {

	public SensorShareLevel GetSensorShareLevelMethod(String username,
			String password, String sensorid) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorShareLevel(sensorid);
	}

	public static void main(String[] args) throws Exception {
		long pre = System.currentTimeMillis();
		GetSensorShareableService gsss = new GetSensorShareableService();
		List<String> str = OperateSensornewUtil.GetAllPlatformIds();
		for (SensorShareLevel ssl : gsss.GetSensorShareLevelsMethod("admin",
				"cswadmin", str)) {
			System.out.println(ssl.getSensorid());
			System.out.println(ssl.getSensorowner());
			System.out.println(ssl.getSharelevel());
		}
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "����");
	}

	public List<SensorShareLevel> GetSensorShareLevelsMethod(String username,
			String password, List<String> sensorids) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorShareLevels(sensorids);
	}
}
