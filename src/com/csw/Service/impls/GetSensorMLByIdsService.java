package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.getSensorMLByIdsServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-5 下午02:44:19
 */
public class GetSensorMLByIdsService implements
		getSensorMLByIdsServiceInterface {
	// 暂时还未测试（2013-09-05）
	public List<String> getSensorMLsByIdsMethod(String username,
			String password, List<String> sensorids) throws ServiceException {
		// 第一步验证用户名的真实性
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> sensormls = new ArrayList<String>();
		if (sensorids != null && sensorids.size() > 0) {
			for (String sensorid : sensorids) {
				String[] strs = { username, password };
				if (null == StringUtil.checkStrsIsNotNULLAndEmptyMethod(strs)) {
					throw new ServiceException("请输入用户名与密码!");
				}
				if (null == StringUtil
						.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
					throw new ServiceException("参数sensorid输入错误，请核实!");
				}
				sensormls
						.add(OperateSensorUtil.GetSensorMLBySensorid(sensorid));
			}
		}
		return sensormls;
	}

}
