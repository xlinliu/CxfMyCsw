package com.csw.Service.impls;

import java.util.HashMap;
import java.util.Map;
import com.csw.Service.interfaces.ShowSensorInforServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.Userutils.UserInfoUtil;

public class ShowSensorInfoUpdateService implements
		ShowSensorInforServiceInterface {

	public Map<String, String> ShowSensorInfoForUpdateMethod(String username,
			String password, String sensorid) throws ServiceException {
		Map<String, String> maps = new HashMap<String, String>();
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid) == null) {
			throw new ServiceException("参数sensorid不能为空!");
		}
		// 第二步，查询该更新的是否是该用户名的
		maps = GetRegistryRegistryInfoUtils
				.GetRegistryPacakgeBasicInfos(sensorid);
		return maps;
	}
}
