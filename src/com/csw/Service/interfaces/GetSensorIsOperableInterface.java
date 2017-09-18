/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorOperable;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-15 下午04:36:57
 */
@WebService
public interface GetSensorIsOperableInterface {
	/**
	 * 获取传感器是否可操作方法接口
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
	 * @return 返回相关传感器是否可操作的信息
	 * @throws ServiceException
	 */
	@WebMethod
	public SensorOperable GetSensorOperableMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取传感器是否可操作集合的方法接口
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器标识符集合
	 * @return 返回相关传感器是否可操作的信息 集合
	 * @throws ServiceException
	 */
	public List<SensorOperable> GetSensorsOperableListMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

}
