package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-8-2 下午06:23:04
 */
@WebService
public interface GetSensorKeyWordsInterface {
	/**
	 * 获取传感器的关键字
	 * 
	 * @param sensorid
	 *            传感器标识符
	 * @param username
	 *            　注册用户名
	 * @param password
	 *            注册中心密码
	 * @return 返回传感器的序列
	 * 
	 * @throws ServiceException
	 */
	@WebMethod
	public String GetSensorKeyWordsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取序列传感器的关键字
	 * 
	 * @param username
	 * @param password
	 * @param sensorids
	 * @return
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorKeyWordsForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

}
