package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorConnectionEmailInterface;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

public class GetSensorConnectionEmailService implements
		GetSensorConnectionEmailInterface {

	public String GetSensorConnectionEmailMethod(String username,
			String password, String sensorid) throws ServiceException {

		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		// �����û����� ���������ݿ����Ƿ���������
		int result = UserInfoUtil.CheckUserInfo(username, password);
		if (result == 1) {
			return OperateSensorUtil.GetConnectionEmailInfo(sensorid);
		} else {
			throw new ServiceException("�û������������!");
		}

	}

	/**
	 * ��ȡָ���Ĵ�����ϵ�е�ͨ���ʼ�
	 */
	public List<SensorBasicInfoType> GetSensorConnectionEmailForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		// �����û����� ���������ݿ����Ƿ���������
		int result = UserInfoUtil.CheckUserInfo(username, password);
		if (result == 1) {
			List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
			if (sensorids != null && sensorids.size() != 0) {
				for (String sensorid : sensorids) {
					SensorBasicInfoType sbit = new SensorBasicInfoType();
					sbit.setSensorid(sensorid);
					sbit.setOthersensorinfo(OperateSensorUtil
							.GetConnectionEmailInfo(sensorid));
					sbits.add(sbit);
				}
			}
			return sbits;
		} else {
			throw new ServiceException("�û������������!");
		}
	}

}
