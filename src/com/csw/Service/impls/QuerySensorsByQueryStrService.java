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
 *��Ŀ����:CxfMyCsw ��������δ���Գɹ�! ������:Administrator ����ʱ��: 2013-8-4 ����12:44:28
 */
@WebService
public class QuerySensorsByQueryStrService implements
		QuerySensorsByQueryStrServiceInterface {
	public List<String> GetSensorsIdByQueryStr(String username,
			String password, String conditionStr) throws ServiceException {
		// ��ʵ�û���Ϣ
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
