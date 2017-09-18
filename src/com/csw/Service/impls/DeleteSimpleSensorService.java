package com.csw.Service.impls;

import com.csw.Service.interfaces.DeleteSimpleSensorServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorMLType;

public class DeleteSimpleSensorService implements
		DeleteSimpleSensorServiceInterface {
	public static void main(String[] args) throws ServiceException {
		DeleteSimpleSensorService dsss = new DeleteSimpleSensorService();
		dsss.DeleteSimpleSensorMethod("admin", "cswadmin",
				"urn:liesmars:insitusensor:BusTAIYUAN-A81757-BDS");
		dsss.DeleteSimpleSensorMethod("admin", "cswadmin",
		"urn:liesmars:insitusensor:platform:BusTAIYUAN-A81757");
		System.out.println("her");
	}

	public int DeleteSimpleSensorMethod(String username, String password,
			String sensorid) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		// ������֤
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("����sensorid����Ϊ��!");
		}
		SensorMLType smType = OperateSensorUtil.GetSensorMLBasicInfo(sensorid);

		// ��ʵ��RegistryPacakge�Ƿ��Ǹ��û���ӵ��
		if (smType != null) {
			OperateSensorUtil.DeleteSensorML(username, password, sensorid);
			// ����ǳ����û��Ϳ���ֱ��ɾ�����߾��Ǳ���
			if (smType.getOwner().equals(username)
					|| UserInfoUtil.GetLevelOfUser(username, password) == 1) {
				String result = TransactionOperation
						.DeleteRegistryPackageById(sensorid);
				if (result.equals("success")) {
					try {
						OperateSensornewUtil.deleteSensorBasicInfo(sensorid);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return 1;
				} else {
					throw new ServiceException("�û�ɾ����ʧ��");
				}
			} else {
				throw new ServiceException("�û�[" + username + "]��Ȩ���ĵ�["
						+ sensorid + "]���в��������ʵ�����²���!");
			}
		} else {

			String result = TransactionOperation
					.DeleteRegistryPackageById(sensorid);
			if (result.equals("success")) {
				OperateSensornewUtil.deleteSensorBasicInfo(sensorid);
				return 1;
			} else {
				throw new ServiceException("�û�ɾ����ʧ��");
			}
		}
	}
}
