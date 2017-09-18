package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetOwnerAllSensorMLDocumentServiceInterface {
	/**
	 * ��ȡ���е���UserName����������SensorML���ĵ����ݵļ���,���ص���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return ������������UserName��SensorML���ĵ����ݵļ���
	 */
	@WebMethod
	public List<String> GetOwnerAllSensorMLDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
