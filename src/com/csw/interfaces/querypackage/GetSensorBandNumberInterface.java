package com.csw.interfaces.querypackage;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetSensorBandNumberInterface {
	/**
	 * ��ȡ�������Ĳ���������Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���Ҫ��ѯ��ȡ�Ĳ���������Ϣ�Ĵ������ı�ʶ��
	 * @return ���ش������Ĳ���������Ϣ
	 * @throws ServiceException
	 *             ���ط������˵Ĵ�����Ϣ
	 */
	@WebMethod
	public String GetSensorBandNumberMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
