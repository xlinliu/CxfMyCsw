/**
 * 
 */
package com.csw.interfaces.querypackage.impls;

import java.util.List;

import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorChineseInputsInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorInputInfoType;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-23 ����09:17:13
 */
public class GetSensorChineseInputsService implements
		GetSensorChineseInputsInterface {

	public SensorInputInfoType GetChineseInputsInfo(String username,
			String password, String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorChineseInputInfo(sensorid);
	}

	public List<SensorInputInfoType> GetChineseInputsInfoList(String username,
			String password, List<String> sensorids) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorChineseInputInfoList(sensorids);
	}

}
