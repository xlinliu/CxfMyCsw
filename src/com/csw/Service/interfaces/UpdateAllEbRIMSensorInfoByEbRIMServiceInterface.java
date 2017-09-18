package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface UpdateAllEbRIMSensorInfoByEbRIMServiceInterface {
	/**
	 * ͨ��SensorML����sensor�Ĺ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorml
	 *            ��Ҫת����sensorml���ĵ�������
	 * @return �ɹ�����ebrim��ʽ���ĵ����ݣ�ʧ�ܷ���null�������쳣����null
	 */
	@WebMethod
	public String UpdateAllEbRIMSensorInfoByEbRIMMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorml") String sensorml)throws ServiceException;
}
