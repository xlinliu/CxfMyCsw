package com.csw.Service.impls;

import java.util.List;
import com.csw.Service.interfaces.GetAllSensorTypesInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *项目名称:CxfMyCsw 类描述：获取传感器中所有的类型集合 创建人:Administrator 创建时间: 2013-8-23 下午02:24:53
 */
public class GetAllSensorTypesService implements GetAllSensorTypesInterface {
	/**
	 * 测试成功!
	 * 
	 * @param args
	 * @throws ServiceException
	 */
	public static void main(String[] args) throws ServiceException {
		GetAllSensorTypesInterface gsti = new GetAllSensorTypesService();
		for (String s : gsti.getAllSensorTypes("admin", "cswadmin")) {
			System.out.println(s);
		}
	}

	public List<String> getAllSensorTypes(String username, String password)
			throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetAllSensorTypes();
	}

}
