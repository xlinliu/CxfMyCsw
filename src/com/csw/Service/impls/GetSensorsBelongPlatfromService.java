package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.GetSensorBelongPlatformInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.PlatformandSensors;
import com.csw.utils.custometypes.SensorInfo;
import com.csw.utils.custometypes.SensorandBelongPlatform;

/**
 *项目名称:CxfMyCsw 类描述：获取传感器所属的平台 创建人:Administrator 创建时间: 2013-8-23 下午01:29:37
 */
public class GetSensorsBelongPlatfromService implements
		GetSensorBelongPlatformInterface {
	public static void main(String[] args) throws ServiceException {
		GetSensorBelongPlatformInterface gsbpInterface = new GetSensorsBelongPlatfromService();
		List<String> sensors = new ArrayList<String>();
		sensors.add("urn:ogc:feature:insitesensor:CarHUBEI-AXA210-GPS");
		sensors.add("urn:ogc:feature:insitesensor:Node001-soilTempHum");
		System.out.println(gsbpInterface.GetSensorsBelongPlatform("admin",
				"cswadmin", sensors).get(0).getPlatform());
	}

	/**
	 * 获取传感器所属的平台
	 */
	public List<SensorandBelongPlatform> GetSensorsBelongPlatform(
			String username, String password, List<String> sensors)
			throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorandBelongPlatform> sbps = new ArrayList<SensorandBelongPlatform>();
		List<PlatformandSensors> platformandSensors = OperateSensorUtil
				.GetPlatFormandSensorsMethod(username, password, "all");
		for (PlatformandSensors ps : platformandSensors) {
			for (SensorInfo si : ps.getSensors()) {
				for (String s : sensors) {
					if (si.getSensor().equals(s)) {
						// 如果两者相等
						SensorandBelongPlatform sbp = new SensorandBelongPlatform();
						sbp.setSensorname(si.getSensorname());
						sbp.setSensor(s);
						sbp.setSensortype(si.getSensortype());
						sbp.setPlatform(ps.getPlatform().getSensor());
						sbp.setPlatformtype(ps.getPlatform().getSensortype());
						sbp.setPlatformname(ps.getPlatform().getSensorname());
						sbps.add(sbp);
					}
				}
			}
		}
		return sbps;
	}
}
