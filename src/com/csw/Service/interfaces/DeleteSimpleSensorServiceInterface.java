package com.csw.Service.interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteSimpleSensorServiceInterface {
	/**
	 *ɾ��ָ����RegistryPackage��idֵ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ɾ����SensorId��������sensorML��ʽ�ģ�Ҳ������ebRIM��ʽ��
	 * @return ɾ���ɹ�����1��ɾ��ʧ�ܷ���0 ��ɾ�������쳣����2
	 */
	public int DeleteSimpleSensorMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
