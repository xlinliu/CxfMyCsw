package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;

@WebService
public interface SOSInterfaces {
	/**
	 * 获取sos中某一传感器的最新的观测时间
	 * 
	 * @param sensorid
	 *            传感器的id
	 * @param obervationPro
	 *            观测属性
	 * @param offingId
	 *            标识符
	 * @param sosadd
	 *            sos的服务端地址
	 * @return 返回最新的观测的时间，如果没有则返回当前的时间
	 */
	@WebMethod
	public String GetLastObservation(
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "obervationPro") String obervationPro,
			@WebParam(name = "offingId") String offingId,
			@WebParam(name = "sosadd") String sosadd) throws ServiceException;
}
