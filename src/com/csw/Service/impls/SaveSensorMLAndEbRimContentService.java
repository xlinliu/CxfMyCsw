package com.csw.Service.impls;

import com.csw.Service.interfaces.SaveSensorMLAndEbRimContentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.SaveSensorMLAndEbRIMUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class SaveSensorMLAndEbRimContentService implements
		SaveSensorMLAndEbRimContentServiceInterface {

	public int SaveSensorMLAndEbRimContentMethod(String username,
			String password, String sensorml, String filename, String createtime)
			throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);

		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorml)) {
			throw new ServiceException("����sensorml����������ʵ!");
		}
		// ��һ������sensorml����Ӧ��ebRIM���ĵ�����
		String ebrimcontent = new TransformSensorMLToebRIMService()
				.TransactionSensorMLToeEbRIMMethod(username, password, sensorml);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(ebrimcontent)) {
			throw new ServiceException("sensorml�ĵ����ݸ�ʽ����ȷ�����ʵsensorml�ĵ�������!");
		}
		// �ڶ����������ɵ�ebRIM���ĵ����ݻ�ȡ��ص�ebRIM��idֵ
		String ebrimid = GetRegistryRegistryInfoUtils
				.GetRegistryPackageIDByEbrimContent(ebrimcontent);
		// ��ȡ��sensorml�Ƿ��Ѿ����洢
		// �ж��Ƿ��Ѿ����ڸ� id��sensorml���ĵ�
		if (!OperateSensorUtil.CheckSensorMLExistMethod(ebrimid).getIsExist()) {
			SaveSensorMLAndEbRIMUtil.SaveDBmyebrimsmlcontentsMethod(username,
					password, sensorml, ebrimcontent, ebrimid, filename,
					createtime, true);
			return 1;
		} else {
			throw new ServiceException("�ϴ����ĵ��Ѿ����ڣ���ȷ���Ƿ���Ҫ�Լ�����!");
		}
	}
}
