package com.csw.Service.impls;

import java.util.List;
import com.csw.Service.interfaces.GetSensorIsOperableInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorOperable;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-15 下午04:43:18
 */
public class GetSensorIsOperableService implements GetSensorIsOperableInterface {

	public SensorOperable GetSensorOperableMethod(String username,
			String password, String sensorid) throws ServiceException {
		// 核实用户信息
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
		System.out.println(now - pre + "----毫秒");
	}

	public List<SensorOperable> GetSensorsOperableListMethod(String username,
			String password, List<String> sensorids) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorOperatables(sensorids);
	}
}
