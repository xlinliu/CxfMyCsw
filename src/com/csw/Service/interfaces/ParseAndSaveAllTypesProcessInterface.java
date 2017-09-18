package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface ParseAndSaveAllTypesProcessInterface {
	/**
	 * �������е����͵���Ϣ����
	 * 
	 * @param sensorml
	 * @return
	 */
	@WebMethod
	public boolean ParseAndSaveAllTypesProcessMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorml") String xmlcontent)
			throws ServiceException;
}
