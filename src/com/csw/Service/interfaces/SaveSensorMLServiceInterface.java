/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;

/**
 *项目名称:CxfMyCsw 类描述：保存数据库中的sensorml的文档的内容 创建人:Administrator 创建时间: 2013-7-26
 * 下午09:55:19
 */
@WebService(name = "SaveSensorMLServiceInterface")
public interface SaveSensorMLServiceInterface {
	/**
	 * 保存用户提供的sensorml
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            传感器的id值（是ebrim格式的传感器的id
	 * 
	 * @param sensormlcontent
	 *            用户提交的sensorml的内容
	 * @return
	 */
	@WebMethod(operationName = "SaveSensorML")
	public boolean SaveSensorML(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "sensormlcontent") String sensormlcontent)
			throws ServiceException;

	/**
	 * 需要删除的文档的SensorML的格式
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器id
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "DeleteSensorML")
	public boolean DeleteSensorML(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 读取存储的文档的信息
	 * 
	 * @param username
	 *            用户名成
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器id
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "ReadSensorContent")
	public String ReadSensorContent(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 判断是对应的sensorml文档是否存在
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            密码
	 * @param sensorid
	 *            传感器的id
	 * @return 返回存在与否，true存在，false不存在
	 */
	@WebMethod(operationName = "IsExistsSensorML")
	public boolean IsExistsSensorML(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取传感器保存时间
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            密码
	 * @param sensorid
	 *            传感器的id
	 * @return 返回存在与否，true存在，false不存在
	 */
	public Date GetSensorMLSaveTime(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
