package com.csw.Service.impls;

import com.csw.Service.interfaces.SaveOnlySensorMLServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.SaveSensorMLAndEbRIMUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class SaveOnlySensorMLService implements
		SaveOnlySensorMLServiceInterface {

	public int SaveOnlySensorMLMethod(String username, String password,
			String sensorml, String sensorid, String filename, String createtime)
			throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("����sensorid����Ϊ�� �����ʵ!");
		}
		// �ж�sensorid�Ƿ�Ϊ��
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorml)) {
			throw new ServiceException("����sensorid����Ϊ�� �����ʵ!");
		}
		// ��һ������sensorml����Ӧ��ebRIM���ĵ�����
		String ebrimcontent = new TransformSensorMLToebRIMService()
				.TransactionSensorMLToeEbRIMMethod(username, password, sensorml);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(ebrimcontent)) {
			throw new ServiceException("����sensorml�е��ĵ����ݲ����Ϲ淶�����ʵ!");
		}
		// �ڶ����������ɵ�ebRIM���ĵ����ݻ�ȡ��ص�ebRIM��idֵ
		String ebrimid = GetRegistryRegistryInfoUtils
				.GetRegistryPackageIDByEbrimContent(ebrimcontent);
		// �ж��Ƿ��Ѿ����ڸ� id��sensorml���ĵ�
		if (!OperateSensorUtil.CheckSensorMLExistMethod(ebrimid).getIsExist()) {
			SaveSensorMLAndEbRIMUtil.SaveDBmyebrimsmlcontentsMethod(username,
					password, sensorml, null, ebrimid, filename, createtime,
					true);
			return 1;
		} else {
			throw new ServiceException("���ĵ��Ѿ�����");
		}
	}
}
