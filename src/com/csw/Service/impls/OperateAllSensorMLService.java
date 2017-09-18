package com.csw.Service.impls;

import java.util.List;
import com.csw.Service.interfaces.OperateAllSensorMLServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class OperateAllSensorMLService implements
		OperateAllSensorMLServiceInterface {
	public int OperateAllSensorMLMethod(String username, String password,
			List<String> ids, List<String> sensormlContents,
			List<String> filenames, List<String> stamps, String operation)
			throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		if (ids == null || ids.size() == 0) {
			throw new ServiceException("����ids����������ʵ!");
		}
		if (sensormlContents == null || sensormlContents.size() == 0) {
			throw new ServiceException("����sensormlContents����������ʵ!");
		}
		if (filenames == null || filenames.size() == 0) {
			throw new ServiceException("����filenames����������ʵ!");
		}
		if (stamps == null || stamps.size() == 0) {
			throw new ServiceException("����stamps����������ʵ!");
		}
		if (sensormlContents.size() == filenames.size()
				&& sensormlContents.size() == stamps.size()) {
			throw new ServiceException(
					"������[sensormlContents,filenames,sensormlContents]����������ʵ !");
		}
		if (operation == null || operation.trim().equals("")
				|| !operation.trim().toLowerCase().equals("insert")
				|| !operation.trim().toLowerCase().equals("update")
				|| !operation.trim().toLowerCase().equals("delete")) {
			throw new ServiceException("����operation����������ʵ!");
		}
		if (ids != null
				&& sensormlContents != null
				&& filenames != null
				&& stamps != null
				&& (ids.size() == sensormlContents.size()
						&& ids.size() == filenames.size() && ids.size() == stamps
						.size())) {
			// �ֱ����д��ݹ�����sensorML���д���
			for (int i = 0; i < ids.size(); i++) {
				if (new OperateSensorUtil().OperateSensorMLAndEbRim(
						username, password, ids.get(i),
						sensormlContents.get(i), filenames.get(i), stamps
								.get(i), operation) != 1) {
					// ֻҪ������һ��������Ҫ�����˳����K��ɾ���Ѿ����ڵ�id
					int k = i;
					if (k != 0) {
						for (int j = 0; j < k; j++) {
							new OperateSensorUtil()
									.OperateSensorMLAndEbRim(username,
											password, ids.get(i),
											sensormlContents.get(i), filenames
													.get(i), stamps.get(i),
											"delete");
						}
					}
					throw new ServiceException(
							"�Դ��ݹ�����sensorml����ĳ�ֲ���ʱ���������⣬����ȷ���ͻ�����������ϵ�ͻ���");
				}
			}
			return 1;
		}
		return 1;
	}

	public int OperateAllSensorMLOneByOneMethod(String username,
			String password, List<String> ids, List<String> sensormlContents,
			List<String> filenames, List<String> stamps, List<String> operations)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (ids == null || ids.size() == 0) {
			throw new ServiceException("����ids����������ʵ!");
		}
		if (sensormlContents == null || sensormlContents.size() == 0) {
			throw new ServiceException("����sensormlContents����������ʵ!");
		}
		if (filenames == null || filenames.size() == 0) {
			throw new ServiceException("����filenames����������ʵ!");
		}
		if (stamps == null || stamps.size() == 0) {
			throw new ServiceException("����stamps����������ʵ!");
		}
		if (sensormlContents.size() == filenames.size()
				&& sensormlContents.size() == stamps.size()) {
			throw new ServiceException(
					"������[sensormlContents,filenames,sensormlContents]����������ʵ !");
		}
		if (operations == null || operations.size() == 0) {
			throw new ServiceException("����operation����������ʵ!");
		}
		// �ڶ������ж��û�������Ĳ��������Ƿ���ȷ���ж��Ƿ�Ψ�֣��������е�list�Ĵ�Сһ��
		if (ids != null
				&& sensormlContents != null
				&& filenames != null
				&& stamps != null
				&& operations != null
				&& (ids.size() == sensormlContents.size()
						&& ids.size() == filenames.size()
						&& ids.size() == stamps.size() && ids.size() == operations
						.size())) {
			// �ֱ����д��ݹ�����sensorML���д���
			for (int i = 0; i < ids.size(); i++) {
				if (new OperateSensorUtil().OperateSensorMLAndEbRim(
						username, password, ids.get(i),
						sensormlContents.get(i), filenames.get(i), stamps
								.get(i), operations.get(i)) != 1) {
					// ֻҪ������һ��������Ҫ�����˳����K��ɾ���Ѿ����ڵ�id
					int k = i;
					if (k != 0) {
						for (int j = 0; j < k; j++) {
							new OperateSensorUtil()
									.OperateSensorMLAndEbRim(username,
											password, ids.get(i),
											sensormlContents.get(i), filenames
													.get(i), stamps.get(i),
											"delete");
						}
					}
					throw new ServiceException("���� ʧ�� [" + ids.get(i) + "]");
				}
			}
			return 1;
		} else {
			throw new ServiceException("����ʧ�ܣ������������ʵ!");
		}
	}
}
