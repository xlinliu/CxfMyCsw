package com.csw.Service.impls;

import java.util.List;

import com.csw.Service.interfaces.GetAllBasicInfoOfSensorMLServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorMLType;

public class GetAllBasicInfoOfSensorMLService implements
		GetAllBasicInfoOfSensorMLServiceInterface {

	public List<SensorMLType> GetAllBasicInfoOfSensorMLMethod(String username,
			String password, boolean type) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		try {
			// �����ȡȫ�����ĵ�����Ϣ������Ҫ�ǹ���Ա�������Ϣ
			if (type) {
				// �ڶ���,�����û��ļ���
				if ((UserInfoUtil.GetLevelOfUser(username, password)) != 1)
					throw new ServiceException("�û�[" + username + "]��Ȩ�޲���");
			}
			// ��ȡ�����û����������ĵ���Ϣ
			// ����������ȡ���л��������û��ϴ����ļ�����Ϣ��id���ϴ�ʱ�䣬�ļ���
			return new OperateSensorUtil()
					.GetAllBasicInfoOfSensorMLMethod(username, password, type);
		} catch (Exception e) {
			throw new ServiceException("��ȡ���е��û��Ļ�����Ϣ�������⣬�������["
					+ e.getLocalizedMessage() + "]");
		}
	}
}
