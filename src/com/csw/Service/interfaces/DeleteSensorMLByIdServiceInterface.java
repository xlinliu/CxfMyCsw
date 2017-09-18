package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteSensorMLByIdServiceInterface {
	/**
	 * ɾ��ָ���û�ָ����sensorml��id
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensormid
	 *            �û���sensomrl��idֵ
	 * @param deleteType
	 *            ɾ�������ͣ�ȡֵ��:"deleteall","deletesensorml"����֮һ
	 * @return 1 ɾ���ɹ�
	 * @throws �׳��쳣��Ϣ
	 */
	@WebMethod
	public int DeleteSensorMLByIdMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensormid,
			@WebParam(name = "deleteType") String deleteType)
			throws ServiceException;
}
