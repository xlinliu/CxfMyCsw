package com.csw.Service.impls;

import java.util.List;
import com.csw.Service.interfaces.GetSensorIsOperableInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorOperable;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-15 ����04:43:18
 */
public class GetSensorIsOperableService implements GetSensorIsOperableInterface {

	public SensorOperable GetSensorOperableMethod(String username,
			String password, String sensorid) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorOperatable(sensorid);
	}

	public static void main(String[] args) throws Exception {
		GetSensorIsOperableService sens = new GetSensorIsOperableService();
		List<String> str = OperateSensornewUtil.GetAllPlatformIds();
		long pre = System.currentTimeMillis();
		List<SensorOperable> sos = sens.GetSensorsOperableListMethod("admin",
				"cswadmin", str);
		for (SensorOperable so : sos) {
			System.out.println(so.getSensorid() + " : " + so.getIsOperable());
		}
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "----����");
	}

	public List<SensorOperable> GetSensorsOperableListMethod(String username,
			String password, List<String> sensorids) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorOperatables(sensorids);
	}
}
