/**
 * 
 */
package com.csw.Service.impls;

import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import com.csw.Service.interfaces.QuerySensorsByQueryStrServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.TransactionsUtil.ParseInputQueryStrUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *项目名称:CxfMyCsw 类描述：未测试成功! 创建人:Administrator 创建时间: 2013-8-4 下午12:44:28
 */
@WebService
public class QuerySensorsByQueryStrService implements
		QuerySensorsByQueryStrServiceInterface {
	public List<String> GetSensorsIdByQueryStr(String username,
			String password, String conditionStr) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		Map<String, String> conditions = ParseInputQueryStrUtil
				.ParseInputQueryStrIntoListStrMethod(conditionStr);
		if (conditions == null) {
			return GetRegistryRegistryInfoUtils.GetRegistryPackageList(
					username, true);
		} else {
			return null;
		}
	}

}
