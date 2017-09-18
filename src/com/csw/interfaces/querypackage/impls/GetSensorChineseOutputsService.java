/**
 * 
 */
package com.csw.interfaces.querypackage.impls;

import java.util.List;

import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorChineseOutputsInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorOutputType;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-23 ����09:23:03
 */
public class GetSensorChineseOutputsService implements
		GetSensorChineseOutputsInterface {

	public SensorOutputType GetSensorChineseOutputInfo(String username,
			String password, String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserInfo(username, password);
		return OperateSensorUtil.GetSensorChineseOutputInfo(sensorid);
	}

	public List<SensorOutputType> GetSensorChineseOutputInfoList(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		UserInfoUtil.CheckUserInfo(username, password);
		return OperateSensorUtil.getSensorChineseOutputInfoList(sensorids);
	}
}
