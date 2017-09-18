package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorInputInfoType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-23 上午08:52:36
 */
@WebService
public interface GetSensorChineseInputsInterface {
	/**
	 * 获取单个传感器的全部中文的输入信息
	 * 
	 * @param username
	 * @param password
	 * @param sensorid
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public SensorInputInfoType GetChineseInputsInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取序列创安琪的全部的中文输入信息集合
	 * 
	 * @param username
	 * @param password
	 * @param sensorids
	 * @return
	 * @throws ServiceException
	 */
	public List<SensorInputInfoType> GetChineseInputsInfoList(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException;

}
