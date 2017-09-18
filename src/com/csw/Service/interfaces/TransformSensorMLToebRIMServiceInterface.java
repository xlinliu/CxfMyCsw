package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface TransformSensorMLToebRIMServiceInterface {
	/**
	 * ��sensormlת��Ϊebrim�Ĺ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorml
	 *            sensorml������
	 * @return �ɹ�����ebrim���ݣ�ʧ�ܷ���null���쳣����null
	 */
	@WebMethod
	public String TransactionSensorMLToeEbRIMMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorml") String sensorml)
			throws ServiceException;
}
