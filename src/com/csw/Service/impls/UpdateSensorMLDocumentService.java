package com.csw.Service.impls;

import com.csw.Service.interfaces.UpdateSensorMLDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class UpdateSensorMLDocumentService implements
		UpdateSensorMLDocumentServiceInterface {

	public int UpdateSensorMLDocumentMethod(String username, String password,
			String sensormlcontent, String filename, String createtime)
			throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(sensormlcontent) == null) {
			throw new ServiceException("������淶��sensorml�ĵ�����");
		}
		// �ڶ�����ȡ���ݹ�����sensorML��id��RegistyPackage��idֵ���������м�����ݿ�������ݿ�
		String ebrimcontent = new TransformSensorMLToebRIMService()
				.TransactionSensorMLToeEbRIMMethod(username, password,
						sensormlcontent);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(ebrimcontent) == null) {
			throw new ServiceException("sensorml�ĵ������ݲ����Ϲ淶�����ʵsnesomrl�ĵ�������!");
		}
		String ebrimid = GetRegistryRegistryInfoUtils
				.GetRegistryPackageIDByEbrimContent(ebrimcontent);
		if (OperateSensorUtil.CheckSensorMLExistMethod(username, ebrimid)) {
			if (OperateSensorUtil.UpdateSensorML(username, password, ebrimid,
					sensormlcontent)) {
				return 1;
			} else {
				throw new ServiceException("sensorml�ĵ�����ʧ�ܣ��������⣬����ϵ�����!");
			}
		} else {
			if (OperateSensorUtil.SaveSensorML(username, password, ebrimid,
					sensormlcontent)) {
				return 1;
			} else {
				return 0;
			}
		}

	}
}
