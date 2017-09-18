/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-29 下午05:08:44
 */
@WebService
public interface GetAllSensorBasicInfoInterface {
	/**
	 * 获取所有传感器的基本信息（包括卫星）但是没有位置信息
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param all
	 *            是否查询其他信息
	 * @return
	 */
	public List<SensorBasicInfoType> getAllSatelliteSensorBasicInfo(
			String username, String password, boolean all)
			throws ServiceException;

	/**
	 * 获取指定用户的全部的传感器
	 * 
	 * @param username
	 *            用户名称
	 * @param password用户密码
	 * @param all
	 *            是否获取所有的传感器的信息（包括别人）
	 * @return
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetAllSensorBasicInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "all") boolean all) throws ServiceException;

	/**
	 * 获取指定用户指定的传感器
	 * 
	 * @param username
	 *            用户名称
	 * @param password用户密码
	 * @param sensorid
	 *            查询的传感器的标识符
	 * @return
	 */
	@WebMethod
	public SensorBasicInfoType GetSingleSensorBasicInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
