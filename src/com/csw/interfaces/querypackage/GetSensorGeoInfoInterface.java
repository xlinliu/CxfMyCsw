package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorGeoInfoInterface {
	/**
	 * 获取传感器的地理空间信息的方法（空间范围）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户查询的传感器的标识符
	 * @return 返回传感器的地理空间范围的信息,格式为："坐标系统,西北角点经纬度,东南角点经纬度"
	 * @throws ServiceException
	 *             返回服务器端的错误的信息
	 */
	@WebMethod
	public String GetSensorGeoInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取传感器的地理空间信息的方法（空间范围）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户查询的传感器的标识符
	 * @return 返回传感器的地理空间范围的信息,格式为："坐标系统,西北角点经纬度,东南角点经纬度"
	 * @throws ServiceException
	 *             返回服务器端的错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorGetoInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
