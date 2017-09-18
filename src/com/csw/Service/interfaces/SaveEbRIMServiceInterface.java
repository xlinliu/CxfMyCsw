package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SaveEbRIMServiceInterface {
	/**
	 * ������sensorml��ʽ�洢�ڽ������ļ������ݣ����������е�processType,SERVICEtYPE,
	 * intendedAPplic����Ϣon ����Ϣ
	 * 
	 * @param intendedApplication
	 * @param processType
	 * @param sensorml
	 * @param serviceType
	 * @return int ���� ����ɹ���Ϊ1
	 */
	@WebMethod
	public int SaveEbRIMMethod(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "itnededApplication") String intendedApplication,
			@WebParam(name = "processType") String processType,
			@WebParam(name = "sensorml") String sensorml,
			@WebParam(name = "serviceType") String serviceType)
			throws ServiceException;

}
