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
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid) == null) {
			throw new ServiceException("����sensorid����Ϊ��!");
		}
		// �ڶ�������ѯ�ø��µ��Ƿ��Ǹ��û�����
		maps = GetRegistryRegistryInfoUtils
				.GetRegistryPacakgeBasicInfos(sensorid);
		return maps;
	}
}
