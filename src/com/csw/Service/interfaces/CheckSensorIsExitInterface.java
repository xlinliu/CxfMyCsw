package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface CheckSensorIsExitInterface {
	/**
	 * 检验是否存在该传感器，存在返回true，不存在返回false
	 * 
	 * @param sensorid
	 * @return
	 */
	@WebMethod
	public boolean CheckSensorIsExitMehtod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
