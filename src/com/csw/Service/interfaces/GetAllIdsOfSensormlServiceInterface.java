package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetAllIdsOfSensormlServiceInterface {
	/**
	 * ��ȡָ�����û���ע���������洢��ȫ����sensorml��idֵ�ļ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param type
	 *            ���ص�idֵ����ʽ����sensorml��ʽ�ģ�����ebrim��ʽ��
	 * @return ��������
	 *         username�����洢��sensorml��idֵ�ļ��ϣ����ﷵ�ص���sensorml��ʽ��idֵ��Ϊ�ջ��߷����쳣����null
	 */
	@WebMethod
	public List<String> GetAllIdsOfSensormlMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "type") String type) throws ServiceException;
}
