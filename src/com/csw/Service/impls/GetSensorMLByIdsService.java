package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.getSensorMLByIdsServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-5 ����02:44:19
 */
public class GetSensorMLByIdsService implements
		getSensorMLByIdsServiceInterface {
	// ��ʱ��δ���ԣ�2013-09-05��
	public List<String> getSensorMLsByIdsMethod(String username,
			String password, List<String> sensorids) throws ServiceException {
		// ��һ����֤�û�������ʵ��
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> sensormls = new ArrayList<String>();
		if (sensorids != null && sensorids.size() > 0) {
			for (String sensorid : sensorids) {
				String[] strs = { username, password };
				if (null == StringUtil.checkStrsIsNotNULLAndEmptyMethod(strs)) {
					throw new ServiceException("�������û���������!");
				}
				if (null == StringUtil
						.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
					throw new ServiceException("����sensorid����������ʵ!");
				}
				sensormls
						.add(OperateSensorUtil.GetSensorMLBySensorid(sensorid));
			}
		}
		return sensormls;
	}

}
