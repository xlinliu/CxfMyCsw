package com.csw.Service.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import com.csw.Service.interfaces.QuerySensorsByConditionsInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.TransactionsUtil.QueryAllQueryConditionUtil;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class QuerySensorsByConditionsService implements
		QuerySensorsByConditionsInterface {

	public List<String> QuerySensorsByConditionsMethod(String username,
			String password, Map<String, String> conditions, boolean all,
			boolean allownull) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		// 获取用户全部的传感器
		List<String> tempsensors = GetRegistryRegistryInfoUtils
				.GetRegistryPackageList(username, all);
		// System.out.println("temsensors.length:" + tempsensors.size());
		List<String> sensors = QueryAllQueryConditionUtil
				.QueryAllConditionMethod(conditions, tempsensors, allownull);
		tempsensors.retainAll(sensors);
		return tempsensors;
		// return sensors;
	}

	public static void main(String[] args) throws ServiceException {
		// urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::physicalProperties:property:payload
		// computingCapability:storage
		long pre = System.currentTimeMillis();
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("sensorBasicValidTimeBegin", "2013-09-01T13:35:00Z");
		conditions.put(" sensorBasicValidTimeEnd", "2013-08-04T13:36:00Z");
		conditions.put("sensorBasicObservedRange", "32,113,29,116");
		QuerySensorsByConditionsService qsbcs = new QuerySensorsByConditionsService();
		List<String> lis = qsbcs.QuerySensorsByConditionsMethod("admin",
				"cswadmin", conditions, true, true);
		System.out.println("============================");
		if (lis != null) {
			System.out.println("共" + lis.size() + "项");
			for (String str : lis) {
				System.out.println(str);
			}
		} else {
			System.out.println("无匹配项!");
		}
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "毫秒");
	}
}
