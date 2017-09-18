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
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-15 下午04:50:05
 */
public class GetSensorShareableService implements GetSensorShareableInterface {

	public SensorShareLevel GetSensorShareLevelMethod(String username,
			String password, String sensorid) throws ServiceException {
		// 核实用户信息
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
		System.out.println(now - pre + "毫秒");
	}

	public List<SensorShareLevel> GetSensorShareLevelsMethod(String username,
			String password, List<String> sensorids) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorShareLevels(sensorids);
	}
}
