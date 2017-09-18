package com.csw.Service.interfaces;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorandBelongPlatform;

/**
 *项目名称:CxfMyCsw 类描述：获取传感器所属的平台 创建人:Administrator 创建时间: 2013-8-23 下午01:26:53
 */
@WebService
public interface GetSensorBelongPlatformInterface {
	/**
	 * 获取传感器的全称
	 * 
	 * @param username
	 *            注册中心名称
	 * @param password
	 *            注册中心密码
	 * @param sensors
	 *            传感器集合
	 * @return 返回序列传感器序列的信息SensorandBelongPlatform集合
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorandBelongPlatform> GetSensorsBelongPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<String> sensors)
			throws ServiceException;

}
