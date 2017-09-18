/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorTypes;

/**
 *项目名称:CxfMyCsw 类描述：获取指定类型的传感器的集合 创建人:Administrator 创建时间: 2013-8-20 下午02:54:02
 */
@WebService
public interface GetSameSensorTypeGroupInterface {
	/**
	 * 获取指定类型的传感器的集合
	 * 
	 * @param sensortype
	 *            需要制定传感器的类型
	 *            原位的则是InsituSensor（只需要输入,包含视频的，气象的，站点的），遥感的则是RemoteSensorScanner
	 *            ，移动的则是InsituSensor-Mobile，视频的则是InsituSensor-Video
	 * @return
	 */
	@WebMethod
	public List<String> getSameSensorTypeSensorGroup(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortype") SensorTypes sensortype)
			throws ServiceException;
}
