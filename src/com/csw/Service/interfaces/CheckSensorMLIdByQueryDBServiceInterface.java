package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorExistBoolean;

@WebService
public interface CheckSensorMLIdByQueryDBServiceInterface {
	/**
	 * 检测用户传递过来的sensorml的id是否已经被使用
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户传递过来需要检测的sensorml的id值 可以是sensorml格式的，也可以是ebrim格式
	 * @return 返回该sensomrl是否已经存在，存在返回true，不存在返回false
	 * @throws 返回异常信息
	 */
	@WebMethod
	public boolean CheckSensorMLIdByQueryDBMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 检测用户传递过来的sensorml的id集合是否已经被使用
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            用户传递过来的需要监测的sensorml的id序列，可以使sensorml格式的，也可是ebrim格式的
	 * @return 返回该sensorml的标识符是否已经存在
	 * @throws ServiceException
	 */
	public List<SensorExistBoolean> CheckSensorMLIdsByQueryDBMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
