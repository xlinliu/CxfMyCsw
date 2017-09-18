package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface UpdateSomeSensorInfoServiceInterface {
	/**
	 * 更新process 的信息
	 * 
	 * @param username
	 * @param password
	 * @param sensorid
	 * @param keywords
	 * @param inputs
	 * @param outputs
	 * @param southenv
	 * @param westenv
	 * @param northenv
	 * @param eastenv
	 * @param positionx
	 * @param positiony
	 * @return 成功返回 1，失败返回0，出现异常返回2；
	 */
	@WebMethod
	public int UpdateSomeSensorInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "keywords") String keywords,
			@WebParam(name = "inputs") String inputs,
			@WebParam(name = "outputs") String outputs,
			@WebParam(name = "southenv") String southenv,
			@WebParam(name = "westenv") String westenv,
			@WebParam(name = "northenv") String northenv,
			@WebParam(name = "eastenv") String eastenv,
			@WebParam(name = "positionx") String positionx,
			@WebParam(name = "positiony") String positiony)
			throws ServiceException;
}
