package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetSensorMLNeedToUpdateServiceInterface {
	/**
	 * 寻找需要更新的sensorml的id集合
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            用户的需要查看的更新sensorml的id集合
	 * @param stamps
	 *            用户需要查看的更新的sensorml的stamp的集合，需要和sensorml的id参数一一对应
	 * @param resulttype
	 *            用户希望返回的id的类型，如"sensorml","ebrim"这两者之一
	 * @return 返回所有需要更新的sensorML的id值集合 
	 */
	@WebMethod
	public List<String> GetSensorMLNeedToUpdateMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "idtimes") List<String> sensorids,
			@WebParam(name = "sensorstamps") List<String> stamps,
			@WebParam(name = "resulttype") String resulttype)
			throws ServiceException;
	// MapAdapter.class
}
