package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.GetAllPlatfromTypesInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.PlatformandSensors;

/**
 *项目名称:CxfMyCsw 类描述：获取所有的平台的类型 创建人:Administrator 创建时间: 2013-8-23 下午02:17:34
 */
public class GetAllPlatfromTypesService implements GetAllPlatfromTypesInterface {
	public static void main(String[] args) throws ServiceException {
		GetAllPlatfromTypesInterface gpt = new GetAllPlatfromTypesService();
		for (String s : gpt.getAllPlatfromType("admin", "cswadmin")) {
			System.out.println(s);
		}
	}

	public List<String> getAllPlatfromType(String username, String password)
			throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		List<PlatformandSensors> plants = OperateSensorUtil
				.GetPlatFormandSensorsMethod(username, password, "all");
		for (PlatformandSensors ps : plants) {
			if (!results.contains(ps.getPlatform().getSensortype())) {
				results.add(ps.getPlatform().getSensortype());
			}
		}
		return results;
	}
}
